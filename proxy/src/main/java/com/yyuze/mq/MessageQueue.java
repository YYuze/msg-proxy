package com.yyuze.mq;

import com.yyuze.entity.DroppedMessage;
import com.yyuze.entity.OverflowedMessage;
import com.yyuze.model.MessageModel;
import com.yyuze.repository.DroppedMessageRepository;
import com.yyuze.repository.OverflowedMessageRepository;
import com.yyuze.repository.RouteRepository;
import com.yyuze.util.EventDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author: yyuze
 * Created: 2018/9/25
 */

@Component
public class MessageQueue {

    private static final int MAX_RESENDING_COUNT = 3;

    private Integer restCapacity = 100;

    private List<MessageModel> msgQueue;

    private DroppedMessageRepository droppedMessageRepository;

    private OverflowedMessageRepository overflowedMessageRepository;

    private RouteRepository routeRepository;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public MessageQueue(DroppedMessageRepository droppedMessageRepository, OverflowedMessageRepository overflowedMessageRepository, RouteRepository routeRepository) {
        this.droppedMessageRepository = droppedMessageRepository;
        this.overflowedMessageRepository = overflowedMessageRepository;
        this.routeRepository = routeRepository;
        this.msgQueue = new ArrayList<>();
    }

    private void drop(MessageModel message) {
        this.msgQueue.remove(message);
        this.restCapacity--;
        DroppedMessage droppedMessage = new DroppedMessage();
        droppedMessage.setRouteToken(message.getRoute().getToken());
        droppedMessage.setData(message.getData());
        droppedMessage.setCreateOn(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(System.currentTimeMillis())));
        this.droppedMessageRepository.save(droppedMessage);
        this.logger.error("dropped: " + message.getData());
    }

    private void confirmDelivered(MessageModel message) {
        this.msgQueue.remove(message);
        this.restCapacity--;
        this.logger.info("delivered: " + message.getData());
    }

    private void putBackToQueue(MessageModel message) {
        this.msgQueue.remove(message);
        message.setTransferedCount(message.getTransferedCount() + 1);
        this.msgQueue.add(message);
    }

    private void loadWaitedMessage() {
        Pageable limit = new PageRequest(0, this.restCapacity, Sort.Direction.DESC, "id");
        synchronized (overflowedMessageRepository) {
            List<OverflowedMessage> msgs = this.overflowedMessageRepository.loadWaitedMessage(limit);
            for (OverflowedMessage msg : msgs) {
                MessageModel message = new MessageModel();
                message.setData(msg.getData());
                message.setRoute(this.routeRepository.getRouteByToken(msg.getRouteToken()));
                message.setTransferedCount(0);
                this.msgQueue.add(message);
                this.overflowedMessageRepository.save(msg);
            }
            this.overflowedMessageRepository.delete(msgs);
        }
    }

    public void pushToMQ(MessageModel message) {
        synchronized (this.restCapacity) {
            boolean isOverflowed = !(this.restCapacity > 0);
            if (isOverflowed) {
                OverflowedMessage msg = new OverflowedMessage();
                msg.setData(message.getData());
                msg.setRouteToken(message.getRoute().getToken());
                msg.setIs_loaded(1);
                synchronized (this.overflowedMessageRepository) {
                    this.overflowedMessageRepository.save(msg);
                }
            }
            else {
                synchronized (this.msgQueue) {
                    this.msgQueue.add(message);
                }
                this.restCapacity++;
            }
        }
    }

    @Scheduled(fixedRate = 5000)
    public void transmitMsg() throws InterruptedException {
        synchronized (this.msgQueue) {
            this.loadWaitedMessage();
            for (int i = 0; i < this.msgQueue.size(); i++) {
                MessageModel message = this.msgQueue.get(i);
                if (message.getTransferedCount() == MessageQueue.MAX_RESENDING_COUNT) {
                    this.drop(message);
                }
                else {
                    EventDispatcher dispatcher = new EventDispatcher(message);
                    dispatcher.run();
                    dispatcher.join();
                    String result = dispatcher.getResult();
                    if (result.equals(message.getRoute().getRequestSuccessFlag())) {
                        this.confirmDelivered(message);
                    }
                    else {
                        this.putBackToQueue(message);
                    }
                }
            }
        }
    }
}

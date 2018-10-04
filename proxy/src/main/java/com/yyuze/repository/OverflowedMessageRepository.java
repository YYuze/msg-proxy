package com.yyuze.repository;

import com.yyuze.entity.OverflowedMessage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author: yyuze
 * Created: 2018/9/30
 */
@Repository
public interface OverflowedMessageRepository extends CrudRepository<OverflowedMessage, Long> {

    @Query("select msg from OverflowedMessage msg where msg.is_loaded=0")
    List<OverflowedMessage> loadWaitedMessage(Pageable limit);
}

package com.yyuze.repository;

import com.yyuze.entity.DroppedMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: yyuze
 * Created: 2018/9/23
 */

@Repository
public interface DroppedMessageRepository extends CrudRepository<DroppedMessage, Long> {

}

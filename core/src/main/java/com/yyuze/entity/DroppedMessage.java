package com.yyuze.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Author: yyuze
 * Created: 2018/9/25
 */

@Getter
@Setter
@Entity
@Table(name = "dropped_message")
public class DroppedMessage {

    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "route_token")
    private String routeToken;

    @Column(name = "transfer_data")
    private String data;

    @Column(name = "create_on")
    private String createOn;


}

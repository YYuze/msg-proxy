package com.yyuze.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Author: yyuze
 * Created: 2018/9/30
 */
@Getter
@Setter
@Entity
@Table(name = "overflowed_message")
public class OverflowedMessage {

    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "route_token")
    private String routeToken;

    @Column(name = "transfer_data")
    private String data;

    @Column(name = "is_loaded")
    private int is_loaded = 0;
}

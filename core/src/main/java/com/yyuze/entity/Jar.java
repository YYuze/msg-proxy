package com.yyuze.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Author: yyuze
 * Created: 2018/8/31
 */

/**
 * 该表以消息type为主键
 * 存放用于处理该type消息的jar包路径和类名
 */

@Getter
@Setter
@Entity
@Table(name = "Jar")
public class Jar {

    @Id
    @Column(name = "type")
    private String type;

    @Column(name = "jar_file_path")
    private String jarFilePath;

    @Column(name = "class_name")
    private String className;

}

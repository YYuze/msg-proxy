package com.yyuze.entity;


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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJarFilePath() {
        return jarFilePath;
    }

    public void setJarFilePath(String jarFilePath) {
        this.jarFilePath = jarFilePath;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}

package com.smart.qna.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Entity
@Table(name="visitors")
public class TextMessage implements Serializable {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name = "uid")
    private String mobileNo;

    @Column(name = "msg")
    private String message;

    @Column(name="status")
    private String status;

    @Column(name="hrname")
    private String name;

    @Column(name="hrbranch")
    private String branch;

    private transient String details;

}

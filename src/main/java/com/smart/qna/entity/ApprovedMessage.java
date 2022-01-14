package com.smart.qna.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Table(name="approved")
public class ApprovedMessage implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="phoneno")
    private String phoneNo;

    @Column(name = "sendername")
    private String senderName;

    @Column(name="msg")
    private String message;

    @Column(name="hrbranch")
    private String hrBranch;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="approveddatetime")
    private Date approvedDateTime;

    @Column(name="priority1")
    private int priority1;

    @Column(name="priority2")
    private int priority2;

    @Column(name="prioratizeddatetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date prioratizedDateTime;

    @Column(name="readstatus")
    private String readStatus;

}

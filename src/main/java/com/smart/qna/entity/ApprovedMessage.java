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
    int id;

    @Column(name="phoneno")
    String phoneNo;

    @Column(name = "sendername")
    String senderName;

    @Column(name="msg")
    String message;

    @Column(name="hrbranch")
    String hrBranch;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="approveddatetime")
    Date approvedDateTime;

    @Column(name="priority_level_1")
    int priorityLevel_1;

    @Column(name="priority_level_2")
    int priorityLevel_2;

    @Column(name="prioratizeddatetime")
    @Temporal(TemporalType.TIMESTAMP)
    Date prioratizedDateTime;

}

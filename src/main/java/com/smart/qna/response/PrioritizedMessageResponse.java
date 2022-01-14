package com.smart.qna.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
@ToString
public class PrioritizedMessageResponse {
    int id;
    String phoneNo;
    String senderName;
    String message;
    String hrBranch;
    int priority1;
    int priority2;

}

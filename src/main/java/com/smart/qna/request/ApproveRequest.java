package com.smart.qna.request;

import com.smart.qna.Enumeration.ApproveStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApproveRequest {

    private int id;
    private ApproveStatus status;
    private int priority;

}

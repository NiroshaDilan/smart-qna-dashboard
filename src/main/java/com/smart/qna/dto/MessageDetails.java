package com.smart.qna.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MessageDetails {

    private String id;
    private String mobileNo;
    private String name;
    private String branch;
    private String message;
}

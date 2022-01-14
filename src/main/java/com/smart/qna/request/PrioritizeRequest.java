package com.smart.qna.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PrioritizeRequest {

    private int id;
    private int priority1;
    private int priority2;

}

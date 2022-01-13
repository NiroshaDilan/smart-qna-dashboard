package com.smart.qna.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MessageListRequest {

    @JsonProperty(value = "page", required = true )
    private int page;

    @JsonProperty(value = "size",required = true)
    private int size;

}

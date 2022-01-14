package com.smart.qna.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class MessageListRequest {

    @JsonProperty(value = "page", required = true )
    private int page;

    @JsonProperty(value = "size",required = true)
    private int size;

}

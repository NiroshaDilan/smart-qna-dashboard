package com.smart.qna.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.smart.qna.dto.MessageDetails;
import com.smart.qna.entity.TextMessage;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@ToString
public class MessageListResponse {

    private List<TextMessage> messageDetailsList;
    private int currentPage;
    private int totalPages;

}

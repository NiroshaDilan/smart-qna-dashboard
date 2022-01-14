package com.smart.qna.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.smart.qna.entity.ApprovedMessage;
import com.smart.qna.entity.TextMessage;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@ToString
public class ApprovedListResponse {

    private List<ApprovedMessage> approvedMessageList;
    private int currentPage;
    private int totalPages;

}



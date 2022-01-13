package com.smart.qna.controller;

import com.smart.qna.entity.TextMessage;
import com.smart.qna.request.MessageListRequest;
import com.smart.qna.response.CommonResponse;
import com.smart.qna.response.MessageListResponse;
import com.smart.qna.service.QuestionnaireHandlerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/smartqna")
public class MainController implements MainControllerInterface{

    private Logger LOGGER;
    private QuestionnaireHandlerService questionnaireHandlerService;

    public MainController (QuestionnaireHandlerService questionnaireHandlerService){
        LOGGER= LoggerFactory.getLogger(this.getClass());
        this.questionnaireHandlerService=questionnaireHandlerService;
    }

    @Override
    public MessageListResponse getMessages(MessageListRequest messageListRequest) {
        LOGGER.info("Inside getMessages");
        MessageListResponse messageListResponse = new MessageListResponse();
    try {
         Page<TextMessage> textMessagePage=questionnaireHandlerService.getSmsMessages(messageListRequest);
         messageListResponse.setCurrentPage(messageListRequest.getPage());
         messageListResponse.setTotalPages(textMessagePage.getTotalPages());
         messageListResponse.setMessageDetailsList(textMessagePage.toList());
    }catch (Exception e){
        LOGGER.error("Error when getting message List",e);
    }

        return messageListResponse;
    }

    @Override
    public CommonResponse getApprovedMessages(MessageListRequest messageListRequest) {
        return null;
    }

    @Override
    public CommonResponse persistApproved(int id) {
        return null;
    }

    @Override
    public CommonResponse persistPriority() {
        return null;
    }
}

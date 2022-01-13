package com.smart.qna.service.impl;

import com.smart.qna.entity.TextMessage;
import com.smart.qna.repository.ReceivedSmsRepository;
import com.smart.qna.request.MessageListRequest;
import com.smart.qna.service.QuestionnaireHandlerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class QuestionHandlerServiceImpl implements QuestionnaireHandlerService {

    private Logger LOGGER;
    private ReceivedSmsRepository receivedSmsRepository;

    @Autowired
    public QuestionHandlerServiceImpl(ReceivedSmsRepository receivedSmsRepository){
        LOGGER=LoggerFactory.getLogger(this.getClass());
        this.receivedSmsRepository=receivedSmsRepository;
    }

    @Override
    public Page<TextMessage> getSmsMessages(MessageListRequest messageListRequest) {
        LOGGER.info("Inside the getSmsMessages method");

        Pageable pageWithElements = PageRequest.of(messageListRequest.getPage(), messageListRequest.getSize(), Sort.by("id"));

        return  receivedSmsRepository.findAllByStatus("S",pageWithElements);

    }
}

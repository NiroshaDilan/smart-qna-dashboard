package com.smart.qna.service;

import com.smart.qna.entity.TextMessage;
import com.smart.qna.request.MessageListRequest;
import org.springframework.data.domain.Page;

public interface QuestionnaireHandlerService {

    Page<TextMessage> getSmsMessages(MessageListRequest messageListRequest) throws Exception;
}

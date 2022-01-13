package com.smart.qna.service.impl;

import com.smart.qna.Enumeration.ApproveStatus;
import com.smart.qna.entity.TextMessage;
import com.smart.qna.exception.AlreadyApprovedException;
import com.smart.qna.exception.AlreadyRejectedException;
import com.smart.qna.repository.ApprovedSmsRepository;
import com.smart.qna.repository.ReceivedSmsRepository;
import com.smart.qna.request.ApproveRequest;
import com.smart.qna.request.MessageListRequest;
import com.smart.qna.service.QuestionnaireHandlerService;
import com.smart.qna.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class QuestionHandlerServiceImpl implements QuestionnaireHandlerService {

    private Logger LOGGER;
    private ReceivedSmsRepository receivedSmsRepository;
    private ApprovedSmsRepository approvedSmsRepository;

    @Autowired
    public QuestionHandlerServiceImpl(ReceivedSmsRepository receivedSmsRepository,
                                      ApprovedSmsRepository approvedSmsRepository) {
        LOGGER = LoggerFactory.getLogger(this.getClass());
        this.receivedSmsRepository = receivedSmsRepository;
        this.approvedSmsRepository = approvedSmsRepository;
    }

    @Override
    public Page<TextMessage> getSmsMessages(MessageListRequest messageListRequest) {
        LOGGER.info("Inside the getSmsMessages method");

        Pageable pageWithElements = PageRequest.of(messageListRequest.getPage(), messageListRequest.getSize(), Sort.by("id"));

        return receivedSmsRepository.findAllByStatus("S", pageWithElements);

    }

    @Override
    public int updateApproved(ApproveRequest approveRequest) throws AlreadyApprovedException,AlreadyRejectedException{
        LOGGER.info("Inside the updateApproved method");
        Optional<TextMessage> textMessageCheckNull = receivedSmsRepository.findById(approveRequest.getId());
        if(textMessageCheckNull.isPresent()) {

            switch (textMessageCheckNull.get().getStatus()){
                case "S":
                    throw new AlreadyApprovedException();
                case "R":
                    throw new AlreadyRejectedException();
                default:
                    break;

            }

        }
            return receivedSmsRepository.updateStatus(approveRequest.getStatus().getValue(), approveRequest.getId());
    }

    @Override
    public int persistApproved(ApproveRequest approveRequest) {
        LOGGER.info("Inside the persistApproved method");
        String currentDateNTime= Util.getDateTimeString(new Date());
        return approvedSmsRepository.insertToApproved(currentDateNTime,approveRequest.getPriority(),
                currentDateNTime,approveRequest.getId());
    }


}

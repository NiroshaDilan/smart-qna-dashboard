package com.smart.qna.controller;

import com.smart.qna.entity.ApprovedMessage;
import com.smart.qna.entity.TextMessage;
import com.smart.qna.exception.AlreadyApprovedException;
import com.smart.qna.exception.AlreadyRejectedException;
import com.smart.qna.request.AnsweredRequest;
import com.smart.qna.request.ApproveRequest;
import com.smart.qna.request.MessageListRequest;
import com.smart.qna.request.PrioritizeRequest;
import com.smart.qna.response.*;
import com.smart.qna.service.QuestionnaireHandlerService;
import com.smart.qna.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/smartqna")
public class MainController implements MainControllerInterface {

    private Logger LOGGER;
    private QuestionnaireHandlerService questionnaireHandlerService;

    public MainController(QuestionnaireHandlerService questionnaireHandlerService) {
        LOGGER = LoggerFactory.getLogger(this.getClass());
        this.questionnaireHandlerService = questionnaireHandlerService;
    }

    @Override
    public MessageListResponse getMessages(MessageListRequest messageListRequest) {
        LOGGER.info("Inside getMessages");
        MessageListResponse messageListResponse = new MessageListResponse();
        try {
            Page<TextMessage> textMessagePage = questionnaireHandlerService.getSmsMessages(messageListRequest);
            messageListResponse.setCurrentPage(messageListRequest.getPage());
            messageListResponse.setTotalPages(textMessagePage.getTotalPages());
            messageListResponse.setTotalElements(textMessagePage.getTotalElements());
            messageListResponse.setMessageDetailsList(textMessagePage.toList());
            LOGGER.info("Inside getMessages : Response Success : Number of Pages ::{}", textMessagePage.getTotalPages());
        } catch (Exception e) {
            LOGGER.error("Error when getting message List", e);
        }
        return messageListResponse;
    }

    @Override
    public ApprovedListResponse getApprovedMessages(MessageListRequest messageListRequest) {
        LOGGER.info("Inside getApprovedMessages");
        ApprovedListResponse approvedListResponse = new ApprovedListResponse();
        try {
            Page<ApprovedMessage> approvedMessages = questionnaireHandlerService.getApprovedMessages(messageListRequest);
            approvedListResponse.setCurrentPage(messageListRequest.getPage());
            approvedListResponse.setTotalPages(approvedMessages.getTotalPages());
            approvedListResponse.setTotalElements(approvedMessages.getTotalElements());
            approvedListResponse.setApprovedMessageList(approvedMessages.toList());
            LOGGER.info("Inside getApprovedMessages : Response Success : Number of Pages ::{}", approvedMessages.getTotalPages());


        } catch (Exception e) {
            LOGGER.error("Error when getting message approved List", e);
        }

        return approvedListResponse;
    }

    @Override
    public CommonResponse persistApproved(ApproveRequest approveRequest) {
        LOGGER.info("Inside persistApproved");
        CommonResponse commonResponse = new CommonResponse();
        try {
            int updatedCount = questionnaireHandlerService.updateApproved(approveRequest);
            if (updatedCount > 0) {
                int insertCount = questionnaireHandlerService.persistApproved(approveRequest);
                if (insertCount > 0) {
                    commonResponse.setResponseCode(Util.CODE_SUCCESS);
                    commonResponse.setResponseStatus(Util.STATUS_SUCCESS);
                    return commonResponse;
                } else {
                    commonResponse.setResponseCode(Util.CODE_FAILED_NO_RECORDS_INSERTED);
                    commonResponse.setErrorDescription(Util.LABEL_FAILED_NO_RECORDS_INSERTED);
                }
            } else {
                commonResponse.setResponseCode(Util.CODE_FAILED_NO_RECORDS_UPDATED);
                commonResponse.setErrorDescription(Util.LABEL_FAILED_NO_RECORDS_UPDATED);
            }

        } catch (AlreadyApprovedException e) {
            LOGGER.error("Error for id {}: ", approveRequest.getId(), e);
            commonResponse.setResponseCode(Util.CODE_FAILED_ALREADY_APPROVED);
            commonResponse.setErrorDescription(e.getMessage());

        } catch (AlreadyRejectedException e) {
            LOGGER.error("Error for id {}: ", approveRequest.getId(), e);
            commonResponse.setResponseCode(Util.CODE_FAILED_ALREADY_REJECTED);
            commonResponse.setErrorDescription(e.getMessage());


        } catch (Exception e) {
            LOGGER.error("Exception occred when persistApproved method called : ", e);
            commonResponse.setResponseCode(Util.CODE_FAILED_UPDATE_EXCEPTION_OCCURRED);
            commonResponse.setErrorDescription(Util.LABEL_FAILED_UPDATE_EXCEPTION_OCCURRED);
        }
        commonResponse.setResponseStatus(Util.STATUS_FAILED);
        return commonResponse;
    }

    @Override
    public CommonResponse persistPriority(@RequestBody PrioritizeRequest prioritizeRequest) {
        LOGGER.info("Inside persistApproved");
        CommonResponse commonResponse = new CommonResponse();
        try {
            int updatedCount = questionnaireHandlerService.persistPrioritized(prioritizeRequest);
            if (updatedCount > 0) {
                commonResponse.setResponseCode(Util.CODE_SUCCESS);
                commonResponse.setResponseStatus(Util.STATUS_SUCCESS);
                return commonResponse;
            }
            commonResponse.setResponseCode(Util.CODE_FAILED_NO_RECORDS_UPDATED);
            commonResponse.setErrorDescription(Util.LABEL_FAILED_NO_RECORDS_UPDATED);
        } catch (Exception e) {
            LOGGER.error("Exception occurred when persistPriority method called : ", e);
            commonResponse.setResponseCode(Util.CODE_FAILED_UPDATE_EXCEPTION_OCCURRED);
            commonResponse.setErrorDescription(Util.LABEL_FAILED_UPDATE_EXCEPTION_OCCURRED);
        }
        commonResponse.setResponseStatus(Util.STATUS_FAILED);
        return commonResponse;
    }

    @Override
    public PrioritizedMessageResponse getPrioritizedMessage() {
        LOGGER.info("Inside getPrioritizedMessage to view");
        PrioritizedMessageResponse prioritizedMessageResponse = new PrioritizedMessageResponse();
        MessageListRequest messageListRequest = new MessageListRequest(0, 1);
        try {
            Page<ApprovedMessage> approvedMessages = questionnaireHandlerService.getApprovedMessages(messageListRequest);
            approvedMessages.get().forEach((n) ->
            {
                prioritizedMessageResponse.setId(n.getId());
                prioritizedMessageResponse.setSenderName(n.getSenderName());
                prioritizedMessageResponse.setHrBranch(n.getHrBranch());
                prioritizedMessageResponse.setMessage(n.getMessage());
                prioritizedMessageResponse.setPhoneNo(n.getPhoneNo());
                prioritizedMessageResponse.setPriority1(n.getPriority1());
                prioritizedMessageResponse.setPriority2(n.getPriority2());
            });

        } catch (Exception e) {
            LOGGER.error("Exception occurred when getPrioritizedMessage method called : ", e);
        }

        return prioritizedMessageResponse;
    }

    @Override
    public CommonResponse persistAnswered(AnsweredRequest answeredRequest) {
        LOGGER.info("Inside persistAnswered");
        CommonResponse commonResponse = new CommonResponse();
        try {
            int updatedCount = questionnaireHandlerService.persistAnswered(answeredRequest);
            if (updatedCount > 0) {
                commonResponse.setResponseCode(Util.CODE_SUCCESS);
                commonResponse.setResponseStatus(Util.STATUS_SUCCESS);
                return commonResponse;
            }
            commonResponse.setResponseCode(Util.CODE_FAILED_NO_RECORDS_UPDATED);
            commonResponse.setErrorDescription(Util.LABEL_FAILED_NO_RECORDS_UPDATED);
        } catch (Exception e) {
            LOGGER.error("Exception occurred when persistAnswered method called : ", e);
            commonResponse.setResponseCode(Util.CODE_FAILED_UPDATE_EXCEPTION_OCCURRED);
            commonResponse.setErrorDescription(Util.LABEL_FAILED_UPDATE_EXCEPTION_OCCURRED);
        }
        commonResponse.setResponseStatus(Util.STATUS_FAILED);
        return commonResponse;
    }
}

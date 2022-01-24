package com.smart.qna.service;

import com.smart.qna.entity.ApprovedMessage;
import com.smart.qna.entity.TextMessage;
import com.smart.qna.exception.AlreadyApprovedException;
import com.smart.qna.exception.AlreadyRejectedException;
import com.smart.qna.request.AnsweredRequest;
import com.smart.qna.request.ApproveRequest;
import com.smart.qna.request.MessageListRequest;
import com.smart.qna.request.PrioritizeRequest;
import org.springframework.data.domain.Page;

public interface QuestionnaireHandlerService {

    Page<TextMessage> getSmsMessages(MessageListRequest messageListRequest);

    Page<ApprovedMessage> getApprovedMessages(MessageListRequest messageListRequest);

    int updateApproved(ApproveRequest approveRequest) throws AlreadyApprovedException, AlreadyRejectedException;

    int persistApproved(ApproveRequest approveRequest);

    long getTotalAnswered();

    long getTotalApproved();

    int persistPrioritized(PrioritizeRequest prioritizeRequest);

    int persistAnswered(AnsweredRequest answeredRequest);
}

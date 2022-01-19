package com.smart.qna.controller;


import com.smart.qna.request.*;
import com.smart.qna.response.*;
import org.springframework.web.bind.annotation.*;

public interface MainControllerInterface {

    @PostMapping("/messages/retrieve")
    MessageListResponse getMessages(@RequestBody MessageListRequest messageListRequest);

    @PostMapping("/approved/retrieve")
    ApprovedListResponse getApprovedMessages(@RequestBody MessageListRequest messageListRequest);

    @PostMapping("/messages/persist/approved")
    CommonResponse persistApproved(@RequestBody ApproveRequest approveRequest);

    @PostMapping("/approved/persist/priority")
    CommonResponse persistPriority(@RequestBody PrioritizeListRequest prioritizeListRequest);

    @GetMapping("/messages/view")
    PrioritizedMessageResponse getPrioritizedMessage();

    @PostMapping("/messages/answered")
    CommonResponse persistAnswered(@RequestBody AnsweredRequest answeredRequest);
}

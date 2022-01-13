package com.smart.qna.controller;


import com.smart.qna.request.MessageListRequest;
import com.smart.qna.response.CommonResponse;
import com.smart.qna.response.MessageListResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface MainControllerInterface {

    @PostMapping("/messages/retrieve")
    MessageListResponse getMessages(@RequestBody MessageListRequest messageListRequest);

    @PostMapping("/approved/retrieve")
    CommonResponse getApprovedMessages(@RequestBody MessageListRequest messageListRequest);

    @GetMapping("/messages/approve")
    CommonResponse persistApproved(@RequestParam int id);

    @PostMapping("/approved/priority/add")
    CommonResponse persistPriority();
}

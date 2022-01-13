package com.smart.qna.exception;

public class AlreadyRejectedException extends Exception{

    @Override
    public String getMessage() {
        return "The Message Already Rejected";
    }
}

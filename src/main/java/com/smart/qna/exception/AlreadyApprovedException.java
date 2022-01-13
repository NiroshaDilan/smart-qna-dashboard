package com.smart.qna.exception;

public class AlreadyApprovedException extends Exception{

    @Override
    public String getMessage() {
        return "The Message Already Approved";
    }
}

package com.smart.qna.Enumeration;

public enum ApproveStatus {

    APPROVED ("S"),
    REJECTED ("R");

    String value;

    ApproveStatus(String value){ this.value=value;}

    public String getValue() {
        return value;
    }
}

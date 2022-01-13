package com.smart.qna.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    //Response Codes
    public static String CODE_SUCCESS="00";
    public static String CODE_FAILED_NO_RECORDS_UPDATED="01";
    public static String CODE_FAILED_UPDATE_EXCEPTION_OCCURRED="02";
    public static String CODE_FAILED_NO_RECORDS_INSERTED="03";
    public static String CODE_FAILED_ALREADY_APPROVED="04";
    public static String CODE_FAILED_ALREADY_REJECTED="05";

    //Response Status
    public static String STATUS_SUCCESS="SUCCESSFUL";
    public static String STATUS_FAILED ="FAILED";

    //Error Descriptions
    public static String LABEL_FAILED_NO_RECORDS_UPDATED="NO RECORDS UPDATED FOR THE ID";
    public static String LABEL_FAILED_UPDATE_EXCEPTION_OCCURRED="EXCEPTION OCCURRED WHILE UPDATING";
    public static String LABEL_FAILED_NO_RECORDS_INSERTED="NO RECORDS INSERTED FOR THE ID";


    public static String getDateTimeString(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return  simpleDateFormat.format(date);
    }
}

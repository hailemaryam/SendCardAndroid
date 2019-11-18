package com.example.sendcard.Util;

public class Config {

    public static final String DATABASE_NAME = "send-card-db";

    //column names of PHONE table
    public static final String TABLE_PHONE = "phone";
    public static final String COLUMN_PHONE_ID = "_id";
    public static final String COLUMN_PHONE_NUMBER = "phone";

    //column names of SENT table
    public static final String TABLE_SENT = "sent";
    public static final String COLUMN_SENT_ID = "_id";
    public static final String COLUMN_SENT_PHONE = "phone";
    public static final String COLUMN_SENT_REQUEST = "request";
    public static final String COLUMN_SENT_RESPONSE = "response";

}

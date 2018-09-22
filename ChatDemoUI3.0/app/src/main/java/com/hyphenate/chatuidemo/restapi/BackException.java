package com.hyphenate.chatuidemo.restapi;

import java.io.IOException;

public class BackException extends IOException {
    protected int errorCode = -1;
    protected String desc = "";

    public BackException(){ super();}

    public BackException(int errorCode, String desc){
        super(desc);
        this.errorCode = errorCode;
        this.desc = desc;
    }

    public BackException(String message) {
        super(message);
    }


    public int getErrorCode() {
        return errorCode;
    }
}

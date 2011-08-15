package org.helloworld.example.jsf;

public enum ErrorCode {

    SESSION_EXPIRED_MULTIPLE_LOGINS(201)
    ;

    private int code;

    private ErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}

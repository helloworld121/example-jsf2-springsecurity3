package org.helloworld.example.jsf;

public enum ErrorCode {

    NOT_LOGED_IN(150),
    SESSION_EXPIRED_MULTIPLE_LOGINS(201),
    BAD_CREDENTIAL(300),
    ;

    private int code;

    private ErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}

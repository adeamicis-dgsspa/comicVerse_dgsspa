package com.dgsspa.comicverse.exception;

public class ErrorResult {

    private String code;
    private String message;

    public ErrorResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() { return code; }
    public String getMessage() { return message; }
    public void setCode(String code) { this.code = code; }
    public void setMessage(String message) { this.message = message; }
}
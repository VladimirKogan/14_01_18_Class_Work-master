package com.example.vladik.a10_01_18_home_work;

/**
 * Created by vladik on 14/01/2018.
 */

public class Error {
    private Errors errors;
    private int code;
    Message message;

    public Error(Errors errors, int code, Message message) {
        this.errors = errors;
        this.code = code;
        this.message = message;
    }

    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}

class Errors {
    private String domain;
    private String reason;
    private Message message;
    private String locationType;
    private String location;

    public Errors(String domain, String reason, Message message, String locationType, String location) {
        this.domain = domain;
        this.reason = reason;
        this.message = message;
        this.locationType = locationType;
        this.location = location;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

class Message{
    private String message;

    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


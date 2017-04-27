package com.ChiliPrepper.ChiliPrepper.web;

/**
 * Created by Andreas on 15.02.2017.
 *
 * This class is used for creating messages to be displayed on the ChiliPrepper webapp.
 *
 * The constructor allows you to create a String message, and mark it with a status.
 *
 * The status is an enum with three different states, depending on the type of message you want (success, failure, info)
 * The status is used to determine which style rules (CSS) apply in teh browser.
 *
 */

public class FlashMessage {
    private String message;
    private Status status;

    public FlashMessage(String message, Status status) {
        this.message = message;
        this.status = status;
    }

    public enum Status {
        SUCCESS,
        INFO,
        FAILURE
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
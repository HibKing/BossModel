package com.example.myapplication.utils;

import java.util.List;

public class MessageDefineEvent {
    private String message;
    private List data;

    public MessageDefineEvent(String message, List data) {
        this.message = message;
        this.data = data;
    }
    public MessageDefineEvent(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}

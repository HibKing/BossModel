package com.example.myapplication.main.model;

import android.widget.ScrollView;

public class MessageUserItem {

    private String mUsername;
    private String mUserId;
    private String mLastMessage;
    private String mTime;

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getLastMessage() {
        return mLastMessage;
    }

    public void setLastMessage(String mLastMessage) {
        this.mLastMessage = mLastMessage;
    }

    public MessageUserItem(String mUsername, String mLastMessage,String mUserId,String mTime) {
        this.mUsername = mUsername;
        this.mLastMessage = mLastMessage;
        this.mUserId=mUserId;
        this.mTime=mTime;
    }
    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String mUserId) {
        this.mUserId = mUserId;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String mTime) {
        this.mTime = mTime;
    }
}

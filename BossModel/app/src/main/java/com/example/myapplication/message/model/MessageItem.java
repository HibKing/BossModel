package com.example.myapplication.message.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class MessageItem implements MultiItemEntity {
    public static final int MINE = 1;
    public static final int OTHER = 2;
    private int itemType;
    private String mMessage;


    public MessageItem(String message,int itemType) {
        this.itemType = itemType;
        mMessage=message;
    }



    @Override
    public int getItemType() {
        return itemType;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String mMessage) {
        this.mMessage = mMessage;
    }
}

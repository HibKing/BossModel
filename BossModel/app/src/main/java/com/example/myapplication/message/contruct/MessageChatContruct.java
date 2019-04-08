package com.example.myapplication.message.contruct;

import android.content.Context;

import java.util.List;

import cn.jpush.im.android.api.model.Message;

public interface MessageChatContruct {
    interface View{
        void refreshMessage(List<Message> data);
        void showEmptyTips();
        void showSendMessageSuccess();
        void showSendMessageFaile();
        void showNewMessage(List data);
    }

    interface Presenter{
        void initConfig(Context context,String userId);
        void getLocationMessage(String toUser);
        void sendMessage(String message,String toUser);
    }
}

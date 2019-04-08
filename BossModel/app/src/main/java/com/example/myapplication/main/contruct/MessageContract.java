package com.example.myapplication.main.contruct;

import android.content.Context;

import java.util.List;

public interface MessageContract {
    interface View{
        void showFriend(List data);
    }

    interface Presenter{
        void getFriendDatas();
    }
}

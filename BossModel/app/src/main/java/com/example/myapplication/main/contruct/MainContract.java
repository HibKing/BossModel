package com.example.myapplication.main.contruct;

import android.content.Context;
import android.widget.Toast;

import com.example.myapplication.account.UserManager;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;

public interface MainContract {
    interface View{

    }

    interface Presenter{
      void initApp(Context context);
    }



}

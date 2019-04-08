package com.example.myapplication.main.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.myapplication.account.UserManager;
import com.example.myapplication.main.contruct.MainContract;
import com.example.myapplication.utils.SpUtils;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;

import static cn.jpush.im.android.api.JMessageClient.FLAG_NOTIFY_WITH_VIBRATE;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mView;
    public MainPresenter(MainContract.View view){
        mView=view;
    }
    @Override
    public void initApp(final Context context) {
        SpUtils.getInstance().setContent(context);
        JMessageClient.setDebugMode(true);
        JMessageClient.init(context);
        JMessageClient.setNotificationFlag(FLAG_NOTIFY_WITH_VIBRATE);


        BasicCallback callback=new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                if(s.equals("Success"))
                {
                    Toast.makeText(context,"登录成功",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context,s,Toast.LENGTH_SHORT).show();
                }
            }
        };
        JMessageClient.login(UserManager.getInstance().getUser(), UserManager.getInstance().getPassword(), callback);
    }
}

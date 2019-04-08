package com.example.myapplication.main.presenter;

import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.main.contruct.MessageContract;
import com.example.myapplication.main.model.MessageUserItem;
import com.example.myapplication.utils.SpUtils;
import com.example.myapplication.utils.TimeUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import cn.jpush.im.android.api.ContactManager;
import cn.jpush.im.android.api.callback.GetUserInfoListCallback;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;

public class MessagePresenter implements MessageContract.Presenter {
    private MessageContract.View mView;
    @Override
    public void getFriendDatas() {
        final List data=new ArrayList();
        ContactManager.getFriendList(new GetUserInfoListCallback() {
            @Override
            public void gotResult(int responseCode, String responseMessage, List<UserInfo> userInfoList) {
                if (0 == responseCode) {
                    for(UserInfo item:userInfoList)
                    {
                        String content= SpUtils.getInstance().getString(item.getUserName());
                        String time="";
                        if(!SpUtils.getInstance().getString(item.getUserName()+"time").isEmpty()&&!SpUtils.getInstance().getString(item.getUserName()+"time").equals("0")){
                            try {
                                time= TimeUtils.dateToStamp(SpUtils.getInstance().getString(item.getUserName()+"time"));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        data.add(new MessageUserItem(item.getNickname(),content,item.getUserName(),time));
                    }
                    mView.showFriend(data);
                } else {
                    Log.d("MessagePresenter",responseMessage);
                }
            }
        });


    }
    public MessagePresenter(MessageContract.View view){
        mView=view;
    }
}

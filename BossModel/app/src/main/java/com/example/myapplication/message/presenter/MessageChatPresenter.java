package com.example.myapplication.message.presenter;

import android.content.Context;
import android.util.Log;
import com.example.myapplication.message.contruct.MessageChatContruct;
import com.example.myapplication.message.model.MessageItem;
import com.example.myapplication.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.event.OfflineMessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.options.MessageSendingOptions;
import cn.jpush.im.api.BasicCallback;

public class MessageChatPresenter implements MessageChatContruct.Presenter {
    private Conversation   mConversation;
    private MessageChatContruct.View mView;
    private String mToUser;
    public MessageChatPresenter(MessageChatContruct.View view,String toUser){
        mView=view;
        mToUser=toUser;
    }
    @Override
    public void initConfig(Context context,String userId) {
        JMessageClient.registerEventReceiver(this);
        mConversation= Conversation.createSingleConversation(userId, null);
    }




    @Override
    public void getLocationMessage(String toUser) {
        List<Message> mMessage=mConversation.getAllMessage();
        Log.d("ggra",String.valueOf(mMessage.size()));
        List data=new ArrayList();
        for(Message item:mMessage){
            TextContent textContent = (TextContent) item.getContent();
            if(JMessageClient.getMyInfo().getUserName().equals(item.getFromUser().getUserName())) {
                data.add(new MessageItem(textContent.getText().toString(), 1));
            }else{
                data.add(new MessageItem(textContent.getText().toString(), 2));
            }
        }
        if(data.size()>0){
            String key=toUser;
            TextContent textContent=(TextContent)mMessage.get(mMessage.size()-1).getContent();
            String value=textContent.getText();
            Log.d("fafa",key);
            SpUtils.getInstance().putString(key+"time",String.valueOf(mMessage.get(mMessage.size()-1).getCreateTime()));
            SpUtils.getInstance().putString(key,value);
        }

            mView.refreshMessage(data);
    }

    @Override
    public void sendMessage(final String messageContent, final String toUser) {
        if (!messageContent.equals("")) {
            final Message message;
            message = mConversation.createSendMessage(new TextContent(messageContent));
            message.setOnSendCompleteCallback(new BasicCallback() {
                @Override
                public void gotResult(int responseCode, String responseDesc) {
                    if (responseCode == 0) {
                        String key=toUser;
                        String value=messageContent;
                        SpUtils.getInstance().putString(key,value);
                        SpUtils.getInstance().putString(key+"time",String.valueOf(message.getCreateTime()));
                        mView.showSendMessageSuccess();
                        //消息发送成功
                    } else {
                        mView.showSendMessageFaile();
                        //消息发送失败
                    }
                }
            });

            MessageSendingOptions options = new MessageSendingOptions();
            options.setRetainOffline(false);

            JMessageClient.sendMessage(message);//使用默认控制参数发送消息
        }
        else {
            mView.showEmptyTips();
        }
    }


    public void onEvent(MessageEvent event) {
        List data=new ArrayList();
        Message msg = event.getMessage();
        TextContent textContent=(TextContent)msg.getContent();
        String content=textContent.getText();
        if(JMessageClient.getMyInfo().getUserName().equals(msg.getFromUser().getUserName())) {
            data.add(new MessageItem(content, 1));
        }else{
            data.add(new MessageItem(content, 2));
        }

        if(data.size()>0){
            String key=msg.getFromUser().getUserName();
            String value=content;
            SpUtils.getInstance().putString(key+"time",String.valueOf(msg.getCreateTime()));
            SpUtils.getInstance().putString(key,value);
        }
        mView.showNewMessage(data);
    }

    /**
     * 接受离线信息
     * @param event
     */
    public void onEvent(OfflineMessageEvent event) {
        //获取事件发生的会话对象
        Conversation conversation = event.getConversation();
        List<Message> newMessageList = event.getOfflineMessageList();//获取此次离线期间会话收到的新消息列表

    }

}

package com.example.myapplication.message.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.message.contruct.MessageChatContruct;
import com.example.myapplication.message.model.MessageItem;
import com.example.myapplication.message.presenter.MessageChatPresenter;
import com.example.myapplication.message.view.adapter.MessageChatAdapter;
import com.example.myapplication.utils.MessageDefineEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.event.OfflineMessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;

public class MessageActivity extends AppCompatActivity implements MessageChatContruct.View {
    @BindView(R.id.message_rv)
    RecyclerView mRecycleView;
    @BindView(R.id.message_content)
    EditText mMessageContentEt;
    @BindView(R.id.message_send)
    Button mMessageSendBtn;
    private MessageChatContruct.Presenter mPresenter;
    private MessageChatAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        mPresenter=new MessageChatPresenter(this,getIntent().getStringExtra("userId"));
        initView();
        initEvent();
    }

    public void initView(){
        List data=new ArrayList();
        mAdapter=new MessageChatAdapter(data);
        Intent intent=getIntent();
        mPresenter.initConfig(this,intent.getStringExtra("userId"));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(layoutManager);
        mRecycleView.setAdapter(mAdapter);
        mPresenter.getLocationMessage(intent.getStringExtra("userId"));
    }

    public void initEvent(){
        mMessageSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=getIntent();
                mPresenter.sendMessage(mMessageContentEt.getText().toString(),intent.getStringExtra("userId"));
                Log.d("ddd1",intent.getStringExtra("userId"));

            }
        });
    }

    @Override
    public void refreshMessage(List data) {
        EventBus.getDefault().post(new MessageDefineEvent("message_refresh"));
        mAdapter.getData().clear();
        mAdapter.getData().addAll(data);
        mAdapter.notifyDataSetChanged();
        if(mAdapter.getItemCount()>0)
        mRecycleView.smoothScrollToPosition(mAdapter.getItemCount()-1);
    }


    @Override
    public void showEmptyTips() {
        Toast.makeText(this,"内容不能为空",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSendMessageSuccess() {
        EventBus.getDefault().post(new MessageDefineEvent("message_refresh"));
        mAdapter.getData().add(new MessageItem(mMessageContentEt.getText().toString(),1));
        mAdapter.notifyDataSetChanged();
        if(mAdapter.getItemCount()>0)
        mRecycleView.smoothScrollToPosition(mAdapter.getItemCount()-1);
        mMessageContentEt.setText("");
        Toast.makeText(this,"发送成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSendMessageFaile() {
        Toast.makeText(this,"发送失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNewMessage(List data) {
        EventBus.getDefault().post(new MessageDefineEvent("message_refresh"));
        mAdapter.getData().addAll(data);
        mAdapter.notifyDataSetChanged();
        if(mAdapter.getItemCount()>0)
        mRecycleView.smoothScrollToPosition(mAdapter.getItemCount()-1);
    }
}

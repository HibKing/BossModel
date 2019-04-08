package com.example.myapplication.main.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.myapplication.R;
import com.example.myapplication.download.utils.DownloadUtils;
import com.example.myapplication.main.contruct.MessageContract;
import com.example.myapplication.main.model.MessageUserItem;
import com.example.myapplication.main.presenter.MessagePresenter;
import com.example.myapplication.main.view.adapter.MessageMainAdapter;
import com.example.myapplication.message.view.activity.MessageActivity;
import com.example.myapplication.utils.MessageDefineEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageFragment extends Fragment implements MessageContract.View {
    @BindView(R.id.message_rv)
    RecyclerView mRecycleView;
    private MessageContract.Presenter mPresenter;
    private MessageMainAdapter mAdapter;
    private List mMessageData=new ArrayList();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.message_fragment, container, false);
        ButterKnife.bind(this, view);
        mPresenter=new MessagePresenter(this);
        initView();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        return view;
    }
    public void initView(){
        mAdapter=new MessageMainAdapter(R.layout.message_item,mMessageData);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MessageUserItem item=(MessageUserItem) adapter.getData().get(position);
                Intent intent=new Intent(getActivity(), MessageActivity.class);
                Log.d("haha",item.getUserId());
                intent.putExtra("userId",item.getUserId());
                startActivity(intent);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecycleView.setLayoutManager(layoutManager);
        mRecycleView.setAdapter(mAdapter);
        mPresenter.getFriendDatas();
    }

    @Override
    public void showFriend(List data) {
        mAdapter.getData().clear();
        mAdapter.getData().addAll(data);
        mAdapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshData(MessageDefineEvent messageDefineEvent) {
        if (messageDefineEvent.getMessage().equals("message_refresh")) {
           mPresenter.getFriendDatas();
        }

    }
}
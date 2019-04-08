package com.example.myapplication.main.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.main.contruct.BussinessContract;
import com.example.myapplication.main.model.BussinessItem;
import com.example.myapplication.main.presenter.BussinessPresenter;
import com.example.myapplication.main.view.adapter.BussinessMainAdapter;
import com.example.myapplication.utils.MessageDefineEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BussinessFragment extends Fragment implements BussinessContract.View
{
    @BindView(R.id.bussiness_rv)
    RecyclerView mRecycleView;
    @BindView(R.id.swiperereshlayout)
    SwipeRefreshLayout mSwLayout;
    private BussinessMainAdapter mAdapter;
    private List<BussinessItem> mdata;
    private BussinessContract.Presenter mPresenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bussiness_fragment, container, false);
        ButterKnife.bind(this, view);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        mPresenter=new BussinessPresenter(this);
        initView();
        return view;
    }
    public void initView(){
        mdata=new ArrayList<>();
        mAdapter=new BussinessMainAdapter(R.layout.bussiness_item,mdata);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecycleView.setLayoutManager(layoutManager);
        mRecycleView.setAdapter(mAdapter);
        mPresenter.requestData();
        mSwLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //设置2秒的时间来执行以下事件
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        mPresenter.requestData();
                    }
                }, 1000);
            }
        });
    }

    @Override
    public void refreshData(List data) {
        mAdapter.getData().clear();
        mAdapter.getData().addAll(data);
        mAdapter.notifyDataSetChanged();
        mSwLayout.setRefreshing(false);
        Toast.makeText(this.getContext(), "加载完成", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshData(MessageDefineEvent messageDefineEvent) {
        if (messageDefineEvent.getMessage().equals("refreshData")) {
           refreshData(messageDefineEvent.getData());
        }

    }
}

package com.example.myapplication.main.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.account.UserManager;
import com.example.myapplication.main.contruct.JobContract;
import com.example.myapplication.main.service.JobFactory;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;

public class JobPresenter implements JobContract.Presenter {
    private JobContract.View mView;
    private JobFactory mJobFactory=new JobFactory();
    @Override
    public void requestRefreshData(int st, int ed) {
        mJobFactory.requestRefreshData(st,ed);
        mJobFactory.setCurrentNumber(0);
    }
    public JobPresenter(JobContract.View view){
        mView=view;
    }

    @Override
    public void requestUploadMoreData() {
        if (mJobFactory.getCurrentNumber() % 4 != 0) {
           mView.loadMoreEnd();
        } else {
            //成功获取更多数据
            mJobFactory.requestUploadMoreData(mJobFactory.getCurrentNumber(),4);
        }
    }


}

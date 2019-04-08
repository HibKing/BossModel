package com.example.myapplication.job.presenter;

import com.example.myapplication.job.contruct.JobDetailContruct;
import com.example.myapplication.main.presenter.JobPresenter;

public class JobDetailPresenter implements JobDetailContruct.Presenter {
    private JobDetailContruct.View mView;
    public JobDetailPresenter(JobDetailContruct.View view){
        mView=view;
    }

    @Override
    public void getJobMessage() {

    }
}

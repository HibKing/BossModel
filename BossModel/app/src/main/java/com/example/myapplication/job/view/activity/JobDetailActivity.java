package com.example.myapplication.job.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.job.contruct.JobDetailContruct;
import com.example.myapplication.job.presenter.JobDetailPresenter;
import com.example.myapplication.utils.MessageDefineEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JobDetailActivity extends AppCompatActivity implements JobDetailContruct.View {
    @BindView(R.id.job_detail_job_name)
    TextView mJobNameTv;
    @BindView(R.id.job_detail_salary)
    TextView mJobSalary;
    @BindView(R.id.job_detail_location)
    TextView mJobLocation;
    @BindView(R.id.job_detail_record)
    TextView mJobRecord;

    private JobDetailContruct.Presenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mPresenter=new JobDetailPresenter(this);
        initData();
    }

    public void initData(){
        mPresenter.getJobMessage();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshData(MessageDefineEvent messageDefineEvent) {
        if (messageDefineEvent.getMessage().equals("job_message")) {

        }
    }

}

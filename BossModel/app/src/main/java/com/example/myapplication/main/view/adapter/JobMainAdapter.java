package com.example.myapplication.main.view.adapter;

import android.util.Log;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.main.model.JobItem;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class JobMainAdapter extends BaseQuickAdapter<JobItem, BaseViewHolder> {
    public JobMainAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, JobItem item) {
        helper.setText(R.id.job_job_name, item.getJobName());
        helper.setText(R.id.job_job_salary, item.getJobSalary());
        helper.setText(R.id.job_bussiness,item.getBussinessName());
        helper.setText(R.id.job_city,item.getCity()+" "+item.getArea()+" "+item.getLocation());
        helper.setText(R.id.job_exercise,item.getExperience());
        helper.setText(R.id.job_education,item.getEducation());
        Log.d("asd",item.getBossIconUrl());
        Glide.with(mContext).load(item.getBossIconUrl()).into((CircleImageView) helper.getView(R.id.job_boss_icon));
        helper.setText(R.id.job_boss_name,item.getBossName()+"."+item.getBossJob());
    }
}

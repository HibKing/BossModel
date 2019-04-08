package com.example.myapplication.job.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class JobDetailItem implements MultiItemEntity {
    public static final int JOB_CONDITION = 1;
    public static final int JOB_BOSS = 2;
    public static final int JOB_REQUIRE=3;
    public static final int JOB_SKILL=4;
    public static final int JOB_TEAM=5;
    public static final int JOB_COMPANY_NAME=6;
    public static final int JOB_COMPANY_LOCATION=7;
    private List mData;

    private int itemType;
    public JobDetailItem(int itemType,List mData) {
        this.itemType = itemType;
        this.mData=mData;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public List getData() {
        return mData;
    }

    public void setData(List mData) {
        this.mData = mData;
    }
}

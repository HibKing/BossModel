package com.example.myapplication.main.model;

import android.view.View;

public class JobItem {
    private String mJobName;
    private String mJobSalary;
    private String mBussinessName;
    private String mCity;
    private String mArea;
    private String mLocation;
    private String mExperience;
    private String mEducation;
    private String mBossName;
    private String mBossJob;
    private String mBossIconUrl;
    private View mView;
    public JobItem(String mJobName, String mJobSalary, String mBussinessName, String mCity, String mArea, String mLocation, String mExperience, String mEducation, String mBossName, String mBossJob, String mBossIconUrl) {
        this.mJobName = mJobName;
        this.mJobSalary = mJobSalary;
        this.mBussinessName = mBussinessName;
        this.mCity = mCity;
        this.mArea = mArea;
        this.mLocation = mLocation;
        this.mExperience = mExperience;
        this.mEducation = mEducation;
        this.mBossName = mBossName;
        this.mBossJob = mBossJob;
        this.mBossIconUrl = mBossIconUrl;
    }

    public String getJobName() {
        return mJobName;
    }

    public JobItem setJobName(String mJobName) {
        this.mJobName = mJobName;
        return this;
    }

    public String getJobSalary() {
        return mJobSalary;
    }

    public JobItem setJobSalary(String mJobSalary) {
        this.mJobSalary = mJobSalary;
        return this;
    }

    public String getBussinessName() {
        return mBussinessName;
    }

    public JobItem setBussinessName(String mBussinessName) {
        this.mBussinessName = mBussinessName;
        return this;
    }

    public String getCity() {
        return mCity;
    }

    public JobItem setCity(String mCity) {
        this.mCity = mCity;
        return this;
    }

    public String getArea() {
        return mArea;
    }

    public JobItem setArea(String mArea) {
        this.mArea = mArea;
        return this;
    }

    public String getLocation() {
        return mLocation;
    }

    public JobItem setLocation(String mLocation) {
        this.mLocation = mLocation;
        return this;
    }

    public String getExperience() {
        return mExperience;
    }

    public JobItem setExperience(String mExperience) {
        this.mExperience = mExperience;
        return this;
    }

    public String getEducation() {
        return mEducation;
    }

    public JobItem setEducation(String mEducation) {
        this.mEducation = mEducation;
        return this;
    }

    public String getBossName() {
        return mBossName;
    }

    public JobItem setBossName(String mBossName) {
        this.mBossName = mBossName;
        return this;
    }

    public String getBossJob() {
        return mBossJob;
    }

    public JobItem setBossJob(String mBossJob) {
        this.mBossJob = mBossJob;
        return this;
    }

    public String getBossIconUrl() {
        return mBossIconUrl;
    }

    public JobItem setBossIconUrl(String mBossIconUrl) {
        this.mBossIconUrl = mBossIconUrl;
        return this;
    }
}

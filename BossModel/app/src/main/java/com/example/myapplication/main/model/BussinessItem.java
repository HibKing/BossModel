package com.example.myapplication.main.model;

public class BussinessItem {
    private String mIconUrl;
    private String mName;
    private String mCity;
    private String mArea;
    private String mLocation;
    private String mScale;
    private String mPeaple;
    private String mType;

    public BussinessItem(String mIconUrl, String mName, String mCity, String mArea, String mLocation, String mScale, String mPeaple, String mType) {
        this.mIconUrl = mIconUrl;
        this.mName = mName;
        this.mCity = mCity;
        this.mArea = mArea;
        this.mLocation = mLocation;
        this.mScale = mScale;
        this.mPeaple = mPeaple;
        this.mType = mType;
    }

    public String getIconUrl() {
        return mIconUrl;
    }

    public void setIconUrl(String mIconUrl) {
        this.mIconUrl = mIconUrl;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String mCity) {
        this.mCity = mCity;
    }

    public String getArea() {
        return mArea;
    }

    public void setArea(String mArea) {
        this.mArea = mArea;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public String getScale() {
        return mScale;
    }

    public void setScale(String mScale) {
        this.mScale = mScale;
    }

    public String getPeaple() {
        return mPeaple;
    }

    public void setPeaple(String mPeaple) {
        this.mPeaple = mPeaple;
    }

    public String getType() {
        return mType;
    }

    public void setType(String mType) {
        this.mType = mType;
    }
}

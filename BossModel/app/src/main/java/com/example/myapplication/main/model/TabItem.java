package com.example.myapplication.main.model;

public class TabItem {
    private String mContent;
    private int mIcon;
    private int mSelectIcon;

    public TabItem(String mContent, int mIcon) {
        this.mContent = mContent;
        this.mIcon = mIcon;
    }

    public String getContent() {
        return mContent;
    }

    public TabItem setContent(String mContent) {
        this.mContent = mContent;
        return this;
    }

    public int getIcon() {
        return mIcon;
    }

    public TabItem setIcon(int mIcon) {
        this.mIcon = mIcon;
        return this;
    }

    public int getSelectIcon() {
        return mSelectIcon;
    }

    public TabItem setSelectIcon(int mSelectIcon) {
        this.mSelectIcon = mSelectIcon;
        return this;
    }
}

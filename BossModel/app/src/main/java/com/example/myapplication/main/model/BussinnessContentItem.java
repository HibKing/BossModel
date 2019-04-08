package com.example.myapplication.main.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class BussinnessContentItem implements MultiItemEntity {
    public static final int TITLE= 1;
    public static final int CONTENT = 2;
    private String title;
    private List<String> content;
    private int itemType;
    public BussinnessContentItem(int itemType,String title) {
        this.itemType = itemType;
        this.title= title;
    }
    public BussinnessContentItem(int itemType,List<String> content) {
        this.itemType = itemType;
        this.content= content;
    }
    @Override
    public int getItemType() {
        return itemType;
    }
    public String getTitle(){
        return title;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }
}

package com.example.myapplication.main.view.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.main.model.JobItem;

import java.util.ArrayList;
import java.util.List;

public class JobAreaOptionAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private View currentPosition=null;
    private String defaultItem=null;
    private int whiteColor=0;
    private int grayColor=0;
    private int selectTextColor=0;
    private int noSelectTextColor=0;
    public JobAreaOptionAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String areaOption) {
        helper.setText(R.id.job_select_area_option, areaOption).addOnClickListener(R.id.job_select_area_option);

        if(areaOption.equals(defaultItem)) {
            currentPosition = (helper.getView(R.id.job_select_area_option));
            TextView currentView=currentPosition.findViewById(R.id.job_select_area_option);
            currentView.setTextColor(selectTextColor);
            currentView.setBackgroundColor(grayColor);

        }

    }
    public void initView(){
        TextView currentView=currentPosition.findViewById(R.id.job_select_area_option);
        currentView.setBackgroundColor(whiteColor);
        currentView.setTextColor(noSelectTextColor);
    }

    public void singleSelect(View view){
        TextView newView=view.findViewById(R.id.job_select_area_option);
        TextView currentView=currentPosition.findViewById(R.id.job_select_area_option);
        if(currentPosition!=view){
         newView.setBackgroundColor(grayColor);
         newView.setTextColor(selectTextColor);
         currentView.setBackgroundColor(whiteColor);
         currentView.setTextColor(noSelectTextColor);
         currentPosition=view;
        }
    }

    public View getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(View currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int getWhiteColor() {
        return whiteColor;
    }

    public void setWhiteColor(int whiteColor) {
        this.whiteColor = whiteColor;
    }

    public int getGrayColor() {
        return grayColor;
    }

    public void setGrayColor(int grayColor) {
        this.grayColor = grayColor;
    }

    public String getDefaultItem() {
        return defaultItem;
    }

    public void setDefaultItem(String defaultItem) {
        this.defaultItem = defaultItem;
    }

    public int getSelectTextColor() {
        return selectTextColor;
    }

    public void setSelectTextColor(int selectTextColor) {
        this.selectTextColor = selectTextColor;
    }

    public int getNoSelectTextColor() {
        return noSelectTextColor;
    }

    public void setNoSelectTextColor(int noSelectTextColor) {
        this.noSelectTextColor = noSelectTextColor;
    }
}

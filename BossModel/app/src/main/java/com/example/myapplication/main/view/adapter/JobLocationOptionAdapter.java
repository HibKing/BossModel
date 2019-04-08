package com.example.myapplication.main.view.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.main.contruct.JobContract;

import java.util.ArrayList;
import java.util.List;

public class JobLocationOptionAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private int gray=0;
    private int selecColor=0;
    private List<View> changeViewData=new ArrayList<>();
    public JobLocationOptionAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String areaOption) {
        helper.setText(R.id.job_select_location_option, areaOption);

    }

    public void selectItem(View view){
        CheckBox checkBox=(CheckBox)view.findViewById(R.id.checkBox);
        if(!checkBox.isChecked()) {
            TextView textView = (TextView) view.findViewById(R.id.job_select_location_option);
            textView.setTextColor(selecColor);
            changeViewData.add(view);
            checkBox.setChecked(true);
        }else {
            TextView textView = (TextView) view.findViewById(R.id.job_select_location_option);
            textView.setTextColor(gray);
            changeViewData.remove(view);
            checkBox.setChecked(false);
        }

    }

    public void initView(){
        for(View item:changeViewData){
            TextView textView = (TextView) item.findViewById(R.id.job_select_location_option);
            CheckBox checkBox=(CheckBox)item.findViewById(R.id.checkBox);
            textView.setTextColor(gray);

            checkBox.setChecked(false);
        }
        changeViewData.clear();
    }

    public int getGray() {
        return gray;
    }

    public void setGray(int gray) {
        this.gray = gray;
    }

    public int getSelecColor() {
        return selecColor;
    }

    public void setSelecColor(int selecColor) {
        this.selecColor = selecColor;
    }
}

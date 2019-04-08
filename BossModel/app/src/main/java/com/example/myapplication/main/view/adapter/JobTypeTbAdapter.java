package com.example.myapplication.main.view.adapter;

import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.common_ui.popuwindow.TabPopuWindowUtils;

import java.util.List;

public class JobTypeTbAdapter {
    private TabLayout mTabLayout;
    private List<String> mTabContent;
    private View mContent;
    private TabPopuWindowUtils tabPopuWindow;
    public void setTabView() {
        tabPopuWindow =new TabPopuWindowUtils();


        for (int i = 0; i < mTabContent.size(); i++) {
            //依次获取标签

            TabLayout.Tab tab = mTabLayout.newTab();

            View inflate = View.inflate(mContent.getContext(), R.layout.job_select_item, null);
            TextView jobSelectName = inflate.findViewById(R.id.job_select_name);
            jobSelectName.setText(mTabContent.get(i));
            jobSelectName.setTextColor(mTabLayout.getTabTextColors());


            ImageView jobSelectIcon= inflate.findViewById(R.id.job_select_icon);
            jobSelectIcon.setImageResource(R.drawable.job_select_selector);
            tab.setCustomView(inflate);

            mTabLayout.addTab(tab);



        }
        mTabLayout.setTabTextColors(R.color.gray_text,R.color.colorPrimary);
        //tab选中的监听事件
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getCustomView().findViewById(R.id.job_select_icon).setSelected(true);
                switch (tab.getPosition()){
                    case 0:
                        tabPopuWindow.setmContext(tab.parent.getContext());
                        tabPopuWindow.typePopupWindow(tab.getCustomView(),R.layout.job_select_type,tab);
                        break;
                    case 1:
                        tabPopuWindow.setmContext(tab.parent.getContext());
                        tabPopuWindow.areaPopupWindow(tab.getCustomView(),R.layout.job_select_area,tab);
                        break;
                    case 2:
                        tabPopuWindow.setmContext(tab.parent.getContext());
                        tabPopuWindow.bussinessPopupWindow(tab.getCustomView(),R.layout.job_select_bussiness,tab);
                        break;
                    case 3:

                        break;


                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                    if(tab.getCustomView().findViewById(R.id.job_select_icon).isSelected()){
                        tab.getCustomView().setClickable(false);

                    }else {
                        tabPopuWindow.setmContext(tab.parent.getContext());
                        tab.getCustomView().findViewById(R.id.job_select_icon).setSelected(true);
                        tab.getCustomView().findViewById(R.id.job_select_name).setSelected(true);
                        tabPopuWindow.typePopupWindow(tab.getCustomView(),R.layout.job_select_type,tab);
                    }
                    break;
                    case 1:
                        if(tab.getCustomView().findViewById(R.id.job_select_icon).isSelected()){
                            tab.getCustomView().setClickable(false);
                        }else {
                            tabPopuWindow.setmContext(tab.parent.getContext());
                            tab.getCustomView().findViewById(R.id.job_select_icon).setSelected(true);
                            tab.getCustomView().findViewById(R.id.job_select_name).setSelected(true);
                            tabPopuWindow.areaPopupWindow(tab.getCustomView(),R.layout.job_select_area,tab);
                        }
                        break;
                    case 2:
                        if(tab.getCustomView().findViewById(R.id.job_select_icon).isSelected()){
                            tab.getCustomView().setClickable(false);
                        }else {
                            tabPopuWindow.setmContext(tab.parent.getContext());
                            tab.getCustomView().findViewById(R.id.job_select_icon).setSelected(true);
                            tab.getCustomView().findViewById(R.id.job_select_name).setSelected(true);
                            tabPopuWindow.bussinessPopupWindow(tab.getCustomView(),R.layout.job_select_bussiness,tab);
                        }
                        break;
                }
            }
        });
    }


    public TabLayout getTabLayout() {
        return mTabLayout;
    }

    public JobTypeTbAdapter setTabLayout(TabLayout mTabLayout) {
        this.mTabLayout = mTabLayout;
        return this;
    }

    public List<String> getTabContent() {
        return mTabContent;
    }

    public JobTypeTbAdapter setTabContent(List<String> mTabContent) {
        this.mTabContent = mTabContent;
        return this;
    }

    public View getContent() {
        return mContent;
    }

    public JobTypeTbAdapter setContent(View mContent) {
        this.mContent = mContent;
        return this;
    }
}

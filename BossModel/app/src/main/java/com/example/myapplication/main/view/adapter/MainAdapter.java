package com.example.myapplication.main.view.adapter;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.main.model.TabItem;

import java.util.ArrayList;


public class MainAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mList;
    private ArrayList<TabItem> mTab;
    private Context context;
    private TabLayout mTabLayout;

    public MainAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    /**
     * 自定义方法，提供自定义Tab
     *
     * @param position 位置
     * @return 返回Tab的View
     */
    public View getTabView(int position) {
        Log.d("ggr",String.valueOf(position));
        View v = LayoutInflater.from(context).inflate(R.layout.main_tab, null);
        TextView textView = (TextView) v.findViewById(R.id.tv_title);
        ImageView imageView = (ImageView) v.findViewById(R.id.iv_icon);
        textView.setText(mTab.get(position).getContent());
        imageView.setImageResource(mTab.get(position).getIcon());
        textView.setTextColor(mTabLayout.getTabTextColors());
        switch (position){
            case 0:
                imageView.setImageResource(R.drawable.job_icon_selector);
                break;
            case 1:
                imageView.setImageResource(R.drawable.business_icon_selector);
                break;
            case 2:
                imageView.setImageResource(R.drawable.message_icon_selector);
                break;
            case 3:
                imageView.setImageResource(R.drawable.mine_icon_selector);
                break;
        }

        return v;
    }



    public ArrayList<Fragment> getmList() {
        return mList;
    }

    public MainAdapter setmList(ArrayList<Fragment> mList) {
        this.mList = mList;
        return this;
    }

    public ArrayList<TabItem> getmTab() {
        return mTab;
    }

    public MainAdapter setmTab(ArrayList<TabItem> mTab) {
        this.mTab = mTab;
        return this;
    }

    public TabLayout getmTabLayout() {
        return mTabLayout;
    }

    public MainAdapter setmTabLayout(TabLayout mTabLayout) {
        this.mTabLayout = mTabLayout;
        return this;
    }


}

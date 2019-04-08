package com.example.myapplication.common_ui.popuwindow;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.myapplication.R;

import com.example.myapplication.main.model.BussinnessContentItem;
import com.example.myapplication.main.view.adapter.JobAreaOptionAdapter;
import com.example.myapplication.main.view.adapter.JobBussinessOptionAdapter;
import com.example.myapplication.main.view.adapter.JobLocationOptionAdapter;
import com.example.myapplication.utils.DataUtils;
import com.example.myapplication.utils.MessageDefineEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TabPopuWindowUtils {
    private Context mContext=null;

    private JobAreaOptionAdapter jobAreaOptionAdapter;
    private JobLocationOptionAdapter jobLocationOptionAdapter;
    private JobBussinessOptionAdapter jobBussinessOptionAdapter;
    public TabPopuWindowUtils(){

    }
    /**
     * 推荐，最新分类PopuWindow
     * @param view
     * @param layout
     * @param tab
     */
    public void typePopupWindow(View view, int layout, final TabLayout.Tab tab) {

        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(mContext).inflate(
                layout, null);
        //设置布局透明度
        contentView.getBackground().setAlpha(180);
        final ShowPopuWindow popupWindow = new ShowPopuWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);

        final TextView mRecommend=(TextView) contentView.findViewById(R.id.job_select_recommend);
        final TextView mLast=(TextView) contentView.findViewById(R.id.job_select_last);
        //根据Tab内容，初始化字体颜色
        initTextColor(tab,mRecommend,mLast);
        LinearLayout total=(LinearLayout) contentView.findViewById(R.id.total);
        total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tab.getCustomView().findViewById(R.id.job_select_icon).setSelected(false);
                tab.getCustomView().findViewById(R.id.job_select_name).setSelected(false);

                popupWindow.dismiss();
            }
        });
      mRecommend.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              tab.getCustomView().findViewById(R.id.job_select_icon).setSelected(false);
              tab.getCustomView().findViewById(R.id.job_select_name).setSelected(false);

              mRecommend.setTextColor(getmContext().getColor(R.color.colorPrimary));
              mLast.setTextColor(getmContext().getColor(R.color.gray_text));
              TextView textView=tab.getCustomView().findViewById(R.id.job_select_name);
              textView.setText("推荐");
              popupWindow.dismiss();
          }
      });
        mLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tab.getCustomView().findViewById(R.id.job_select_icon).setSelected(false);
                tab.getCustomView().findViewById(R.id.job_select_name).setSelected(false);
                mRecommend.setTextColor(getmContext().getColor(R.color.gray_text));
                mLast.setTextColor(getmContext().getColor(R.color.colorPrimary));
                TextView textView=tab.getCustomView().findViewById(R.id.job_select_name);
                textView.setText("最新");
                popupWindow.dismiss();
            }
        });
        popupWindow.initListenter();
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                List tabContent=new ArrayList();
                tabContent.add(tab);
                EventBus.getDefault().post(new MessageDefineEvent("job_init_background",tabContent));
            }
        });



        // 设置好参数之后再show
        popupWindow.showAsDropDown(view);

    }

    /**
     * 根据Tab的内容来决定选项的颜色
     */
    public void initTextColor(TabLayout.Tab tab,TextView mRecommend,TextView mLast ){
        TextView nameTv=tab.getCustomView().findViewById(R.id.job_select_name);
        if(nameTv.getText().toString().equals("推荐")){
            mRecommend.setTextColor(getmContext().getColor(R.color.colorPrimary));
            mLast.setTextColor(getmContext().getColor(R.color.gray_text));
        }else {
            mRecommend.setTextColor(getmContext().getColor(R.color.gray_text));
            mLast.setTextColor(getmContext().getColor(R.color.colorPrimary));
        }
    }

    public void areaPopupWindow(View view, int layout, final TabLayout.Tab tab) {
            TextView tabName=(TextView) tab.getCustomView().findViewById(R.id.job_select_name);
            // 一个自定义的布局，作为显示的内容
            View contentView = LayoutInflater.from(mContext).inflate(
                    layout, null);
            //设置布局透明度
            contentView.getBackground().setAlpha(180);
            final ShowPopuWindow popupWindow = new ShowPopuWindow(contentView,
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);

            final TabLayout tabLayout =(TabLayout) contentView.findViewById(R.id.job_select_area_type);

            tabLayout.addTab(tabLayout.newTab().setText("商圈"));
            tabLayout.addTab(tabLayout.newTab().setText("地铁"));
            //初始化RecycleView数据
            initAreaRecycleView(contentView,tabName.getText().toString());
            //初始化监听事件
            initAreaListener(contentView,tab,popupWindow,tabLayout);
            popupWindow.initListenter();
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {

                    String tabName=tab.getText().toString();
                    if(tabName.equals("商圈")) {
                        resetBussinessData();
                    }else{
                        resetUndergroundData();
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });


            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    //弹窗消失时要刷新UI
                    List tabContent=new ArrayList();
                    tabContent.add(tab);
                    EventBus.getDefault().post(new MessageDefineEvent("job_init_background",tabContent));
                }
            });

            // 设置好参数之后再show
            popupWindow.showAsDropDown(view);
    }

    public void bussinessPopupWindow(View view, int layout, final TabLayout.Tab tab) {
        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(mContext).inflate(
                layout, null);
        //设置布局透明度
        contentView.getBackground().setAlpha(180);
        final ShowPopuWindow popupWindow = new ShowPopuWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        initBussinnessListener(contentView,tab,popupWindow);
        initBussinnessRecycleView(contentView);
        popupWindow.initListenter();


        LinearLayout total=(LinearLayout) contentView.findViewById(R.id.total);
        total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tab.getCustomView().findViewById(R.id.job_select_icon).setSelected(false);
                tab.getCustomView().findViewById(R.id.job_select_name).setSelected(false);

                popupWindow.dismiss();
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                List tabContent=new ArrayList();
                tabContent.add(tab);
                EventBus.getDefault().post(new MessageDefineEvent("job_init_background",tabContent));
            }
        });


        // 设置好参数之后再show
        popupWindow.showAsDropDown(view);
    }





    public void initAreaRecycleView(View contentView,String defaultItem){
        RecyclerView areaRecyclerView =(RecyclerView)contentView.findViewById(R.id.job_select_area_option);
        RecyclerView locationRecyclerView =(RecyclerView)contentView.findViewById(R.id.job_select_location_option);
        LinearLayoutManager areaLayoutManager = new LinearLayoutManager(getmContext());
        LinearLayoutManager locationLayoutManager = new LinearLayoutManager(getmContext());
        areaRecyclerView.setLayoutManager(areaLayoutManager);
        locationRecyclerView.setLayoutManager(locationLayoutManager);
        final List data=new ArrayList();
        data.add("广州");
        data.add("天河区");
        data.add("白云区");
        jobAreaOptionAdapter=new JobAreaOptionAdapter(R.layout.job_select_area_option_item,data);
        DataUtils dataUtils=new DataUtils();
        jobLocationOptionAdapter=new JobLocationOptionAdapter(R.layout.job_select_area_loaction_item,(List)dataUtils.getAreaOption().get(defaultItem));
        jobAreaOptionAdapter.setWhiteColor(getmContext().getColor(R.color.white));
        jobAreaOptionAdapter.setGrayColor(getmContext().getColor(R.color.gray));
        jobAreaOptionAdapter.setSelectTextColor(getmContext().getColor(R.color.colorPrimary));
        jobAreaOptionAdapter.setNoSelectTextColor(getmContext().getColor(R.color.gray_text));
        jobLocationOptionAdapter.setGray(getmContext().getColor(R.color.gray_text));
        jobLocationOptionAdapter.setSelecColor(getmContext().getColor(R.color.colorPrimary));
        jobAreaOptionAdapter.setDefaultItem(defaultItem);

        jobAreaOptionAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                jobAreaOptionAdapter.singleSelect(view);
                String areakey=(String)adapter.getData().get(position);
                Log.d("hahaha",areakey);
                DataUtils dataUtils=new DataUtils();
                List dataLocation=(List)dataUtils.getAreaOption().get(areakey);
                Log.d("hahaha",String.valueOf(dataLocation.size()));
                jobLocationOptionAdapter.getData().clear();
                jobLocationOptionAdapter.getData().addAll(dataLocation);
                jobLocationOptionAdapter.initView();
                jobLocationOptionAdapter.notifyDataSetChanged();
            }
        });

        jobLocationOptionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                jobLocationOptionAdapter.selectItem(view);
            }
        });

        areaRecyclerView.setAdapter(jobAreaOptionAdapter);
        locationRecyclerView.setAdapter(jobLocationOptionAdapter);
    }

    public void initBussinnessRecycleView(View contentView){
        RecyclerView areaRecyclerView =(RecyclerView)contentView.findViewById(R.id.job_select_bussiness_option);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getmContext());
        areaRecyclerView.setLayoutManager(layoutManager);

        final List data=initBussinnessData();
        jobBussinessOptionAdapter=new JobBussinessOptionAdapter(data);

        areaRecyclerView.setAdapter(jobBussinessOptionAdapter);

    }

    public List initBussinnessData(){
           List data=new ArrayList<>();
           List itemdata=new ArrayList();
           itemdata.add("全部");
           itemdata.add("电子商务");
           itemdata.add("游戏");
           data.add(new BussinnessContentItem(1,"融资阶段"));
           data.add(new BussinnessContentItem(2,itemdata));

           return data;
    }

    public void initAreaListener(View contentView, final TabLayout.Tab tab, final ShowPopuWindow popupWindow, final TabLayout tabLayout){
        //点击空白处消失
        LinearLayout total=(LinearLayout) contentView.findViewById(R.id.total);
        total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tab.getCustomView().findViewById(R.id.job_select_icon).setSelected(false);
                tab.getCustomView().findViewById(R.id.job_select_name).setSelected(false);

                popupWindow.dismiss();
            }
        });
        Button sureBtn=(Button)contentView.findViewById(R.id.job_select_area_sure_bt);
        sureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        Button resetBtn=(Button)contentView.findViewById(R.id.job_select_area_reset_bt);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tabName=tabLayout.getTabAt(tabLayout.getSelectedTabPosition()).getText().toString();
                if(tabName.equals("商圈")) {
                    resetBussinessData();
                }else{
                    resetUndergroundData();
                }
            }
        });
    }

    public void initBussinnessListener(View contentView, final TabLayout.Tab tab, final ShowPopuWindow popupWindow){
        //点击空白处消失
        LinearLayout total=(LinearLayout) contentView.findViewById(R.id.total);
        total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tab.getCustomView().findViewById(R.id.job_select_icon).setSelected(false);
                tab.getCustomView().findViewById(R.id.job_select_name).setSelected(false);

                popupWindow.dismiss();
            }
        });
        Button sureBtn=(Button)contentView.findViewById(R.id.job_select_bussiness_sure_bt);
        sureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        Button resetBtn=(Button)contentView.findViewById(R.id.job_select_bussiness_reset_bt);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }



    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public void resetBussinessData(){
        List data = new ArrayList();
        data.add("广州");
        data.add("天河区");
        data.add("白云区");
        jobAreaOptionAdapter.replaceData(data);
        DataUtils dataUtils=new DataUtils();
        jobLocationOptionAdapter.replaceData((List) dataUtils.getAreaOption().get("广州"));

        jobAreaOptionAdapter.initView();
        jobLocationOptionAdapter.initView();
        jobAreaOptionAdapter.notifyDataSetChanged();
        jobLocationOptionAdapter.notifyDataSetChanged();
    }
    public void resetUndergroundData(){
        List data = new ArrayList();
        data.add("广州");
        data.add("1号线");
        data.add("2号线");
        data.add("3号线");
        data.add("4号线");
        jobAreaOptionAdapter.replaceData(data);
        DataUtils dataUtils=new DataUtils();
        jobLocationOptionAdapter.replaceData((List) dataUtils.getAreaOption().get("广州"));

        jobAreaOptionAdapter.initView();
        jobLocationOptionAdapter.initView();
        jobAreaOptionAdapter.notifyDataSetChanged();
        jobLocationOptionAdapter.notifyDataSetChanged();
    }
}

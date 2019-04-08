package com.example.myapplication.main.view.activity;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.main.contruct.MainContract;
import com.example.myapplication.main.presenter.MainPresenter;
import com.example.myapplication.main.view.adapter.MainAdapter;
import com.example.myapplication.main.model.TabItem;
import com.example.myapplication.main.view.fragment.BussinessFragment;
import com.example.myapplication.main.view.fragment.JobFragment;
import com.example.myapplication.main.view.fragment.MessageFragment;
import com.example.myapplication.main.view.fragment.MineFragment;
import com.example.myapplication.utils.MessageDefineEvent;
import com.snowfish.cn.ganga.offline.helper.SFCommonSDKInterface;
import com.snowfish.cn.ganga.offline.helper.SFGameExitListener;
import com.snowfish.cn.ganga.offline.helper.SFOfflineInitListener;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    @BindView(R.id.main_tb)
    TabLayout mMainTb;
    @BindView(R.id.main_vp)
    ViewPager mContentVp;
    private MainContract.Presenter mPresenter;
    private ArrayList mFragment = new ArrayList<Fragment>();
    private ArrayList<TabItem> mTab = new ArrayList<TabItem>();
    private MainAdapter mMainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        EventBus.getDefault().register(this);
        mPresenter = new MainPresenter(this);
        mPresenter.initApp(this);
        initTab();
        initViewPager();
        SFCommonSDKInterface.onInit(this,new SFOfflineInitListener() {
            @Override
            public void onResponse(String tag, String value) {
                if(tag.equalsIgnoreCase("success")){
                    Log.d("ggg",value);
                    //初始化成功的回调
                }else if(tag.equalsIgnoreCase("fail")){
                    //初始化失败的回调，value：如果SDK返回了失败的原因，会给value赋
                    Log.d("ggg",value);
                }
            }});

    }

    private void initTab() {
        mTab.add(new TabItem("职位", R.drawable.job).setSelectIcon(R.drawable.job_select));
        mTab.add(new TabItem("公司", R.drawable.business).setSelectIcon(R.drawable.business_select));
        mTab.add(new TabItem("消息", R.drawable.message).setSelectIcon(R.drawable.message_select));
        mTab.add(new TabItem("我的", R.drawable.mine).setSelectIcon(R.drawable.mine_select));

    }

    public void initViewPager() {
        mFragment.add(new JobFragment());
        mFragment.add(new BussinessFragment());
        mFragment.add(new MessageFragment());
        mFragment.add(new MineFragment());
        mMainAdapter = new MainAdapter(getSupportFragmentManager(), this).setmList(mFragment).setmTab(mTab).setmTabLayout(mMainTb);
        mContentVp.setAdapter(mMainAdapter);
        mMainTb.setupWithViewPager(mContentVp);
        for (int i = 0; i < mMainTb.getTabCount(); i++) {
            TabLayout.Tab tab = mMainTb.getTabAt(i);
            tab.setCustomView(mMainAdapter.getTabView(i));
        }

        mMainTb.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getCustomView().findViewById(R.id.iv_icon).setSelected(true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshData(MessageDefineEvent messageDefineEvent) {
        if (messageDefineEvent.getMessage().equals("open_apk")) {

        }
    }

    protected void onDestroy(){
        super.onDestroy();
        SFCommonSDKInterface.onDestroy(this);
    }

    protected void onResume(){
        super.onResume();
        SFCommonSDKInterface.onResume(this);
    }

    protected void onPause(){
        super.onPause();
        SFCommonSDKInterface.onResume(this);
    }
}

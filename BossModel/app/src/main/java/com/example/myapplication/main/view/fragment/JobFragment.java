package com.example.myapplication.main.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.myapplication.R;
import com.example.myapplication.job.view.activity.JobDetailActivity;
import com.example.myapplication.main.view.adapter.JobMainAdapter;
import com.example.myapplication.main.view.adapter.JobTypeTbAdapter;
import com.example.myapplication.main.contruct.JobContract;
import com.example.myapplication.main.model.JobItem;
import com.example.myapplication.main.presenter.JobPresenter;
import com.example.myapplication.utils.DataUtils;
import com.example.myapplication.utils.MessageDefineEvent;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class JobFragment extends Fragment implements JobContract.View {
    @BindView(R.id.job_rv)
    RecyclerView mRecycleView;
    @BindView(R.id.job_tb)
    TabLayout mJobTb;
    @BindView(R.id.swiperereshlayout)
    SwipeRefreshLayout swiperereshlayout;

    private JobMainAdapter mAdapter;
    private List<JobItem> mListDate = new ArrayList<>();
    private JobContract.Presenter mPresent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.job_fragment, container, false);

        ButterKnife.bind(this, view);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        mPresent = new JobPresenter(this);
        swiperereshlayout.setRefreshing(true);
        initTab(view);
        initAdapter();
        initDownRefresh();

        return view;
    }


    public void initTab(View view) {
        DataUtils dataUtils =new DataUtils();
        JobTypeTbAdapter jobTbAdapter = new JobTypeTbAdapter().setTabLayout(mJobTb).setTabContent(dataUtils.getJobTab()).setContent(view);
        jobTbAdapter.setTabView();
        //这里是为了实现全部Tab不选中
        mJobTb.getTabAt(0).getCustomView().findViewById(R.id.job_select_name).setSelected(false);
        mJobTb.getTabAt(0).getCustomView().findViewById(R.id.job_select_icon).setSelected(false);
/*
        mJobTb.setTabMode(TabLayout.MODE_FIXED);         //设置分割线
        LinearLayout linearLayout = (LinearLayout) mJobTb.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(getContext(),
        R.drawable.divider)); //设置分割线的样式
*/
    }

    public void initAdapter() {
        mAdapter = new JobMainAdapter(R.layout.job_item, mListDate);
        mAdapter.setEnableLoadMore(true);
        View view = View.inflate(getContext(), R.layout.job_empty, null);
        mAdapter.setEmptyView(view);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
               Intent intent=new Intent(getContext(),JobDetailActivity.class);
               startActivity(intent);
            }
        });
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                    @Override
                    public void onLoadMoreRequested() {
                        mRecycleView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPresent.requestUploadMoreData();

                    }

                }, 1000);
            }
        }, mRecycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        mRecycleView.setLayoutManager(layoutManager);
        mRecycleView.setAdapter(mAdapter);

        //数据请求
        mPresent.requestRefreshData(0, 4);
    }

    public void initDownRefresh() {
        swiperereshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //设置2秒的时间来执行以下事件
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        mPresent.requestRefreshData(0, 4);
                    }
                }, 1000);
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshData(MessageDefineEvent messageDefineEvent) {
        if (messageDefineEvent.getMessage().equals("job_refresh")) {
            mAdapter.getData().clear();
            mAdapter.setNewData(messageDefineEvent.getData());
            mAdapter.notifyDataSetChanged();
            swiperereshlayout.setRefreshing(false);
        }
        if (messageDefineEvent.getMessage().equals("job_upload_more_refresh")) {
            mAdapter.addData(messageDefineEvent.getData());
            mAdapter.loadMoreComplete();
        }
        if (messageDefineEvent.getMessage().equals("job_init_background")) {

            TabLayout.Tab tab = (TabLayout.Tab) messageDefineEvent.getData().get(0);
            tab.getCustomView().findViewById(R.id.job_select_icon).setSelected(false);
            tab.getCustomView().findViewById(R.id.job_select_name).setSelected(false);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void loadMoreEnd() {
        mAdapter.loadMoreEnd();
    }
}

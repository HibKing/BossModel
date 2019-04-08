package com.example.myapplication.main.view.adapter;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.main.contruct.JobContract;
import com.example.myapplication.main.model.BussinnessContentItem;

import java.util.List;



public class JobBussinessOptionAdapter extends BaseMultiItemQuickAdapter<BussinnessContentItem, BaseViewHolder> {

    public JobBussinessOptionAdapter(List data) {
        super(data);
        addItemType(BussinnessContentItem.TITLE, R.layout.job_select_bussinness_title);
        addItemType(BussinnessContentItem.CONTENT, R.layout.job_select_bussinness_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, BussinnessContentItem item) {
        switch (helper.getItemViewType()) {
            case BussinnessContentItem.TITLE:
                helper.setText(R.id.job_select_bussiness_title_tv, item.getTitle());
                break;
            case BussinnessContentItem.CONTENT:
                LinearLayout totalLayout=(LinearLayout) helper.getView(R.id.job_select_bussiness_total);
                TableLayout tableLayout=(TableLayout) helper.getView(R.id.job_select_bussiness_list);

                    TableRow row = new TableRow(tableLayout.getContext());
                        for(String itemContent:item.getContent()) {
                            LinearLayout layout = (LinearLayout) View.inflate(tableLayout.getContext(), R.layout.job_select_bussinness_item, null);
                            TextView textView = (TextView) layout.findViewById(R.id.job_select_bussiness_item_tv);
                            textView.setText(itemContent);
                            row.addView(layout);
                        }
                            tableLayout.addView(row);
                        break;

        }
    }

}

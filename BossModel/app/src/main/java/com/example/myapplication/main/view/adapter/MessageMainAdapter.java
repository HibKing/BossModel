package com.example.myapplication.main.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.main.model.MessageUserItem;

import java.util.List;

public class MessageMainAdapter extends BaseQuickAdapter<MessageUserItem, BaseViewHolder> {
    public MessageMainAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageUserItem item) {
        helper.setText(R.id.username, item.getUsername());
        helper.setText(R.id.last_message, item.getLastMessage());
        if(!item.getTime().isEmpty()){
            helper.setText(R.id.message_time,item.getTime());
        }
    }


}

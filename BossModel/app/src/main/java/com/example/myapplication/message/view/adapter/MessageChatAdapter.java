package com.example.myapplication.message.view.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.message.model.MessageItem;

import java.util.List;

public class MessageChatAdapter extends BaseMultiItemQuickAdapter<MessageItem, BaseViewHolder> {
    public MessageChatAdapter(List data) {
        super(data);
        addItemType(MessageItem.MINE, R.layout.message_to_user);
        addItemType(MessageItem.OTHER, R.layout.message_from_user);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageItem item) {
        switch (helper.getItemViewType()) {
            case MessageItem.MINE:
                helper.setText(R.id.to_user,item.getMessage());
                break;
            case MessageItem.OTHER:
                helper.setText(R.id.from_user,item.getMessage());
                break;
        }
    }

}

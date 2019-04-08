package com.example.myapplication.main.view.adapter;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.main.model.BussinessItem;

import java.util.List;

public class BussinessMainAdapter  extends BaseQuickAdapter<BussinessItem, BaseViewHolder> {
    public BussinessMainAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BussinessItem item) {
        Glide.with(mContext).load(item.getIconUrl()).into( (ImageView) helper.getView(R.id.bussiness_icon));
        helper.setText(R.id.bussiness_name, item.getName());
        helper.setText(R.id.bussiness_location,item.getCity()+" "+item.getArea()+" "+item.getLocation());
        helper.setText(R.id.bussiness_scale,item.getScale());
        helper.setText(R.id.bussiness_people_number,item.getPeaple());
        helper.setText(R.id.bussiness_type,item.getType());

    }
}
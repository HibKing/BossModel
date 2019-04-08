package com.example.myapplication.main.service;

import android.util.Log;

import com.example.myapplication.main.model.BussinessItem;
import com.example.myapplication.utils.MessageDefineEvent;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BussinessFactory {
    public void requestRefreshData( ){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://xiaoguokeng.bj01.bdysite.com/")
                .build();
        BussinessService bussinessProtocol = retrofit.create(BussinessService.class);
        Call<ResponseBody> call = bussinessProtocol.getBussinessListInfo(1);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    parseJSONWithJSONObject(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    private void parseJSONWithJSONObject(String JsonData) {
        List<BussinessItem> mData=new ArrayList<>();

        try
        {
            JSONArray jsonArray = new JSONArray(JsonData);

            for (int i=0; i < jsonArray.length(); i++)    {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if(jsonObject.getString("check").equals("1")) {
                    mData.add(new BussinessItem(jsonObject.getString("icon_url"),jsonObject.getString("name"),
                            jsonObject.getString("city"),jsonObject.getString("area"),
                            jsonObject.getString("location"),jsonObject.getString("scale"),
                            jsonObject.getString("people"),jsonObject.getString("type")));

                }else {
                    Log.d("haha", "dome");
                }
            }
            EventBus.getDefault().post(new MessageDefineEvent("refreshData",mData));

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

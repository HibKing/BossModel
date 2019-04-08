package com.example.myapplication.job.service;

import android.util.Log;

import com.example.myapplication.job.model.JobDetailItem;
import com.example.myapplication.main.model.JobItem;
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

public class JobDetailFactory {
    public void requestRefreshData(int jid){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://xiaoguokeng.bj01.bdysite.com/")
                .build();
        JobDetailService jobProtocol = retrofit.create(JobDetailService.class);
        Call<ResponseBody> call = jobProtocol.getJobListInfo(jid);

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
        List<JobItem> mData=new ArrayList<>();
        try
        {
            JSONArray jsonArray = new JSONArray(JsonData);

            for (int i=0; i < jsonArray.length(); i++)    {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if(jsonObject.getString("check").equals("1")) {
                 //   mData.add(new JobItem("","",""));
                }else {

                }
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}

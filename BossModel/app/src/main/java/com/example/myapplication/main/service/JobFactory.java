package com.example.myapplication.main.service;

import android.util.Log;

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

public class JobFactory {
    private int currentNumber=0;
    public void requestRefreshData(int st,int ed){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://xiaoguokeng.bj01.bdysite.com/")
                .build();
        JobService jobProtocol = retrofit.create(JobService.class);
        Call<ResponseBody> call = jobProtocol.getJobListInfo(st,ed);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    currentNumber=0;
                    parseJSONWithJSONObject(response.body().string(),1);
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
    public void requestUploadMoreData(int st,int ed){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://xiaoguokeng.bj01.bdysite.com/")
                .build();
        JobService jobProtocol = retrofit.create(JobService.class);
        Call<ResponseBody> call = jobProtocol.getJobListInfo(st,ed);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    parseJSONWithJSONObject(response.body().string(),2);
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

    private void parseJSONWithJSONObject(String JsonData,int type) {
        List<JobItem> mData=new ArrayList<>();
        try
        {
            JSONArray jsonArray = new JSONArray(JsonData);

            for (int i=0; i < jsonArray.length(); i++)    {
                currentNumber++;
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if(jsonObject.getString("check").equals("1")) {
                    mData.add(new JobItem(jsonObject.getString("jobname"), jsonObject.getString("jobsalary")
                            , jsonObject.getString("jobbussiness"), jsonObject.getString("jobcity")
                            , jsonObject.getString("jobarea"), jsonObject.getString("joblocation")
                            , jsonObject.getString("jobexperience"), jsonObject.getString("jobeducation")
                            , jsonObject.getString("jobbossname"), jsonObject.getString("jobbossicon")
                            , jsonObject.getString("jobbossjob")));
                }else {
                    Log.d("ggra",jsonObject.getString("st"));
                    Log.d("ggra",jsonObject.getString("ed"));
                    Log.d("ggra",jsonObject.getString("sql"));
                }
            }

            if(type==1) {
                EventBus.getDefault().post(new MessageDefineEvent("job_refresh", mData));
            }else{
                EventBus.getDefault().post(new MessageDefineEvent("job_upload_more_refresh", mData));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public int getCurrentNumber(){
        return currentNumber;
    }
    public void setCurrentNumber(int number){
        currentNumber=number;
    }
}

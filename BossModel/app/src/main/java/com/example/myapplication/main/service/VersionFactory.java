package com.example.myapplication.main.service;

import com.example.myapplication.main.model.JobItem;

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

public class VersionFactory {
    public void requestVersionData(int version){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://xiaoguokeng.bj01.bdysite.com/")
                .build();
        VersionService versionProtocol = retrofit.create(VersionService.class);
        Call<ResponseBody> call = versionProtocol.getVersionInfo(version);

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

package com.example.myapplication.job.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface JobDetailService {
    @FormUrlEncoded
    @POST("showtime/select_job.php")
    Call<ResponseBody> getJobListInfo(@Field("jid") int jid);
}

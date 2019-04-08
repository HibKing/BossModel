package com.example.myapplication.main.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface VersionService {
    @FormUrlEncoded
    @POST("showtime/select_version.php")
    Call<ResponseBody> getVersionInfo(@Field("version") int version);
}

package com.example.myapplication.download.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface DownloadService {
    /**
     * 下载文件用
     * @param fileUrl
     * @return
     */
    @Streaming //添加这个注解用来下载大文件
    @GET()
    Call<ResponseBody> downloadFileUrl(@Url String fileUrl);
}

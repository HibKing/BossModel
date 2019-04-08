package com.example.myapplication.download.utils;

import android.util.Log;

import com.example.myapplication.download.service.DownloadService;
import com.example.myapplication.utils.MessageDefineEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.NumberFormat;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DownloadUtils {
    public static void downloadFile(final String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://xiaoguokeng.bj01.bdysite.com/")
                .build();
        DownloadService mDownloadService = retrofit.create(DownloadService.class);
        Call<ResponseBody> callback = mDownloadService.downloadFileUrl(url);
        callback.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ResponseBody body = response.body();
                        File file = new File("/sdcard" + File.separator + "boss.apk");
                        if (file.exists()) {
                            file.delete();
                        }
                        InputStream inputStream = null;
                        OutputStream outputStream = null;

                        byte[] fileReader = new byte[4096];

                        long fileSize = body.contentLength();
                        long fileSizeDownloaded = 0;

                        try {
                            inputStream = body.byteStream();

                            outputStream = new FileOutputStream(file);

                            while (true) {
                                int read = inputStream.read(fileReader);
                                if (read == -1) {
                                    break;
                                }

                                outputStream.write(fileReader, 0, read);
                                fileSizeDownloaded += read;
                                NumberFormat numberFormat = NumberFormat.getInstance();
                                numberFormat.setMaximumFractionDigits(2);
                                String result = numberFormat.format((float) fileSizeDownloaded / (float) fileSize * 100);
                                if (result.equals("100")) {
                                    Log.d("ggra", "complete");
                                    EventBus.getDefault().post(new MessageDefineEvent("open_apk"));
                                }

                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }


}








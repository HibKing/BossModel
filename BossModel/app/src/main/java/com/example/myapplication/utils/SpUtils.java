package com.example.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.myapplication.account.UserManager;

import static android.content.Context.MODE_PRIVATE;

public class SpUtils {
    private static volatile SpUtils sInstance;
    private Context mContent;
    public static SpUtils getInstance( ) {
        if (sInstance == null) {
            synchronized (UserManager.class) {
                if (sInstance == null) {
                    sInstance = new SpUtils();
                }
            }
        }
        return sInstance;
    }


    public void putString(String key,String value){
        SharedPreferences sharedPreferences=mContent.getSharedPreferences("boss",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(key,value);
        editor.commit();
    }




    public String getString(String key){
        SharedPreferences sharedPreferences=mContent.getSharedPreferences("boss",MODE_PRIVATE);
        String value=sharedPreferences.getString(key,"");
        return value;
    }

    public Context getContent() {
        return mContent;
    }

    public void setContent(Context mContent) {
        this.mContent = mContent;
    }
}

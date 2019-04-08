package com.example.myapplication.main.contruct;

import android.content.Context;

public interface JobContract {
    interface View{
        void loadMoreEnd();
    }

    interface Presenter{
        void requestRefreshData(int st,int ed);
        void requestUploadMoreData();
    }

}

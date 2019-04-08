package com.example.myapplication.main.contruct;

import java.util.List;

public interface BussinessContract {
    interface View{
        void refreshData(List data);
    }
    interface Presenter{
        void requestData();
    }
}

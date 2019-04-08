package com.example.myapplication.main.presenter;

import com.example.myapplication.main.contruct.BussinessContract;
import com.example.myapplication.main.service.BussinessFactory;

public class BussinessPresenter implements BussinessContract.Presenter {
    private BussinessContract.View mView;
    @Override
    public void requestData() {
        BussinessFactory bussinessFactory=new BussinessFactory();
        bussinessFactory.requestRefreshData();
    }

    public BussinessPresenter(BussinessContract.View view){
        mView=view;
    }


}

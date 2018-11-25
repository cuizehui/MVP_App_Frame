package com.nela.mvpdemo;

import android.content.Context;

import com.nela.mvpdemo.data.AppData;

public class AppPresenter implements AppContract.Presenter {

    private boolean mBeginCloudBusiness = false;

    public AppPresenter(Context context) {
        AppData.init(context);
    }

    @Override
    public void start() {

    }


    @Override
    public void stop() {

    }

    @Override
    public void onDestroy() {

    }
}

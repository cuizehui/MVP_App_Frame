package com.nela.mvpdemo;

import android.app.Application;
import android.content.Context;

public class App extends Application {

    public static Context sContext;

    private AppPresenter mAppPresenter;

    @Override
    public void onCreate() {
        super.onCreate();

        sContext = this;

        mAppPresenter = new AppPresenter(this);
        mAppPresenter.start();
    }

}

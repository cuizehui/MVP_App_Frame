package com.nela.mvpdemo.presenter;


import android.content.Context;

import com.nela.mvpdemo.base.BasePresenterImpl;
import com.nela.mvpdemo.contract.MainContract;
import com.nela.mvpdemo.ui.HomeActivity;

public class MainPresenter extends BasePresenterImpl<MainContract.View> implements MainContract.Presenter {
    MainContract.View mView;

    public MainPresenter(MainContract.View view) {
        super(view);
        mView = view;
    }

    @Override
    public void start() {
        HomeActivity.launch((Context) mView);
    }

    @Override
    public void stop() {

    }

    @Override
    public void onDestroy() {

    }

}

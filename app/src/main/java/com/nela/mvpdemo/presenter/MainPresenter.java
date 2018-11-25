package com.nela.mvpdemo.presenter;


import com.nela.mvpdemo.base.BasePresenterImpl;
import com.nela.mvpdemo.contract.MainContract;

public class MainPresenter extends BasePresenterImpl<MainContract.View> implements MainContract.Presenter{

    public MainPresenter(MainContract.View view) {
        super(view);
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

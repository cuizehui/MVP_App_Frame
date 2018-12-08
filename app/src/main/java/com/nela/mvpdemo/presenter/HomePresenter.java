package com.nela.mvpdemo.presenter;


import com.nela.mvpdemo.contract.HomeContract;

public class HomePresenter implements HomeContract.Present {
    HomeContract.View mView;

    public HomePresenter(HomeContract.View view) {
        super();
        mView = view;
    }

    @Override
    public void start() {
        mView.showMainFragment();
    }

    @Override
    public void stop() {

    }

    @Override
    public void onDestroy() {

    }
}

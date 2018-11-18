package com.nela.mvpdemo.presenter;


import com.nela.mvpdemo.contract.MainContract;

public class MainPresenter implements MainContract.Presenter {
    MainContract.View mView;

    public MainPresenter(MainContract.View view){
       mView =view;
    }

}

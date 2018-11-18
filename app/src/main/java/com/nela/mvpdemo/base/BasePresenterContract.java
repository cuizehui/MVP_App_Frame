package com.nela.mvpdemo.base;

public class BasePresenterContract<T extends BaseView> {
    public T mView;

    public BasePresenterContract(T view) {
        mView = view;
    }
}

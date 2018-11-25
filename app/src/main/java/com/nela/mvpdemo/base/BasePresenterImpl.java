package com.nela.mvpdemo.base;

public class BasePresenterImpl<T extends BaseView>  {
    public T mView;

    public BasePresenterImpl(T view) {
        mView = view;
    }
}

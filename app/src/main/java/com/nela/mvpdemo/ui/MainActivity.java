package com.nela.mvpdemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.nela.mvpdemo.R;
import com.nela.mvpdemo.base.BaseActivity;
import com.nela.mvpdemo.contract.MainContract;
import com.nela.mvpdemo.presenter.MainPresenter;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.start();
    }

    @Override
    protected MainContract.Presenter setPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        initToolBar(mToolbar, false, R.string.toolbar_main);
    }

    @Override
    public void showInfo(int info) {
    }
}

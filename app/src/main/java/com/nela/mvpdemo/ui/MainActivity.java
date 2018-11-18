package com.nela.mvpdemo.ui;

import android.os.Bundle;

import com.nela.mvpdemo.R;
import com.nela.mvpdemo.base.BaseActivity;
import com.nela.mvpdemo.contract.MainContract;
import com.nela.mvpdemo.presenter.MainPresenter;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @Override
    protected void setPresenter(MainContract.Presenter presenter) {
        mPresenter = new MainPresenter(this);
    }

    @Override
    protected int attachLayoutRes() {
        return 0;
    }

    @Override
    protected void initViews() {

    }


    @Override
    public void showInfo(String info) {

    }


}

package com.nela.mvpdemo.contract;

import com.nela.mvpdemo.base.BasePresenter;
import com.nela.mvpdemo.base.BaseView;

public interface HomeContract {
    interface View extends BaseView {
        /**
         * 显示主页
         */
        void showMainFragment();


        /**
         * 显示通讯录界面
         */
        void showContactsFragment();

    }

    interface Present extends BasePresenter {

    }
}

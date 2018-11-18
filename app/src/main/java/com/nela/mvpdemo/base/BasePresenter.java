package com.nela.mvpdemo.base;


public interface BasePresenter {

    /**
     * 启动 Presenter
     */
    void start();

    /**
     * 停止 Presenter
     */
    void stop();

    void onDestroy();

}

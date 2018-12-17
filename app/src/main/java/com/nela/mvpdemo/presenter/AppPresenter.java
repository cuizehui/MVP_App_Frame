package com.nela.mvpdemo.presenter;

import android.Manifest;
import android.content.Context;

import com.nela.mvpdemo.contract.AppContract;
import com.nela.mvpdemo.data.AppData;
import com.nela.mvpdemo.data.event.PermissionEvent;
import com.nela.mvpdemo.utils.ContactsSync;
import com.nela.mvpdemo.utils.CrashHandler;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class AppPresenter implements AppContract.Presenter {


    public AppPresenter(Context context) {
        AppData.init(context);
    }

    private void beginBusiness() {
        ContactsSync.init(AppData.getContext());
    }

    @Override
    public void start() {

        EventBus.getDefault().register(this);
        beginBusiness();
    }


    //同步联系人
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPermissionEvent(PermissionEvent event) {
        if (event.permissions.contains(Manifest.permission.READ_CONTACTS)) {
            ContactsSync.sync();
        }
    }

    @Override
    public void stop() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroy() {

    }
}

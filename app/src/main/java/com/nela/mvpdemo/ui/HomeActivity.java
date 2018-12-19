package com.nela.mvpdemo.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.nela.mvpdemo.R;
import com.nela.mvpdemo.base.BaseActivity;
import com.nela.mvpdemo.contract.HomeContract;
import com.nela.mvpdemo.presenter.HomePresenter;
import com.nela.mvpdemo.ui.contracts.ContactsFragment;
import com.nela.mvpdemo.utils.PermissionHelper;

import butterknife.BindView;
import cn.nela.tools.ActivityTool;

public class HomeActivity extends BaseActivity<HomeContract.Present> implements HomeContract.View {

    @BindView(R.id.bottom_navigation)
    BottomNavigationView mBottomNavigation;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private ContactsFragment mContractsFragment;

    public static void launch(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected HomeContract.Present setPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PermissionHelper.requestPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA, Manifest.permission.READ_CONTACTS);
        mPresenter.start();
    }

    @Override
    protected void initViews() {
        mBottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mBottomNavigation.setOnNavigationItemReselectedListener(mOnNavigationItemReselectedListener);
        initToolBar(mToolbar, false, R.string.home_toolbar_main);
        mContractsFragment = ContactsFragment.newInstance();
        ActivityTool.addFragmentToActivity(getSupportFragmentManager(), mContractsFragment, R.id.fragment_content);
    }

    @Override
    public void showInfo(int info) {

    }

    @Override
    public void showMainFragment() {
        ActivityTool.hideFragmentFromActivity(getSupportFragmentManager(), mContractsFragment);
    }

    private void initBaseToolbar() {
        mToolbar.removeAllViews();
        getLayoutInflater().inflate(R.layout.app_bar_main, mToolbar);
    }

    private void initContactsToolbar() {
        mToolbar.removeAllViews();
        getLayoutInflater().inflate(R.layout.app_bar_contracts, mToolbar, true);

        final Button replayType = findViewById(R.id.message_replay_type);

        replayType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (replayType.isSelected()) {
                    replayType.setSelected(false);
                } else {
                    replayType.setSelected(true);
                }
                mContractsFragment.changeMessageReplayType();
            }
        });
    }


    @Override
    public void showContactsFragment() {
        ActivityTool.showFragmentInActivity(getSupportFragmentManager(), mContractsFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (mBottomNavigation.getSelectedItemId() == R.id.navigation_main) {

        } else if (mBottomNavigation.getSelectedItemId() == R.id.navigation_contact) {

        }
        return true;
    }


    /************ 底部导航栏监听 ************/

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_main:
                    initBaseToolbar();
                    initToolBar(mToolbar, false, R.string.home_toolbar_main);
                    showMainFragment();
                    break;
                case R.id.navigation_contact:
                    initContactsToolbar();
                    initToolBar(mToolbar, false, R.string.home_toolbar_contact);
                    showContactsFragment();
                    break;

                default:
                    break;
            }
            invalidateOptionsMenu();
            return true;
        }
    };

    private BottomNavigationView.OnNavigationItemReselectedListener mOnNavigationItemReselectedListener = new BottomNavigationView.OnNavigationItemReselectedListener() {
        @Override
        public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.navigation_main:
                    break;
            }
        }
    };
}
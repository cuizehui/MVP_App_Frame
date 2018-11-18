package com.nela.mvpdemo.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public abstract class BaseFragment<T extends BasePresenter> extends Fragment {

    protected T mPresenter;

    private View mRootView;
    protected Context mContext;

    //Activity 和fragment 通用回调
    public interface BaseFragmentCallBack {
        /**
         * 完成 fragment
         *
         * @param tagFragment
         * @param object      需要传递的数据
         */
        void onFinishFragment(String tagFragment, Object object);
    }

    private BaseFragmentCallBack mCallback;

    protected void finishFragment(String targetFragment, Object o) {
        mCallback.onFinishFragment(targetFragment, o);
    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @LayoutRes
    protected abstract int attachLayoutRes();

    protected abstract T setPresenter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(attachLayoutRes(), null);
            ButterKnife.bind(this, mRootView);
            mPresenter = setPresenter();
            initViews();
        }
        return mRootView;
    }

    /**
     * 初始化视图控件
     */
    protected abstract void initViews();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (mCallback != null) {
            mCallback = (BaseFragmentCallBack) context;
        }
    }
}

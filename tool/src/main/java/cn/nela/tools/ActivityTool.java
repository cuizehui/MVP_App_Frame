package cn.nela.tools;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import static android.support.v4.util.Preconditions.checkNotNull;

public class ActivityTool {

    /**
     * 加入 fragment
     *
     * @param fragmentManager
     * @param fragment
     * @param frameId
     */
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    public static void replaceFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                                 @NonNull Fragment fragment, int frameId) {
        if (fragment == null)
            return;
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //
        transaction.replace(frameId, fragment);
        transaction.commit();
    }

    @SuppressLint("RestrictedApi")
    public static void removeFragmentFromActivity(@NonNull FragmentManager fragmentManager,
                                                  @NonNull Fragment fragment) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.remove(fragment);
        transaction.commitAllowingStateLoss();
    }

    @SuppressLint("RestrictedApi")
    public static void showFragmentInActivity(@NonNull FragmentManager fragmentManager,
                                              @NonNull Fragment fragment) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.show(fragment);
        transaction.commitAllowingStateLoss();
    }

    @SuppressLint("RestrictedApi")
    public static void hideFragmentFromActivity(@NonNull FragmentManager fragmentManager,
                                                @NonNull Fragment fragment) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.hide(fragment);
        transaction.commitAllowingStateLoss();
    }


    public static void showFragmentToActivity(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, boolean show) {
        if (fragment == null) {
            return;
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (show) {
            transaction.show(fragment);
        } else {
            transaction.hide(fragment);
        }
        transaction.commit();
    }


    /**
     * 控制系统UI例如 Navigation Bar,Status Bar 显示
     *
     * @param activity
     * @param show
     */
    public static void showSystemUI(Activity activity, boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (show) {
                activity.getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            } else {
                activity.getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                                //               | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                                | View.SYSTEM_UI_FLAG_IMMERSIVE
                );
            }
        }
    }

    /**
     * 设置全屏属性
     *
     * @param activity
     * @param fullScreen
     */
    public static void setActivityFullScreen(Activity activity, boolean fullScreen) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams attrs = window.getAttributes();
        if (fullScreen) {
            attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            window.setAttributes(attrs);
        } else {
            attrs.flags &= ~(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            window.setAttributes(attrs);
        }
    }

    /**
     * 设置类似通话界面的Attr
     *
     * @param window
     */
    public static void setCallLikeWindowAttr(Window window) {
        WindowManager.LayoutParams attrs = window.getAttributes();
        attrs.flags |= WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
        window.setAttributes(attrs);
    }

    public static void showAlertDialog(Context context, String titleRes, String messageRes, String positiveTextRes, DialogInterface.OnClickListener positiveClick, String negativeTextRes, DialogInterface.OnClickListener negativeClick) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (!TextUtils.isEmpty(titleRes) && !TextUtils.isEmpty(titleRes)) {
            builder.setTitle(titleRes);
        }
        if (!TextUtils.isEmpty(messageRes) && !TextUtils.isEmpty(messageRes)) {
            builder.setMessage(messageRes);
        }
        if (!TextUtils.isEmpty(positiveTextRes) && !TextUtils.isEmpty(positiveTextRes)) {
            builder.setPositiveButton(positiveTextRes, positiveClick);
        }
        if (!TextUtils.isEmpty(negativeTextRes) && !TextUtils.isEmpty(negativeTextRes)) {
            builder.setNegativeButton(negativeTextRes, negativeClick);
        }
        builder.create().show();
    }
}

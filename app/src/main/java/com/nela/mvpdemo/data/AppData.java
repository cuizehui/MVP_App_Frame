package com.nela.mvpdemo.data;

import android.content.Context;
import android.os.Handler;


public class AppData {

    private static Context sContext;
    private static Handler sHandler;

    /**
     * 进程已启动就调用
     *
     * @param context
     */
    public static void init(Context context) {
        sContext = context.getApplicationContext();
        sHandler = new Handler(sContext.getMainLooper());
    }

    /**
     * 获得上下文
     *
     * @return
     */
    public static Context getContext() {
        return sContext;
    }

    /**
     * 在主线程延迟执行
     *
     * @param runnable
     */
    public static void postRun(Runnable runnable) {
        sHandler.post(runnable);
    }

    /**
     * 在主线程延迟执行
     *
     * @param runnable
     * @param delayMillis
     */
    public static void postDelayRun(Runnable runnable, long delayMillis) {
        sHandler.postDelayed(runnable, delayMillis);
    }
}

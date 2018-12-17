package com.nela.mvpdemo.data;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SettingData {

    private final static String LAST_LOGIN_USERID = "last_login_userid";

    private static SharedPreferences sSharedPreferences;

    private static void init() {
        if (sSharedPreferences == null) {
            sSharedPreferences = PreferenceManager.getDefaultSharedPreferences(AppData.getContext());
        }
    }

    /**
     * 最近一次登陆的UserId
     *
     * @return
     */
    public static String getLastLoginUserId() {
        init();
        return sSharedPreferences.getString(LAST_LOGIN_USERID, "");
    }

    /**
     * 设置最近一次登陆的UserId
     *
     * @param userId
     */
    public static void setLastLoginUserId(String userId) {
        init();
        sSharedPreferences.edit().putString(LAST_LOGIN_USERID, userId).commit();
    }
}

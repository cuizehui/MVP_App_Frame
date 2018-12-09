package com.nela.mvpdemo.data.event;

import android.support.annotation.IntDef;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class CommonEvent {

    @IntDef({
            EVENT_LOGIN, EVENT_LOGOUT, EVENT_CLIENT_STATE_CHANGE
            , EVENT_NET_CHANGE})
    @Retention(RetentionPolicy.SOURCE)
    @interface EventType {
    }

    /**
     * 登陆结果事件
     */
    public static final int EVENT_LOGIN = 0;
    /**
     * 登出事件
     */
    public static final int EVENT_LOGOUT = 1;
    /**
     * 登陆状态变化事件
     */
    public static final int EVENT_CLIENT_STATE_CHANGE = 2;
    /**
     * 网络变化事件
     */
    public static final int EVENT_NET_CHANGE = 10;

    public class Login {
        public boolean result;
        public int reason;
    }

    /**
     * 登出信息
     */
    public class Logout {
        public int reason;
    }

    /**
     * 登陆状态变化
     */
    public class ClientStateChange {
        public int state;
        public int oldState;
    }

    /**
     * 网络变化
     */
    public class NetChange {
        public int newNetType;
        public int oldNetType;
    }


    /**
     * 事件类型
     */
    @EventType
    public int type;
    /**
     * 登陆
     */
    public Login login;
    /**
     * 登出
     */
    public Logout logout;
    /**
     * 登陆状态变化
     */
    public ClientStateChange clientStateChange;
    /**
     * 网络变化
     */
    public NetChange netChange;

    /**
     * 构造登陆事件
     *
     * @param result
     * @param reason
     * @return
     */
    public static CommonEvent buildLogin(boolean result, int reason) {
        CommonEvent event = new CommonEvent(CommonEvent.EVENT_LOGIN);
        event.login = event.new Login();
        event.login.result = result;
        event.login.reason = reason;
        return event;
    }

    /**
     * 构造登出事件
     *
     * @param reason
     * @return
     */
    public static CommonEvent buildLogout(int reason) {
        CommonEvent event = new CommonEvent(CommonEvent.EVENT_LOGOUT);
        event.logout = event.new Logout();
        event.logout.reason = reason;
        return event;
    }

    /**
     * 构造登陆状态变化事件
     *
     * @param state
     * @param oldState
     * @return
     */
    public static CommonEvent buildClientStateChange(int state, int oldState) {
        CommonEvent event = new CommonEvent(CommonEvent.EVENT_CLIENT_STATE_CHANGE);
        event.clientStateChange = event.new ClientStateChange();
        event.clientStateChange.state = state;
        event.clientStateChange.oldState = state;
        return event;
    }

    /**
     * 构造网络变化事件
     *
     * @param newNetType
     * @param oldNetType
     * @return
     */
    public static CommonEvent buildNetChange(int newNetType, int oldNetType) {
        CommonEvent event = new CommonEvent(CommonEvent.EVENT_NET_CHANGE);
        event.netChange = event.new NetChange();
        event.netChange.newNetType = newNetType;
        event.netChange.oldNetType = oldNetType;
        return event;
    }


    /**
     * 构造函数
     *
     * @param type 事件
     */
    public CommonEvent(@EventType int type) {
        this.type = type;
    }


}


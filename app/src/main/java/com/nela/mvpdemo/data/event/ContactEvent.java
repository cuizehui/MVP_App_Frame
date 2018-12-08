package com.nela.mvpdemo.data.event;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ContactEvent {

    @IntDef({
            EVENT_READ_ANDROID_CONTACTS
    })
    @Retention(RetentionPolicy.SOURCE)
    @interface EventType {
    }

    /**
     * 读取联系人
     */
    public static final int EVENT_READ_ANDROID_CONTACTS = 0;


    @EventType
    public int type;

    /**
     * 构造函数
     *
     * @param type 事件
     */
    public ContactEvent(@ContactEvent.EventType int type) {
        this.type = type;
    }
}

package com.nela.mvpdemo.data.event;

import android.support.annotation.IntDef;


import com.nela.mvpdemo.data.search.ContactsSearchManager;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class SearchEvent {
    @IntDef({
            EVENT_SEARCH_ADDRESS_BOOK_CONTACTS, EVENT_SEARCH_DIAL_CONTACTS
    })
    @Retention(RetentionPolicy.SOURCE)

    public @interface EventType {
    }

    /**
     * 查找联系人
     */

    public static final int EVENT_SEARCH_ADDRESS_BOOK_CONTACTS = 0;

    public static final int EVENT_SEARCH_DIAL_CONTACTS = 1;

    @EventType
    public int type;

    ContactsSearchManager.SearchAsyncTask.SearchContactsWrapper mSearchContactsWrapper;

    /**
     * 构造函数
     *
     * @param type                  事件
     * @param searchContactsWrapper
     */
    public SearchEvent(@EventType int type, ContactsSearchManager.SearchAsyncTask.SearchContactsWrapper searchContactsWrapper) {
        this.type = type;
        this.mSearchContactsWrapper = searchContactsWrapper;
    }

    public ContactsSearchManager.SearchAsyncTask.SearchContactsWrapper getSearchContactsWrapper() {
        return mSearchContactsWrapper;
    }
}

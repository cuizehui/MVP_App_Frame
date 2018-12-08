package com.nela.mvpdemo.data.search;

import android.os.AsyncTask;


import com.nela.mvpdemo.data.event.SearchEvent;
import com.nela.mvpdemo.utils.ContactsSync;

public class SearchAsycTaskManager {

    private static SearchAsycTaskManager mSearchAsycTaskManager;

    public static SearchAsycTaskManager getInstance() {
        if (mSearchAsycTaskManager != null) {
            return mSearchAsycTaskManager;
        }
        mSearchAsycTaskManager = new SearchAsycTaskManager();
        return mSearchAsycTaskManager;
    }

    private SearchAsycTaskManager() {
    }

    private String mSearchKey = "";
    private SearchAsyncTask mSearchTask;

    public void startSearch(String searchKey, ContactsSync.ContactsWrapper contactsWrapper, @SearchEvent.EventType int from) {
        if (mSearchKey != searchKey) {
            mSearchKey = searchKey;
            if (mSearchTask != null) {
                if (mSearchTask.getStatus() == AsyncTask.Status.RUNNING) {
                    mSearchTask.cancel(true);
                }
            }
            mSearchTask = new SearchAsyncTask(contactsWrapper, from);
            mSearchTask.execute(searchKey);
        }
    }
}

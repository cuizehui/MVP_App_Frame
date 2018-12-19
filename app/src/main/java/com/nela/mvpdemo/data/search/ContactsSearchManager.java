package com.nela.mvpdemo.data.search;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.nela.mvpdemo.data.event.SearchEvent;
import com.nela.mvpdemo.utils.ContactsSync;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import cn.nela.tools.ContactTool;
import cn.nela.tools.NumberTool;

public class ContactsSearchManager {

    private static ContactsSearchManager mContactsSearchManager;

    public static ContactsSearchManager getInstance() {
        if (mContactsSearchManager != null) {
            return mContactsSearchManager;
        }
        mContactsSearchManager = new ContactsSearchManager();
        return mContactsSearchManager;
    }

    private ContactsSearchManager() {
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


    public class SearchAsyncTask extends AsyncTask<String, Void, SearchAsyncTask.SearchContactsWrapper> {

        private int mFrom;

        public class SearchContactsWrapper {
            public String searchKey;
            public List<ContactTool.AndroidContact> searchResultList = new ArrayList<>();
            public List<String> searchResultSections = new ArrayList<>();
            public List<Integer> searchResultSectionPos = new ArrayList<>();
        }

        ContactsSync.ContactsWrapper mContactsWrapper;

        public SearchAsyncTask(ContactsSync.ContactsWrapper contactsWrapper, @SearchEvent.EventType int from) {
            super();
            mFrom = from;
            mContactsWrapper = contactsWrapper;
        }

        @Override
        protected SearchAsyncTask.SearchContactsWrapper doInBackground(String... searchKey) {
            SearchAsyncTask.SearchContactsWrapper mSearchContactsWrapper = new SearchAsyncTask.SearchContactsWrapper();
            mSearchContactsWrapper.searchKey = searchKey[0];
            String lastSection = null;
            for (int i = 0; i < mContactsWrapper.contacts.size(); i++) {
                if (isCancelled()) {
                    break;
                }
                ContactTool.AndroidContact contact = mContactsWrapper.contacts.get(i);
                if (contact.phone.contains(searchKey[0]) || contact.name.contains(searchKey[0])) {
                    mSearchContactsWrapper.searchResultList.add(contact);
                }
                contact.phone = NumberTool.formatPhoneWithDefaultCountryPrefix(contact.phone);
                //首字母
                String section = contact.name.substring(0, 1).toUpperCase();
                if (section.charAt(0) >= 'A' && section.charAt(0) <= 'Z') {

                } else {
                    section = "#";
                }
                if (!TextUtils.equals(lastSection, section)) {
                    mSearchContactsWrapper.searchResultSections.add(section.toUpperCase());
                    mSearchContactsWrapper.searchResultSectionPos.add(i);
                    lastSection = section;
                }
            }
            return mSearchContactsWrapper;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(SearchAsyncTask.SearchContactsWrapper searchContactsWrapper) {
            super.onPostExecute(searchContactsWrapper);
            if (searchContactsWrapper != null) {
                EventBus.getDefault().post(new SearchEvent(mFrom, searchContactsWrapper));
            }
        }

        @Override
        protected void onCancelled() {
            mContactsWrapper = null;
            super.onCancelled();
        }
    }

}

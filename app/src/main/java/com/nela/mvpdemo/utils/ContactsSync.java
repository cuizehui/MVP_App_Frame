package com.nela.mvpdemo.utils;

import android.Manifest;
import android.content.Context;
import android.database.ContentObserver;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.text.TextUtils;

import com.nela.mvpdemo.data.event.ContactEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import cn.nela.tools.AppTool;
import cn.nela.tools.ContactTool;
import cn.nela.tools.NumberTool;

public class ContactsSync {

    public static class ContactsWrapper {
        public List<ContactTool.AndroidContact> contacts = new ArrayList<>();
        public List<String> sections = new ArrayList<>();
        public List<Integer> sectionPos = new ArrayList<>();
    }

    private static boolean sSyncing;
    private static Context sContext;
    private static boolean sRegisterObserver;
    private static boolean sDirty;
    private static ContactsWrapper sContactsWrapper;

    public static void init(Context context) {
        if (sContext != null) {
            return;
        }
        sContext = context;
        // 增加城市和姓的数据字典

    }

    public static void uninit() {
        if (sContext != null) {
            sContext = null;
        }
    }

    public static void sync() {
        if (sContext == null) {
            return;
        }
        if (!AppTool.hasPermission(sContext, Manifest.permission.READ_CONTACTS)) {
            return;
        }
        registerContactObserver();
        new AsyncTask<Void, Void, ContactsWrapper>() {
            @Override
            protected ContactsWrapper doInBackground(Void... voids) {
                ContactsWrapper contactsWrapper;
                synchronized (ContactsSync.class) {
                    if (sSyncing) {
                        sDirty = true;
                        return null;
                    }
                    do {
                        sDirty = false;
                        contactsWrapper = syncProc();
                        sSyncing = false;
                    } while (sDirty);
                }
                return contactsWrapper;
            }

            @Override
            protected void onPostExecute(ContactsWrapper contactsWrapper) {
                super.onPostExecute(contactsWrapper);
                if (contactsWrapper != null) {
                    sContactsWrapper = contactsWrapper;
                    EventBus.getDefault().post(new ContactEvent(ContactEvent.EVENT_READ_ANDROID_CONTACTS));
                }
            }
        }.executeOnExecutor(Executors.newCachedThreadPool());
    }

    public static ContactsWrapper getContactsWrapper() {
        return sContactsWrapper == null ? new ContactsWrapper() : sContactsWrapper;
    }

    private static ContactsWrapper syncProc() {
        ContactsWrapper contactsWrapper = new ContactsWrapper();
        contactsWrapper.contacts = ContactTool.getAllContacts(sContext);
        String lastSection = null;
        for (int i = 0; i < contactsWrapper.contacts.size(); i++) {
            ContactTool.AndroidContact contact = contactsWrapper.contacts.get(i);
            contact.phone = NumberTool.formatPhoneWithDefaultCountryPrefix(contact.phone);
            String section = contact.name.substring(0, 1).toUpperCase();
            if (section.charAt(0) >= 'A' && section.charAt(0) <= 'Z') {

            } else {
                section = "#";
            }
            if (!TextUtils.equals(lastSection, section)) {
                contactsWrapper.sections.add(section.toUpperCase());
                contactsWrapper.sectionPos.add(i);
                lastSection = section;
            }
        }
        return contactsWrapper;
    }

    private static void registerContactObserver() {
        if (!AppTool.hasPermission(sContext, Manifest.permission.READ_CONTACTS)) {
            return;
        }
        if (sRegisterObserver) {
            return;
        }
        sRegisterObserver = true;
        sContext.getContentResolver().registerContentObserver(ContactsContract.Contacts.CONTENT_URI, true, new ContentObserver(null) {
            @Override
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);
                sync();
            }
        });
    }
}

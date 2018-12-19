package com.nela.mvpdemo.presenter.contracts;

import android.text.TextUtils;

import com.nela.mvpdemo.contract.contracts.ContactsContract;
import com.nela.mvpdemo.data.event.ContactEvent;
import com.nela.mvpdemo.data.event.SearchEvent;
import com.nela.mvpdemo.data.search.ContactsSearchManager;
import com.nela.mvpdemo.utils.ContactsSync;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import cn.nela.tools.ContactTool;

public class ContractsPresenter implements ContactsContract.Presenter {

    private int mReplayType;
    ContactsContract.View mView;
    private String mSearchKey;
    private ContactsSync.ContactsWrapper mContactsWrapper;
    private ContactsSearchManager.SearchAsyncTask.SearchContactsWrapper mSearchContactsWrapper;

    public ContractsPresenter(ContactsContract.View view) {
        super();
        mView = view;
    }

    @Override
    public void start() {
        EventBus.getDefault().register(this);
        loadData();
        mView.showContactsList();
    }

    private void loadData() {
        mContactsWrapper = ContactsSync.getContactsWrapper();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onContactEvent(ContactEvent event) {
        switch (event.type) {
            case ContactEvent.EVENT_READ_ANDROID_CONTACTS:
                loadData();
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemReplayType() {
        return mReplayType;
    }

    @Override
    public void setItemReplayType(int type) {
        this.mReplayType = type;
    }

    @Override
    public int getDataCount() {
        return mContactsWrapper != null ? mContactsWrapper.contacts.size() : 0;
    }

    @Override
    public ItemDraw getItemDraw(int index) {
        ItemDraw item = new ItemDraw();
        ContactTool.AndroidContact androidContact = mContactsWrapper.contacts.get(index);
        if (androidContact != null) {
            item.showSection = mContactsWrapper.sectionPos.contains(index);
            if (item.showSection) {
                item.section = mContactsWrapper.sections.get(mContactsWrapper.sectionPos.indexOf(index));
            }
            item.header = androidContact.photoUri;
            item.name = androidContact.name;
            item.number = androidContact.phone;
            item.showCloud = false;
            return item;
        }
        return null;
    }

    @Override
    public int getSectionPos(String section) {
        if (mContactsWrapper == null) {
            return 0;
        }
        for (int i = 0; i < mContactsWrapper.sections.size(); i++) {
            if (TextUtils.equals(mContactsWrapper.sections.get(i), section)) {
                return mContactsWrapper.sectionPos.get(i);
            }
        }
        return 0;
    }

    @Override
    public String getSectionByIndex(int index) {
        if (mContactsWrapper == null) {
            return "";
        }
        for (int i = 0; i < mContactsWrapper.sectionPos.size(); i++) {
            int nextIndex =
                    (i == mContactsWrapper.sectionPos.size() - 1) ? mContactsWrapper.contacts.size() : mContactsWrapper.sectionPos.get(i + 1);
            if (mContactsWrapper.sectionPos.get(i) <= index && index < nextIndex) {
                return mContactsWrapper.sections.get(i);
            }
        }
        return "";
    }

    @Override
    public void call(int index) {
        //todo 检测是否是加密通话用户 if(true):
        final String name = mContactsWrapper.contacts.get(index).name;
        final String phone = mContactsWrapper.contacts.get(index).phone;
        mView.showCallActivity(phone, false);
    }

    @Override
    public void encryptedCall(int index) {

    }

    @Override
    public void showContactsDetailsActivity(int position) {
        mView.startContactsDetailsActivity(getItemDraw(position).number);
    }

    @Override
    public void showTextResponseUI(int uid) {
        mView.showTextResponseUI(String.valueOf(uid));
    }


    @Override
    public List<String> getAllSectionPos() {
        return mContactsWrapper.sections;
    }


    @Override
    public void stop() {

    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
    }

    /*********************************** 搜索 **************************************/

    @Override
    public void startSearch(String searchKey) {
        if (TextUtils.isEmpty(searchKey)) {
            mView.showContactsList();
            return;
        }
        mSearchKey = searchKey;
        ContactsSearchManager.getInstance().startSearch(searchKey, mContactsWrapper, SearchEvent.EVENT_SEARCH_ADDRESS_BOOK_CONTACTS);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSearchEvent(SearchEvent event) {
        switch (event.type) {
            case SearchEvent.EVENT_SEARCH_ADDRESS_BOOK_CONTACTS:
                ContactsSearchManager.SearchAsyncTask.SearchContactsWrapper wrapper = event.getSearchContactsWrapper();
                if (wrapper != null) {
                    if (TextUtils.equals(mSearchKey, wrapper.searchKey)) {
                        mSearchContactsWrapper = wrapper;
                        mView.showSearchList();
                        //todo 显示搜索界面
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public int getSearchDataCount() {
        return mSearchContactsWrapper.searchResultList != null ? mSearchContactsWrapper.searchResultList.size() : 0;
    }

    @Override
    public ItemDraw getSearchItemDraw(int index) {
        ItemDraw item = new ItemDraw();
        ContactTool.AndroidContact androidContact = mSearchContactsWrapper.searchResultList.get(index);
        if (androidContact != null) {
            item.showSection = mSearchContactsWrapper.searchResultSectionPos.contains(index);
            if (item.showSection) {
                item.section = mSearchContactsWrapper.searchResultSections.get(mSearchContactsWrapper.searchResultSectionPos.indexOf(index));
            }
            item.header = androidContact.photoUri;
            item.name = androidContact.name;
            item.number = androidContact.phone;
            item.showCloud = false;
            item.searchKey = mSearchContactsWrapper.searchKey;
            return item;
        }
        return null;
    }

    public void showContactsDetailsActivityFromSearch(int position) {
        mView.startContactsDetailsActivity(getSearchItemDraw(position).number);
    }
}

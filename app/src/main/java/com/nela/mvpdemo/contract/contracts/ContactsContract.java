package com.nela.mvpdemo.contract.contracts;

import com.nela.mvpdemo.base.BasePresenter;
import com.nela.mvpdemo.base.BaseView;

import java.util.List;

public class ContactsContract {
    public interface View extends BaseView {

        void showSearchList();

        void showContactsList();

        /**
         * 数据改变
         */
        void onDataChange();

        /***
         *  掉起通话界面
         */
        void showCallActivity(String number, boolean video);

        /**
         * 跳转至显示联系人详情界面
         *
         * @param number
         */
        void startContactsDetailsActivity(String number);

        void showTextResponseUI(String uid);

    }

    public interface Presenter extends BasePresenter {
        /**
         * 渲染对象
         */
        class ItemDraw {
            public boolean showSection;
            public String section;
            public String header;
            public String name;
            public String number;
            public boolean showCloud;
            public String searchKey;
        }

        /**
         * 获得渲染对象
         *
         * @param index
         * @return
         */
        ItemDraw getItemDraw(int index);

        /**
         * 获取消息回复类型
         *
         * @return
         */
        int getItemReplayType();

        /**
         * 设置消息回复类型
         *
         * @param type
         */
        void setItemReplayType(int type);

        /**
         * 数据个数
         *
         * @return
         */
        int getDataCount();


        void startSearch(String key);

        /**
         * 获得 section 对应的起始位置
         *
         * @param section
         * @return
         */
        int getSectionPos(String section);

        /**
         * 获取所有索引
         *
         * @return 索引list
         */
        List<String> getAllSectionPos();

        /**
         * 获得索引号对应的section
         *
         * @param index
         * @return
         */
        String getSectionByIndex(int index);


        /**
         * 普通通话
         *
         * @param index
         * @return
         */
        void call(int index);

        /**
         * 加密通话
         */

        void encryptedCall(int index);

        /**
         * 联系人列表跳转至联系人详情界面
         *
         * @param position
         */
        void showContactsDetailsActivity(int position);

        void showTextResponseUI(int uid);

        /**************************搜索*********************************/

        /**
         * 搜索数据个数
         *
         * @return
         */
        int getSearchDataCount();

        /**
         * 搜索列表跳转至联系人详情界面
         *
         * @param position
         */
        void showContactsDetailsActivityFromSearch(int position);

        /**
         * 获得搜索列表渲染对象
         *
         * @param index
         * @return
         */
        ItemDraw getSearchItemDraw(int index);
    }
}

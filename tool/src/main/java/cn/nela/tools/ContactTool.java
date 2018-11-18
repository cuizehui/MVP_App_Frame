package cn.nela.tools;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

public class ContactTool {

    private static final String[] PROJECTION_PHONE = new String[]{ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone.PHOTO_URI, ContactsContract.CommonDataKinds.Phone.SORT_KEY_PRIMARY};
    private static final int COLUMN_CONTACT_ID = 0;
    private static final int COLUMN_DISPLAY_NAME = 1;
    private static final int COLUMN_NUMBER = 2;
    private static final int COLUMN_PHOTO_URI = 3;
    private static final int COLUMN_SORT_KEY_PRIMARY = 4;

    public static class AndroidContact {
        public long id;
        public String name;
        public String sortKey;
        public String phone;
        public String photoUri;
    }

    /**
     * 获得数据库中所有号码的联系人，需检查联系人读取权限
     *
     * @param context
     * @return
     */
    public static List<AndroidContact> getAllContacts(Context context) {
        List<AndroidContact> contacts = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                PROJECTION_PHONE, null, null, ContactsContract.CommonDataKinds.Phone.SORT_KEY_PRIMARY);
        if (cursor != null) {
            try {
                while (cursor.moveToNext()) {
                    AndroidContact contact = new AndroidContact();
                    contact.id = cursor.getLong(COLUMN_CONTACT_ID);
                    contact.name = cursor.getString(COLUMN_DISPLAY_NAME);
                    contact.phone = NumberTool.formatPhone(cursor.getString(COLUMN_NUMBER));
                    contact.sortKey = cursor.getString(COLUMN_SORT_KEY_PRIMARY);
                    contact.photoUri = cursor.getString(COLUMN_PHOTO_URI);
                    contacts.add(contact);
                }
            } finally {
                cursor.close();
            }
        }
        return contacts;
    }

}

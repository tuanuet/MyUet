package vnu.uet.tuan.myuet.Database;

import android.provider.BaseColumns;

/**
 * Created by Admin on 15/9/2016.
 */
public class Contract {
    public class ItemNotification implements BaseColumns {
        public static final String NAMETABLE = "table_notification";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_CONTENT = "content";
        public static final String COLUMN_LINK = "link";
        public static final int VERSION = 1;
    }
}


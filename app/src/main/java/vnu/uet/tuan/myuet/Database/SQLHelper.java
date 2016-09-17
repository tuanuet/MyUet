package vnu.uet.tuan.myuet.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import vnu.uet.tuan.myuet.Database.Contract.ItemNotification;
import vnu.uet.tuan.myuet.Models.Noti_data;

/**
 * Created by Admin on 15/9/2016.
 */
public class SQLHelper extends SQLiteOpenHelper {
    Context context;
    public SQLHelper(Context context) {
        super(context, ItemNotification.NAMETABLE, null,ItemNotification.VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table " + ItemNotification.NAMETABLE + " ( " +
                ItemNotification._ID + " integer primary key autoincrement, "+
                ItemNotification.COLUMN_TITLE + " text not null, "+
                ItemNotification.COLUMN_CONTENT + " text not null, "+
                ItemNotification.COLUMN_LINK + " text not null ,"+

                " UNIQUE (" + ItemNotification.COLUMN_TITLE + ", " +
                ItemNotification.COLUMN_CONTENT + ", " +
                ItemNotification.COLUMN_LINK +") ON CONFLICT REPLACE);";

        sqLiteDatabase.execSQL(sql);
    }
    //
    public Cursor loadItem(){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from "+ ItemNotification.NAMETABLE;
        return db.rawQuery(sql,null);
    }

    //
    public void insertItem(Noti_data item){
        ContentValues cv = new ContentValues();
        cv.put(ItemNotification.COLUMN_TITLE,item.getTitle());
        cv.put(ItemNotification.COLUMN_CONTENT,item.getContent());
        cv.put(ItemNotification.COLUMN_LINK,item.getLink());

        SQLiteDatabase db = getWritableDatabase();
        if ( db.insert(ItemNotification.NAMETABLE,null,cv)>-1){
            Log.d("database","insert Thanh cong");
        }else
            Log.d("database","insert that bai");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ ItemNotification.NAMETABLE);
    }
}

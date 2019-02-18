package com.example.contentproviderdemoone;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @ProjectName: ZSXQProjects
 * @Package: com.example.contentproviderdemoone
 * @ClassName: MyOPenHelper
 * @Author: DashingQI
 * @CreateDate: 2019/2/18 11:11 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/2/18 11:11 PM
 * @UpdateRemark:
 * @Version: 1.0
 */
public class MyOPenHelper extends SQLiteOpenHelper {

    public static final String CREATE_BOOK = "create table book( id integer primary key autoincrement," +
            "author text," +
            "price real," +
            "pages integer," +
            "name text)";

    public static final String CREATE_CATEGORY = "create table Category (id integer primary key autoincrement," +
            "category_name text," +
            "category_code integer)";
    public MyOPenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists book");
        db.execSQL("drop table if exists Category");
        onCreate(db);

    }
}

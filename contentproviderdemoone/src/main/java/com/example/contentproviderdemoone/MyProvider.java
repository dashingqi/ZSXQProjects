package com.example.contentproviderdemoone;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @ProjectName: ZSXQProjects
 * @Package: com.example.contentproviderdemoone
 * @ClassName: MyProvider
 * @Author: DashingQI
 * @CreateDate: 2019/2/17 11:01 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/2/17 11:01 PM
 * @UpdateRemark:
 * @Version: 1.0
 */
public class MyProvider extends ContentProvider {

    public static final int TABLE1_DIR = 0;
    public static final int TABLE1_ITEM = 1;
    public static final int TABLE2_DIR = 2;
    public static final int TABLE2_ITEM = 3;
    public static UriMatcher uriMatcher;


    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.contentproviderdemo", "table1", TABLE1_DIR);
        uriMatcher.addURI("com.example.contentproviderdemo", "table1/#", TABLE1_ITEM);
        uriMatcher.addURI("com.example.contentproviderdemo", "table2", TABLE2_DIR);
        uriMatcher.addURI("com.example.contentproviderdemo", "table2/#", TABLE2_ITEM);
    }

    @Override
    public boolean onCreate() {

        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        switch (uriMatcher.match(uri)) {
            case TABLE1_DIR:
                //查询table1中的所有数据
                break;
            case TABLE1_ITEM:
                //查询table1中的单条数据
                break;
            case TABLE2_DIR:
                //查询table2中的所有数据
                break;
            case TABLE2_ITEM:
                //查询table2中的单条数据
                break;
        }
        return null;
    }

    /**
     * @param uri
     * @return
     * 获取到Uri对应的MIME类型
     * MIME类型格式规定如下
     * 1. 以vnd开头
     * 2. 如果URI以路径结尾 那么后接 android.cursor.dir/  如果以id结尾 那么后接是 android.cursor.item/
     * 3. vnd.<authority>(android.cursor.dir(item)).<path>
     */
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case TABLE1_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.contentproviderdemo.table1";
            case TABLE1_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.contentproviderdemo.table1";
            case TABLE2_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.contentproviderdemo.table2";
            case TABLE2_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.contentproviderdemo.table2";
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}

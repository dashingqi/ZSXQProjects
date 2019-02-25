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
 *
 * 1. ContentProvider ContentResolver ContentObserver
 *
 * ContentProvider:
 *      1.Android中四大组件之一，叫做内容提供者；主要对外提供数据
 *      2.实现应用程序之间数据的共享（跨应用），比如手机联系人的应用，使用了它。其实真正的数据源是文件或者SQLite，ContentProvider只是个中间人。
 *      3.对外共享数据，你可以把应用内的数据通过ContentProvider来提供给其他应用，其他应用也可以通过ContentProvider来对你应用内的数据进行CRUD操作。
 *  ContentResolver：
 *      1.内容解析者，用来操作内容提供者提供的数据。
 *      2.解析Uri：ContentResolver.notifyChange(uri)
 *  ContentObserver：
 *      1.内容监听器，监听数据的改变状态。
 *      2.主要监听Uri引起的数据变化，继而做一些处理。
 *
 *   三者的关系是这样的 用ContentResolver来解析ContentProvider提供的数据，同时注册ContentObserver监听Uri引起数据库数据的变化。
 *
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

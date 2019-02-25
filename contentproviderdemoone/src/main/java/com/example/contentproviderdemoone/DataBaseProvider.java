package com.example.contentproviderdemoone;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @ProjectName: ZSXQProjects
 * @Package: com.example.contentproviderdemoone
 * @ClassName: DataBaseProvider
 * @Author: DashingQI
 * @CreateDate: 2019/2/20 11:10 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/2/20 11:10 PM
 * @UpdateRemark:
 * @Version: 1.0
 */
public class DataBaseProvider extends ContentProvider {

    private MyOPenHelper dbOPenHelper;
    public static final int BOOK_DIR = 0;
    public static final int BOOK_ITEM = 1;
    public static final int CATEGORY_DIR = 2;
    public static final int CATEGORY_ITEM = 3;
    public static final String AUTHORITY = "com.example.dashingqi.provider";
    private static UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "book", BOOK_DIR);
        uriMatcher.addURI(AUTHORITY, "book/#", BOOK_ITEM);
        uriMatcher.addURI(AUTHORITY, "Category", CATEGORY_DIR);
        uriMatcher.addURI(AUTHORITY, "Category/#", CATEGORY_ITEM);
    }


    @Override
    public boolean onCreate() {
        dbOPenHelper = new MyOPenHelper(getContext(), "BookStore.db", null, 2);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = dbOPenHelper.getWritableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                cursor = db.query("book", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                cursor = db.query("book", projection, "id = ?", new String[]{bookId}, null, null, sortOrder);
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = dbOPenHelper.getWritableDatabase();
        Uri mUri = null;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
            case BOOK_ITEM:
                long bookId = db.insert("book", null, values);
                mUri = Uri.parse("content://" + AUTHORITY + "/book/" + bookId);
                break;
            case CATEGORY_DIR:
            case CATEGORY_ITEM:
                long categoryId = db.insert("Category", null, values);
                mUri = Uri.parse("content://" + AUTHORITY + "/Category/" + categoryId);
                break;
        }

        return mUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = dbOPenHelper.getWritableDatabase();
        int book = 0;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
            case BOOK_ITEM:
                book = db.delete("book", selection, selectionArgs);
                break;
            case CATEGORY_DIR:
            case CATEGORY_ITEM:
                break;
        }
        return book;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = dbOPenHelper.getWritableDatabase();
        int id = 0;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
            case BOOK_ITEM:
                id = db.update("book", values, selection, selectionArgs);
                break;
            case CATEGORY_DIR:
            case CATEGORY_ITEM:
                break;
        }
        return id;
    }
}

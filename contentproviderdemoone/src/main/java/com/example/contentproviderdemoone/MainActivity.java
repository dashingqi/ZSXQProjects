package com.example.contentproviderdemoone;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    private Button btnReadContact;
    private Button btnCreateBookTable;
    private MyOPenHelper myOPenHelper;
    private Button btnAddData;
    private SQLiteDatabase database;
    private Button btnUpdateData;
    private Button btnDeleteData;
    private Button btnQueryData;
    private Button btnQueryProvider;
    private Button btnAddDataProvider;
    private Button btnUpdateProvider;
    private Button deleteDataProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initBookStore();
    }

    private void initBookStore() {
        myOPenHelper = new MyOPenHelper(MainActivity.this, "BookStore.db", null, 2);
    }

    private void readContactMethod() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, 0);

        } else {
            readPhoneContact();
        }

    }

    private void readPhoneContact() {
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phoneNum = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                Log.i(TAG, "name = " + name + " phoneNum = " + phoneNum);

            }
        }

    }

    private void initView() {
        btnReadContact = findViewById(R.id.btnReadContact);
        btnReadContact.setOnClickListener(this);

        btnCreateBookTable = findViewById(R.id.btnCreateBookTable);
        btnCreateBookTable.setOnClickListener(this);

        btnAddData = findViewById(R.id.btnAddData);
        btnAddData.setOnClickListener(this);

        btnUpdateData = findViewById(R.id.btnUpdateData);
        btnUpdateData.setOnClickListener(this);

        btnDeleteData = findViewById(R.id.btnDeleteData);
        btnDeleteData.setOnClickListener(this);

        btnQueryData = findViewById(R.id.btnQueryData);
        btnQueryData.setOnClickListener(this);

        btnQueryProvider = findViewById(R.id.btnQueryProvider);
        btnQueryProvider.setOnClickListener(this);

        btnAddDataProvider = findViewById(R.id.btnAddDataProvider);
        btnAddDataProvider.setOnClickListener(this);

        btnUpdateProvider = findViewById(R.id.btnUpdateProvider);
        btnUpdateProvider.setOnClickListener(this);

        deleteDataProvider = findViewById(R.id.btnDeleteDataProvider);
        deleteDataProvider.setOnClickListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 0:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readPhoneContact();
                } else {
                    //提示
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnReadContact:
                readContactMethod();
                break;
            case R.id.btnCreateBookTable:
                database = myOPenHelper.getWritableDatabase();
                break;
            case R.id.btnAddData:
                addData();
                break;
            case R.id.btnUpdateData:
                updateData();
                break;
            case R.id.btnDeleteData:
                deleteData();
                break;
            case R.id.btnQueryData:
                queryData();
                break;
            case R.id.btnQueryProvider:
                queryDataProvider();
                break;
            case R.id.btnAddDataProvider:
                addDataProvider();
                break;
            case R.id.btnUpdateProvider:
                updateProviderData();
                break;
            case R.id.btnDeleteDataProvider:
                deleteDataProviderMethod();
                break;

        }
    }

    private void deleteDataProviderMethod() {
        Uri uri = Uri.parse("content://com.example.dashingqi.provider/book");
        int delete = getContentResolver().delete(uri, "name = ?", new String[]{"MacBookPro"});
        Log.i(TAG, "delete = " + delete);
    }

    /**
     * 更新数据 provider
     */
    private void updateProviderData() {
        Uri uri = Uri.parse("content://com.example.dashingqi.provider/book");
        ContentValues values = new ContentValues();
        values.put("name", "MacBookPro");
        int update = getContentResolver().update(uri, values, "author = ?", new String[]{"guolin"});
        Log.i(TAG, "update = " + update);

    }

    /**
     * 添加数据 provider
     */
    private void addDataProvider() {
        ContentValues values = new ContentValues();
        values.put("name", "the first code");
        values.put("author", "guolin");
        values.put("pages", "300");
        values.put("price", "45.9");
        Uri uri = Uri.parse("content://com.example.dashingqi.provider/book");
        Uri mUri = getContentResolver().insert(uri, values);
        String id = mUri.getPathSegments().get(1);
        Log.i(TAG, "id = " + id);


    }

    /**
     * 查询数据
     */
    private void queryDataProvider() {
        Uri uri = Uri.parse("content://com.example.dashingqi.provider/book");
        Cursor cursor = getContentResolver().query(uri, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                Log.i(TAG, "name = " + name);
                Log.i(TAG, "author = " + author);

            }
        }
    }

    private void queryData() {
        SQLiteDatabase db = myOPenHelper.getWritableDatabase();
        Cursor cursor = db.query("book", null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                String pages = cursor.getString(cursor.getColumnIndex("pages"));
                double price = cursor.getDouble(cursor.getColumnIndex("price"));
                Log.i(TAG, "name =  " + name);
                Log.i(TAG, "author = " + author);
                Log.i(TAG, "pages = " + pages);
                Log.i(TAG, "price = " + price);

            }
        }
    }

    private void deleteData() {

        SQLiteDatabase db = myOPenHelper.getWritableDatabase();
        db.delete("book", "pages > ?", new String[]{"500"});

    }

    private void updateData() {

        SQLiteDatabase db = myOPenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", "zhangqi");
        db.update("book", contentValues, "name = ?", new String[]{"The Lost Symbol"});
    }

    /**
     * 添加数据
     */
    private void addData() {
        SQLiteDatabase db = myOPenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", "The Da Vinci Code");
        contentValues.put("author", "Dan Brown");
        contentValues.put("pages", 454);
        contentValues.put("price", 16.96);
        db.insert("book", null, contentValues);
        contentValues.clear();

        contentValues.put("name", "The Lost Symbol");
        contentValues.put("author", "Dan Brown");
        contentValues.put("pages", 510);
        contentValues.put("price", 19.95);
        db.insert("book", null, contentValues);
    }
}

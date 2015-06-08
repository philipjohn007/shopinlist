package com.ubaier.shopinlist.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Helper for database
 */
public class ShopinlistDatabaseHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "shopinlist.SQLite";
    public static final int DB_VERSION = 1;

    public static String SHOPPING_LIST_TABLE = "ShopInList";
    public static String RECORD_ID = "ID";
    public static String ITEM_NAME = "Name";
    public static String IS_DONE = "IsDone";
    public static String IS_DELETED = "IsDeleted";

    public ShopinlistDatabaseHelper(Context context) {
        super(context,DB_NAME ,
                null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase shoppinlistDB) {

        String sqlStatement = "CREATE TABLE " + SHOPPING_LIST_TABLE
                + " ("
                + RECORD_ID + " integer primary key autoincrement not null, "
                + ITEM_NAME + " string, "
                + IS_DONE + " boolean, "
                + IS_DELETED + " boolean"
                + ");";
        Log.d("Shopinlist DB", "sql Statement for create is: "+ sqlStatement);

        shoppinlistDB.execSQL(sqlStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

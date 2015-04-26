package com.ubaier.shopinlist;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ubaier.shopinlist.db.ShopinlistDatabaseHelper;

import java.util.ArrayList;

/**
 * Created by Ubaier on 25/04/2015.
 */
public class ShopinlistApplication extends Application{
    private SQLiteDatabase shopinglistDB;
    private ArrayList<ListItem> allListItems;


    @Override
    public void onCreate() {
        super.onCreate();

        ShopinlistDatabaseHelper databaseHelper = new ShopinlistDatabaseHelper(this);
        shopinglistDB = databaseHelper.getWritableDatabase();
        readItemsFromShopinglistDB();
    }

    public void addItem(ListItem listItem){
        assert listItem != null;

        ContentValues cv = new ContentValues();
        cv.put(ShopinlistDatabaseHelper.ITEM_NAME, listItem.getName());
        cv.put(ShopinlistDatabaseHelper.IS_DONE, listItem.getIsDone());
        cv.put(ShopinlistDatabaseHelper.IS_DELETED, listItem.getIsDone());

        Log.d("ShopinglistDB: ", "Before insert " + listItem.toString());
        long idPassedBack = shopinglistDB.insert(ShopinlistDatabaseHelper.SHOPPING_LIST_TABLE,null,cv );
        listItem.setId(idPassedBack);
        Log.d("ShopinglistDB: ", "Ater insert " + listItem.toString());

        allListItems.add(listItem);

    }

    public ArrayList<ListItem> getAllListItems(){

        return allListItems;
    }

    public void readItemsFromShopinglistDB(){
        allListItems = new ArrayList<ListItem>() ;;

        Cursor shopinlistCursor = shopinglistDB.query(ShopinlistDatabaseHelper.SHOPPING_LIST_TABLE,
                new String[] {ShopinlistDatabaseHelper.RECORD_ID, ShopinlistDatabaseHelper.ITEM_NAME, ShopinlistDatabaseHelper.IS_DONE, ShopinlistDatabaseHelper.IS_DELETED},
                ShopinlistDatabaseHelper.IS_DELETED + " = "+ 0,
                null, null, null, null);

        ListItem tempItem;


        shopinlistCursor.moveToFirst();

        Log.d("ShopinlistDB", "Cursor problem "+ shopinlistCursor.getColumnIndex(ShopinlistDatabaseHelper.ITEM_NAME));



        if(shopinlistCursor != null & !shopinlistCursor.isAfterLast())
        {
            do{
                long id = shopinlistCursor.getLong(shopinlistCursor.getColumnIndex(ShopinlistDatabaseHelper.RECORD_ID));
                String name = shopinlistCursor.getString(shopinlistCursor.getColumnIndex(ShopinlistDatabaseHelper.ITEM_NAME));
                Boolean isDone = (shopinlistCursor.getLong(shopinlistCursor.getColumnIndex(ShopinlistDatabaseHelper.IS_DONE)) == 1);
                Boolean isDeleted = false ;
                tempItem = new ListItem(id, name, isDone, isDeleted);
                Log.d("Shopinlist " , tempItem.toString());
                allListItems.add(tempItem);

            }while(shopinlistCursor.moveToNext());

        }

        shopinlistCursor.close();


    }

    public void removeItem(){


    }

}

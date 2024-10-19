package com.example.crudapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "INVENTORY";
    private static int DB_VERSION = 1;
    private static final String TABLE_ITEM = "Items";
    private static final String COL_ITEM_NAME = "Item_name";
    private static final String COL_QUANTITY = "Quantity";

    public DbHelper( Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //query for creating the db and tables
        //CREATE TABLE Items (Item_name TEXT PRIMARY KEY, Quantity INTEGER)
        String query = "CREATE TABLE " + TABLE_ITEM + " (" + COL_ITEM_NAME + " TEXT PRIMARY KEY, " + COL_QUANTITY + " INTEGER)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //DROP TABLE IF EXISTS Items;
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM);
    }

    public boolean createItem(String item, int qnty){
        SQLiteDatabase db = this.getReadableDatabase();//get the db
        ContentValues values = new ContentValues();//create our data skeleton

        // .put(col name, value)
        values.put(COL_ITEM_NAME,item);
        values.put(COL_QUANTITY,qnty);

        //on insert return if inserted or not
        long result = db.insert(TABLE_ITEM,null,values);//table, null(ignore), values

        //no insert = -1 insert = long
        return result != -1;//if inserted return true else false

    }

    public boolean updateItem(String item, int qnty){
        SQLiteDatabase db = this.getReadableDatabase();//get the db
        ContentValues values = new ContentValues();//create our data skeleton

        // .put(col name, value)
        values.put(COL_QUANTITY,qnty);

        //tablename, values, where - string
        //returns no of rows
        return db.update(TABLE_ITEM, values,COL_ITEM_NAME + "=?", new String[]{item}) > 0;// where Item_name = item
    }

    public boolean deleteItem(String item){
        SQLiteDatabase db = this.getReadableDatabase();//get the db

        //delete
        //returns no of rows deleted
        return db.delete(TABLE_ITEM,COL_ITEM_NAME + "=?", new String[]{item}) > 0;
    }

    public Cursor readItems(){
        SQLiteDatabase db = this.getReadableDatabase();//get the db

        // SELECT * FROM TABLE
        return db.rawQuery("SELECT * FROM " + TABLE_ITEM,null);
    }






}

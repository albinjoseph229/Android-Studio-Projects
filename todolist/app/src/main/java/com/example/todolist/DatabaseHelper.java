package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "tasks.db";
    private static final String TABLE_NAME = "task_table";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "NAME";
    private static final String COL_3 = "DUE_DATE";
    private static final String COL_4 = "LABEL";
    private static final String COL_5 = "COLOR_CODE";
    private static final String COL_6 = "IS_DONE";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, DUE_DATE TEXT, LABEL TEXT, COLOR_CODE INTEGER, IS_DONE INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, task.getName());
        contentValues.put(COL_3, task.getDueDate());
        contentValues.put(COL_4, task.getLabel());
        contentValues.put(COL_5, task.getColorCode());
        contentValues.put(COL_6, task.isDone() ? 1 : 0);

        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }
}

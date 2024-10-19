package com.example.studentdb;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.ContentValues;
import android.content.Context;

import androidx.core.view.ViewPropertyAnimatorListener;

public class DBHandler extends SQLiteOpenHelper{
    private static final String DB_NAME="student_db";
    private static final int DB_VERSION=1;

    private static final String TABLE_NAME="student_details";
    private static final String ID="id";
    private static final String NAME="name";
    private static final String COURSE="course";
    private static final String SEMESTER="semester";


    public DBHandler(Context context)
    {
        super(context,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query= "CREATE TABLE " + TABLE_NAME + "(" +ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +NAME+ " TEXT, " +COURSE+ " TEXT, "
                +SEMESTER+ " INTEGER)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate(sqLiteDatabase);

    }
    public void addNewStudent(String name,String course ,String semester)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(NAME,name);
        values.put(COURSE,course);
        values.put(SEMESTER,semester);

        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public Student searchStudent(String studentname)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME + " where " + NAME + " = ?" ,new String[]{studentname});
        Student student = new Student();
        if(cursor.moveToFirst())
        {
            cursor.moveToFirst();
            student.setId(Integer.parseInt(cursor.getString(0)));
            student.setName(cursor.getString(1));
            student.setcCourse(cursor.getString(2));
            student.setSemester(Integer.parseInt(cursor.getString(3)));
            cursor.close();
        }
        else
        {
            student=null;
        }
        db.close();
        return student;
    }

    public boolean deleteStudent(String studentname)
    {
        boolean result  =false;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME + " where " + NAME + " = ?" ,new String[]{studentname});
        Student student = new Student();
        if(cursor.moveToFirst())
        {
            student.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_NAME,ID +"= ?",new String[]{String.valueOf(student.getId())});
            cursor.close();
            result=true;
        }
        db.close();
        return result;
    }

    public void update(String name, String course, int semester)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(NAME,name);
        value.put(COURSE,course);
        value.put(SEMESTER,semester);
        db.update(TABLE_NAME, value, NAME + "= ?", new String[]{name});
        db.close();
    }
}

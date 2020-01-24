package com.mk_kadish.fundarsshiyou.DatabaseRelations;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SchoolDbHelper extends SQLiteOpenHelper
{
    public static final String database_name="FAL_db";
    public static final int database_version=1;
    public static final String table_create_statement=
            "CREATE TABLE " + SchoolContract.SchoolEntry.table_name + "("+ SchoolContract.SchoolEntry.school_id + " number PRIMARY KEY," + SchoolContract.SchoolEntry.school_name +
            " TEXT" + ")";
    public static final String user_table_create_statement=
            "CREATE TABLE " + StudentContract.studentEntry.table_name + "("+
                    StudentContract.studentEntry.user_id +
                    " number PRIMARY KEY," + StudentContract.studentEntry.user_name +
            " TEXT," +StudentContract.studentEntry.user_password +
                    " TEXT," + StudentContract.studentEntry.studentPoints +
                    " TEXT," + StudentContract.studentEntry.user_type +
                    " TEXT " +")";

    public static final  String drop_user_table="DROP TABLE IF EXISTS "+ StudentContract.studentEntry.table_name ;

    public static final  String drop_table="DROP TABLE IF EXISTS "+ SchoolContract.SchoolEntry.table_name;

    public SchoolDbHelper(Context context)
    {
        super(context,database_name,null,database_version);
        Log.d("Database_Operation","DataBase Is Created....");
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(table_create_statement);
        db.execSQL(user_table_create_statement);
        Log.d("Database_Operation","SchoolTable Is Created....");

        Log.d("Database_Operation","UserTable Is Created....");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(drop_table);
        db.execSQL(drop_user_table);
        onCreate(db);
    }

    public void addSchool(int s_id , String s_name,SQLiteDatabase mydatabase)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(SchoolContract.SchoolEntry.school_id,s_id);
        contentValues.put(SchoolContract.SchoolEntry.school_name,s_name);
        mydatabase.insert(SchoolContract.SchoolEntry.table_name,null,contentValues);
        Log.d("Database_Operation","School Inserted To  Database....");
    }

    public void addUser(int u_id , String u_name,SQLiteDatabase mydatabase)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(StudentContract.studentEntry.user_id,u_id);
        contentValues.put(StudentContract.studentEntry.user_name,u_name);
        String password="123456";
        String std_points="0";
        contentValues.put(StudentContract.studentEntry.user_password,password);
        contentValues.put(StudentContract.studentEntry.user_type,"STD");
        contentValues.put(StudentContract.studentEntry.studentPoints,std_points);
        mydatabase.insert(StudentContract.studentEntry.table_name,null,contentValues);
        Log.d("Database_Operation","Student Inserted To  Database....");
    }

    public Cursor readSchool(SQLiteDatabase database)
    {
        String[] projections={SchoolContract.SchoolEntry.school_id,SchoolContract.SchoolEntry.school_name};
        Cursor cursor=database.query(SchoolContract.SchoolEntry.table_name, projections,null,null,null,null,null);
        return cursor;
    }
    public Cursor readUsers(SQLiteDatabase database)
    {
        String[] projections={StudentContract.studentEntry.user_id,StudentContract.studentEntry.user_name,StudentContract.studentEntry.user_password};
        Cursor cursor=database.query(StudentContract.studentEntry.table_name, projections,null,null,null,null,null);
        return cursor;
    }

    public  void updatePoints(int uid,Long pointsToadd,SQLiteDatabase mydatabase)
    {
        Long currentPoints;
//        currentPoints=getCurrentPoints(uid,mydatabase);
//        currentPoints=pointsToadd+currentPoints;
        ContentValues contentValues=new ContentValues();
        contentValues.put(StudentContract.studentEntry.studentPoints,pointsToadd);
        String selection=StudentContract.studentEntry.user_id+" = "+ uid;
        mydatabase.update(StudentContract.studentEntry.table_name,contentValues,selection,null);
    }

    public Long getCurrentPoints(int uid, SQLiteDatabase mydatabase) {
        Long userPoints=0L;
        String str=null;
        String[] projections={StudentContract.studentEntry.user_id,StudentContract.studentEntry.user_name,StudentContract.studentEntry.studentPoints};
        Cursor cursor=mydatabase.query(StudentContract.studentEntry.table_name, projections,null,null,null,null,null);
        while(cursor.moveToNext())
        {
            int id =cursor.getInt(cursor.getColumnIndex(StudentContract.studentEntry.user_id));
            if(uid==id)
               str= cursor.getString(cursor.getColumnIndex(StudentContract.studentEntry.studentPoints));
        }
        userPoints=Long.parseLong(str);
        return userPoints+0L;
    }

    public void updateSchool(int id,String name,SQLiteDatabase mydatabase)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(SchoolContract.SchoolEntry.school_name,name);
        String selection=SchoolContract.SchoolEntry.school_id+" = "+ id;
        mydatabase.update(SchoolContract.SchoolEntry.table_name,contentValues,selection,null);
    }

    public void deleteSchool(int id,SQLiteDatabase mydatabase)
    {
        String selection=SchoolContract.SchoolEntry.school_id+" = "+ id;
        mydatabase.delete(SchoolContract.SchoolEntry.table_name,selection,null);
    }
}
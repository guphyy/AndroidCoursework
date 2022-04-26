package com.example.justloginregistertest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBOpenHelper extends SQLiteOpenHelper {

    private final SQLiteDatabase db;

    public DBOpenHelper(Context context){
        super(context,"db_test",null,1);
        db = getReadableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS newUser(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "password TEXT," +
                "position, TEXT," +
                "Create_time, DATATIME1," +
                "update_time, DATATIME2)");
    }
    //Updating in the onUpgrade function often doesn't work, a better solution is to update the database in onOpen
    public void onOpen(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS newUser(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "password TEXT," +
                "position, TEXT," +
                "Create_time, DATATIME1," +
                "update_time, DATATIME2)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS newUser");
        onCreate(db);
    }

    public void add(String name, String password, String position){
        db.execSQL("INSERT INTO newUser (name,password,position) VALUES(?,?,?)",new Object[]{name,password,position});
        db.close();
    }
    public void delete(String name,String password){
        db.execSQL("DELETE FROM newUser WHERE name = AND password ="+name+password);
    }
    public void updata(String password){
        db.execSQL("UPDATE newUser SET password = ?",new Object[]{password});
    }


    public ArrayList<newUser> getAllData(){

        ArrayList<newUser> list = new ArrayList<>();
        Cursor cursor = db.query("newUser",null,null,null,null,null,"name DESC");
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String position = cursor.getString(cursor.getColumnIndex("position"));
            list.add(new newUser(name, password, position));
        }
        cursor.close();
        return list;
    }
}

package com.example.sqlinjectionlogin2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "fun.db";

    public DBHelper(Context context) {

        super(context, "fun.db", null, 1);
    }

    /*Create user table  */
    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("CREATE TABLE users(username TEXT PRIMARY KEY, password TEXT)");

//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("user", "admin");
//        contentValues.put("pass", "admin1");
//        contentValues.put("user", "testInjection");
//        contentValues.put("pass", "admin12");
//
//        db.insert("users",null,contentValues);


    }

    /* */
    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("DROP TABLE IF EXISTS users");
    }

    /*Add user record to database*/
    public void addRecord(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues Values = new ContentValues();
        Values.put(username, username);
        Values.put(password, password);
        db.insert("users",null, Values);
//        String dbInsert = "INSERT INTO users (username, password) VALUES ('" + username + "','" + password + "')";
//        Log.d(":addRecord()", dbInsert);
//
//        db.execSQL(dbInsert);
        db.close();
    }
    /*List all users  */
//    public List<String> getAllUsers(String username, String password) {
//        List<String> userList = new ArrayList<String>();
//        Cursor c =  db.rawQuery("SELECT username, password FROM users WHERE username = '" + username + "' AND " + "password" + "='" + password + "'", new String[]{username,password});
//        while (c.moveToNext()){
//            userList.add(
//                    new String(
//                            c.getString(c.getColumnIndex("username")),
//                            c.getString(c.getColumnIndex("password"))
//                    )
//            );
//        }
//        //        List<String> usersList = new ArrayList<String>();
//        // DB connection
////        SQLiteDatabase db = this.getWritableDatabase();
////        //This is the statement we can exploit
////        String columns = "SELECT * FROM users WHERE username = '" + username + "' AND " + "password" + "='" + password + "'";
////
////        Cursor cursor = db.rawQuery(columns, null);
////        String column1 = cursor.getString(1);
////        String column2 = cursor.getString(2);
////
////        userList.add(column1);
////        userList.add(column2);
//        cursor.close();
//        db.close();
//
//        return userList;
//    }
    public boolean userNameCheck(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = ?",new String[]{username});
        //if cursor. get count > 0 return true else false
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Cursor getUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c =db.rawQuery("SELECT * FROM users", null);
        return c;
    }



}

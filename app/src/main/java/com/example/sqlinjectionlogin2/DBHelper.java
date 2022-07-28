package com.example.sqlinjectionlogin2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "fun.db";

    public DBHelper(Context context) {
        super(context, "fun.db", null, 1);
    }

    /*Create user table  */
    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("CREATE TABLE users(ID INTEGER NOT NULL, username TEXT PRIMARY KEY, password TEXT)");
    }

    /* */
    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("DROP TABLE IF EXISTS users");
    }

    /*Add user record to database*/
    public void addRecord(String username, String password){
        String dbInsert = "INSERT INTO users (username, password) VALUES ('" + username + "','" + password + "')";
        Log.d(":addRecord()", dbInsert);
        SQLiteDatabase MyDB = this.getWritableDatabase();
        MyDB.execSQL(dbInsert);
        MyDB.close();
    }
    /*List all users  */
    public List<users> getAllUsers() {

    }
}

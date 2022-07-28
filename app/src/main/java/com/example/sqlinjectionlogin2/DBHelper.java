package com.example.sqlinjectionlogin2;

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
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(dbInsert);
        db.close();
    }
    /*List all users  */
    public List<String> getAllUsers(String username, String password) {
        ArrayList userList = new ArrayList();
        List<String> usersList = new ArrayList<String>();
        // DB connection
        SQLiteDatabase db = this.getWritableDatabase();
        //This is the statement we can exploit
        String columns = "SELECT * FROM users WHERE username = '" + username + "' AND " + "password" + "='" + password + "'";

        Cursor cursor = db.rawQuery(columns, null);
        String column1 = cursor.getString(1);
        String column2 = cursor.getString(2);

        userList.add(column1);
        userList.add(column2);
        cursor.close();
        db.close();

        return userList;
    }


}

package com.example.sqlinjectionlogin2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.EditTextPreference;
import android.util.Log;
import android.widget.EditText;

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

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user", "admin");
        contentValues.put("pass", "admin");
        contentValues.put("user", "testInjection");
        contentValues.put("pass", "admin12");

        db.insert("users",null,contentValues);


    }

    /* */
    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("DROP TABLE IF EXISTS users");
    }

    /*Add user record to database*/
    public boolean addRecord(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues Values = new ContentValues();
        Values.put("username", username);
        Values.put("password", password);
        long result = db.insert("users",null, Values);

        if(result==-1) return false;
        else
            return true;
    }


    //Checks to see if the username is valid
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

    //Checks username and password to verify user exists when logging in
    public boolean isValidLogin(String username, String password){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from users where username = '" + username + "' and password = '" + password + "'";
        Cursor c = db.rawQuery(query, null);
        return c.getCount() != 0;


    }


}

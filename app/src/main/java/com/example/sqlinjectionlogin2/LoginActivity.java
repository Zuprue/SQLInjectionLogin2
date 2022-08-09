package com.example.sqlinjectionlogin2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button loginbtn;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        loginbtn = (Button) findViewById(R.id.loginbtn1);
        db = new DBHelper(this);


        //Login to SQLite database
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();
                Cursor cursor = db.getUsers();

                if (user.equals("")||pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Please enter a username or password ", Toast.LENGTH_LONG).show();
                  //This is where i'm getting stuck. Need to figure out a way to implement DBHelper to check for login
                    //Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = ? AND password = ?");
                  else {
                    boolean usernamecheck = db.usernamecheck(user);
                    if(usernamecheck == true){
                        Toast.makeText(LoginActivity.this, "Signin successful",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Invalid sign creds",Toast.LENGTH_LONG).show();
                    }

                }
                    if(cursor.getCount() > 1){
                        Toast.makeText(LoginActivity.this, "Signin successful",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }


            }
        });
    }
}
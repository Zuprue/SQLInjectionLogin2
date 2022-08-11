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

                if (user.equals("") || pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Please enter a username or password ", Toast.LENGTH_LONG).show();

                else {
                    boolean isValidLogin = db.isValidLogin(user,pass);
                    if(isValidLogin == true){
                        Toast.makeText(LoginActivity.this, "Signin successful",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this,
                                db.getUsers().toString(),Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }
}
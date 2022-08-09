package com.example.sqlinjectionlogin2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button loginbtn, btnRegister;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        loginbtn = (Button) findViewById(R.id.loginbtn);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        db = new DBHelper(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                Cursor cursor = db.getUsers();

                if (user.equals("") || pass.equals(""))
                    Toast.makeText(MainActivity.this, "Please enter a username or password ", Toast.LENGTH_LONG).show();
                    //This is where i'm getting stuck. Need to figure out a way to implement DBHelper to check for login
                    //Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = ? AND password = ?");
                else {
                    boolean usernamecheck = db.usernamecheck(user);
                    if (usernamecheck == true) {
                        Toast.makeText(MainActivity.this, "Signin successful", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid sign creds", Toast.LENGTH_LONG).show();
                    }

                }
                if (cursor.getCount() > 1) {
                    Toast.makeText(MainActivity.this, "Signin successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                }

            }
        });


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

            }
        });
    }
}
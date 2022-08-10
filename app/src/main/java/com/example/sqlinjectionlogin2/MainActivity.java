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

                boolean userNameCheck = db.userNameCheck(user);
                //if the username and password are blank than throw error
                if (user.equals("") || pass.equals(""))
                    Toast.makeText(MainActivity.this, "Please enter a username or password ", Toast.LENGTH_LONG).show();
                else {
                    if (userNameCheck == false) {
                        db.addRecord(user,pass);
                        Toast.makeText(MainActivity.this, "Signin successful", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid sign creds", Toast.LENGTH_LONG).show();
                    }

                }
//                if (cursor.getCount() > 1) {
//                    Toast.makeText(MainActivity.this, "Signin successful", Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//                    startActivity(intent);
//                }

            }
        });


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
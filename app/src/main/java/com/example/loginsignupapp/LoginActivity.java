package com.example.loginsignupapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText loginemail, loginpassword;
    Button loginbutton;

    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        loginemail=(EditText) findViewById(R.id.emaillogin);
        loginpassword=(EditText) findViewById(R.id.passwordlogin);
        loginbutton=(Button) findViewById(R.id.buttonlogi);

        db=new Database(this);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = loginemail.getText().toString();
                String password = loginpassword.getText().toString();
                if (email.equals("")||password.equals("")){
                    Toast.makeText(LoginActivity.this, "Your Enter all credentials..", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean result = db.checkuserEmailPassword(email, password);
                    if (result == true){
                        Intent intent = new Intent(getApplicationContext(),HomePageActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid credentials..", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
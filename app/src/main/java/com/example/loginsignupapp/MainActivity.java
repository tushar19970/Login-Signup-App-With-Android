package com.example.loginsignupapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name, user_name, email, password, repassword;
    Button login, signup;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        name=(EditText) findViewById(R.id.edit1);
        user_name=(EditText) findViewById(R.id.edit2);
        email=(EditText) findViewById(R.id.edit3);
        password=(EditText) findViewById(R.id.edit4);
        repassword=(EditText) findViewById(R.id.edit5);

        signup=(Button) findViewById(R.id.button1);
        login=(Button) findViewById(R.id.button2);

        db=new Database(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1 = name.getText().toString();
                String user_name1 = user_name.getText().toString();
                String email1 = email.getText().toString();
                String password1 = password.getText().toString();
                String repassword1 = repassword.getText().toString();

                if (name1.equals("")|| user_name1.equals("")|| email1.equals("")||password1.equals("")){
                    Toast.makeText(MainActivity.this, "Please Enter All the fields..", Toast.LENGTH_SHORT).show();
                } else {
                    if (password1.equals(repassword1)) {
                        Boolean checkResult = db.checkuseremail(email1);
                        if (checkResult == false) {
                            Boolean result = db.insert_data(name1, user_name1, email1, password1);
                            if (result == true) {
                                Toast.makeText(MainActivity.this, "Registered SuccessFully..", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(MainActivity.this, "Registered Failed..", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(MainActivity.this, "User Already Exists\n Please Login.", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(MainActivity.this, "Your Password Is Not Matched..", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
package com.example.keepmehealthy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLoginActivity extends AppCompatActivity {

    EditText et_password;
    EditText et_username;
    Button bt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        bt_login = findViewById(R.id.bt_login);

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (et_username.getText().toString().length() == 0) {
                    Toast.makeText(AdminLoginActivity.this, "Username cannot be Empty", Toast.LENGTH_LONG).show();

                } else if (et_password.getText().toString().length() == 0) {
                    Toast.makeText(AdminLoginActivity.this, "Password cannot be Empty", Toast.LENGTH_LONG).show();

                } else if(et_username.getText().toString().equals(("admin")) && et_password.getText().toString().equals("123"))
                {
                    Intent i2 = new Intent(AdminLoginActivity.this, AdminActivity.class);
                    startActivity(i2);
                }
                else
                {
                    Toast.makeText(AdminLoginActivity.this, "Incorrect username or password", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

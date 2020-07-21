package com.example.keepmehealthy;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
Database mydb = new Database(this);
    EditText et_password;
    EditText et_username;
    TextView tv_message;
    Button bt_login, bt_signup;
    String user=" ";
    String pass=" ";
    String cal=" ";
    int h,w,age,g;
    String gender;
    double bmr_calc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        bt_signup = findViewById(R.id.bt_signup);
        bt_login = findViewById(R.id.bt_login);
        tv_message=findViewById(R.id.tv_message);
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_username.getText().toString().length() == 0) {
                    Toast.makeText(LoginActivity.this, "Username cannot be Empty", Toast.LENGTH_LONG).show();

                } else if (et_password.getText().toString().length() == 0) {
                    Toast.makeText(LoginActivity.this, "Password cannot be Empty", Toast.LENGTH_LONG).show();

                } else {
                    String username = et_username.getText().toString();
                    String password = et_password.getText().toString();
                    Cursor res = mydb.valiadteUser(username);
                    if(res.moveToFirst()) {
                        do {
                            pass = res.getString(7);
                            user = res.getString(6);
                            h=res.getInt(4);
                            w=res.getInt(5);
                            age=res.getInt(3);
                            gender=res.getString(2);
                        } while (res.moveToNext());
                    }

                    if (user.compareTo(username)!=0)
                        Toast.makeText(LoginActivity.this, "Incorrect Username", Toast.LENGTH_LONG).show();
                    else if (pass.compareTo(password)!=0)
                        Toast.makeText(LoginActivity.this, "Incorrect Password", Toast.LENGTH_LONG).show();
                    else {
                        Intent i1 = new Intent(LoginActivity.this, MainActivity.class);
                        cal = calc(res);
                        i1.putExtra("username", et_username.getText().toString());
                        i1.putExtra("calc",cal);
                        startActivity(i1);
                    }
                }
            }
        });

        bt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i2 = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i2);
            }
        });

    }
    public String calc(Cursor res){
        String cal="";


        if(gender=="Male")
            g=1;            //for MALE
        else
            g=2;             //for FEMALE

        if(g==1)
            bmr_calc = (10*w) +(6.25*h) - (5*age) + 5;
        else
            bmr_calc = (10*w) +(6.25*h) - (5*age) - 161;

        double cal_calc = (bmr_calc*1.375);

        cal = Double.toString(cal_calc);
    //  cal=Integer.toString(h);
        return cal;
       }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.admin_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id == R.id.option1){
            Intent i4 = new Intent(LoginActivity.this, AdminLoginActivity.class);
            startActivity(i4);
        }
        return super.onOptionsItemSelected(item);
    }
}

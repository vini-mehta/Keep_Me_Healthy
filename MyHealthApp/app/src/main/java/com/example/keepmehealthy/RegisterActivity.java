package com.example.keepmehealthy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    Database mydb;
    TextView tv_heading, tv_gender;
    EditText et_name, et_phno, et_height, et_weight, et_age, et_username, et_password;
    Button bt_signup;
    RadioButton rd_genderButton,rb_male,rb_female;
    RadioGroup rb_genderGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mydb = new Database(this);

        tv_heading = findViewById(R.id.tv_heading);
        tv_gender = findViewById(R.id.tv_gender);

        et_name = findViewById(R.id.et_name);
        et_phno = findViewById(R.id.et_phno);
        et_height = findViewById(R.id.et_height);
        et_weight = findViewById(R.id.et_weight);
        et_age = findViewById(R.id.et_age);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);

        rb_genderGroup =  findViewById(R.id.rb_genderGroup);
        rb_male = findViewById(R.id.rb_male);
        rb_female = findViewById(R.id.rb_female);

        bt_signup= findViewById(R.id.bt_signup);

        bt_signup.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selected;
                selected = rb_genderGroup.getCheckedRadioButtonId();
                rd_genderButton = findViewById(selected);

                if (et_name.getText().toString().length() == 0) {
                    Toast.makeText(RegisterActivity.this, "Enter Name. ", Toast.LENGTH_LONG).show();
                }
                else if (!(et_phno.getText().toString().matches("[0-9]{10}")) ){
                    Toast.makeText(RegisterActivity.this, "Enter 10 dight phone number. ", Toast.LENGTH_LONG).show();
                }
                else if (rb_male.isChecked()==false && rb_female.isChecked()==false) {
                    Toast.makeText(RegisterActivity.this, "Select Your Gender", Toast.LENGTH_SHORT).show();
                }
                else if (et_age.getText().toString().length() == 0) {
                    Toast.makeText(RegisterActivity.this, "Enter your age. ", Toast.LENGTH_LONG).show();
                }
                else if (et_height.getText().toString().length() == 0) {
                    Toast.makeText(RegisterActivity.this, "Enter your height. ", Toast.LENGTH_LONG).show();
                }
                else if (et_weight.getText().toString().length() == 0) {
                    Toast.makeText(RegisterActivity.this, "Enter your weight. ", Toast.LENGTH_LONG).show();
                }
                else if (et_username.getText().toString().length() == 0) {
                    Toast.makeText(RegisterActivity.this, "Enter username. ", Toast.LENGTH_LONG).show();
                }
                else if (et_password.getText().toString().length() == 0) {
                    Toast.makeText(RegisterActivity.this, "Enter password. ", Toast.LENGTH_LONG).show();
                }
                else{
                    AddUserData();
                    pass();
                    }
            }
        }));
    }

    public void AddUserData() {
        boolean isInserted = mydb.insertUserData(
                et_name.getText().toString(),
                et_phno.getText().toString(),
                rd_genderButton.getText().toString(),
                et_age.getText().toString(),
                et_height.getText().toString(),
                et_weight.getText().toString(),
                et_username.getText().toString(),
                et_password.getText().toString());
        if (isInserted)
            Toast.makeText(RegisterActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(RegisterActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
    }
    public void pass(){
        String username,bmi,cal;
        Intent i1 = new Intent(RegisterActivity.this, DisplayData.class);
        int h,w,age,g;
        double bmr_calc;
        String gender;

        h=Integer.parseInt(et_height.getText().toString());
        w=Integer.parseInt(et_weight.getText().toString());
        age=Integer.parseInt(et_age.getText().toString());
        gender = rd_genderButton.getText().toString();

        if(gender=="Male")
            g=1;            //for MALE
        else
            g=2;             //for FEMALE

        float bmi_calc = (w*100*100)/(h*h);

        if(g==1)
            bmr_calc = (10*w) +(6.25*h) - (5*age) + 5;
        else
            bmr_calc = (10*w) +(6.25*h) - (5*age) - 161;

        double cal_calc = (bmr_calc*1.375);

        bmi=Float.toString(bmi_calc);
        cal = Double.toString(cal_calc);
        username = et_username.getText().toString();
        i1.putExtra("username",username);
        i1.putExtra("bmi",bmi);
        i1.putExtra("cal",cal);
        startActivity(i1);
    }

}
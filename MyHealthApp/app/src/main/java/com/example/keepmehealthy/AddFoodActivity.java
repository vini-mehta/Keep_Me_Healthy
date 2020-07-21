package com.example.keepmehealthy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddFoodActivity extends AppCompatActivity {
EditText item,calorie;
Button add;
Database mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        item = findViewById(R.id.et_item);
        calorie = findViewById(R.id.et_calorie);
        add = findViewById(R.id.bt_add);
        mydb = new Database(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.getText().toString().length() == 0) {
                    Toast.makeText(AddFoodActivity.this, "Enter Food Item ", Toast.LENGTH_LONG).show();
                } else if (calorie.getText().toString().length() == 0) {
                    Toast.makeText(AddFoodActivity.this, "Enter Calories", Toast.LENGTH_LONG).show();
                }else{
                    addFoodData();
                }
            }
        });
    }
    public void addFoodData() {

        boolean isInserted = mydb.insertFoodData(
                item.getText().toString(),
                calorie.getText().toString());
        if (isInserted) {
            Toast.makeText(AddFoodActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
            Intent i8 = new Intent(AddFoodActivity.this, AdminActivity.class);
            startActivity(i8);
        }else
            Toast.makeText(AddFoodActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
    }
}

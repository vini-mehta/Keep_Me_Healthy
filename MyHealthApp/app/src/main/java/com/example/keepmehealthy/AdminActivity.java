package com.example.keepmehealthy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {
Database mydb = new Database(this);
Button user_info,food_info,add_food,delete_user;
EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        user_info = findViewById(R.id.bt_user_info);
        food_info = findViewById(R.id.bt_food_items);
        add_food = findViewById(R.id.bt_add_food);
        delete_user = findViewById(R.id.bt_delete_user);

        user_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewUserInfo();
            }
        });
        food_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFoodInfo();
            }
        });
        add_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i4 = new Intent(AdminActivity.this, AddFoodActivity.class);
                startActivity(i4);
            }
        });
        delete_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i5 = new Intent(AdminActivity.this, DeleteActivity.class);
                startActivity(i5);
            }
        });


    }


    public void viewUserInfo() {
        Cursor res = mydb.getUserData();
        if (res.getCount() == 0) {
            // show message
            showMessage("Error", "Nothing found");
            return;
        }

        StringBuilder buffer = new StringBuilder();
        while (res.moveToNext()) {
            buffer.append("\nName:" + res.getString(0) + "\n");
            buffer.append("Phone Number :" + res.getString(1) + "\n");
            buffer.append("Gender :" + res.getString(2) + "\n");
            buffer.append("Age :" + res.getString(3) + "\n");
            buffer.append("Height :" + res.getString(4) + "\n");
            buffer.append("Weight :" + res.getString(5) + "\n");
            buffer.append("Username :" + res.getString(6) + "\n");
            buffer.append("Password :" + res.getString(7) + "\n\n");
        }
        // Show all data
        showMessage("Data", buffer.toString());
    }
    public void viewFoodInfo() {
        Cursor res = mydb.getFoodData();
        if (res.getCount() == 0) {
            // show message
            showMessage("Error", "Nothing found");
            return;
        }

        StringBuilder buffer = new StringBuilder();
        while (res.moveToNext()) {
            buffer.append("\nFood Item :" + res.getString(0) + "\n");
            buffer.append("Calories :" + res.getString(1) + "\n");
        }
        // Show all data
        showMessage("Data", buffer.toString());
    }
    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    public void DeleteData() {
        delete_user.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = mydb.deleteUserData(et.getText().toString());
                        if (deletedRows > 0)
                            Toast.makeText(AdminActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AdminActivity.this, "Data not Deleted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}

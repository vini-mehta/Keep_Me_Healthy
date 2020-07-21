package com.example.keepmehealthy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteActivity extends AppCompatActivity {
    EditText username;
    Button delete;
    Database mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        username = findViewById(R.id.et_user);
        delete = findViewById(R.id.bt_delete);
        mydb = new Database(this);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int del = mydb.deleteUserData(username.getText().toString());

                if(del > 0)
                    Toast.makeText(DeleteActivity.this, "User Record Deleted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(DeleteActivity.this, "Record does not exist", Toast.LENGTH_LONG).show();
            }
        });
    }
}

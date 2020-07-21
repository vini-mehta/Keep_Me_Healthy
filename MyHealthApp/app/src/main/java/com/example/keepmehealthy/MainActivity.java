package com.example.keepmehealthy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button bt_calcnt,bt_water;
    TextView tv_cal;
    String username,cal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle b1=getIntent().getExtras();
        username=(String)b1.get("username");
        cal=(String)b1.get("calc");
       // Toast.makeText(MainActivity.this,username,Toast.LENGTH_SHORT).show();

        bt_calcnt = findViewById(R.id.bt_cal);
        bt_water = findViewById(R.id.bt_water);
        tv_cal = findViewById(R.id.tv_cal);

        tv_cal.setText(cal);

       bt_calcnt.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i1 = new Intent(MainActivity.this, CalorieActivity.class);
               i1.putExtra("calc",cal);
               i1.putExtra("username",username);
               startActivity(i1);

           }
       });
        bt_water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(MainActivity.this, WaterActivity.class);
                startActivity(i2);
            }
        });

    }
}

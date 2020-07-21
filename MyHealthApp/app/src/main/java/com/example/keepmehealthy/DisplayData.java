package com.example.keepmehealthy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayData extends Activity {
    public static  final int SPLASH_TIME_OUT =8000;
    String res;
    String username;
    TextView tv_bmi_calc,tv_cal_calc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        tv_bmi_calc = findViewById(R.id.tv_bmi_calc);
        tv_cal_calc = findViewById(R.id.tv_cal_calc);

        Bundle b1=getIntent().getExtras();
        username=(String)b1.get("username");

        //Toast.makeText(DisplayData.this,username,Toast.LENGTH_SHORT).show();

        res = (String)b1.get("bmi");
        tv_bmi_calc.setText(res);

        res = (String)b1.get("cal");
        tv_cal_calc.setText(res);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i1 = new Intent(DisplayData.this, MainActivity.class);
                i1.putExtra("username",username);
                i1.putExtra("calc",res);
                startActivity(i1);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}

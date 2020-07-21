package com.example.keepmehealthy;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WaterActivity extends AppCompatActivity {

    Button bt_p,bt_n;
    TextView tv_glass;
    int quantity=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

        bt_p = findViewById(R.id.bt_p);
        bt_n = findViewById(R.id.bt_n);
        tv_glass=findViewById(R.id.tv_glass);

        bt_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity=quantity+1;
                if(quantity>=8)
                    Toast.makeText(WaterActivity.this,"CONGRATULATIONS!!! \n You have drank 8 glasses of water today",Toast.LENGTH_LONG).show();
            display(quantity);
            }
        });

        bt_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity=quantity-1;
                if(quantity<=-1)
                    quantity=0;
                display(quantity);
            }
        });
    }
    private  void display(int quantity){
        TextView tv = findViewById(R.id.tv);
        TextView tv_no = findViewById(R.id.tv_no);
        tv.setText("" + quantity);
        tv_no.setText("" + quantity);
    }

}

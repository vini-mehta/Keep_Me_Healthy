package com.example.keepmehealthy;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class CalorieActivity extends AppCompatActivity {
Button bt_plus1,bt_plus2,bt_plus3,bt_review;
String username,cal;
Database mydb;
    int t;

int progressStatus=0;
//ProgressBar pb_result;
//Handler handler=new Handler();
//TextView tv_result;
float c;
int total;
    TextView tv_tot,tv_calc,tv_of,tv_consumed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie);
        mydb=new Database(this);
        Bundle b2=getIntent().getExtras();
        username=(String)b2.get("username");
        cal=(String)b2.get("calc");

        //Toast.makeText(CalorieActivity.this,username,Toast.LENGTH_SHORT).show();

        bt_plus1 = findViewById(R.id.bt_breakfast);
        bt_plus2 = findViewById(R.id.bt_lunch);
        bt_plus3 = findViewById(R.id.bt_dinner);
        bt_review = findViewById(R.id.bt_review);
      //  pb_result = findViewById(R.id.pb_result);
        tv_tot = findViewById(R.id.tv_tot);
        tv_calc = findViewById(R.id.tv_calc);
        tv_of = findViewById(R.id.tv_of);
        tv_consumed = findViewById(R.id.tv_consumed);


        tv_calc.setText(cal);
      //  Toast.makeText(CalorieActivity.this, t, Toast.LENGTH_SHORT).show();
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                while(progressStatus < total){
                    progressStatus+=1;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            pb_result.setProgress(progressStatus);
                            tv_result.setText(progressStatus+"/"+pb_result.getMax());
                        }
                    });
                    try{
                        Thread.sleep(20);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
*/
        bt_plus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(CalorieActivity.this, SearchActivity.class);
                i1.putExtra("username",username);
                startActivity(i1);
            }
        });

        bt_plus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(CalorieActivity.this, SearchActivity.class);
                i1.putExtra("username",username);
                startActivity(i1);
            }
        });

        bt_plus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(CalorieActivity.this, SearchActivity.class);
                i1.putExtra("username",username);
                startActivity(i1);
            }
        });
        total=findTotal();
        tv_tot.setText(Integer.toString(total));
        bt_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewReviewInfo();
            }
        });
    }
    public int findTotal(){
        int c;
        Cursor res=mydb.total(username);

        if(res.moveToFirst()){
            do{
                c = res.getInt(res.getColumnIndex("CALORIE"));
                t=t+c;
            }while (res.moveToNext());
        }

        return t;
    }
    public void viewReviewInfo() {
        Cursor res = mydb.getReviewData(username);
        if (res.getCount() == 0) {
            // show message
            showMessage("Oops", "Go and eat something first!");
            return;
        }

        StringBuilder buffer = new StringBuilder();
        while (res.moveToNext()) {
            buffer.append("\nUser ID :" + res.getString(0) + "\n");
            buffer.append("Food Item :" + res.getString(1) + "\n");
            buffer.append("Calories :" + res.getString(2) + "\n");
         //   buffer.append("Total Calories :" + res.getString(3) + "\n");
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

}

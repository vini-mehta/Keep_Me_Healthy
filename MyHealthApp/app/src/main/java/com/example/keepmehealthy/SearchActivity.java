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


public class SearchActivity extends AppCompatActivity {
    String username;
    Button bt_search;
    EditText et_search;
    int total;
    Database mydb;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Bundle b2=getIntent().getExtras();
        username=(String)b2.get("username");

      //  Toast.makeText(SearchActivity.this,username,Toast.LENGTH_SHORT).show();

        bt_search=findViewById(R.id.bt_search);
        et_search=findViewById(R.id.et_search);

        mydb = new Database(this);
        bt_search.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_search.getText().toString().length() == 0) {
                        Toast.makeText(SearchActivity.this, "Enter Food item ", Toast.LENGTH_LONG).show();
                }
                else{
                    SearchData();

                   }
            }
        }));
    }
    public void SearchData() {
        Cursor res = mydb.searchFoodData(et_search.getText().toString());
        if (res.getCount() == 0) {
            // show message
            showMessage("Error", "Nothing found");
        }
        else {
             //addData(res);
            String food=" ";
            String cal=" ";
            if(res.moveToFirst()){
                do{
                    food = res.getString(res.getColumnIndex("ITEM"));
                    cal = res.getString(res.getColumnIndex("CALORIE"));
                }while (res.moveToNext());
            }
            total=findTotal();
            String total_cal=Integer.toString(total);

            boolean isInserted = mydb.insertCalcData(username,food,cal,total_cal);
            if (isInserted) {
                Toast.makeText(SearchActivity.this, "Record Inserted", Toast.LENGTH_LONG).show();


            }else
                Toast.makeText(SearchActivity.this, "Record not Inserted", Toast.LENGTH_LONG).show();
            }
    }
    public int findTotal(){
       int t=0;
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
    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
     /* public void showMessage1(String title, List<FoodModel> lst) {
        String str=" ";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        for(FoodModel f:lst){
            str=str+f.getFood_name()+"\t\t\t\t"+f.getFood_cal()+"\n";
        }
        builder.setMessage(str);
        builder.show();
    }*/
}

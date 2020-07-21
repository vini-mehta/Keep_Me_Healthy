package com.example.keepmehealthy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyHealth1.db";
    private static final String TABLE_NAME_1 = "Input_table";
    private static final String TABLE_NAME_2 = "Food";
    private static final String TABLE_NAME_3 = "Calculation";

    private static final String T1C1 = "NAME";
    private static final String T1C2 = "PH_NO";
    private static final String T1C3 = "GENDER";
    private static final String T1C4 = "AGE";
    private static final String T1C5 = "HEIGHT";
    private static final String T1C6 = "WEIGHT";
    private static final String T1C7 = "USERNAME";
    private static final String T1C8 = "PASSWORD";

    private static final String T2C1 = "ITEM";
    private static final String T2C2 = "CALORIE";

    private static final String T3C4 = "TOTAL_CAL";



    Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + TABLE_NAME_1 + " (NAME TEXT  ,PH_NO TEXT,GENDER TEXT,AGE INTEGER,HEIGHT REAL,WEIGHT REAL,USERNAME TEXT PRIMARY KEY,PASSWORD TEXT)");
        db.execSQL("create table if not exists " + TABLE_NAME_2 + " (ITEM TEXT PRIMARY KEY, CALORIE INTEGER)");
        db.execSQL("create table if not exists " + TABLE_NAME_3 + " (USERNAME TEXT ,ITEM TEXT, CALORIE INTEGER, TOTAL_CAL INTEGER, FOREIGN KEY (USERNAME) REFERENCES "+TABLE_NAME_1+"(USERNAME),FOREIGN KEY (ITEM) REFERENCES "+TABLE_NAME_2+"(ITEM))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_3);
        onCreate(db);
    }

    boolean insertUserData(String name, String ph_no,String gender,String age, String height,String weight,String username,String password ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(T1C1, name);
        contentValues.put(T1C2, ph_no);
        contentValues.put(T1C3, gender);
        contentValues.put(T1C4, age);
        contentValues.put(T1C5, height);
        contentValues.put(T1C6, weight);
        contentValues.put(T1C7, username);
        contentValues.put(T1C8, password);
        long result = db.insert(TABLE_NAME_1, null, contentValues);
        return result != -1;
    }

     boolean insertFoodData(String item, String calorie) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(T2C1,item);
        contentValues.put(T2C2,calorie);
        long result = db.insert(TABLE_NAME_2, null, contentValues);
        return result != -1;
    }
     boolean insertCalcData(String username, String item,String calorie,String total_cal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(T1C7, username);
        contentValues.put(T2C1, item);
        contentValues.put(T2C2, calorie);
        contentValues.put(T3C4, total_cal);
        long result = db.insert(TABLE_NAME_3, null, contentValues);
        return result != -1;
    }

    Cursor getUserData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from " + TABLE_NAME_1, null);
    }
    Cursor getFoodData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from " + TABLE_NAME_2, null);
    }
    Cursor getReviewData(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from " + TABLE_NAME_3+" where "+T1C7+" = ?",new String[]{username}, null);
    }
     public Cursor searchFoodData(String item) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME_2+" where "+T2C1+" = ?",new String[]{item},null);
        return cursor;
    }
    public Cursor valiadteUser(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME_1+" where "+T1C7+" = ?",new String[]{username},null);
        return cursor;
    }
    public Cursor total(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME_3+" where "+T1C7+" = ?",new String[]{username},null);
        return cursor;
    }
 /*
    boolean updateUserData(String name, Long  ph_no,String gender,int age, float height,float weight,String username,String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(T1C1, name);
        contentValues.put(T1C2, ph_no);
        contentValues.put(T1C3, gender);
        contentValues.put(T1C4, age);
        contentValues.put(T1C5, height);
        contentValues.put(T1C6, weight);
        contentValues.put(T1C7, username);
        contentValues.put(T1C8, password);
        db.update(TABLE_NAME, contentValues, "NAME = ?", new String[]{name});
        return true;
    }
*/
    Integer deleteUserData(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_1, "USERNAME= ?", new String[]{username});
    }
}
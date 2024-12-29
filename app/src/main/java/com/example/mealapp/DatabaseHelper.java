package com.example.mealapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ratings.db";
    private static final String TABLE_NAME = "meal_ratings";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "RESTAURANT_NAME";
    private static final String COL_3 = "DISH_NAME";
    private static final String COL_4 = "RATING";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, RESTAURANT_NAME TEXT, DISH_NAME TEXT, RATING REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String restaurantName, String dishName, float rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, restaurantName);
        contentValues.put(COL_3, dishName);
        contentValues.put(COL_4, rating);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1; // Return true if data was inserted
    }
}

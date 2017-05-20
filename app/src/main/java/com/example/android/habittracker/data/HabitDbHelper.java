package com.example.android.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import com.example.android.habittracker.data.HabitContract.HabitEntry;

/**
 * Created by Nicholas on 5/20/2017.
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Habits.db";


    public HabitDbHelper (Context context){
        super (context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        String SQL_CREATE_HABITS_TABLE =  "CREATE TABLE "+HabitEntry.Table_Name + "("+
                HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                HabitEntry.Column_Habit_Name + " TEXT NOT NULL, "+
                HabitEntry.Column_Habit_Description + " TEXT, "+
                HabitEntry.Column_Habit_Due + " INTEGER NOT NULL);";

        db.execSQL(SQL_CREATE_HABITS_TABLE);
    }
    public void onUpgrade (SQLiteDatabase db, int oldVerison, int newVersion){

        onCreate(db);
    }
    public void onDowngrade (SQLiteDatabase db, int oldVerison, int newVersion){
        onUpgrade(db,oldVerison,newVersion);
    }
}

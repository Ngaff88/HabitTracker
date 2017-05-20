package com.example.android.habittracker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.example.android.habittracker.data.HabitContract.HabitEntry;
import com.example.android.habittracker.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new HabitDbHelper(this);
    }

    protected void onStart(){
        super.onStart();
        displayDatabaseInfo();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the habits database.
     */
    private void displayDatabaseInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = { HabitEntry._ID, HabitEntry.Column_Habit_Name,HabitEntry.Column_Habit_Description,
                HabitEntry.Column_Habit_Due};

        Cursor cursor = db.query(HabitEntry.Table_Name, projection, null, null, null, null, null);
        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // habits table in the database).
            TextView displayView = (TextView) findViewById(R.id.text_view_habit);
            displayView.setText("This list contains " + cursor.getCount() + "Habits.\n\n");
            displayView.append(HabitEntry._ID + " - " + HabitEntry.Column_Habit_Name + " - " +
                    HabitEntry.Column_Habit_Description +" - " +HabitEntry.Column_Habit_Due +"\n");

            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HabitEntry.Column_Habit_Name);
            int descColumnIndex = cursor.getColumnIndex(HabitEntry.Column_Habit_Description);
            int dueDateColumnIndex = cursor.getColumnIndex(HabitEntry.Column_Habit_Due);

            while(cursor.moveToNext()){
                int currentId = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentDesc = cursor.getString(descColumnIndex);
                int currentDueDate = cursor.getInt(dueDateColumnIndex);



                displayView.append(("\n" + currentId + " - " + currentName + " - " + currentDesc + " - "
                        + currentDueDate + "\n"));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }
    private void insertHabit(){


        SQLiteDatabase db = mDbHelper.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(HabitEntry.Column_Habit_Name, "Brush Teeth");
        values.put(HabitEntry.Column_Habit_Description, "Make Sure to Brush Your Teeth Twice Today");
        values.put(HabitEntry.Column_Habit_Due, 2);

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(HabitEntry.Table_Name, null, values);
        Log.v("Main Activity","New Row ID" + newRowId);
    }

}

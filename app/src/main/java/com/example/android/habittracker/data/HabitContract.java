package com.example.android.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by Nicholas on 5/20/2017.
 */

public class HabitContract {

    public static abstract class HabitEntry implements BaseColumns {

        public static final String Table_Name = "habits";
        public static final String _ID = BaseColumns._ID;
        public static final String Column_Habit_Name = "name";
        public static final String Column_Habit_Description = "description";
        public static final String Column_Habit_Due = "date due";


        //Possible Choices for Dates
        public static final int Due_Today = 0;
        public static final int Due_Tomorrow = 1;
        public static final int Due_Next_Week = 2;

    }
}

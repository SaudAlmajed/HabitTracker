package com.example.saudalmajed.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by saudalmajed on 9/20/2017 AD.
 */

public final class HabitContract {

    private HabitContract(){}


    public static final class HabitEntry implements BaseColumns{

        public static final String TABLE_NAME="HabitTracker";

        public final static  String _ID=BaseColumns._ID;
        public static final String CULUMN_NAME="nameOfHabit";
        public static final String CULUMN_START_DATE="StartDateOfHabit";
        public static final String CULUMN_END_DATE="EndDateOfHabit";
        public static final String CULUMN_COUNT="DoneOfHabit";

    }
}

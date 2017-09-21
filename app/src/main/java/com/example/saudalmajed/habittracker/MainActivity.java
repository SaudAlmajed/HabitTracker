package com.example.saudalmajed.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.saudalmajed.habittracker.data.HabitContract.HabitEntry;
import com.example.saudalmajed.habittracker.data.HabitDbHelper;

/**
 * Created by saudalmajed on 9/20/2017 AD.
 */

public class MainActivity extends AppCompatActivity{


    private HabitDbHelper habitsDBHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_db);


         habitsDBHelper = new HabitDbHelper(this);

        ContentValues contentValues = new ContentValues();
        contentValues.put(HabitEntry.CULUMN_NAME, "Drinking water");
        contentValues.put(HabitEntry.CULUMN_START_DATE, "28/9/2017");
        contentValues.put(HabitEntry.CULUMN_COUNT, 0);
        habitsDBHelper.insertHabit(contentValues);

        contentValues.put(HabitEntry.CULUMN_END_DATE, "9/10/2017");
        contentValues.put(HabitEntry.CULUMN_COUNT, 1);
        habitsDBHelper.updateHabit(1,contentValues);

        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {
        SQLiteDatabase db = habitsDBHelper.getReadableDatabase();

        String[] projection = {
                HabitEntry._ID,
                HabitEntry.CULUMN_NAME,
                HabitEntry.CULUMN_START_DATE,
                HabitEntry.CULUMN_END_DATE,
        };

        // Perform a query on the pets table
        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        TextView displayView = (TextView) findViewById(R.id.textView);

        try {

            displayView.setText("The Habit table contains " + cursor.getCount() + " Habits.\n\n");
            displayView.append(HabitEntry._ID + " - " +

                    HabitEntry.CULUMN_NAME + " - " +
                    HabitEntry.CULUMN_START_DATE + " - " +
                    HabitEntry.CULUMN_END_DATE + "\n");


            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HabitEntry.CULUMN_NAME);
            int StartColumnIndex = cursor.getColumnIndex(HabitEntry.CULUMN_START_DATE);
            int EndColumnIndex = cursor.getColumnIndex(HabitEntry.CULUMN_END_DATE);


            while (cursor.moveToNext()) {

                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentBreed = cursor.getString(StartColumnIndex);
                int currentGender = cursor.getInt(EndColumnIndex);

                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentBreed + " - " +
                        currentGender ));
            }
        } finally {

            cursor.close();
        }
    }

}

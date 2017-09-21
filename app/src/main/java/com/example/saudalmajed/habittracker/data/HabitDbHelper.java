package com.example.saudalmajed.habittracker.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.saudalmajed.habittracker.data.HabitContract.HabitEntry;
/**
 * Created by saudalmajed on 9/20/2017 AD.
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "habit.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;
    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_HABIT_TABLE =  "CREATE TABLE " + HabitEntry .TABLE_NAME + " ("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry .CULUMN_NAME + " TEXT NOT NULL, "
                + HabitEntry .CULUMN_START_DATE + " INTEGER NOT NULL, "
                + HabitEntry .CULUMN_END_DATE + " INTEGER,"
                + HabitEntry .CULUMN_COUNT + " INTEGER NOT NULL"+ ")";

        db.execSQL(SQL_CREATE_HABIT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // The database is still at version 1, so there's nothing to do be done here.

    }

    public void  insertHabit(ContentValues values){

        db=getWritableDatabase();
        try {
            db.insert(HabitEntry.TABLE_NAME,null,values);

        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            db.close();
        }
    }

    public void updateHabit(int recordId, ContentValues values) {
        db = getWritableDatabase();
        try {
            db.update(
                    HabitContract.HabitEntry.TABLE_NAME,
                    values,
                    HabitContract.HabitEntry._ID + "=" + recordId,
                    null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
    }

    public Cursor readHabits() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                HabitContract.HabitEntry._ID,
                HabitContract.HabitEntry.CULUMN_NAME,
                HabitContract.HabitEntry.CULUMN_START_DATE,
                HabitContract.HabitEntry.CULUMN_END_DATE,
                HabitContract.HabitEntry.CULUMN_COUNT,
        };
        Cursor cursor = db.query(
                HabitContract.HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        return cursor;
    }
}

package com.example.quarantime;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.Date;

public class TaskDBHandler extends SQLiteOpenHelper {
    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "taskDB.db";
    public static final String TABLE_NAME = "Task";
    public static final String COLUMN_ID = "TaskID";
    public static final String COLUMN_NAME = "TaskName";
    public static final String COLUMN_DESC = "Description";
    public static final String COLUMN_TIME = "Time";
    public static final String COLUMN_STATUS = "Status";
    public static final String COLUMN_REMIND = "Reminder";
    public static final String COLUMN_CATEGORY = "Category";
    public static final String COLUMN_SCORE = "Score";

    //initialize the database
    public TaskDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID +
                " INTEGER PRIMARY KEY, " + COLUMN_NAME + " TEXT, " + COLUMN_DESC + " TEXT, " +
                COLUMN_TIME + " TEXT, " + COLUMN_STATUS + " TEXT, " + COLUMN_REMIND + " TEXT, " +
                COLUMN_CATEGORY + " TEXT, " + COLUMN_SCORE + " INTEGER)";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {}
    public String loadHandler() {
        Log.d("taskdbhandler", "load handler");

        String result = "";
        String query = "Select * FROM " + TABLE_NAME;
        Log.d("taskdbhandler", "query created");

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            String result_2 = cursor.getString(2);
            result += String.valueOf(result_0) + " " + result_1 + " " + result_2 +
                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }

    public void addHandler(Task task) {
        Log.d("taskdbhandler", "add handler");

        ContentValues values = new ContentValues();
        Log.d("taskdbhandler", "values thing");

        values.put(COLUMN_ID, task.getID());
        Log.d("taskdbhandler", "1");
        values.put(COLUMN_NAME, task.getName());
        Log.d("taskdbhandler", "2");
        values.put(COLUMN_DESC, task.getDesc());
        Log.d("taskdbhandler", "3");
        values.put(COLUMN_TIME, task.getStringTime());
        Log.d("taskdbhandler", "4");
        values.put(COLUMN_STATUS, task.getStatus());
        Log.d("taskdbhandler", "5");
        values.put(COLUMN_REMIND, task.getReminder());
        Log.d("taskdbhandler", "6");
        values.put(COLUMN_CATEGORY, task.getCategory());
        Log.d("taskdbhandler", "7");
        values.put(COLUMN_SCORE, task.getScore());
        Log.d("taskdbhandler", "8");
        Log.d("taskdbhandler", "put things");

        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("taskdbhandler", "get db");

        db.insert(TABLE_NAME, null, values);
        Log.d("taskdbhandler", "insert things");

        db.close();
    }

    //Take in password and verify it
    public User findHandler(String username) {
        String query = "Select * FROM " + TABLE_NAME + "WHERE" + COLUMN_NAME + " = " + "'" + username + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User user = new User();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            user.setID(Integer.parseInt(cursor.getString(0)));
            user.setUsername(cursor.getString(1));
            cursor.close();
        } else {
            user = null;
        }
        db.close();
        return user;
    }

    //Why does it need to make a User user?
    public boolean deleteHandler(int ID) {
        boolean result = false;
        String query = "Select * FROM" + TABLE_NAME + "WHERE" + COLUMN_ID + " = " + String.valueOf(ID) + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User user = new User();
        if (cursor.moveToFirst()) {
            user.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_NAME, COLUMN_ID + "=?",
                    new String[] {
                            String.valueOf(user.getID())
                    });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public boolean updateHandler(int id, String name, String desc, String time, boolean reminder, String status, String category, int score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_ID, id);
        if (name != null) args.put(COLUMN_NAME, name);
        if (desc != null) args.put(COLUMN_DESC, desc);
        if (time != null) args.put(COLUMN_TIME, time);
        if (status != null) args.put(COLUMN_NAME, status);
        if (category != null) args.put(COLUMN_CATEGORY, category);
        args.put(COLUMN_REMIND, reminder);
        args.put(COLUMN_SCORE, score);
        return db.update(TABLE_NAME, args, COLUMN_ID + "=" + id, null) > 0;
    }

}
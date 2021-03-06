package com.example.quarantime;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;
import java.sql.Date;
import java.text.ParseException;

public class TaskDBHandler extends SQLiteOpenHelper {
    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "taskDB.db";
    public static final String TABLE_NAME = "Task";
    public static final String COLUMN_ID = "TaskID";
    public static final String COLUMN_NAME = "TaskName";
    public static final String COLUMN_DESC = "Description";
    public static final String COLUMN_TIME = "Time";
    public static final String COLUMN_DUR= "Duration";
    public static final String COLUMN_STATUS = "Status";
    public static final String COLUMN_REMIND = "Reminder";
    public static final String COLUMN_CATEGORY = "Category";
    public static final String COLUMN_SCORE = "Score";


    //initialize the database
    public TaskDBHandler(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID +
                " INTEGER PRIMARY KEY, " + COLUMN_NAME + " TEXT, " + COLUMN_DESC + " TEXT, " +
                COLUMN_TIME + " DATE, " + COLUMN_DUR + " DOUBLE, " + COLUMN_STATUS + " TEXT, " + COLUMN_REMIND + " TEXT, " +
                COLUMN_CATEGORY + " TEXT, " + COLUMN_SCORE + " INTEGER)";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {}
    public String getFirstTitle() {
        Log.d("Notes: taskdbhandler", "load handler");

        String result = "";
        String query = "Select * FROM " + TABLE_NAME;
        Log.d("Notes: taskdbhandler", "query created");

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            result = cursor.getString(1);
        }
        cursor.close();
        db.close();
        return result;
    }

    public void addHandler(Task task) throws ParseException {
        Log.d("Notes: taskdbhandler", "add handler");

        ContentValues values = new ContentValues();
        Log.d("Notes: taskdbhandler", "values thing");

        //values.put(COLUMN_ID, task.getID());
        Log.d("Notes: taskdbhandler", "1");
        values.put(COLUMN_NAME, task.getName());
        Log.d("Notes: taskdbhandler", "2");
        values.put(COLUMN_DESC, task.getDesc());
        Log.d("Notes: taskdbhandler", "3");
        values.put(COLUMN_TIME, task.getTimeStr());
        Log.d("Notes: taskdbhandler", "3.5");
        values.put(COLUMN_DUR, task.getDuration());
        Log.d("Notes: taskdbhandler", "4");
        values.put(COLUMN_STATUS, task.getStatus());
        Log.d("Notes: taskdbhandler", "5");
        values.put(COLUMN_REMIND, task.getReminder());
        Log.d("Notes: taskdbhandler", "6");
        values.put(COLUMN_CATEGORY, task.getCategory());
        Log.d("Notes: taskdbhandler", "7");
        values.put(COLUMN_SCORE, task.getScore());
        Log.d("Notes: taskdbhandler", "8");
        Log.d("Notes: taskdbhandler", "put things");

        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("Notes: taskdbhandler", "get db");

        db.insert(TABLE_NAME, null, values);
        Log.d("Notes: taskdbhandler", "insert things");

        db.close();
    }

    public Task deleteHandler(int ID) {
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + ID;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Task task = null;
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String desc = cursor.getString(2);
            String time = cursor.getString(3);
            double duration = cursor.getDouble(4);
            // String status = cursor.getString(5);
            String remind = cursor.getString(6);
            String category = cursor.getString(7);
            int score = cursor.getInt(8);

            boolean rem = false;
            if (remind.equals("true")) rem = true;
            task = new Task(id, name, desc, time, duration, rem, category, score);


            db.delete(TABLE_NAME, COLUMN_ID + "=?",
                    new String[] {
                            String.valueOf(Integer.parseInt(cursor.getString(0)))
                    });
            cursor.close();
        }
        db.close();
        return task;
    }

    public boolean updateHandler(int id, String name, String desc, String time, double duration, boolean reminder, String status, String category, int score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_ID, id);
        if (name != null) args.put(COLUMN_NAME, name);
        if (desc != null) args.put(COLUMN_DESC, desc);
        if (time != null) args.put(COLUMN_TIME, time);
        if (duration > 0) args.put(COLUMN_DUR, duration);
        if (status != null) args.put(COLUMN_NAME, status);
        if (category != null) args.put(COLUMN_CATEGORY, category);
        args.put(COLUMN_REMIND, reminder);
        if (score > 0) args.put(COLUMN_SCORE, score);
        return db.update(TABLE_NAME, args, COLUMN_ID + "=" + id, null) > 0;
    }

    public Task[] getTasks() {
        Log.d("Notes: taskdbhandler", "load handler");

        String query = "Select * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_TIME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        int number = cursor.getCount();

        Task[] result = new Task[number];
        Log.d("Notes: taskdbhandler", "query created");

        db = this.getWritableDatabase();
        cursor = db.rawQuery(query, null);
        for (int i = 0; i < result.length; i++) {
            cursor.moveToNext();

            //Get data
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String desc = cursor.getString(2);
            String time = cursor.getString(3);
            double duration = cursor.getDouble(4);
            // String status = cursor.getString(5);
            String remind = cursor.getString(6);
            String category = cursor.getString(7);
            int score = cursor.getInt(8);

            boolean rem = false;
            if (remind.equals("true")) rem = true;
            Task task = new Task(id, name, desc, time, duration, rem, category, score);

            result[i] = task;
        }

        cursor.close();
        db.close();
        return result;
    }
}
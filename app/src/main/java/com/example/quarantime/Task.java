package com.example.quarantime;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Task {
    private int id;
    private String name;
    private String desc;
    private Date time;
    private String timeStr;
    private double duration;
    private String status;
    private boolean reminder;
    private String category; //Maybe in the future have a set (array) of categories, and pick one (an index) from them?
    private int score;

    public Task(int id, String name, String desc, String time, double duration, boolean reminder, String category, int score) {
        Log.d("task", "create task");
        this.id = id;
        this.name = name;
        this.desc = desc;
        timeStr = time;
        this.duration = duration;
        this.status = "Not Started";
        this.reminder = reminder;
        this.category = category;
        this.score = score;
        Log.d("task", "done creating task");
    }

    /* Set Methods */
    public void setID(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDesc(String description) {
        this.desc = description;
    }
    public void setTime(Date time) {
        this.time = time;
    }
    public void setDuration(double duration){
        this.duration = duration;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setReminder(boolean reminder) {
        this.reminder = reminder;
    }
    public void getCategory(String category) {
        this.category = category;
    }
    public void getScore(int score) {
        this.score = score;
    }

    /* Get Methods */
    public int getID() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public String getDesc() {
        return this.desc;
    }
    public Date getTime() throws ParseException {
        if (time != null)
            return this.time;

        // SimpleDateFormat d = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
        SimpleDateFormat d = new SimpleDateFormat("dd/mm/yyyy", Locale.US);
        this.time = d.parse(timeStr);
        return time;
    }
    public String getTimeStr() {
        return timeStr;
    }
    public String getYear() {
        return timeStr.substring(0,4);
    }
    public String getMonth() {
        return timeStr.substring(5,7);
    }
    public String getDay() {
        return timeStr.substring(8);
    }
    public double getDuration() {
        return this.duration;
    }
    public String getStatus() {
        return this.status;
    }
    public boolean getReminder() {
        return this.reminder;
    }
    public String getCategory() {
        return this.category;
    }
    public int getScore() {
        return this.score;
    }
}
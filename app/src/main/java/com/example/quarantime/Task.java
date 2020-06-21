package com.example.quarantime;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
    private int id;
    private String name;
    private String desc;
    private Date time;
    private String status;
    private boolean reminder;
    private String category; //Maybe in the future have a set (array) of categories, and pick one (an index) from them?
    private int score;

    public Task() {
        System.out.println("ohno");
    }
    public Task(int id, String name, String desc, Date time, boolean reminder, String category, int score) {
        Log.d("task", "create task");
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.time = time;
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
    public void getDesc(String description) {
        this.desc = description;
    }
    public void getTime(Date time) {
        this.time = time;
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
    public Date getTime() {
        return this.time;
    }
    public String getStringTime(){
        if (this.time == null) return "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        return dateFormat.format(this.time);

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
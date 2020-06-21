package com.example.quarantime;

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

    public Task() {}
    public Task(int id, String name, String desc, Date time, boolean reminder, String category, int score) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.time = time;
        this.status = "Not Started";
        this.reminder = reminder;
        this.category = category;
        this.score = score;

    }
    public void setID(int id) {
        this.id = id;
    }
    public int getID() {
        return this.id;
    }
    public String getDesc() {
        return this.desc;
    }
    public Date getTime() {
        return this.time;
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
    //Make set methods for everything

}
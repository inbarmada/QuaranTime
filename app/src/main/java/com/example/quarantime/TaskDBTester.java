package com.example.quarantime;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.util.Date;

public class TaskDBTester extends AppCompatActivity {
    static int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_db_tester);
    }


    public void addTask(View view) throws ParseException {
        //Debug
        Log.d("tasktester", "Added");

        //Get username and password variables
        EditText nameET   = (EditText)findViewById(R.id.taskname);
        EditText descET   = (EditText)findViewById(R.id.taskdesc);
        Log.d("tasktester", "Added1");

        //Create database
        TaskDBHandler dbHandler = new TaskDBHandler(this, null);
        Log.d("tasktester", "Added2");

        //int id = Integer.parseInt(userid.getText().toString());
        String name = nameET.getText().toString();
        String desc = descET.getText().toString();
        Log.d("tasktester", "Name: " + name + " Description: " + desc);
        Task task = new Task(name, desc, "", false, "study", 5);
        Log.d("tasktester", "new task");

        dbHandler.addHandler(task);
        Log.d("tasktester", "handle task");

        nameET.setText("");
        descET.setText("");
        Log.d("tasktester", "finish");

    }

    public void loadTasks(View view) {
        //Debug
        Log.d("tasktester", "Loaded");

        //Get username and password variables
        EditText username   = (EditText)findViewById(R.id.taskname);
        EditText userpassword   = (EditText)findViewById(R.id.taskdesc);
        TextView show = (TextView)findViewById(R.id.show);
        //Create database
        TaskDBHandler dbHandler = new TaskDBHandler(this, null);
        show.setText(dbHandler.loadHandler());
        username.setText("");
        userpassword.setText("");

    }
}

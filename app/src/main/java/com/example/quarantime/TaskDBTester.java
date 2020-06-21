package com.example.quarantime;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class TaskDBTester extends AppCompatActivity {
    static int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_db_tester);
    }


    public void addTask(View view) {
        //Debug
        Log.d("addUser", "Added");

        //Get username and password variables
        EditText nameET   = (EditText)findViewById(R.id.username);
        EditText descET   = (EditText)findViewById(R.id.username);
        //Create database
        TaskDBHandler dbHandler = new TaskDBHandler(this, null, null, 1);
        //int id = Integer.parseInt(userid.getText().toString());
        String name = nameET.getText().toString();
        String desc = descET.getText().toString();
        Log.d("loadUsers", "Name: " + name + " Description: " + desc);
        Task task = new Task(++id, name, desc, null, false, "study", 5);
        dbHandler.addHandler(task);
        nameET.setText("");
        descET.setText("");
    }
}

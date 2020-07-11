package com.example.quarantime;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import java.util.Date;

public class Pop extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popwindow);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.6));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y=-20;
        getWindow().setAttributes(params);
    }

    static int taskID = 1;
    public void addTaskButton(View view) {

        Log.d("Notes: pop", "adding task");

        TaskDBHandler dbHandler = new TaskDBHandler(this, null);
        //Date d =
        Task t = new Task(taskID++, "Homework", "Tough", new Date(), false, "school", 15);

        dbHandler.addHandler(t);
    }
}

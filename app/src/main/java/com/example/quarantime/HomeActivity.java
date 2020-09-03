package com.example.quarantime;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.quarantime.ui.dashboard.DashboardFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        getScore();
    }

    public void addTaskClicked (View view) {
        Log.d("Notes: HomeActivity", "add task clicked");
        Intent i = new Intent(getApplicationContext(),Pop.class);
        startActivityForResult(i, 2);
    }

    // For getting result from pop in order to refresh dashboard view
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==2) {
            TaskDBHandler taskDB = new TaskDBHandler(this, null);
            Log.d("Notes: HomeActivity", "creating fragment: ");
            DashboardFragment dashboard = new DashboardFragment();
            Log.d("Notes: HomeActivity", "drawing fragment: ");
            RecyclerView r = findViewById(R.id.recView);
            dashboard.updateTasksView(r, taskDB);
            Log.d("Notes: HomeActivity", "done with fragment: ");
        }

    }


    public void taskCompleted(View view) {
        Log.d("Notes: HomeActivity", "taskCompleted");
        CheckBox c = (CheckBox) view;
        ConstraintLayout constlayout = (ConstraintLayout)(c.getParent().getParent().getParent());
        TextView id = (TextView) constlayout.getChildAt(0);
        Log.d("Notes: HomeActivity", "taskCompleted: " + c.getText());
        Log.d("Notes: HomeActivity", "taskCompleted: " + id.getText());
        // Get database
        TaskDBHandler taskDB = new TaskDBHandler(this, null);
        // Get deleted task
        Task t = taskDB.deleteHandler(Integer.parseInt(id.getText() + ""));
        // Add to score
        addScore(t.getScore());
        Log.d("Notes: HomeActivity", "creating fragment: ");
        DashboardFragment dashboard = new DashboardFragment();
        Log.d("Notes: HomeActivity", "drawing fragment: ");
        RecyclerView r = findViewById(R.id.recView);
        dashboard.updateTasksView(r, taskDB);
        Log.d("Notes: HomeActivity", "done with fragment: ");
    }


    public int getScore() {
        int score = 0;
        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        try {
            score = prefs.getInt("score", 0); //0 is the default value
            Log.d("Notes: HomeActivity", "Getting score : " + score);
        } catch (Exception e) {
            Log.d("Notes: HomeActivity", "Getting score : " + 0 + " (zero)");
        }
        return score;
    }

    public void addScore(int amount) {
        Log.d("Notes: HomeActivity", "Putting score : ");
        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("score", getScore() + amount);
        editor.apply();
    }

    public void cardClicked(View view) {
        Log.d("Notes: HomeActivity", "cardClicked");
        ConstraintLayout constlayout = (ConstraintLayout)((CardView)view).getChildAt(0);
        TextView expand_is_on = (TextView)constlayout.getChildAt(3);

        LinearLayout bottomTask = (LinearLayout)constlayout.getChildAt(2);
        LinearLayout bottomRight = (LinearLayout)bottomTask.getChildAt(1);
        LinearLayout durView = (LinearLayout)bottomRight.getChildAt(1);
        FrameLayout catView = (FrameLayout)bottomRight.getChildAt(2);
        Button editButton = (Button)bottomRight.getChildAt(3);
        Log.d("Notes: HomeActivity", "/" + expand_is_on.getText() + "/ msg");
        if (expand_is_on.getText().equals("")) {
            Log.d("Notes: HomeActivity", "not expanded");

            durView.setVisibility(View.VISIBLE);
            catView.setVisibility(View.VISIBLE);
            editButton.setVisibility(View.VISIBLE);
            expand_is_on.setText("1");
        } else {
            Log.d("Notes: HomeActivity", "expanded");
            durView.setVisibility(View.GONE);
            catView.setVisibility(View.GONE);
            editButton.setVisibility(View.GONE);
            expand_is_on.setText("");
        }
    }

    public void editTask(View view) {
        ConstraintLayout constlayout = (ConstraintLayout)(view.getParent().getParent().getParent());
        LinearLayout taskTop = (LinearLayout)constlayout.getChildAt(1);
        LinearLayout titleHolder = (LinearLayout)taskTop.getChildAt(0);
        LinearLayout bottomTask = (LinearLayout)constlayout.getChildAt(2);
        LinearLayout bottomRight = (LinearLayout)bottomTask.getChildAt(1);

        TextView taskID = (TextView)constlayout.getChildAt(0);

        EditText titleView = (EditText)titleHolder.getChildAt(1);
        EditText dateView = (EditText)taskTop.getChildAt(1);
        EditText descView = (EditText)bottomTask.getChildAt(0);
        EditText scoreView = (EditText)((LinearLayout)bottomRight.getChildAt(0)).getChildAt(1);
        EditText durView = (EditText)((LinearLayout)bottomRight.getChildAt(1)).getChildAt(1);
        Spinner catView = (Spinner)((FrameLayout)bottomRight.getChildAt(2)).getChildAt(0);

        Button editButton = (Button)bottomRight.getChildAt(3);

        if (editButton.getText().equals("EDIT")) {
            titleView.setEnabled(true);
            dateView.setEnabled(true);
            descView.setEnabled(true);
            scoreView.setEnabled(true);
            durView.setEnabled(true);
            catView.setEnabled(true);
            editButton.setText(R.string.done);
        } else {
            int id = Integer.parseInt(taskID.getText() + "");
            TaskDBHandler taskDB = new TaskDBHandler(this, null);
            String title = titleView.getText() + "";
            String desc = descView.getText() + "";
            double duration = Double.parseDouble(durView.getText() + "");
            int score = Integer.parseInt(scoreView.getText() + "");
            taskDB.updateHandler(id, title, desc, null, duration, false, null, null, score);
            titleView.setEnabled(false);
            dateView.setEnabled(false);
            descView.setEnabled(false);
            scoreView.setEnabled(false);
            durView.setEnabled(false);
            catView.setEnabled(false);
            editButton.setText(R.string.edit);
        }
    }
}
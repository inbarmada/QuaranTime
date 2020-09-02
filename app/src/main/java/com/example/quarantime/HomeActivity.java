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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void cardClicked(View view) {
        Log.d("Notes: HomeActivity", "cardClicked");
        ConstraintLayout constlayout = (ConstraintLayout)((CardView)view).getChildAt(0);
        TextView expand_is_on = (TextView)constlayout.getChildAt(3);

        LinearLayout bottomTask = (LinearLayout)constlayout.getChildAt(2);
        LinearLayout bottomRight = (LinearLayout)bottomTask.getChildAt(1);
        EditText durView = (EditText)bottomRight.getChildAt(1);
        EditText catView = (EditText)bottomRight.getChildAt(2);
        Button editButton = (Button)bottomRight.getChildAt(3);
        Log.d("Notes: HomeActivity", "/" + expand_is_on.getText() + "/ msg");
        if (expand_is_on.getText().equals("")) {
            Log.d("Notes: HomeActivity", "not expanded");

            durView.setVisibility(View.VISIBLE);
            catView.setVisibility(View.VISIBLE);
            editButton.setVisibility(View.VISIBLE);
            expand_is_on.setText("expanded");
        } else {
            Log.d("Notes: HomeActivity", "expanded");
            durView.setVisibility(View.GONE);
            catView.setVisibility(View.GONE);
            editButton.setVisibility(View.GONE);
            expand_is_on.setText("");
        }


    }
}
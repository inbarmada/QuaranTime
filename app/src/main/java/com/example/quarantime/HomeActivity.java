package com.example.quarantime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.quarantime.ui.dashboard.DashboardFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    private static int id = 0;
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
            RecyclerView r = (RecyclerView) findViewById(R.id.recView);
            dashboard.updateTasksView(r, taskDB);
            Log.d("Notes: HomeActivity", "done with fragment: ");
        }

    }


    public void taskCompleted(View view) {
        Log.d("Notes: HomeActivity", "taskCompleted");
        CheckBox c = (CheckBox) view;
        CardView card = (CardView) view.getParent();
        TextView id = (TextView) card.getChildAt(0);
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
        RecyclerView r = (RecyclerView) findViewById(R.id.recView);
        dashboard.updateTasksView(r, taskDB);
        Log.d("Notes: HomeActivity", "done with fragment: ");
    }

    public void showScore(View view) {
        TextView tv_score = (TextView)findViewById(R.id.tv_score);
        tv_score.setText(""+getScore());
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

    public void getScore(View view) {
        TextView t = (TextView) findViewById(R.id.tv_score);
        t.setText("Score : " + getScore());
    }

    public void addScore(int amount) {
        Log.d("Notes: HomeActivity", "Putting score : ");
        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("score", getScore() + amount);
        editor.apply();
    }
}
package com.example.quarantime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    }

    public void addTaskClicked (View view) {
        Log.d("Notes: HomeActivity", "add task clicked");
        Intent i = new Intent(getApplicationContext(),Pop.class);
        startActivity(i);
    }
    
    public void addUser(View view) {
        //Debug
        Log.d("Notes: addUser", "Added");


        //Get username and password variables
        EditText username   = (EditText)findViewById(R.id.username);
        EditText userpassword   = (EditText)findViewById(R.id.username);
        //Create database
        DBHandler dbHandler = new DBHandler(this, null, null, 1);
        //int id = Integer.parseInt(userid.getText().toString());
        id++;
        String name = username.getText().toString();
        int password_hash = (userpassword.getText().toString()).hashCode();
        Log.d("Notes: loadUsers", "Name:" + name + "  Password:"+password_hash);
        User user = new User(id, name, password_hash);
        dbHandler.addHandler(user);
        username.setText("");
        userpassword.setText("");
    }


    public void loadUsers(View view) {
        //Debug
        Log.d("Notes: loadUsers", "Loaded");

        //Get username and password variables
        EditText username   = (EditText)findViewById(R.id.username);
        EditText userpassword   = (EditText)findViewById(R.id.username);
        TextView show = (TextView)findViewById(R.id.show);
        //Create database
        DBHandler dbHandler = new DBHandler(this, null, null, 1);
        show.setText(dbHandler.loadHandler());
        username.setText("");
        userpassword.setText("");
    }

//    public void getScore(View view) {
//        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
//        try {
//            int score = prefs.getInt("score", 0); //0 is the default value
//            Log.d("HomeActivity", "Getting score : " + score);
//        } catch (Exception e) {
//            Log.d("HomeActivity", "Getting score : " + 0 + " (zero)");
//        }
//    }

    public int getScore() {
        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        try {
            int score = prefs.getInt("score", 0); //0 is the default value
            Log.d("Notes: HomeActivity", "Getting score : " + score);
            return score;
        } catch (Exception e) {
            Log.d("Notes: HomeActivity", "Getting score : " + 0 + " (zero)");
            return 0;
        }
    }

    public  void addScore(View view) {
        Log.d("Notes: HomeActivity", "Putting score : ");
        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("Notes: score", getScore() + 5);
        editor.commit();
        Log.d("Notes: HomeActivity", "Checking score : " + getScore());
        TextView t = (TextView) findViewById(R.id.displayScore);
        t.setText("Notes: Score : " + getScore());
    }



}
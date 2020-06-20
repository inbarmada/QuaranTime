package com.example.quarantime;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    private static int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


    public void addUser(View view) {
        //Get username and password variables
        EditText username   = (EditText)findViewById(R.id.username);
        EditText userpassword   = (EditText)findViewById(R.id.username);
        //Create database
        DBHandler dbHandler = new DBHandler(this, null, null, 1);
        //int id = Integer.parseInt(userid.getText().toString());
        id++;
        String name = username.getText().toString();
        int password_hash = (userpassword.getText().toString()).hashCode();
        User user = new User(id, name, password_hash);
        dbHandler.addHandler(user);
        username.setText("");
        userpassword.setText("");
    }


    public void loadUsers(View view) {
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

}
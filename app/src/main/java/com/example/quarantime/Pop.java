package com.example.quarantime;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Date;

public class Pop extends Activity {
    private EditText edittext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popwindow);
        Spinner spinner = (Spinner) findViewById(R.id.taskCatSP);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.8));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;
        getWindow().setAttributes(params);
        addKeyListener();
    }



    public void addTaskButton(View view) throws ParseException {

        Log.d("Notes: pop", "adding task");

        TaskDBHandler dbHandler = new TaskDBHandler(this, null);
        String name = ((EditText)findViewById(R.id.taskNameET)).getText().toString();
        String desc = ((EditText)findViewById(R.id.taskDescET)).getText().toString();
        String date = ((EditText)findViewById(R.id.taskDateET)).getText().toString();
        String cat = ((Spinner)findViewById(R.id.taskCatSP)).getSelectedItem().toString();
        boolean rem = ((CheckBox)findViewById(R.id.taskRemCB)).isChecked();
        int score = Integer.parseInt(((EditText)findViewById(R.id.taskScoreET)).getText().toString());

        Task t = new Task(-1, name, desc, date, rem, cat, score);

        dbHandler.addHandler(t);
    }

    public void addKeyListener() {

        // get edittext component
        edittext = (EditText) findViewById(R.id.taskDescET);

        // add a keylistener to monitor the keaybord avitvity...
        edittext.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                // if the users pressed a button and that button was "0"
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_0)) {

                    // display the input text....
                    Toast.makeText(Pop.this,edittext.getText(), Toast.LENGTH_LONG).show();
                    return true;

                    // if the users pressed a button and that button was "9"
                } else if ((event.getAction() == KeyEvent.ACTION_DOWN)  && (keyCode == KeyEvent.KEYCODE_9)) {

                    // display message
                    Toast.makeText(Pop.this, "Number 9 is pressed!", Toast.LENGTH_LONG).show();
                    return true;
                }

                return false;
            }
        });
    }
}

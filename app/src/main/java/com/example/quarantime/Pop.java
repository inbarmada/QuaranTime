package com.example.quarantime;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Locale;


public class Pop extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popwindow);
        Spinner spinner = findViewById(R.id.taskCatSP);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.6));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;
        getWindow().setAttributes(params);
    }


    public void addTaskButton(View view) throws ParseException {

        Log.d("Notes: pop", "adding task");

        TaskDBHandler dbHandler = new TaskDBHandler(this, null);
        String name = ((EditText)findViewById(R.id.taskNameET)).getText().toString();
        String desc = ((EditText)findViewById(R.id.taskDescET)).getText().toString();
        String date = ((Button)findViewById(R.id.taskDateET)).getText().toString();
        double duration = Double.parseDouble(((EditText)findViewById(R.id.taskDurET)).getText().toString());
        String cat = ((Spinner)findViewById(R.id.taskCatSP)).getSelectedItem().toString();
        boolean rem = ((CheckBox)findViewById(R.id.taskRemCB)).isChecked();
        int score = Integer.parseInt(((EditText)findViewById(R.id.taskScoreET)).getText().toString());

        Task t = new Task(-1, name, desc, date, duration, rem, cat, score);

        dbHandler.addHandler(t);

        Intent i = new Intent(getApplicationContext(), HomeActivity.class);
        i.putExtra("result_code", 1);
        setResult(2,i);
        finish();
    }

    public void showDatePickerDialog(View view) {
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day){
                Button date = findViewById(R.id.taskDateET);
                String d = String.format(Locale.US, "%02d", day);
                String m = String.format(Locale.US, "%02d", (month + 1));
                String y = String.format(Locale.US, "%04d", year);
                String dateString = d + "/" + m + "/" + y;
                date.setText(dateString);
            }
        }, day, month, year);
        dpd.getDatePicker().setMinDate(System.currentTimeMillis());
        dpd.show();
    }

}

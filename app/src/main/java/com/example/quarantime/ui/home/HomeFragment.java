package com.example.quarantime.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.quarantime.R;
import com.example.quarantime.TaskDBHandler;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.textHome);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                String welcome = "Welcome ";
                textView.setText(welcome);
            }
        });
        setNextTask(root);
        setCompletedTasks(root);
        return root;
    }

    public void setNextTask(View root) {
        TextView nextTask = (TextView) root.findViewById(R.id.nextTask);
        TaskDBHandler taskDB = new TaskDBHandler(getContext(), null);
        nextTask.setText(taskDB.getFirstTitle());
    }

    public void setCompletedTasks(View root) {
        Log.d("Notes: HomeAc", "set completed tasks");

        String one = null;
        String two = null;
        String three = null;
        try {
            SharedPreferences prefs = this.getActivity().getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
            one = prefs.getString("completedOne", null); //0 is the default value
            two = prefs.getString("completedTwo", null); //0 is the default value
            three = prefs.getString("completedThree", null); //0 is the default value
        } catch (Exception e) {
            Log.d("Notes: HomeFragment", "problem getting tasks");
        }
        Log.d("Notes: HomeAc", one + "//////" + two + "//////" + three);

        TextView completedOne = (TextView) root.findViewById(R.id.completedOne);
        setCompletedText(completedOne, one);
        TextView completedTwo = (TextView) root.findViewById(R.id.completedTwo);
        setCompletedText(completedTwo, two);
        TextView completedThree = (TextView) root.findViewById(R.id.completedThree);
        setCompletedText(completedThree, three);
    }

    public void setCompletedText(TextView tv, String text) {
        if (text != null) {
            tv.setVisibility(View.VISIBLE);
            tv.setText(text);
        } else {
            tv.setVisibility(View.GONE);
        }
    }
}
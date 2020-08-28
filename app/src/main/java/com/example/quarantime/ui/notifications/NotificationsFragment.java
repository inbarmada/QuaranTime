package com.example.quarantime.ui.notifications;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.quarantime.HomeActivity;
import com.example.quarantime.R;

public class NotificationsFragment extends Fragment {
    private int score = 0;
    TextView tv_score;
    TextView displayScore;
    Button add;
    private NotificationsViewModel notificationsViewModel;
    View cont;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        cont = container;
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        tv_score = (TextView) root.findViewById(R.id.tv_score);
        getScore();
        return root;
    }

    public void adds(View view){
        score += 5;
        tv_score.setText(""+score);
    }

    public int getScore() {
        int score = 0;
        SharedPreferences prefs = this.getActivity().getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        try {
            score = prefs.getInt("score", 0); //0 is the default value
            Log.d("Notes: HomeActivity", "Getting score : " + score);
        } catch (Exception e) {
            Log.d("Notes: HomeActivity", "Getting score : " + 0 + " (zero)");
        }
        tv_score.setText("Score : " + score);
        return score;
    }
}
package com.example.quarantime.ui.notifications;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.quarantime.R;

public class NotificationsFragment extends Fragment {
    TextView tv_score;
    View cont;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
    // NotificationsViewModel notificationsViewModel =
    //              ViewModelProviders.of(this).get(NotificationsViewModel.class);
        cont = container;
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        tv_score = root.findViewById(R.id.tv_score);
        getScore();
        return root;
    }

    public int getScore() {
        int score = 0;
        try {
            SharedPreferences prefs = this.getActivity().getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
            score = prefs.getInt("score", 0); //0 is the default value
            Log.d("Notes: HomeActivity", "Getting score : " + score);
        } catch (Exception e) {
            Log.d("Notes: HomeActivity", "Getting score : " + 0 + " (zero)");
        }
        String scr = "Score : " + score;
        tv_score.setText(scr);
        return score;
    }
}
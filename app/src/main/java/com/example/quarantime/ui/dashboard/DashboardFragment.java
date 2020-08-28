package com.example.quarantime.ui.dashboard;

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
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quarantime.R;
import com.example.quarantime.Task;
import com.example.quarantime.TaskDBHandler;

import java.text.ParseException;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private RecyclerView recyclerView;
    // private RecyclerView.LayoutManager layoutManager;
    private View root;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        Log.d("Notes: DashFragment", "oncreateview created root");
        recyclerView = (RecyclerView) root.findViewById(R.id.recView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        TaskDBHandler taskDB = new TaskDBHandler(getContext(), null);
        root = updateTasksView(recyclerView, taskDB);
        return root;
    }

    public View updateTasksView(RecyclerView recyclerView, TaskDBHandler taskDB){
        Log.d("Notes: DashFragment", "drawlay: creating recView");

        // 1. get a reference to recyclerView
        Log.d("Notes: DashFragment", "drawlay: createddd recView");

        Log.d("Notes: DashFragment", "drawlay: set size");

        Log.d("Notes: DashFragment", "oncreateview: created recView");

        // 2. set layoutManger
        Log.d("Notes: DashFragment", "oncreateview: set layout manager");

        // 3. Get Data
        Task[] taskList = taskDB.getTasks();
        Log.d("Notes: DashFragment", "oncreateview: get tasks");

        for (int i = 0; i < taskList.length; i++) {
            Log.d("Notes: DashFragment", "task name " + i + " " + taskList[i].getName());
        }
        // 4. create an adapter
        MyAdapter mAdapter = new MyAdapter(taskList);
        Log.d("Notes: DashFragment", "oncreateview: create adapter");
        // 5. set adapter

        Log.d("Notes: DashFragment", "oncreateview: set adapter");
        recyclerView.setAdapter(mAdapter);
        Log.d("Notes: DashFragment", "oncreateview: set adapter");
        return root;
    }
}
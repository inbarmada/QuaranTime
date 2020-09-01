package com.example.quarantime.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quarantime.R;
import com.example.quarantime.Task;
import com.example.quarantime.TaskDBHandler;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    // private RecyclerView.LayoutManager layoutManager;
    private View root;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        Log.d("Notes: DashFragment", "oncreateview created root");
        RecyclerView recyclerView = root.findViewById(R.id.recView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        TaskDBHandler taskDB = new TaskDBHandler(getContext(), null);
        root = updateTasksView(recyclerView, taskDB);
        return root;
    }

    public View updateTasksView(RecyclerView recyclerView, TaskDBHandler taskDB){
        // get data
        Task[] taskList = taskDB.getTasks();
        Log.d("Notes: DashFragment", "oncreateview: got tasks");

        // create an adapter
        MyAdapter mAdapter = new MyAdapter(taskList);
        Log.d("Notes: DashFragment", "oncreateview: create adapter");

        // set adapter
        recyclerView.setAdapter(mAdapter);
        Log.d("Notes: DashFragment", "oncreateview: set adapter");
        return root;
    }
}
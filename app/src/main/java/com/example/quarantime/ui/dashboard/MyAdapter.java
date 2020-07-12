package com.example.quarantime.ui.dashboard;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quarantime.R;
import com.example.quarantime.Task;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Task[] mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public CheckBox checkBox;
        public MyViewHolder(CardView v) {
            super(v);
            checkBox = (CheckBox)v.getChildAt(0);
            textView = (TextView)v.getChildAt(1);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(Task[] myDataset) {
        Log.d("Notes: MyAdapter", "constructor");
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {

        Log.d("Notes: MyAdapter", "oncreateviewholder created textview");


        // create a new view
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_view, parent, false);

        Log.d("Notes: MyAdapter", "oncreateviewholder created textview");

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Log.d("Notes: DashFragment", "onBindViewHolder");
        holder.checkBox.setText(mDataset[position].getName());
        holder.textView.setText(mDataset[position].getDesc());
        Log.d("Notes: DashFragment", "onBindViewHolder done");
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
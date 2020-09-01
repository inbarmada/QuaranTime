package com.example.quarantime.ui.dashboard;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quarantime.R;
import com.example.quarantime.Task;

import java.text.ParseException;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Task[] mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView descView;
        public TextView taskID;
        public TextView dateView;
        public TextView scoreView;
        public CheckBox checkBox;
        public CardView card;
        public MyViewHolder(CardView v) {
            super(v);
            card = v;
            ConstraintLayout constlayout = (ConstraintLayout)v.getChildAt(0);
            taskID = (TextView)constlayout.getChildAt(0);
            checkBox = (CheckBox)constlayout.getChildAt(1);
            dateView = (TextView)constlayout.getChildAt(2);
            descView = (TextView)constlayout.getChildAt(3);
            scoreView = (TextView)constlayout.getChildAt(4);
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
        holder.taskID.setText(mDataset[position].getID() + "");
        holder.checkBox.setText(mDataset[position].getName());
        holder.dateView.setText("Due: " + mDataset[position].getTimeStr());
        holder.descView.setText(mDataset[position].getDesc());
        holder.scoreView.setText("Score: " + mDataset[position].getScore());
        int drawable_color = R.drawable.solid_red_box;
        if (position % 4 == 0) {
            holder.card.setBackgroundResource(R.drawable.box_outline_blue);
            drawable_color = R.drawable.solid_blue_box;
        } else if (position % 4 == 1) {
            holder.card.setBackgroundResource(R.drawable.box_outline_green);
            drawable_color = R.drawable.solid_green_box;
        } else if (position % 4 == 2) {
            holder.card.setBackgroundResource(R.drawable.box_outline_yellow);
            drawable_color = R.drawable.solid_yellow_box;
        } else {
            holder.card.setBackgroundResource(R.drawable.box_outline_red);
        }
        holder.checkBox.setBackgroundResource(drawable_color);
        holder.dateView.setBackgroundResource(drawable_color);
        holder.descView.setBackgroundResource(drawable_color);
        holder.scoreView.setBackgroundResource(drawable_color);


        Log.d("Notes: DashFragment", "onBindViewHolder done");
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
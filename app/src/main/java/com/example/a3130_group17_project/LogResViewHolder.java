package com.example.a3130_group17_project;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class LogResViewHolder extends RecyclerView.ViewHolder {
    TextView name, percentage,date;

    public LogResViewHolder(View view) {
        super(view);
        name = view.findViewById(R.id.product_name);
        percentage = view.findViewById(R.id.product_percentage);
        date=view.findViewById(R.id.log_date);
    }
}



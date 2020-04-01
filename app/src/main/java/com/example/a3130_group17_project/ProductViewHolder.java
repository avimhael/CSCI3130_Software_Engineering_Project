package com.example.a3130_group17_project;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    TextView name, percentage;

    public ProductViewHolder(View view) {
        super(view);
        name = view.findViewById(R.id.product_name);
        percentage = view.findViewById(R.id.product_percentage);
    }

    public void setListener(final Product p, final RecyclerViewClickListener listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(p, view);
            }
        });
    }


}


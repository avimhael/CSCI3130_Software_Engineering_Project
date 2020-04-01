package com.example.a3130_group17_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder>{
    private List<Product> data;
    private RecyclerViewClickListener aListener;
    public ProductAdapter(List<Product> list, RecyclerViewClickListener listener){
        super();
        aListener = listener;
        data = list;
    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent,false);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.setListener(data.get(position), aListener);
        holder.name.setText(data.get(position).getName());
        holder.percentage.setText(data.get(position).getPercentage());

        if (data.get(position) instanceof AlcoholProduct){
            holder.itemView.setBackgroundResource(R.color.aclBG);
        }
        else{
            holder.itemView.setBackgroundResource(R.color.canBG);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

package com.example.a3130_group17_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import androidx.annotation.NonNull;

public class CannabisProductAdapter extends FirestoreRecyclerAdapter<CannabisProduct, ProductViewHolder> {
    private RecyclerViewClickListener aListener;

    public CannabisProductAdapter(FirestoreRecyclerOptions<CannabisProduct> options, RecyclerViewClickListener listener) {
        super(options);
        this.aListener = listener;
    }

    @Override
    protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull CannabisProduct model) {
        holder.name.setText(model.getName());
        holder.setListener(model, aListener);
        holder.percentage.setText(model.getPercentage());
        holder.itemView.setBackgroundResource(R.color.canBG);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent,false);
        return new ProductViewHolder(v);
    }
}

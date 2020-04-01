package com.example.a3130_group17_project;

import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;


public class AlcoholProductAdapter extends FirestoreRecyclerAdapter<AlcoholProduct, ProductViewHolder> {
    private RecyclerViewClickListener aListener;

    public AlcoholProductAdapter(FirestoreRecyclerOptions<AlcoholProduct> options, RecyclerViewClickListener listener) {
        super(options);
        this.aListener = listener;
    }

    @Override
    protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull AlcoholProduct model) {
        holder.setListener(model, aListener);
        holder.name.setText(model.getName());
        holder.percentage.setText(model.getPercentage());
        holder.itemView.setBackgroundResource(R.color.aclBG);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent,false);
        return new ProductViewHolder(v);
    }
}

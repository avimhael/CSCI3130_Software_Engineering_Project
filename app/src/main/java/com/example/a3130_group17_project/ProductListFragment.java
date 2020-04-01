package com.example.a3130_group17_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/** Fragment for viewing the product list.
 * @author      Tongtong Zhang
 * @author      Allan Jones
 * @author      Matt Wuard
 * @author      Ziheng Shi
 * @author      Omar Shams
 * @author      Yunzhong Xiao
 */
public class ProductListFragment extends Fragment {
    RecyclerView list;
    FirebaseFirestore fdb = FirebaseFirestore.getInstance();
    ProductAdapter allProductAdapter;
    FirestoreRecyclerAdapter<AlcoholProduct, ProductViewHolder> alcAdapter;
    FirestoreRecyclerAdapter<CannabisProduct, ProductViewHolder> canAdapter;

    List<Product> allProductList = new ArrayList<Product>();
    BottomNavigationView bottomNavigationView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.product_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        bottomNavigationView = view.findViewById(R.id.bottom_nav_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.cannabis_button:
                        list.setAdapter(canAdapter);
                        return true;
                    case R.id.alcohol_button:
                        list.setAdapter(alcAdapter);
                        return true;
                    case R.id.all_product_button:
                        list.setAdapter(allProductAdapter);
                        return true;
                }
                return false;
            }
        });

        list = view.findViewById(R.id.list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        list.setLayoutManager(layoutManager);

        Query alcListQuery = fdb.collection("defaultAlcoholList").orderBy("name");
        Query canListQuery = fdb.collection("defaultCannabisList").orderBy("name");

        alcListQuery.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for (QueryDocumentSnapshot doc : queryDocumentSnapshots){
                    allProductList.add(doc.toObject(AlcoholProduct.class));
                }
            }
        });

        canListQuery.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for (QueryDocumentSnapshot doc : queryDocumentSnapshots){
                    allProductList.add(doc.toObject(CannabisProduct.class));
                }
            }
        });

        FirestoreRecyclerOptions<AlcoholProduct> alcOptions = new FirestoreRecyclerOptions.Builder<AlcoholProduct>()
                .setQuery(alcListQuery, AlcoholProduct.class)
                .build();

        FirestoreRecyclerOptions<CannabisProduct> canOptions = new FirestoreRecyclerOptions.Builder<CannabisProduct>()
                .setQuery(canListQuery, CannabisProduct.class)
                .build();

        alcAdapter = new AlcoholProductAdapter(alcOptions, new RecyclerViewClickListener() {
            @Override
            public void onItemClick(Product p, View view) {
                Intent intent = new Intent (view.getContext(), AlcoholProductPage.class);
                intent.putExtra("product", p);
                startActivity(intent);
            }
        });

        canAdapter = new CannabisProductAdapter(canOptions, new RecyclerViewClickListener() {
            @Override
            public void onItemClick(Product p, View view) {
                Intent intent = new Intent (view.getContext(), CannabisProductPage.class);
                intent.putExtra("product", p);
                startActivity(intent);
            }
        });

        allProductAdapter = new ProductAdapter(allProductList, new RecyclerViewClickListener() {
            @Override
            public void onItemClick(Product p, View view) {
                if (p instanceof AlcoholProduct){
                    Intent intent = new Intent(view.getContext(), AlcoholProductPage.class);
                    intent.putExtra("product", p);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent (view.getContext(), CannabisProductPage.class);
                    intent.putExtra("product", p);
                    startActivity(intent);
                }
            }
        });

        list.setAdapter(alcAdapter);

        DividerItemDecoration dvs = new DividerItemDecoration(view.getContext(), layoutManager.getOrientation());
        list.addItemDecoration(dvs);
        list.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onStart(){
        super.onStart();
        alcAdapter.startListening();
        canAdapter.startListening();
    }

    @Override
    public void onStop(){
        super.onStop();
        alcAdapter.stopListening();
        canAdapter.stopListening();
    }
}

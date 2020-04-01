package com.example.a3130_group17_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

/** Activity for viewing log entries for a date.
 * @author      Tongtong Zhang
 * @author      Allan Jones
 * @author      Matt Wuard
 * @author      Ziheng Shi
 * @author      Omar Shams
 * @author      Yunzhong Xiao
 */
public class Summary_Page extends AppCompatActivity {
    RecyclerView list;
    FirebaseFirestore fdb=FirebaseFirestore.getInstance();
    FirestoreRecyclerAdapter<LogResource,LogResViewHolder> logAdapter;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary__page);
        this.setTitle("Summary");

        user = FirebaseAuth.getInstance().getCurrentUser();
        list = findViewById(R.id.summaryList);

        int day, month, year;
        day = getIntent().getIntExtra("day", 0);
        month = getIntent().getIntExtra("month", 0);
        year = getIntent().getIntExtra("year", 0);

        Query logQuery=fdb.collection("logs")
                .document(user.getUid())
                .collection("log")
                .whereEqualTo("day", day)
                .whereEqualTo("month", month)
                .whereEqualTo("year", year)
                .orderBy("time");

        FirestoreRecyclerOptions<LogResource> options=new FirestoreRecyclerOptions.Builder<LogResource>()
                .setQuery(logQuery, LogResource.class)
                .build();

        logAdapter=new FirestoreRecyclerAdapter<LogResource, LogResViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull LogResViewHolder holder, int position, @NonNull LogResource model) {
                holder.name.setText(model.getProductName());
                holder.percentage.setText(model.getProductPercentage());
                holder.date.setText(model.getDay()+"/"+model.getMonth()+"/"+model.getYear());
            }

            @NonNull
            @Override
            public LogResViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.log_res_view,parent,false);
                return new LogResViewHolder(view);
            }
        };

        list.setAdapter(logAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);

    }

    @Override
    protected void onStart() {
        super.onStart();
        logAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        logAdapter.stopListening();
    }
}

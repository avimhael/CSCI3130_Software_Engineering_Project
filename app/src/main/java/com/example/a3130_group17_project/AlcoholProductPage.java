package com.example.a3130_group17_project;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.DatePicker;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.GregorianCalendar;

/** Activity for viewing an alcohol product.
 * @author      Tongtong Zhang
 * @author      Allan Jones
 * @author      Matt Wuard
 * @author      Ziheng Shi
 * @author      Omar Shams
 * @author      Yunzhong Xiao
 */
public class AlcoholProductPage extends AppCompatActivity {
    private AlcoholProduct product;
    private TextView name, alcPercentage, statusLine;
    private DatePicker datePicker;
    private FirebaseFirestore fdb = FirebaseFirestore.getInstance();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private BottomAppBar bottomAppBar;
    private FloatingActionButton fab;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alcohol_product_page);

        product=(AlcoholProduct) getIntent().getSerializableExtra("product");
        name = findViewById(R.id.product_name);
        alcPercentage =findViewById(R.id.apv_percentage);
        statusLine = findViewById(R.id.status_line);
        bottomAppBar = findViewById(R.id.bottomAppBar);
        fab = findViewById(R.id.log_button);
        datePicker = findViewById(R.id.date_picker);

        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                finish();
                return true;
            }
        });
        datePicker.setMaxDate(System.currentTimeMillis());
        name.setText(product.getName());
        alcPercentage.setText("APV: "+product.getAPV());
    }

    /**
     * Logs the product to firestore.
     * @param v the view that was clicked.
     */
    public void logProduct(View v) {

        if (user != null) {
            Calendar calendar = new GregorianCalendar();
            calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
            long time = calendar.getTimeInMillis();
            LogResource lr = new LogResource(product.getName(), product.getPercentage(), datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), time);
            fdb.collection("logs")
                    .document(user.getUid())
                    .collection("log")
                    .add(lr)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            statusLine.setText(product.getName() + " logged successfully!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            statusLine.setText("Error: Could not log " + product.getName() + ".");
                        }
                    });
        } else {
            statusLine.setText("You must be signed in to log products!");
        }
    }
}

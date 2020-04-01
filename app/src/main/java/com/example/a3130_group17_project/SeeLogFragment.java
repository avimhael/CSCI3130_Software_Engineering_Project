package com.example.a3130_group17_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/** Activity for choosing a date to view log entries.
 * @author      Tongtong Zhang
 * @author      Allan Jones
 * @author      Matt Wuard
 * @author      Ziheng Shi
 * @author      Omar Shams
 * @author      Yunzhong Xiao
 */
public class SeeLogFragment extends Fragment {

    private DatePicker datepicker;
    private TextView seelogview ;
    private Button seelogbutton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.seelog_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        datepicker = view.findViewById(R.id.seelog_date);
        seelogview = view.findViewById(R.id.seelog_textview);
        seelogbutton = view.findViewById(R.id.seelog_button);

        datepicker.setMaxDate(System.currentTimeMillis());

        seelogbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Summary_Page.class);
                intent.putExtra("day", datepicker.getDayOfMonth());
                intent.putExtra("month", datepicker.getMonth());
                intent.putExtra("year", datepicker.getYear());
                startActivity(intent);
            }
        });

        seelogview.setText("Choose a Date to review your consumption");

        seelogbutton = view.findViewById(R.id.seelog_button);
    }
}

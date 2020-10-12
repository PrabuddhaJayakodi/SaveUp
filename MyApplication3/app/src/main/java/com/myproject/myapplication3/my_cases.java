package com.myproject.myapplication3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.myproject.myapplication3.ui.main.Case;

public class my_cases extends AppCompatActivity {

    private RecyclerView recyclerView;

    FirebaseRecyclerOptions<Case> options1;
    FirebaseRecyclerAdapter<Case,MyviewHolder> adapter1;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cases);

        ui_declare();
        
        Loard_data();
    }

    private void Loard_data() {

        options1 = new FirebaseRecyclerOptions.Builder<Case>().setQuery(databaseReference,Case.class).build();
        adapter1 = new FirebaseRecyclerAdapter<Case, MyviewHolder>(options1) {
            @Override
            protected void onBindViewHolder(@NonNull MyviewHolder holder, int position, @NonNull Case model) {

                holder.case_date.setText("Date :"+model.getCase_upload_date());
                holder.case_time.setText(model.getCase_upload_time());
                holder.case_v_no.setText(model.getVehicle_Number());
                holder.case_details.setText(model.getCase_Details());
            }

            @NonNull
            @Override
            public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_case_ui,parent,false);
                return new MyviewHolder(v);
            }
        };
        adapter1.startListening();
        recyclerView.setAdapter(adapter1);
    }

    private void ui_declare() {

        recyclerView = findViewById(R.id.case_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Customer Complain");

    }
}
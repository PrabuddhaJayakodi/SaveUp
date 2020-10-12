package com.myproject.myapplication3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.myproject.myapplication3.ui.main.Case;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Fragment2 extends Fragment {
    //Declare view
    public View v;

    //variable
    private RecyclerView recyclerView;
    FirebaseRecyclerOptions<Case> options;
    FirebaseRecyclerAdapter<Case,MyviewHolder> adapter;
    
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment2_layout,container,false);
        v = inflater.inflate(R.layout.fragment2_layout,container,false);

        recyclerView = v.findViewById(R.id.case_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));

        //Equal all
        UI_Declare();
        
        Loard_data();


        return v;
    }

    private void Loard_data() {

        String key =firebaseAuth.getUid();

        Query query = databaseReference.orderByChild("User_ID").startAt(key).endAt(key+"\uf8ff");

        options = new FirebaseRecyclerOptions.Builder<Case>().setQuery(query,Case.class).build();
        adapter = new FirebaseRecyclerAdapter<Case, MyviewHolder>(options) {
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
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }

    private void UI_Declare() {

        recyclerView.setHasFixedSize(true);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Customer Complain");
    }
}

package com.myproject.myapplication3;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class Fragment1 extends Fragment {

    public View v;
    private Button button,submit_case;
    private EditText vehical_no,case_details;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment1_layout,container,false);

        //ui declaretion
        UI_Declare();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(),MapDemo.class));
            }
        });

        submit_case.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user_id = firebaseAuth.getUid().toString().trim();
                String v_number = vehical_no.getText().toString().trim();
                String c_details = case_details.getText().toString().trim();
                String latitude = String.valueOf(MapDemo.latitude);
                String longitiude = String.valueOf(MapDemo.longitude);

                if (v_number.isEmpty() && c_details.isEmpty())
                {
                    Toast.makeText(v.getContext(),"Please Fill All Details",Toast.LENGTH_SHORT).show();
                }else {
                        Upload_data(user_id,v_number,c_details);
                       // Toast.makeText(v.getContext(),"Got it",Toast.LENGTH_SHORT).show();
                }
            }

            private void Upload_data(String user_id,String vehical_no,String case_data) {

                final String key = databaseReference.push().getKey();

                HashMap hashMap = new HashMap();
                hashMap.put("User_ID",user_id);
                hashMap.put("Vehicle_Number",vehical_no);
                hashMap.put("Case_Details",case_data);

                databaseReference.child(key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Toast.makeText(v.getContext(),"Case was submited....!!",Toast.LENGTH_SHORT).show();
                        Clear_feild();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(v.getContext(),"Something Wrong....!!",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        return v;
    }

    private void UI_Declare() {
        button = v.findViewById(R.id.button2);
        vehical_no = v.findViewById(R.id.tv_vehicalNo);
        case_details = v.findViewById(R.id.tv_caseDetails);
        submit_case = v.findViewById(R.id.btn_submitCase);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference().child("Customer Complain");
    }

    private void Clear_feild()
    {
        vehical_no.setText("");
        case_details.setText("");
    }
}

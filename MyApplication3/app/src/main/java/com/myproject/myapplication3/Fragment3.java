package com.myproject.myapplication3;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Fragment3 extends Fragment {

    public View fragment_v3;
    //UI component
    private Button profile,email,password,logout,contact,delete;
    //Firebase
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseUser firebaseUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragment_v3 =  inflater.inflate(R.layout.fragment3_layout,container,false);

        //UI Declare
        UI_Declare();

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),profile.class));
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),change_email.class));
            }
        });

        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),change_password.class));
            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),contact_us.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Are you sure...??");
                builder.setMessage("Please Confirm you want to logout....??");
                builder.setPositiveButton("Logout Now", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        firebaseAuth.signOut();
                        Toast.makeText(getContext(),"Logout Successfully",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getContext(),loadingPage.class));
                        getActivity().finish();

                    }
                });

                builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Are you sure...??");
                builder.setMessage("Delete this account will result in completely removing your" +
                        "account from the system and you wan't be able to access this app.");
                builder.setPositiveButton("Delete Now", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        delete_user_data();

                        firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful())
                                {
                                    Toast.makeText(getContext(),"Profile Delete Successful..!!",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getContext(),loadingPage.class));
                                    getActivity().finish();
                                }else {
                                    Toast.makeText(getContext(),"Profile Delete Failed..!!",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });

                builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });


        return fragment_v3;
    }

    private void delete_user_data() {
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("User Profile Data").child(firebaseAuth.getUid());
        databaseReference.removeValue();
    }

    private void UI_Declare() {
        profile = fragment_v3.findViewById(R.id.my_profile);
        email = fragment_v3.findViewById(R.id.change_email);
        password = fragment_v3.findViewById(R.id.change_password);
        contact = fragment_v3.findViewById(R.id.contact_us);
        logout = fragment_v3.findViewById(R.id.log_out);
        delete = fragment_v3.findViewById(R.id.delete_account);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
    }



    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment3_layout, container, false);
    }*/
}
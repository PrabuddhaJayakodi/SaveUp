package com.myproject.myapplication3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class register extends AppCompatActivity {

    EditText mFirstname,mLastname,mUsername,mPassword;
    Button mRegisteration;
    TextView mNavigateloginpage;
    FirebaseAuth fAuth;
    ProgressBar mprogressbar;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);

        mFirstname = findViewById(R.id.firstname);
        mLastname = findViewById(R.id.lastname);
        mUsername = findViewById(R.id.username);
        mPassword = findViewById(R.id.password);
        mRegisteration = findViewById(R.id.login);
        mNavigateloginpage = findViewById(R.id.navigateRegister);
        mprogressbar = findViewById(R.id.progressBar);
        fAuth =FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        if(fAuth.getCurrentUser() !=null) {
            startActivity(new Intent(getApplicationContext(), trackU.class));

        }

        mRegisteration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsername.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(username))
                {
                    mUsername.setError("Username is required");
                    return;
                }

                if(TextUtils.isEmpty(password))
                {

                    mPassword.setError("Password is required");
                    return;
                }

                if(password.length()<6)
                {
                    mPassword.setError("password must be atleast 6 characteristic");
                    return;
                }

                mprogressbar.setVisibility(View.VISIBLE);

                //register user in firebase
                fAuth.createUserWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            Upload_user_data();
                            //Toast.makeText(register.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),login.class));
                        } else
                        {

                            Toast.makeText(register.this, "Error !", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),loadingPage.class));
                        }
                    }
                });


            }
        });

        // when click login here, navigate to login page

        mNavigateloginpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),login.class));
            }
        });
    }

    private void Upload_user_data() {
        String first_name = mFirstname.getText().toString().trim();
        String last_name = mLastname.getText().toString().trim();
        String user_email = mUsername.getText().toString().trim();

        databaseReference = firebaseDatabase.getReference().child("User Profile Data").child(fAuth.getUid());

        HashMap hashMap = new HashMap();
        hashMap.put("user_email",user_email);
        hashMap.put("first_name",first_name);
        hashMap.put("last_name",last_name);

        databaseReference.setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(register.this, "User Created..!", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(register.this, "User Created Fail..!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

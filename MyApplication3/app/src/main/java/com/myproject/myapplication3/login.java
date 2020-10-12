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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    EditText mUsername,mPassword;
    Button mlogin;
    TextView mnavigateRegister;
    FirebaseAuth fAuth;
    ProgressBar mprogressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mUsername = findViewById(R.id.username);
        mPassword = findViewById(R.id.password);
        mlogin = findViewById(R.id.login);
        mnavigateRegister = findViewById(R.id.navigateRegister);
        mprogressbar = findViewById(R.id.progressBar);
        fAuth =FirebaseAuth.getInstance();

        mlogin.setOnClickListener(new View.OnClickListener() {
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

                //authenticate the users
                fAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            Toast.makeText(login.this, "logged in successfuly", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),trackU.class));
                        } else {

                            Toast.makeText(login.this, "Error !"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }


        });

        //when click new here? create account link, navigate to register page

        mnavigateRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),register.class));
            }
        });
    }
}

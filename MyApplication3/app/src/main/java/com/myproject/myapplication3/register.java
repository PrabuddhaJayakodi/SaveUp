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

public class register extends AppCompatActivity {

    EditText mFirstname,mLastname,mUsername,mPassword;
    Button mRegisteration;
    TextView mNavigateloginpage;
    FirebaseAuth fAuth;
    ProgressBar mprogressbar;


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
                            Toast.makeText(register.this, "User Created", Toast.LENGTH_SHORT).show();
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
}

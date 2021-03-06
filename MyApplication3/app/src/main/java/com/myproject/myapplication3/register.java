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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class register extends AppCompatActivity {

    EditText mFirstname,mLastname,mUsername,mPassword,mcontact,mnic;
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
        mcontact = findViewById(R.id.contact);
        mnic = findViewById(R.id.nic_no);
        mRegisteration = findViewById(R.id.login);
        mNavigateloginpage = findViewById(R.id.navigateRegister);
        mprogressbar = findViewById(R.id.progressBar);
        fAuth =FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

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

                if (!is_ValidNic(mnic.getText().toString().trim()))
                {
                    mnic.setError("Please enter valid NIC");
                    return;
                }
                if (!is_contact(mcontact.getText().toString().trim()))
                {
                    mcontact.setError("Please enter valid contact");
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
        String contact = mcontact.getText().toString();
        String nic = mnic.getText().toString();

        databaseReference = firebaseDatabase.getReference().child("User Profile Data").child(fAuth.getUid());

        HashMap hashMap = new HashMap();
        hashMap.put("user_email",user_email);
        hashMap.put("first_name",first_name);
        hashMap.put("last_name",last_name);
        hashMap.put("contact_number",contact);
        hashMap.put("nic_number",nic);

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



    public static boolean is_ValidNic(String nic) {

        String stringToSearch = nic;

        Pattern p = Pattern.compile("([0-9]{9}[a-z]{1})");
        Matcher m = p.matcher(stringToSearch);


        if (m.find() && nic.length()==10)
            return true;
        else
            return false;
    }


    public static boolean is_contact(final String contact)
    {
        String StringTosearch = contact;

        Pattern p = Pattern.compile("(^1300\\d{6}$)|(^0[1|3|7|6|8]{1}[0-9]{8}$)|(^13\\d{4}$)|(^04\\d{2,3}\\d{6}$)");
        Matcher m = p.matcher(StringTosearch);


        if (m.find())
            return true;
        else
            return false;
    }
}

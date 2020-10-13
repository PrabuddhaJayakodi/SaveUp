package com.myproject.myapplication3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class change_password extends AppCompatActivity {

    private Button submit;
    private EditText password,re_password;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        UI_Declare();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String new_password = password.getText().toString().trim();
                String re_new_password =re_password.getText().toString().trim();

                if (Validation.is_password_same(new_password,re_new_password))
                {
                    if (new_password.length()>6)
                    {
                        firebaseUser.updatePassword(new_password).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful())
                                {
                                    Toast.makeText(change_password.this,"Password Update..!!",Toast.LENGTH_SHORT).show();
                                    //startActivity(new Intent(user_change_password.this,settings_panel.class));
                                    finish();
                                }else {
                                    Toast.makeText(change_password.this,"Password update Failed..!!",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }else {
                        Toast.makeText(change_password.this,"Incorrect Password",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(change_password.this,"Please Fill Details",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void UI_Declare() {

        submit = findViewById(R.id.cp_btn_submit);
        password = findViewById(R.id.et_cp_password);
        re_password = findViewById(R.id.et_cp_repassword);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
    }
}
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
import com.google.firebase.database.FirebaseDatabase;

public class change_email extends AppCompatActivity {

    private Button submit;
    private EditText new_email;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);

        UI_declare();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = new_email.getText().toString().trim();

                if (email.isEmpty())
                {
                    Toast.makeText(change_email.this,"Enter Your Email",Toast.LENGTH_SHORT).show();
                }else {
                    if (Validation.is_Validmail(email))
                    {
                        firebaseUser.updateEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful())
                                {
                                    Toast.makeText(change_email.this,"Email Update Successfully",Toast.LENGTH_SHORT).show();
                                    //startActivity(new Intent(user_change_email.this,settings_panel.class));
                                    finish();
                                }
                            }
                        });
                    }else {
                        Toast.makeText(change_email.this,"Invalid Email Type",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void UI_declare() {
        submit = findViewById(R.id.ce_btn_submit);
        new_email = findViewById(R.id.et_ce_new_email);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
    }
}
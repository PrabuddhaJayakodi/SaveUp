package com.myproject.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class loadingPage extends AppCompatActivity {


    Button mloadinglogin, mloadingregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_page);

        mloadinglogin = findViewById(R.id.loadingLogin);
        mloadingregister = findViewById(R.id.loadingRegister);

        mloadinglogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),login.class));
            }
        });


        mloadingregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),register.class));
            }
        });
    }
}

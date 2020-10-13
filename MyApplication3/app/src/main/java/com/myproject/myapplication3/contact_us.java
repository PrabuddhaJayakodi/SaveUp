package com.myproject.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class contact_us extends AppCompatActivity {

    private Button get_call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        get_call = findViewById(R.id.gc_btn_call);

        get_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String number = "0711079823";

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+number));
                startActivity(intent);
            }
        });
    }
}
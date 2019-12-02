package com.example.newhelloandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        System.out.println("Detail Created");
        Bundle extras = getIntent().getExtras();
        String ResID = extras.getString("positionID");
        System.out.println("positionID = "+ResID);
    }
}

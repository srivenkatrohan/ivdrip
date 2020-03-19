package com.example.ivdrip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class Activity2 extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        button = (Button) findViewById(R.id.checkDrip);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCheckDrip();
            }
        });

        button = (Button) findViewById(R.id.dripRateCal);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDripRateCalculator();
            }
        });

        button = (Button) findViewById(R.id.overview);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOverview();
            }
        });

        button = (Button) findViewById(R.id.settings);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettings();
            }
        });

        button = (Button) findViewById(R.id.logout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });


    }

    public void openCheckDrip() {
        Intent intent = new Intent(this, CheckDrip.class);
        startActivity(intent);
    }

    public void openDripRateCalculator() {
        Intent intent = new Intent(this, DripRateCalculator.class);
        startActivity(intent);
    }

    public void openOverview() {
        Intent intent = new Intent(this, Overview.class);
        startActivity(intent);
    }

    public void openSettings() {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }



    public void openMainActivity() {
        deleteFile("note.txt");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

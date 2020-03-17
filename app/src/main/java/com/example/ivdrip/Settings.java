package com.example.ivdrip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Settings extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        button = (Button) findViewById(R.id.instructionManual);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInstructionManual();
            }
        });

        button=(Button)findViewById(R.id.accountInfo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccountInfo();
            }
        });

        button=(Button)findViewById(R.id.aboutUs);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAboutUs();
            }
        });
    }
    public void openInstructionManual() {
        Intent intent = new Intent(this, InstructionManual.class);
        startActivity(intent);
    }

    public void openAccountInfo(){
        Intent intent=new Intent(this, AccountInfo.class);
        startActivity(intent);
    }

    public void openAboutUs(){
        Intent intent=new Intent(this, AboutUs.class);
        startActivity(intent);
    }

}

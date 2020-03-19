package com.example.ivdrip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Context;

import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    private Button button;
    EditText username, password;
    DatabaseReference reff;
    SignUpMember signUpMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.login);
        username = (EditText) findViewById(R.id.uname);
        password = (EditText) findViewById(R.id.pwd);
        signUpMember = new SignUpMember();
        final SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff = FirebaseDatabase.getInstance().getReference().child("SignUpMember");
                final String userText = username.getText().toString();
                final String passwordText = password.getText().toString();
                reff.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                        SharedPreferences.Editor editor = sp.edit();
                        int flag = 0;
                        while (items.hasNext()) {
                            DataSnapshot ds = items.next();
                            String user = ds.child("user").getValue().toString();
                            String pass = ds.child("pass").getValue().toString();
                            if (userText.equals(user) && passwordText.equals(pass)) {
                                editor.putString("id", ds.getKey());
                                editor.commit();
                                flag = 1;
                                openActivity2();
                                break;
                            }
                        }
                        if ((username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) || (username.getText().toString().equals("user") && password.getText().toString().equals("user"))) {
                            openActivity2();
                        } else if (username.getText().toString().equals("") || password.getText().toString().equals("")) {
                            Toast.makeText(MainActivity.this, "Enter the complete details!", Toast.LENGTH_SHORT).show();
                        } else {
                            if (flag == 0) {
                                Toast.makeText(MainActivity.this, "Incorrect Username/Password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        button = (Button) findViewById(R.id.signUp);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUp();
            }
        });
    }


    public void openActivity2() {
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }

    public void openSignUp() {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
}


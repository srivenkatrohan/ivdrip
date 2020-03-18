package com.example.ivdrip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    private Button button;
    EditText username, password;
    String user,pass;
    DatabaseReference reff;
    //Member member;
    SignUpMember signUpMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.login);
        username = (EditText) findViewById(R.id.uname);
        password = (EditText) findViewById(R.id.pwd);
        signUpMember=new SignUpMember();

        //reff=FirebaseDatabase.getInstance().getReference().child("Member");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //member.setUser(username.getText().toString());
                //member.setPass(password.getText().toString());
                //reff.push().setValue(member);
                //Toast.makeText(MainActivity.this, "Data entered into the database", Toast.LENGTH_SHORT).show();
                reff=FirebaseDatabase.getInstance().getReference().child("SignUpMember");
                final String userText = username.getText().toString();
                String passwordText = password.getText().toString();
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                        while(items.hasNext())
                        {
                            DataSnapshot ds = items.next();
                            user = ds.child("user").getValue().toString();
                            if(userText.equals(user))
                            {
                                pass = ds.child("pass").getValue().toString();
                                break;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

               if((username.getText().toString().equals("admin")&&password.getText().toString().equals("admin"))||(username.getText().toString().equals("user")&&password.getText().toString().equals("user"))||(username.getText().toString().equals(user)&&password.getText().toString().equals(pass)))
                {
                    openActivity2();
                }
                else if(username.getText().toString().equals("")||password.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Enter the complete details!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Incorrect Username/Password", Toast.LENGTH_SHORT).show();
                }
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


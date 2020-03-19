package com.example.ivdrip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {
    Button button;
    EditText firstname,lastname,dob,phone,email,username,password;
    CheckBox male_gender,female_gender,other_gender, confirm;
    DatabaseReference reff;
    SignUpMember signUpMember;
    long maxid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firstname=(EditText)findViewById(R.id.firstName);
        lastname= (EditText)findViewById(R.id.lastName);
        male_gender=(CheckBox)findViewById(R.id.male);
        female_gender=(CheckBox)findViewById(R.id.female);
        other_gender=(CheckBox)findViewById(R.id.other);
        dob= (EditText)findViewById(R.id.dateOfBirth);
        phone=(EditText)findViewById(R.id.contact);
        email=(EditText)findViewById(R.id.emailId);
        username=(EditText)findViewById(R.id.uname);
        password=(EditText)findViewById(R.id.pwd);
        confirm=(CheckBox) findViewById(R.id.confirm);
        button = (Button) findViewById(R.id.register);
        signUpMember=new SignUpMember();
        reff=FirebaseDatabase.getInstance().getReference().child("SignUpMember");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxid=(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //int dt = Integer.parseInt(dob.getText().toString());
                    Long phn=Long.parseLong(phone.getText().toString());
            signUpMember.setFname(firstname.getText().toString());
            signUpMember.setLname(lastname.getText().toString());
            signUpMember.setMale(male_gender.isChecked());
            signUpMember.setFemale(female_gender.isChecked());
            signUpMember.setOther(other_gender.isChecked());
            signUpMember.setDate_of_birth(dob.getText().toString());
            signUpMember.setNumber(phn);
            signUpMember.setEmail(email.getText().toString());
            signUpMember.setUser(username.getText().toString());
            signUpMember.setPass(password.getText().toString());
            signUpMember.setConfirmation(confirm.isChecked());
            reff.child(String.valueOf(maxid+1)).setValue(signUpMember);
                Toast.makeText(SignUp.this, "Signed Up successfully. Detailed entered into Database", Toast.LENGTH_SHORT).show();
                openMainActivity();
            }
        });
    }


    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}


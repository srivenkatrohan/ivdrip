package com.example.ivdrip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class ChangePassword extends AppCompatActivity {
private Button button;
EditText oldPassword,newPassword,confirmNewPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        oldPassword = (EditText)findViewById(R.id.pwd);
        newPassword= (EditText)findViewById(R.id.pwd1);
        confirmNewPassword= (EditText)findViewById(R.id.pwd1);
        button = (Button) findViewById(R.id.updatePassword);
        if(oldPassword.getText().toString().equals("")||newPassword.getText().toString().equals("")||confirmNewPassword.getText().toString().equals(""))
        {
            Toast.makeText(this, "Fill the complete details.!", Toast.LENGTH_SHORT).show();
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccountInfo();
            }
        });


    }

   public void openAccountInfo() {
        Intent intent = new Intent(this, AccountInfo.class);
        startActivity(intent);
    }

}

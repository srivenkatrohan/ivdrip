package com.example.ivdrip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class CheckDrip extends AppCompatActivity {
    DatabaseReference reff;
    DripRateMember dripRateMember;
    TextView drip, fluid;
    Switch switchState;
    String driprate, fluidlimit;
    EditText notepad;
    Button savebtn;

    public void onClick(View view) {
        Toast.makeText(this, "Alarm sent to Nurse or Doctor!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_drip);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor spedit = sp.edit();
        final String id = sp.getString("id", " ");

        drip = (TextView) findViewById(R.id.dripRate);
        fluid = (TextView) findViewById(R.id.fluidLimit);
        switchState = (Switch) findViewById(R.id.switchState);
        notepad = (EditText) findViewById(R.id.notePad);
        savebtn = (Button) findViewById(R.id.saveButton);
        setText(notepad, id);

        dripRateMember = new DripRateMember();
        reff = FirebaseDatabase.getInstance().getReference().child("DripRateMember");
        switchState.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switchState.isChecked() == true)
                    switchState.setText("Device on");
                else
                    switchState.setText("Device off");
            }
        });
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                driprate = dataSnapshot.child("output").getValue().toString();
                fluidlimit = dataSnapshot.child("vol").getValue().toString();
                drip.setText(driprate);
                fluid.setText(fluidlimit);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = notepad.getText().toString();
                try {
                    FileOutputStream fos = openFileOutput("note.txt", MODE_PRIVATE);
                    fos.write(text.getBytes());
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                reff = FirebaseDatabase.getInstance().getReference().child("SignUpMember");
                reff.child(id).child("message").setValue(text);
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setText(final EditText notepad, String id) {
        try {
            String myExternalFile = "note.txt";
            String myData = "";
            FileInputStream fis = openFileInput(myExternalFile);
            DataInputStream in = new DataInputStream(fis);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                myData = myData + strLine;
            }
            in.close();
            notepad.setText(myData);
        } catch (IOException e) {
            reff = FirebaseDatabase.getInstance().getReference().child("SignUpMember").child(id);
            reff.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String text = dataSnapshot.child("message").getValue().toString();
                    if(!text.equals(""))
                    {
                        notepad.setText(text);
                        try {
                            FileOutputStream fos = openFileOutput("note.txt", MODE_PRIVATE);
                            fos.write(text.getBytes());
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }
}

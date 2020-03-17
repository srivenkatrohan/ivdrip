package com.example.ivdrip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class CheckDrip extends AppCompatActivity {
    DatabaseReference reff;
    DripRateMember dripRateMember;
    TextView drip,fluid;
    Switch switchState;
    String driprate,fluidlimit;

    public void onClick(View view){
        Toast.makeText(this, "Alarm sent to Nurse or Doctor!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_drip);
        drip=(TextView)findViewById(R.id.dripRate);
        fluid=(TextView)findViewById(R.id.fluidLimit);
        switchState=(Switch)findViewById(R.id.switchState);
        dripRateMember=new DripRateMember();
        reff=FirebaseDatabase.getInstance().getReference().child("DripRateMember");
        switchState.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(switchState.isChecked()==true)
                    switchState.setText("Device on");
                else
                    switchState.setText("Device off");
            }
        });
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                driprate=dataSnapshot.child("output").getValue().toString();
                fluidlimit=dataSnapshot.child("vol").getValue().toString();
                drip.setText(driprate);
                fluid.setText(fluidlimit);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}

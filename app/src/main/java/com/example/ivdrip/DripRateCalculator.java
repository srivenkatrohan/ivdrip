package com.example.ivdrip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DripRateCalculator extends AppCompatActivity {
    Button calc;
    TextView output;
    Switch switch1;
    EditText vol,dropFactor,timerate;
    DatabaseReference reff;
    DripRateMember dripRateMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drip_rate_calculator);
        vol=(EditText)findViewById(R.id.volume);
        dropFactor=(EditText)findViewById(R.id.drops);
        timerate=(EditText)findViewById(R.id.time);
        calc=(Button)findViewById(R.id.calculate);
        switch1=(Switch)findViewById(R.id.switch1);
        output=(TextView)findViewById(R.id.output);
        dripRateMember=new DripRateMember();
        reff= FirebaseDatabase.getInstance().getReference().child("DripRateMember");

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(switch1.isChecked()==true)
                    switch1.setText("Minutes");
                else
                    switch1.setText("Hours");
            }
        });




        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer volume=Integer.parseInt(vol.getText().toString());
                Integer drop=Integer.parseInt(dropFactor.getText().toString());
                Integer time=Integer.parseInt(timerate.getText().toString());
                dripRateMember.setVol(volume);
                dripRateMember.setDrop(drop);
                dripRateMember.setDuration(time);
                //if(time<24)
                {
                    if(switch1.isChecked()==false)
                    time=time*60;
                    else
                        ;
                }


                int total = (volume * drop) / time;
                String display = Integer.toString(total);
                output.setText(display + " drips per minute");
                dripRateMember.setOutput(total);
                reff.setValue(dripRateMember);
                Toast.makeText(DripRateCalculator.this, "Values entered", Toast.LENGTH_SHORT).show();
                /*if (volume.equals(null) || dropFactor.equals(null) || time.equals(null)) {
                    Toast.makeText(DripRateCalculator.this, "Enter complete details", Toast.LENGTH_SHORT).show();
                }*/
            }
        });


    }
}

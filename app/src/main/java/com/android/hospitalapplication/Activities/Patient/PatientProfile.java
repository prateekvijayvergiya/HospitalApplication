package com.android.hospitalapplication.Activities.Patient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Spinner;

import com.android.hospitalapplication.R;


public class PatientProfile extends AppCompatActivity {


    private Spinner blood_group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }
}

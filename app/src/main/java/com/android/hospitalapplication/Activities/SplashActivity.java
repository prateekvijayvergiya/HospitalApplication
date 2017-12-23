package com.android.hospitalapplication.Activities;

import android.content.Intent;
import android.os.Build;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.android.hospitalapplication.Activities.Doctor.DoctorActivity;
import com.android.hospitalapplication.Activities.Patient.PatientActivity;
import com.android.hospitalapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashActivity extends AppCompatActivity {

    Animation fadeIn;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    DatabaseReference dbrefUser;

    LinearLayout LinearLayout;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_splash);

        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        LinearLayout =  findViewById(R.id.splash_layout);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(auth.getCurrentUser()!=null){
                    FirebaseUser user = auth.getCurrentUser();
                    final String uid = user.getUid();
                    dbrefUser = FirebaseDatabase.getInstance().getReference("Users");

                    dbrefUser.child(uid).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String type = dataSnapshot.child("type").getValue().toString();
                            if(type.equals("Doctor")){
                                startActivity(new Intent(SplashActivity.this,DoctorActivity.class));
                                finish();
                            }
                            else if(type.equals("Patient")){
                                startActivity(new Intent(SplashActivity.this,PatientActivity.class));
                                finish();

                            }
                        }


                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        LinearLayout.setAnimation(fadeIn);


    }
}

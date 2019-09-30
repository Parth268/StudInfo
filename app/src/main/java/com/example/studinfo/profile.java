package com.example.studinfo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile extends AppCompatActivity {

    ImageView imageView;
    EditText editText;
    Button button;

    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference reference;
    TextView textname,textcollege,textViewenroll,textViewdepartment,textViewmobile,textViewsemester;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textname=(TextView)findViewById(R.id.textViewname);
        textcollege=(TextView)findViewById(R.id.textViewcollege);
        textViewdepartment=(TextView)findViewById(R.id.textViewdepartment);
        textViewenroll=(TextView)findViewById(R.id.textViewenrollment);
        textViewmobile=(TextView)findViewById(R.id.textViewmobile);
        textViewsemester=(TextView)findViewById(R.id.textViewsemester);


        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();


        reference= FirebaseDatabase.getInstance().getReference().child(user.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name,depart,college,enroll,mobile,sem;
                name=dataSnapshot.child("name").getValue().toString();
                depart=dataSnapshot.child("department").getValue().toString();
                college=dataSnapshot.child("college").getValue().toString();
                enroll=dataSnapshot.child("enrollment").getValue().toString();
                mobile=dataSnapshot.child("mobile").getValue().toString();
                sem=dataSnapshot.child("semester").getValue().toString();


                textname.setText(name);
                textViewdepartment.setText(depart);
                 textcollege.setText(college);
                textViewenroll.setText(enroll);
                 textViewmobile.setText(mobile);
                textViewsemester.setText(sem);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


}
   /* private void load() {
        final FirebaseUser user=firebaseAuth.getCurrentUser();
        if(user!=null){

            if(user.isEmailVerified()){
                text.setText("email verified");
            }else {
                text.setText("email not verified (click to verified)");
                text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(profile.this,"email send",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
            }
        }
    }
}*/

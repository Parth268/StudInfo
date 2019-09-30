package com.example.studinfo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Field;

public class testing extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference reference;
    TextView textViewname,textView1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        textViewname=(TextView)findViewById(R.id.name);
        textView1=(TextView)findViewById(R.id.mobile);

        reference=FirebaseDatabase.getInstance().getReference().child(user.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name,depart;
                name=dataSnapshot.child("college").getValue().toString();
                depart=dataSnapshot.child("department").getValue().toString();
                textViewname.setText(name);
                textView1.setText(depart);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}

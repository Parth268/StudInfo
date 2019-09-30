package com.example.studinfo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class event extends AppCompatActivity {

    DatabaseReference databaseReference;
    Button buttonadd;
    ListView listView;
    List<attribute> artList;
    attribute at;


  //  TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        databaseReference= FirebaseDatabase.getInstance().getReference("Event");
        buttonadd=(Button)findViewById(R.id.mainbutton);
        listView=(ListView) findViewById(R.id.listforevent);
        artList=new ArrayList<>();

        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(event.this,Addevent.class));
            }
        });

    }

    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                artList.clear();
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    attribute art=dataSnapshot1.getValue(attribute.class);
                    artList.add(art);
                }
                addpter addpater1=new addpter(event.this,artList);
                listView.setAdapter(addpater1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}














/* textView1=(TextView)findViewById(R.id.collgegecr);
        textView2=(TextView)findViewById(R.id.brizinger);
        textView3=(TextView)findViewById(R.id.dategecr);

        textView4=(TextView)findViewById(R.id.ms);
        textView5=(TextView)findViewById(R.id.footprint);
        textView6=(TextView)findViewById(R.id.datefoot);

        textView7=(TextView)findViewById(R.id.om);
        textView8=(TextView)findViewById(R.id.spetrum);
        textView9=(TextView)findViewById(R.id.datesptrum);*/

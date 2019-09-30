package com.example.studinfo;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Addevent extends AppCompatActivity {
    DatabaseReference databaseReference;
    Button buttonadd,buttonupdate;
    EditText editTextCollegeName,editTextevent,editTextdate1,editTextcity1;
    ListView listView;
    List<attribute> artList;

    attribute at;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addevent);
        buttonupdate=(Button)findViewById(R.id.buttonaddevent);
        databaseReference= FirebaseDatabase.getInstance().getReference("Event");

        editTextcity1=(EditText)findViewById(R.id.editTextcity);
        editTextdate1=(EditText)findViewById(R.id.editTextdate);
        editTextCollegeName=(EditText)findViewById(R.id.editTextcollege);
        editTextevent=(EditText)findViewById(R.id.editTextevent);

        buttonupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String college23 = editTextCollegeName.getText().toString().trim();
                String event23 = editTextevent.getText().toString().trim();
                String date23 = editTextdate1.getText().toString().trim();
                String city23 = editTextcity1.getText().toString().trim();


                if (college23.isEmpty()) {
                    editTextCollegeName.setError("College name can't be blank");
                    editTextCollegeName.requestFocus();
                    return;
                }

                if (event23.isEmpty()) {
                    editTextevent.setError("Event name can't be blank");
                    editTextevent.requestFocus();
                    return;
                }
                if (date23.isEmpty()) {
                    editTextdate1.setError("Date can't be blank");
                    editTextdate1.requestFocus();
                    return;
                }
                if (city23.isEmpty()) {
                    editTextcity1.setError("City name can't be blank");
                    editTextcity1.requestFocus();
                    return;
                }
                String id=databaseReference.push().getKey();
                attribute art=new attribute(college23,event23,city23,date23);
                databaseReference.child(id).setValue(art);
                Toast.makeText(Addevent.this,"adding data",Toast.LENGTH_LONG).show();


            }
        });

        }



}

package com.example.studinfo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Create_Profile extends AppCompatActivity  {

    EditText name,enroll,college,mobile,dob,dept,sem;;
    Button buttonsave;
    String downloaduris;

    ImageView imageView;
    ProgressBar progressBar;
    Uri profileimage;
    TextView textView;

    FirebaseAuth user;
    DatabaseReference ref;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__profile);
        name=(EditText)findViewById(R.id.editTextname);
        enroll=(EditText)findViewById(R.id.editTextenroll);
        college=(EditText)findViewById(R.id.editTextcollege);
        mobile=(EditText)findViewById(R.id.editTextmobile);


        sem=(EditText) findViewById(R.id.editTextsemester);
        dept=(EditText) findViewById(R.id.editTextdepartment);

        textView=(TextView)findViewById(R.id.textViewclick);
        dob=(EditText)findViewById(R.id.editTextdob);
        buttonsave=(Button)findViewById(R.id.save1);
        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        imageView=(ImageView)findViewById(R.id.profile_image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                showimage();
                textView.setText(" ");

            }
        });

        buttonsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Create_Profile.this,"working",Toast.LENGTH_LONG).show();
                saveinfo();
//                startActivity(new Intent(Create_Profile.this,Student_data.class));

            }
        });
        user = FirebaseAuth.getInstance();


        ref = FirebaseDatabase.getInstance().getReference();

        storageReference=FirebaseStorage.getInstance().getReference();


    }
    public void saveinfo(){
        String name1=name.getText().toString().trim();
        if(name1.isEmpty()){
            name.setError("please enter name");
            name.requestFocus();
            return;
        }
        String enroll1=enroll.getText().toString().trim();
        if(enroll1.isEmpty()){
            enroll.setError("please enter name");
            enroll.requestFocus();
            return;
        }
        String college1=college.getText().toString().trim();
        if(college1.isEmpty()){
            college.setError("please enter name");
            college.requestFocus();
            return;
        }
        String mobile1=mobile.getText().toString().trim();
        if(mobile1.isEmpty()){
            mobile.setError("please enter name");
            mobile.requestFocus();
            return;
        }

        String depart1=dept.getText().toString().trim();
        if(depart1.isEmpty()){
            dept.setError("please enter department");
            dept.requestFocus();
            return;
        }


        String semester=sem.getText().toString().trim();
        if(semester.isEmpty()){
            sem.setError("please enter semester");
            sem.requestFocus();
            return;
        }




        String dob1=dob.getText().toString().trim();
        if(dob1.isEmpty()){
            dob.setError("please enter name");
            dob.requestFocus();
            return;
        }
         DatabaseReference current_user ;

        StorageReference filepart=storageReference.child(user.getUid());

       filepart.putFile(profileimage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
           @Override
           public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                downloaduris=taskSnapshot.getUploadSessionUri().toString();
                progressBar.setVisibility(View.GONE);
                startActivity(new Intent(Create_Profile.this,Student_profile.class));
               finish();
           }
       });


        String userid=user.getUid();
        current_user=ref.child(userid);

        current_user.child("name").setValue(name1);
        current_user.child("enrollment").setValue(enroll1);
        current_user.child("college").setValue(college1);
        current_user.child("mobile").setValue(mobile1);
        current_user.child("department").setValue(depart1);
        current_user.child("semester").setValue(semester);
        current_user.child("dob").setValue(dob1);
        current_user.child(user.getUid()).setValue(downloaduris);

        Toast.makeText(this,"Please wait uploading data",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if(requestCode==1&&resultCode==RESULT_OK) {

                profileimage = data.getData();
                imageView.setImageURI(profileimage);

            }
    }

    private  void showimage(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }
}
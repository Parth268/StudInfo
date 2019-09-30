package com.example.studinfo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Student_profile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageView imageViewprofile,imageView;
    EditText editText;
    Button button;
    StorageReference storageReference,gots;
    TextView text;
    FirebaseAuth auth;

    String hello,name;
    FirebaseUser user;
    DatabaseReference reference;
    TextView textname,textcollege,textViewenroll,textViewdepartment,textViewmobile,textViewsemester,tdob,temail;
    TextView hemail,hname;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressBar=(ProgressBar)findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.VISIBLE);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        imageView=(ImageView) findViewById(R.id.imageViewsymbole);
        tdob=(TextView)findViewById(R.id.textViewdob);
        textname=(TextView)findViewById(R.id.textViewname);
        textcollege=(TextView)findViewById(R.id.textViewcollege);
        textViewdepartment=(TextView)findViewById(R.id.textViewdepartment);
        textViewenroll=(TextView)findViewById(R.id.textViewenrollment);
        textViewmobile=(TextView)findViewById(R.id.textViewmobile);
        textViewsemester=(TextView)findViewById(R.id.textViewsemester);
        temail=(TextView)findViewById(R.id.textViewverification);
        imageViewprofile =(ImageView)findViewById(R.id.profile_image);
        hemail=(TextView)findViewById(R.id.emailaddress);
        hname=(TextView)findViewById(R.id.headname) ;

        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();

        storageReference = FirebaseStorage.getInstance().getReference().child(auth.getUid());

        reference= FirebaseDatabase.getInstance().getReference().child(auth.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String depart,college,enroll,mobile,sem,img,dob,hemail;
                name=dataSnapshot.child("name").getValue().toString();
                depart=dataSnapshot.child("department").getValue().toString();
                college=dataSnapshot.child("college").getValue().toString();
                enroll=dataSnapshot.child("enrollment").getValue().toString();
                mobile=dataSnapshot.child("mobile").getValue().toString();
                sem=dataSnapshot.child("semester").getValue().toString();
                  hello=name;
                // img=dataSnapshot.child(user.getUid()).getValue().toString();
                dob=dataSnapshot.child("dob").getValue().toString();
                tdob.setText(dob);

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

        load();
        loadimge();



    }
    private void load() {
        final FirebaseUser user=auth.getCurrentUser();
        if(user!=null){

            if(user.isEmailVerified()){
                temail.setText("verified");
            }else {
                temail.setText("not verified");
                temail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(Student_profile.this,"sending email",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
            }
        }
    }


    private void loadimge() {

        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Uri downloadUrl = uri;

                String got;

                got=downloadUrl.toString();

                Toast.makeText(Student_profile.this,"Hi "+hello,Toast.LENGTH_LONG).show();

                String url=""+got+"";
                Glide.with(getApplicationContext()).load(url).into(imageViewprofile);

            }});
        progressBar.setVisibility(View.GONE);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


   public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.editsetting) {
            startActivity(new Intent(Student_profile.this,Create_Profile.class));
            return true;
        }
        if(id==R.id.refresh){
            finish();
            startActivity(new Intent(Student_profile.this,Student_profile.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            // Handle the camera action
        } else if (id == R.id.result) {
            startActivity(new Intent(Student_profile.this,Result.class ));

        } else if (id == R.id.point) {
           startActivity(new Intent(Student_profile.this,point100.class));
        }
        else if(id==R.id.event){
            startActivity(new Intent(Student_profile.this,event.class));
        }
        else if (id == R.id.signout) {
              FirebaseAuth.getInstance().signOut();
              finish();
              startActivity(new Intent(Student_profile.this,MainActivity.class));

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

package com.example.studinfo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth firebaseAuth;
    EditText editTextemail,editTextpassword;
  static boolean data=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(data==false){
            data=true;
          startActivity(new Intent(MainActivity.this,Wellcomepage.class));

        }
        findViewById(R.id.sign).setOnClickListener(this);
        findViewById(R.id.login1).setOnClickListener(this);
        editTextemail=(EditText)findViewById(R.id.email1);
        firebaseAuth=FirebaseAuth.getInstance();
        editTextpassword=(EditText)findViewById(R.id.password1);
        }

    public void user() {
        String email=editTextemail.getText().toString().trim();
        String password=editTextpassword.getText().toString().trim();


        if(email.isEmpty()){
            editTextemail.setError("please enter email");
            editTextemail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editTextpassword.setError("please enter password");
            editTextpassword.requestFocus();
            return;
        }
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    finish();
                    Intent intent=new Intent(MainActivity.this, Student_profile.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }else {
                    Toast.makeText(getApplication(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sign:
            {

                startActivity(new Intent(this,sign_in_page.class));
                break;
            }
            case R.id.login1:{

                user();
                break;
            }
        }

    }
}

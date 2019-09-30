package com.example.studinfo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class sign_in_page extends AppCompatActivity implements View.OnClickListener {

    EditText editTextemail12, editTextpassword12;
    Button login;
    FirebaseAuth firebaseAuth;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);
        editTextemail12=(EditText)findViewById(R.id.email1);
        editTextpassword12 =(EditText)findViewById(R.id.password1);
        findViewById(R.id.login1).setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login1:
            {
                regester();

                break;
            }
        }
    }

    private void regester() {
        String email=editTextemail12.getText().toString().trim();
        String password= editTextpassword12.getText().toString().trim();
        if(email.isEmpty()){
            editTextemail12.setError("please enter email");
            editTextemail12.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editTextpassword12.setError("please enter password");
            editTextpassword12.requestFocus();
            return;
        }
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Registered",Toast.LENGTH_LONG).show();
                    finish();
                   startActivity(new Intent(sign_in_page.this,Create_Profile.class));
                }
                if(task.getException() instanceof FirebaseAuthUserCollisionException){
                    Toast.makeText(getApplicationContext(),"already registered",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}

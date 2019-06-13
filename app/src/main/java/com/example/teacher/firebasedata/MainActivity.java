package com.example.teacher.firebasedata;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


    EditText etusername, etpassword;
    Button btnregister, btnlogin;

    private FirebaseAuth mAuth;

    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        etusername = findViewById(R.id.etusername);
        etpassword  = findViewById(R.id.etpassword);
        btnregister = findViewById(R.id.btnregister);
        btnlogin = findViewById(R.id.btnlogin);

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                register();

            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void validation()
    {
        username = etusername.getText().toString();
        password = etpassword.getText().toString();

        //validation: check for presence
        if (TextUtils.isEmpty(username)){
            Toast.makeText(getApplicationContext(),"Please enter email address",Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(),"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }
    }

    private void login(){
        validation();

        mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            
                            Intent in = new Intent(getApplicationContext(),naome.class);
                            startActivity(in);

                        } else {

                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }


                    }
                });
    }

    private void register(){
        validation();

        mAuth.createUserWithEmailAndPassword(username,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"User Registered Successfully",Toast.LENGTH_LONG).show();

                        }else{
                            Toast.makeText(getApplicationContext(),"User Registration Failed",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
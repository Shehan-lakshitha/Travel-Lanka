package com.example.travellanka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    private EditText edtTextName, editTextEmail, editTextPassword;
    private Button btnSignUp;
    private Button btnFacebook, btnGoogle, btnTwitter;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initValue();

        mAuth= FirebaseAuth.getInstance();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

    }

    //method to initialize the buttons
    private void initValue(){

        edtTextName = findViewById(R.id.edtTextName);
        editTextEmail = findViewById(R.id.edtTextEmail);
        editTextPassword = findViewById(R.id.edtTextPassword);

        btnSignUp = findViewById(R.id.btnSignUp);
        btnFacebook = findViewById(R.id.btnFacebook);
        btnGoogle = findViewById(R.id.btnGoogle);
        btnTwitter = findViewById(R.id.btnTwitter);
    }

    private void registerUser(){
        String name = edtTextName.toString().trim();
        String email = editTextEmail.toString().trim();
        String password = editTextPassword.toString().trim();

        if (name.isEmpty()){
            edtTextName.setError("Please enter your name");
            edtTextName.requestFocus();
            return;
        }
        if (email.isEmpty()){
            editTextEmail.setError("Enter your email");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            editTextPassword.setError("Please enter a valid password");
            editTextPassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(SignUp.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUp.this,MainActivity.class));
                }else {
                    Toast.makeText(SignUp.this, "SignUp Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }




}
















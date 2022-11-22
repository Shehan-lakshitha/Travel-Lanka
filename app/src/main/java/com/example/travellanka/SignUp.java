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
        mAuth = FirebaseAuth.getInstance();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

    }


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
        String name = edtTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password =editTextPassword.getText().toString().trim();


        if (name.isEmpty()){
            edtTextName.setError("Please Enter your Name");
            edtTextName.requestFocus();
            return;
        }
        if (email.isEmpty()){
            editTextEmail.setError("Please Enter your Email");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            editTextPassword.setError("Please Enter a Password");
            editTextPassword.requestFocus();
            return;
        }
        if (password.length()<6){
            editTextPassword.setError("Min 6 characters");
            editTextPassword.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please Enter valid email");
            editTextEmail.requestFocus();
            return;
        }



        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            User user = new User(email,name);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()){
                                        Toast.makeText(SignUp.this, "Sign in successful", Toast.LENGTH_LONG).show();

                                        Intent intent = new Intent(SignUp.this,SignIn.class);
                                        startActivity(intent);
                                    }else {
                                        Toast.makeText(SignUp.this, "Login failed, Please try again", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }else {
                            Toast.makeText(SignUp.this, "Login failed, Please try again", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
















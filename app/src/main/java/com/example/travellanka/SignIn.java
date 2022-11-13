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

public class SignIn extends AppCompatActivity {

    private Button btnLogin,btnFacebook,btnGoogle,btnTwitter;
    private EditText edtTextEmail,edtTextPassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });
    }


    private void userLogin(){
        String email = edtTextEmail.getText().toString().trim();
        String password = edtTextPassword.getText().toString().trim();

        if (email.isEmpty()){
            edtTextEmail.setError("Please enter a email");
            edtTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            edtTextPassword.setError("Please enter your password");
            edtTextPassword.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edtTextEmail.setError("Please enter valid email");
            edtTextEmail.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    Toast.makeText(SignIn.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignIn.this,MainActivity.class));
                }else {
                    Toast.makeText(SignIn.this, "Login Failed,Please check your credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //method to initialize buttons
    private void initValue(){
        btnLogin = findViewById(R.id.btnLogin);
        btnFacebook = findViewById(R.id.btnFacebook);
        btnGoogle = findViewById(R.id.btnGoogle);
        btnTwitter = findViewById(R.id.btnTwitter);

        edtTextEmail = findViewById(R.id.edtTextEmail);
        edtTextPassword = findViewById(R.id.edtTextPassword);
    }

}
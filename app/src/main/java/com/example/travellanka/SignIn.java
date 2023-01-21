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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initValue();



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
package com.example.fastmilk_costumer;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignInActivity extends AppCompatActivity {
    private static final String TAG = "SignInActivity";
    private TextView btnForgotPassword;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private String getUserID;
    private Button btnSignIn;
    private EditText txtEmail;
    private EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        txtEmail = findViewById(R.id.input_email);
        txtPassword = findViewById(R.id.input_password);

        btnForgotPassword = findViewById(R.id.txt_forgot_password);
        btnForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ForgotPasswordActivity.class));
                finish();
            }
        });

        btnSignIn = findViewById(R.id.btnLogin);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtEmail.getText().toString().trim().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Harap isi Email dengan benar!", Toast.LENGTH_LONG).show();
                    return;
                }

                if(txtPassword.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Harap isi Password dengan benar!", Toast.LENGTH_LONG).show();
                    return;
                }
                SignIn();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), activity_landing.class));
        finish();
    }

    private void SignIn(){
        Log.d(TAG, "SignIn");
        if(!validateForm()){
            return;
        }

        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
//                Log.d(TAG, "SignIn:onComplete:" + task.isSuccessful());

                if(task.isSuccessful()){
                    onAuthSuccess();
                } else {
                    Toast.makeText(getApplicationContext(), "Login Gagal", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void onAuthSuccess(){
        Toast.makeText(getApplicationContext(), "Login Berhasil", Toast.LENGTH_LONG).show();
    }

    private boolean validateForm(){
        boolean result = true;

        if(TextUtils.isEmpty(txtEmail.getText().toString())){
            txtEmail.setError("Email harus diisi");
            result = false;
        } else {
            txtEmail.setError(null);
        }

        if(TextUtils.isEmpty(txtPassword.getText().toString())){
            txtPassword.setError("Password harus diisi");
            result = false;
        } else {
            txtPassword.setError(null);
        }

        return result;
    }
}
package com.example.fastmilk_costumer;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fastmilk_costumer.entity.Pelanggan;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = "SignUpActivity";
    private FirebaseAuth mAuth;
    private FirebaseFirestore database;
    private CollectionReference reference;
    private EditText txtEmail;
    private EditText txtNama;
    private EditText txtNotelp;
    private EditText txtAlamat;
    private EditText txtPassword;
    private Button btnSignUp;
    private ProgressBar loader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();
        reference = database.collection("Pelanggan");

        txtEmail = findViewById(R.id.input_email);
        txtNama = findViewById(R.id.input_nama);
        txtNotelp = findViewById(R.id.input_telepon);
        txtAlamat = findViewById(R.id.input_alamat);
        txtPassword = findViewById(R.id.input_password);
        loader = findViewById(R.id.loader);

        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUp();

                if(txtEmail.getText().toString().trim().isEmpty()){
                    Snackbar.make(view, "Email tidak boleh kosong", Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(txtNama.getText().toString().trim().isEmpty()){
                    Snackbar.make(view, "Nama tidak boleh kosong", Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(txtNotelp.getText().toString().trim().isEmpty()){
                    Snackbar.make(view, "No.telp tidak boleh kosong", Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(txtAlamat.getText().toString().trim().isEmpty()){
                    Snackbar.make(view, "Alamat tidak boleh kosong", Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(txtPassword.getText().toString().trim().isEmpty()){
                    Snackbar.make(view, "Password tidak boleh kosong", Snackbar.LENGTH_LONG).show();
                    return;
                } else if (txtPassword.length() < 6) {
                    Snackbar.make(view, "Input minimal 6 huruf", Snackbar.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), activity_landing.class));
        finish();
    }

    private void SignUp(){
        Log.d(TAG, "SignUp");

        if(!validateForm()){
            return;
        }

        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();
        loader.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "SignUp:onComplete:" + task.isSuccessful());

                if(task.isSuccessful()){
                    loader.setVisibility(View.GONE);
                    onAuthSuccess(task.getResult().getUser());
                } else {
                    Toast.makeText(getApplicationContext(), "Daftar Gagal :(", Toast.LENGTH_LONG);
                }
            }
        });
    }

    private boolean validateForm(){
        boolean result = true;

        if(TextUtils.isEmpty(txtEmail.getText().toString())){
            result = false;
        } else {
            txtEmail.setError(null);
        }
        if(TextUtils.isEmpty(txtPassword.getText().toString())){
            result = false;
        } else {
            txtPassword.setError(null);
        }
        return result;
    }

    private void onAuthSuccess(FirebaseUser user) {
        String nama_lengkap = txtNama.getText().toString();
        String no_telp = txtNotelp.getText().toString();
        String alamat = txtAlamat.getText().toString();

        createNewCustomer(user.getUid(), user.getEmail() ,nama_lengkap, no_telp, alamat);
    }

    private void createNewCustomer(String idPel, String Email_pelanggan, String Nama_pelanggan, String No_Telepon, String Alamat) {
        Pelanggan pelanggan = new Pelanggan(idPel ,Email_pelanggan, Nama_pelanggan, No_Telepon, Alamat);

        reference.add(pelanggan).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(), "Daftar baru berhasil !", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            }
        });
    }
}

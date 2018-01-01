package com.example.asus.fictor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by ASUS on 20/12/2017.
 */


public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextRetypePassword;

    private Button btnRegister;
    private Button btnLoginNow;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();

        editTextEmail = (EditText) findViewById(R.id.editText_Email);
        editTextPassword = (EditText) findViewById(R.id.editText_Password);
        editTextRetypePassword = (EditText) findViewById(R.id.confirmPassword);

        btnRegister = (Button) findViewById(R.id.btn_Register);
        btnLoginNow = (Button) findViewById(R.id.btnLoginNow);

        btnRegister.setOnClickListener(this);
        btnLoginNow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_Register){
            register();
        }
        if (id == R.id.btnLoginNow){
            Intent login = new Intent(this, LoginActivity.class);
            startActivity(login);
        }
    }

    private void register(){
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String retypePassword = editTextRetypePassword.getText().toString();

        progressDialog = new ProgressDialog(this);

        if(!isValidateEmail(email)){
            Toast.makeText(this, "Email kosong atau salah",Toast.LENGTH_SHORT).show();
        }else if(!isEmptyField(password)){
            Toast.makeText(this, "Password harus diisi",Toast.LENGTH_SHORT).show();
        }else if(!isEmptyField(retypePassword)){
            Toast.makeText(this, "Retype password harus diisi",Toast.LENGTH_SHORT).show();
        }else if(!isMatch(password,retypePassword)){
            Toast.makeText(this, "Password tidak cocok",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Register Berhasil",Toast.LENGTH_SHORT).show();
        }

        progressDialog.setMessage("Mendaftarkan Pengguna...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            //user sukses terdaftar
                            Toast.makeText(SignUpActivity.this, "Pendaftaran berhasil", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     *
     * @param email
     * Method dibawah ini untuk validasi email kosong atau salah
     */
    private boolean isValidateEmail(String email){
        return !TextUtils.isEmpty(email)&& Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     *
     * @param yourField
     * Method ini digunakan untuk validasi field kosong atau tidak
     */
    private boolean isEmptyField(String yourField){
        return !TextUtils.isEmpty(yourField);
    }

    /**
     *
     * @param password
     * @param retypePassword
     * method ini digunakan untuk mencocokan password dengan retype password
     */
    private boolean isMatch(String password, String retypePassword){
        return password.equals(retypePassword);
    }
}

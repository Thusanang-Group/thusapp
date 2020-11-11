package com.example.thusanang;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class ParentLogin extends AppCompatActivity {

    EditText Username, Password;
    TextView ForgotPassword, NewUser;
    Button LoginIn;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_login);

        Username = findViewById(R.id.emailAddress);
        Password = findViewById(R.id.password);

        NewUser = findViewById(R.id.createAccount);
        LoginIn = findViewById(R.id.sign_in);
        firebaseAuth = FirebaseAuth.getInstance();

        LoginIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Username.getText().toString().trim();
                String password = Password.getText().toString().trim();


                if (TextUtils.isEmpty(email)) {
                    Username.setError("Email is Required!");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Password.setError("Password is Required");
                } else if (password.length() <= 7) {
                    Password.setError("Incorrect password Details");
                }

                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ParentLogin.this, "You Have Logged Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Dashboard.class));
                        } else {
                            Toast.makeText(ParentLogin.this, "Wrong User Email or Password Please try Again", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
        NewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ParentLogin.this, Registration.class));
            }
        });

    }
}

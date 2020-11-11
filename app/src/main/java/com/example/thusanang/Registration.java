package com.example.thusanang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {
    EditText FullName,UserEmail,PhoneNumber,Occupation,HomeAddress,UserPassword;
    Button CreateAccount;
    FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private static final String USERS = "users";
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_layout);

        FullName = findViewById(R.id.fullName);
        UserEmail = findViewById(R.id.emailAddress);

        UserPassword = findViewById(R.id.createPassword);
        CreateAccount = findViewById(R.id.createUser);

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference(USERS);

        CreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = FullName.getText().toString().trim();
                String Email = UserEmail.getText().toString().trim();
                String Pwd = UserPassword.getText().toString().trim();


                if(TextUtils.isEmpty(Name)){
                    FullName.setError("Full Name Required");
                    return;
                }
                if(TextUtils.isEmpty(Email)){
                    UserEmail.setError("Email Required");
                    return;
                }
                if(TextUtils.isEmpty(Email)){
                    HomeAddress.setError("Home Address Required");
                    return;
                }
                if (TextUtils.isEmpty(Pwd)) {
                    UserPassword.setError("New password is Required");
                    return;
                } else if (Pwd.length() <= 7) {
                    UserPassword.setError("The Password characters has to equal or greater than seven !");
                    return;
                }


                user = new User(Name,Email);
                firebaseAuth.createUserWithEmailAndPassword(Email,Pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            updateUI(firebaseUser);

                        }else
                        {
                            Toast.makeText(Registration.this, "Failed To Create New Account Please Try Again" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
    }
    public void updateUI(FirebaseUser currentUser) {
        String keyid = mDatabase.push().getKey();
        mDatabase.child(keyid).setValue(user); //adding user info to database
        Intent loginIntent = new Intent(this, MainActivity.class);
        startActivity(loginIntent);
    }
}

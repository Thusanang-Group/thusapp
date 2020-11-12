package com.example.thusanang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import static androidx.constraintlayout.widget.Constraints.TAG;

import de.hdodenhof.circleimageview.CircleImageView;

public class Dashboard extends AppCompatActivity {
    CardView gameOne, educator, candy;
    TextView fullName, Email, Occupation;
    private CircleImageView profileImageView;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference mFirebaseDatabase;
    private Uri imageUri;
    int GALLERY_REQUEST = 1000;
    //Firebase
    FirebaseStorage storage;
    StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Init();
        Navigation();
        GetCurrentUser();

    }
    //Code to pull the current user from the Firebase Database
     public void GetCurrentUser(){
         mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                 if (firebaseUser != null) {
                     Log.d(TAG, "onCreate: " + firebaseUser.getDisplayName());
                     if (firebaseUser.getDisplayName() != null) {
                         fullName.setText(firebaseUser.getDisplayName());
                     }
                     if (firebaseUser.getPhotoUrl() != null) {
                         Glide.with(Dashboard.this)
                                 .load(firebaseUser.getPhotoUrl())
                                 .into(profileImageView);
                     }

                 }
                 for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                     User user = postSnapshot.getValue(User.class);
                     String name = user.getFull_Name();
                     String email = user.getEmail();
                     String work = user.getWork();
                     String address = user.getAddress();
                     String phone = user.getPhone();

                     fullName.setText(name);
                     Email.setText(email);


                 }

             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });

     }
     public void Init(){
         //Init of Card Views
         gameOne = findViewById(R.id.brainTrainer);
         educator = findViewById(R.id.Education);
         candy = findViewById(R.id.match);
         //Init of the Profile Card View
         profileImageView = findViewById(R.id.profile_picture);
         fullName = findViewById(R.id.full_name);
         Email = findViewById(R.id.email);
         Occupation = findViewById(R.id.occupation);
         ///
         /**init**/
         storage = FirebaseStorage.getInstance();
         storageReference = storage.getReference();
         mFirebaseDatabase = FirebaseDatabase.getInstance().getReference().child("users");
         firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


     }
     public void Navigation(){
         gameOne.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(Dashboard.this, BrainTrainer.class));
             }
         });
         educator.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(Dashboard.this, EducationSection.class));
             }
         });
         candy.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(Dashboard.this, MatchTheCandy.class));
             }
         });
     }
}
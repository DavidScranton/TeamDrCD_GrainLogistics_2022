package com.example.teamdrcd_grainlogistics_2022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignOnWithEmail extends AppCompatActivity {
    //private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_on_with_email);
        //FirebaseUser currentUser = mAuth.getCurrentUser();
        Intent intent = getIntent();
        String emailLink = intent.getData().toString();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        User cur = new User();
// Confirm the link is a sign-in with email link.
        if (mAuth.isSignInWithEmailLink(emailLink)) {

            // The client SDK will parse the code from the link for you.
            mAuth.signInWithEmailLink(cur.getEmail(), emailLink)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //Log.d(TAG, "Successfully signed in with email link!");
                                AuthResult result = task.getResult();
                                FirebaseUser user = result.getUser();
                                updateUI(user);
                                // You can access the new user via result.getUser()
                                // Additional user info profile *not* available via:
                                // result.getAdditionalUserInfo().getProfile() == null
                                // You can check if the user is new or existing:
                                // result.getAdditionalUserInfo().isNewUser()
                            } else {
                                //Log.e(TAG, "Error signing in with email link", task.getException());
                            }
                        }
                    });
        }
    }

    private void updateUI(FirebaseUser user) {
        startActivity(new Intent(SignOnWithEmail.this, MapsActivity.class));
    }
}
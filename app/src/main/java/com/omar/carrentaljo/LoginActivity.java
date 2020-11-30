package com.omar.carrentaljo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

public class LoginActivity extends AppCompatActivity {

     FirebaseAuth mAuth;
    EditText Email,Password;
    Button Login,SignUp,Admin;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    String checkEmail;

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email=(EditText)findViewById(R.id.Email_login);
        Password=(EditText)findViewById(R.id.Pass_login);
        Login=(Button)findViewById(R.id.Login_login);
        SignUp=(Button)findViewById(R.id.SignUp_login);
        Admin=(Button)findViewById(R.id.admin_login);

        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mDatabaseReference=mFirebaseDatabase.getReference();


        Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, AdminLoginActivity.class);
                startActivity(intent);
            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser()!=null) {
            checkEmail=mAuth.getCurrentUser().getEmail();
            if(checkEmail.equals("kiwan@yahoo.com") || checkEmail.equals("hasan@yahoo.com"))
            {
                Intent i = new Intent(getApplicationContext(), AdminActivity.class);
                startActivity(i);
            }
            else {
                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(i);
            }

        }
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String Email1 = Email.getText().toString();
                String Pass1 = Password.getText().toString();

                if(!Email1.equals("") || !Pass1.equals("") )
                {
                mAuth.signInWithEmailAndPassword(Email1, Pass1)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("hi", "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    // startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                                    Intent mIntent = new Intent(LoginActivity.this, HomeActivity.class);
                                    mIntent.putExtra("Email", Email1);

                                    startActivity(mIntent);


                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("hi", "signInWithEmail:failure", task.getException());
                                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }

                                // ...
                            }
                        });

            }
            else
                    Toast.makeText(LoginActivity.this, "Please fil the above.",Toast.LENGTH_LONG).show();



                            /////////////////////////////////
            }
        });





    }
}


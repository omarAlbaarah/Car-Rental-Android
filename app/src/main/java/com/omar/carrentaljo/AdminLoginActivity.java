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


public class AdminLoginActivity extends AppCompatActivity {


FirebaseAuth maAuth;
EditText email,password;
Button login;
DatabaseReference mDatabaseReference ;
    boolean flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        email=(EditText)findViewById(R.id.adminLogin_email);
        password=(EditText)findViewById(R.id.admin_login_password);

        login=(Button)findViewById(R.id.adminlogin_submit);

        maAuth = FirebaseAuth.getInstance();
        mDatabaseReference= FirebaseDatabase.getInstance().getReference("Admin");
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String Email1=email.getText().toString();
                String Pass1=password.getText().toString();
                if(Email1.equalsIgnoreCase("Kiwan@yahoo.com") || Email1.equalsIgnoreCase("hasan@yahoo.com"))
                    flag=true;


                /*mDatabaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for(DataSnapshot value :dataSnapshot.getChildren()) {
                            adminClass adm = value.getValue(adminClass.class);
                            if(Email1.equals(adm.getAdminEmail())) {
                             flag=true;
                            }

                        }
                        if(flag==false)
                        {
                            email.setError("user does not exist");

                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });*/


                if(flag==true) {
                    maAuth.signInWithEmailAndPassword(Email1, Pass1)
                            .addOnCompleteListener(AdminLoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("hi", "signInWithEmail:success");
                                        FirebaseUser user = maAuth.getCurrentUser();

                                        Intent mIntent = new Intent(AdminLoginActivity.this, AdminActivity.class);
                                        mIntent.putExtra("Email", Email1);

                                        startActivity(mIntent);


                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("hi", "signInWithEmail:failure", task.getException());
                                        Toast.makeText(AdminLoginActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }

                                    // ...
                                }
                            });
                }


            }
        });









    }
}

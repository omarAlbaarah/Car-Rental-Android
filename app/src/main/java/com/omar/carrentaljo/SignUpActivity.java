package com.omar.carrentaljo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.ProviderQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    FirebaseDatabase fb;
    DatabaseReference DR;
    private FirebaseAuth FA;
    DatabaseReference Data_city;
    String userId;

    EditText Name,Email,Password,ConfirmedPassword,Age,Number,Address;
    Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Name=(EditText)findViewById(R.id.Name_signup);
        Email=(EditText)findViewById(R.id.Email_signup);
        Password=(EditText)findViewById(R.id.Pass_signup);
        ConfirmedPassword=(EditText)findViewById(R.id.Confirm_signup);
        Age=(EditText)findViewById(R.id.Age_signup);
        Number=(EditText)findViewById(R.id.Phone_signup);
        Address=(EditText)findViewById(R.id.Address_signup);

        signup=(Button)findViewById(R.id.Signup_signup);

        signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String name1=Name.getText().toString();
        String email1=Email.getText().toString();
        String address1=Address.getText().toString();
        String pass1=Password.getText().toString();
        String confirmPass1=ConfirmedPassword.getText().toString();
        int phone1=Integer.parseInt(Number.getText().toString());
        int age1=Integer.parseInt(Age.getText().toString());

        FA = FirebaseAuth.getInstance();
        fb= FirebaseDatabase.getInstance();
        DR = fb.getReference("users");



        if(email1.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email1).matches())
        {
            Email.setError("Please Enter a Valid Email");
            Email.requestFocus();
            return;
        }



        if(pass1.length()<8 &&!isValidPassword(pass1)){

            Password.setError("Password must be at least 8 characters with 1 Alphabet, 1 Number and 1 Special Character");
            Password.requestFocus();
            return;

        }

        if(!confirmPass1.equals(pass1))
        {
            ConfirmedPassword.setError("Does Not Match");
            ConfirmedPassword.requestFocus();
            return;
        }

       FA.fetchProvidersForEmail(email1).addOnCompleteListener(new OnCompleteListener<ProviderQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<ProviderQueryResult> task) {
                Boolean check=!task.getResult().getProviders().isEmpty();
                if(check)
                {
                    Email.setError("Email is already exist");
                    return;
                }
            }
        });


        userId=DR.push().getKey();
       UserInformaton obj1=new UserInformaton(name1,userId,pass1,email1,address1,age1,phone1);

        DR.child(userId).setValue(obj1);




        FA.createUserWithEmailAndPassword(email1,pass1).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),"Registered successful",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),SliderActivity.class));

                }
                else
                    Toast.makeText(getApplicationContext(),"Registered Failed",Toast.LENGTH_LONG).show();

            }
        });


    }
    public static boolean isValidPassword(final String password){

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);


        return pattern.matcher(password).matches();

    }
}

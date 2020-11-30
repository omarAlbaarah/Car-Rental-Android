package com.omar.carrentaljo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyInfoActivity extends AppCompatActivity {

    DatabaseReference mReference;
    String userEmail;
    ArrayList<UserInformaton>userInformaton;
    TextView name,phone,address,email;

    String userid,userPhone,userlocaiton,username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);

        name=(TextView) findViewById(R.id.myinfo_name);
        phone=(TextView) findViewById(R.id.info_phone);
        address=(TextView) findViewById(R.id.info_address);
        email=(TextView) findViewById(R.id.info_email);
        mReference= FirebaseDatabase.getInstance().getReference("users");

        userInformaton=new ArrayList<>();

        userEmail= FirebaseAuth.getInstance().getCurrentUser().getEmail();

        email.setText(userEmail);


        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot value : dataSnapshot.getChildren()) {
                    UserInformaton obj1=value.getValue(UserInformaton.class);

                    if(obj1.getEmail().equals(userEmail)) {
                        userid = obj1.getId();
                        userPhone = obj1.getPhoneNumber() + "";
                        userlocaiton = obj1.getHomeAddress();
                        username = obj1.getName();
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Toast.makeText(getApplicationContext(),"hi "+userid,Toast.LENGTH_LONG).show();
        name.setText(username+" ok");
        phone.setText(userPhone);
        address.setText(userlocaiton);
        email.setText(userEmail);

    }
}

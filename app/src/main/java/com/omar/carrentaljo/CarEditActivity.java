package com.omar.carrentaljo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CarEditActivity extends AppCompatActivity {

    EditText CarName,CarPrice,CarLocation,CarOfficeName;
    Switch CarStatus;
    String Car_id;
    DatabaseReference mDatabaseReference;
    CarClass CarObject;
    Button Edit,Delete;
    List<CarClass> arraylist;
    Boolean CarSt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_edit);

        mDatabaseReference= FirebaseDatabase.getInstance().getReference("Cars");
        CarName=(EditText)findViewById(R.id.CarEdit_CarName);
        CarPrice=(EditText)findViewById(R.id.CarEdit_Price);
        CarLocation=(EditText)findViewById(R.id.CarEdit_Location);


        Edit=(Button)findViewById(R.id.CarEdit_Confirm);
        Delete=(Button)findViewById(R.id.CarEdit_Delete);
        CarStatus=(Switch)findViewById(R.id.CarEdit_status);
        arraylist=new ArrayList<>();
        Bundle mBundle=getIntent().getExtras();
        if(mBundle !=null)
        {
           Car_id=mBundle.getString("CarId");

        }


        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot value :dataSnapshot.getChildren())
                {
                    CarClass obj4=value.getValue(CarClass.class);

                    if(obj4.getId().equals(Car_id))
                        arraylist.add(obj4);



                }
                CarName.setText(arraylist.get(0).getCarName());
                CarPrice.setText(arraylist.get(0).getPrice()+"");
                CarLocation.setText(arraylist.get(0).getLocation());
                CarSt=arraylist.get(0).getStatus();

                if(CarSt==true)
                 CarStatus.setChecked(true);
                else
                    CarStatus.setChecked(false);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!CarName.equals(null) || !CarOfficeName.equals(null)
                        || !CarPrice.equals(null) || !CarLocation.equals(null))
                {
                    double pr=Double.parseDouble(CarPrice.getText().toString());
                    mDatabaseReference.child(arraylist.get(0).getId()).child("carName").setValue(CarName.getText().toString());
                    mDatabaseReference.child(arraylist.get(0).getId()).child("price").setValue(pr);
                    mDatabaseReference.child(arraylist.get(0).getId()).child("location").setValue(CarLocation.getText().toString());


                 if(CarStatus.isChecked()==true)
                    mDatabaseReference.child(arraylist.get(0).getId()).child("status").setValue(true);

                 else
                     mDatabaseReference.child(arraylist.get(0).getId()).child("status").setValue(false);


                    if(CarSt==true)
                        CarStatus.setChecked(true);
                    else
                        CarStatus.setChecked(false);


                }
                else
                    Toast.makeText(getApplicationContext(),"you must fill all of the above",Toast.LENGTH_LONG).show();
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseReference_delete=FirebaseDatabase.getInstance()
                        .getReference("Cars").child(Car_id);
                databaseReference_delete.removeValue();
                CarName.setText("");
                CarPrice.setText("");
                CarLocation.setText("");
                CarOfficeName.setText("");
            }
        });



    }
}

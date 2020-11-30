package com.omar.carrentaljo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Admin_confirmReservaionActivity extends AppCompatActivity {

    String CarId;
    TextView carname,location,username,userphone,price,pick,drop;
    Button confirm,cancel;
    DatabaseReference mReference,Reference2;
    ReservationClass reservation_obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_confirm_reservaion);


        Bundle mBundle=getIntent().getExtras();
        if(mBundle !=null)
        {
            CarId=mBundle.getString("CarId");

        }


        carname=(TextView)findViewById(R.id.ad_carName);
        location=(TextView)findViewById(R.id.ad_Location);
        username=(TextView)findViewById(R.id.ad_userName);
        userphone=(TextView)findViewById(R.id.ad_phone);
        price=(TextView)findViewById(R.id.ad_TotalPrice);
        pick=(TextView)findViewById(R.id.ad_pickdate);
        drop=(TextView)findViewById(R.id.ad_dropdate);
        confirm=(Button)findViewById(R.id.ad_confirm);
        cancel=(Button)findViewById(R.id.ad_cancel);

        mReference= FirebaseDatabase.getInstance().getReference("Reservation");
        Reference2=FirebaseDatabase.getInstance().getReference("Confirmed_Reservation");

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot value : dataSnapshot.getChildren())
                {
                    ReservationClass obj=value.getValue(ReservationClass.class);
                    if(obj.getCarId().equals(CarId))
                    {
                        carname.setText(obj.getCarName());
                        location.setText(obj.getLocation());
                        username.setText(obj.getUserName());
                        userphone.setText(obj.getUser_PhoneNumber()+"");
                        price.setText(obj.getTotalPrice()+"");
                        int month1,month2;
                        month1=obj.getPickUp_Date().getMonth();
                        month1++;
                        month2=obj.getDropOFF_Date().getMonth();
                        month2++;
                        pick.setText(obj.getPickUp_Date().getDate()+", "+month1+", "+obj.getPickUp_Date().getYear());
                        drop.setText(obj.getDropOFF_Date().getDate()+", "+month2+", "+obj.getDropOFF_Date().getYear());

                        reservation_obj=obj;
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference databaseReference_delete=FirebaseDatabase.getInstance()
                        .getReference("Reservation").child(reservation_obj.getReservationId());
                databaseReference_delete.removeValue();
                Intent in2=new Intent(Admin_confirmReservaionActivity.this,AdminActivity.class);
                startActivity(in2);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reservation_obj.setReservationId(Reference2.push().getKey());
                Reference2.child(reservation_obj.getReservationId()).setValue(reservation_obj);

                DatabaseReference databaseReference_delete=FirebaseDatabase.getInstance()
                        .getReference("Reservation").child(reservation_obj.getReservationId());
                databaseReference_delete.removeValue();
                Intent in2=new Intent(Admin_confirmReservaionActivity.this,AdminActivity.class);
                startActivity(in2);
            }
        });



    }
}

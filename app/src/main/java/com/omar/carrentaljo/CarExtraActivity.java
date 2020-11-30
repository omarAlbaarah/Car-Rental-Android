package com.omar.carrentaljo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarExtraActivity extends AppCompatActivity {

    String CarName;
    String CarId;
    ListView mListView;
    List<CarClass> List1;
    Button Continue;
     double CarPrice;
    CheckBox CancelProtection,Roadside,Driver,BabySeat,RoofTop;
    DatabaseReference mReference;

    String Pick_Time,Drop_Time,CityName;
    Date Pick_Date,Drop_Date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_extra);

        mReference= FirebaseDatabase.getInstance().getReference("Cars");
        mListView=(ListView)findViewById(R.id.carextra_listview);
        List1=new ArrayList<>();

        Continue=(Button)findViewById(R.id.carextra_ContinueBook);
        CancelProtection=(CheckBox)findViewById(R.id.CancelProtection_checkBox);
        Roadside=(CheckBox)findViewById(R.id.Roadside_checkBox);
        Driver=(CheckBox)findViewById(R.id.AdditionalDriver_checkBox);
        BabySeat=(CheckBox)findViewById(R.id.babyseat_checkbox);
        RoofTop=(CheckBox)findViewById(R.id.RoofTop_checkbox);



        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent mIntent1=new Intent(CarExtraActivity.this,ConfirmReservationActivity.class);
                mIntent1.putExtra("Cancel",CancelProtection.isChecked());
                mIntent1.putExtra("Driver",Driver.isChecked());
                mIntent1.putExtra("RoadSide",Roadside.isChecked());
                mIntent1.putExtra("BabySeat",BabySeat.isChecked());
                mIntent1.putExtra("RoofTop",RoofTop.isChecked());
                mIntent1.putExtra("CarPrice",CarPrice);
                mIntent1.putExtra("CarId",CarId);

                mIntent1.putExtra("PickUpDate",Pick_Date);
                mIntent1.putExtra("PickupTime",Pick_Time);
                mIntent1.putExtra("DropDate",Drop_Date);
                mIntent1.putExtra("DropTime",Drop_Time);
                mIntent1.putExtra("CityName",CityName);
                startActivity(mIntent1);




            }
        });


        Bundle mBundle=getIntent().getExtras();
        if(mBundle !=null)
        {
            CarName=mBundle.getString("CarName");
            CarId=mBundle.getString("CarId");

            CityName=mBundle.getString("CityName");
            Pick_Date= (Date) mBundle.get("PickUpDate");
            Pick_Time=mBundle.getString("PickupTime");
            Drop_Date= (Date) mBundle.get("DropDate");
            Drop_Time=mBundle.getString("DropTime");


        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              List1.clear();
                for(DataSnapshot value :dataSnapshot.getChildren()){
                    CarClass Obj1=value.getValue(CarClass.class);

                    if(Obj1.getId().equalsIgnoreCase(CarId)) {
                        List1.add(Obj1);
                    CarPrice=Obj1.getPrice();
                    }
                }


                    CarAdapter adapter = new CarAdapter(CarExtraActivity.this, List1);
                    mListView.setAdapter(adapter);




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}



















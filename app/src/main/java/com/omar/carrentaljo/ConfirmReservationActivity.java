package com.omar.carrentaljo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class ConfirmReservationActivity extends AppCompatActivity {

    TextView CarCost,AdditionalOptionsCost,TotalCost,Driver1,Driver2,
            RoadSide1,RoadSide2,BabySeat1,BabySeat2,RoofTop1,RoofTop2;

    RadioGroup radioGroup;
    RadioButton cash,online;
    Button BookNow;
    DatabaseReference mReference,CarReference,reservaitonRefernce;
    EditText NameOnCard,CardNumber;
    String Carid,adminid,carname;
    int Cost1=0;
    String userEmail,userid,userlocaiton,username;
    int userPhone;
    String reservation_id;
    double Totalcost11;
    String Pick_Time,Drop_Time,CityName;
    Date Pick_Date,Drop_Date;
    Boolean flagOnline=false;
    private LinearLayout lay1,lay2,lay3,lay4,lay5,lay6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_reservation);

        CarCost=(TextView)findViewById(R.id.CostofCar);
        AdditionalOptionsCost=(TextView)findViewById(R.id.AdditionalCost);
        Driver1=(TextView)findViewById(R.id.Additional_Driver1);
        Driver2=(TextView)findViewById(R.id.Additional_Driver2);
        RoadSide1=(TextView)findViewById(R.id.RoadsideAssistance1);
        RoadSide2=(TextView)findViewById(R.id.RoadsideAssistance2);
        BabySeat1=(TextView)findViewById(R.id.Babyseat1);
        BabySeat2=(TextView)findViewById(R.id.Babyseat2);
        RoofTop1=(TextView)findViewById(R.id.RoofTop1);
        RoofTop2=(TextView)findViewById(R.id.RoofTop2);
        TotalCost=(TextView)findViewById(R.id.TotalCost);
        radioGroup=(RadioGroup)findViewById(R.id.rg1);
        cash=(RadioButton)findViewById(R.id.Cash);
        online=(RadioButton)findViewById(R.id.Online);
        BookNow=(Button)findViewById(R.id.BookNow);
        NameOnCard=(EditText)findViewById(R.id.nameOnTheCard);
        CardNumber=(EditText)findViewById(R.id.CardNumber);
        lay1=(LinearLayout)findViewById(R.id.lay1);
        lay2=(LinearLayout)findViewById(R.id.lay2);
        lay3=(LinearLayout)findViewById(R.id.lay3);
        lay4=(LinearLayout)findViewById(R.id.lay4);

        userEmail= FirebaseAuth.getInstance().getCurrentUser().getEmail();
        mReference= FirebaseDatabase.getInstance().getReference("users");
        CarReference=FirebaseDatabase.getInstance().getReference("Cars");
        reservaitonRefernce=FirebaseDatabase.getInstance().getReference("Reservation");



        Bundle mBundle=getIntent().getExtras();
        if(mBundle !=null)
        {

            CityName=mBundle.getString("CityName");
            Pick_Date= (Date) mBundle.get("PickUpDate");
            Pick_Time=mBundle.getString("PickupTime");
            Drop_Date= (Date) mBundle.get("DropDate");
            Drop_Time=mBundle.getString("DropTime");

            Carid=mBundle.getString("CarId");
            if(mBundle.getBoolean("Driver")) {
                Driver2.setText("JOD : 25");
                Cost1+=25;
            }
            else {
                Driver1.setVisibility(View.GONE);
                Driver2.setVisibility(View.GONE);
                lay1.setVisibility(View.GONE);
            }
            if(mBundle.getBoolean("RoadSide")) {
                RoadSide2.setText("JOD : 20");
                Cost1+=20;
            }
            else {
                RoadSide1.setVisibility(View.GONE);
                RoadSide2.setVisibility(View.GONE);
                lay2.setVisibility(View.GONE);
            }

            if(mBundle.getBoolean("BabySeat")) {
                BabySeat2.setText("JOD : 5");
                Cost1+=5;
            }
            else {
                BabySeat1.setVisibility(View.GONE);
                BabySeat2.setVisibility(View.GONE);
                lay3.setVisibility(View.GONE);
            }

            if(mBundle.getBoolean("RoofTop")) {
                RoofTop2.setText("JOD : 8");
                Cost1+=8;
            }
            else {
                RoofTop1.setVisibility(View.GONE);
                RoofTop2.setVisibility(View.GONE);
                lay4.setVisibility(View.GONE);
            }
            AdditionalOptionsCost.setText("JOR : "+Cost1);

            double carCost22=mBundle.getDouble("CarPrice");
            Totalcost11=carCost22+Cost1;
           CarCost.setText("JOR : "+carCost22);
           TotalCost.setText("JOR : "+Totalcost11);


        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){

                    case R.id.Cash :

                        break;
                    case R.id.Online:
                        break;

                }
            }
        });
        CarReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot value : dataSnapshot.getChildren()) {
                 CarClass obj11=value.getValue(CarClass.class);

                    if(obj11.getId().equals(Carid)) {
                        adminid = obj11.getAdmin_id();
                        carname=obj11.getCarName();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot value : dataSnapshot.getChildren()) {
                    UserInformaton obj1=value.getValue(UserInformaton.class);
                    if(obj1.getEmail().equals(userEmail)) {
                        userid = obj1.getId();
                        userPhone=obj1.getPhoneNumber();
                        userlocaiton=obj1.getHomeAddress();
                        username=obj1.getName();
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        BookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             DatabaseReference r=FirebaseDatabase.getInstance().getReference("CreditCard");

                if(online.isChecked()) {
                    if (NameOnCard.getText().equals("") || CardNumber.getText().equals("")) {
                        if (NameOnCard.getText().equals(""))
                            NameOnCard.setError("must enter");
                        if (CardNumber.getText().equals(""))
                            CardNumber.setError("must enter");

                    }
                    else {

                        r.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                for(DataSnapshot value : dataSnapshot.getChildren())
                                {
                                    CreditCard obj=value.getValue(CreditCard.class);
                                    if(NameOnCard.equals(obj.getCardholderName()) && CardNumber.getText().equals(obj.getCardNumber()))
                                    {flagOnline=true;}

                                }

                                if(flagOnline)
                                {

                                    reservation_id=reservaitonRefernce.push().getKey();
                                    ReservationClass obj1=new ReservationClass(reservation_id,Carid,userid,adminid,Pick_Date,Drop_Date,Totalcost11,userPhone,userlocaiton,username,carname);



                                    reservaitonRefernce.child(reservation_id).setValue(obj1);
                                    Toast.makeText(getApplicationContext(),"Reservation done",Toast.LENGTH_LONG).show();
                                }



                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


                    }







                }

                else {


                    reservation_id=reservaitonRefernce.push().getKey();
                  ReservationClass obj1=new ReservationClass(reservation_id,Carid,userid,adminid,Pick_Date,Drop_Date,Totalcost11,userPhone,userlocaiton,username,carname);



                   reservaitonRefernce.child(reservation_id).setValue(obj1);
                    Toast.makeText(getApplicationContext(),"Reservation done",Toast.LENGTH_LONG).show();


                }
            }


        });

    }
}

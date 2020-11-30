package com.omar.carrentaljo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class fragment_complete  extends Fragment{


    View v;
    DatabaseReference mReference,CarRefernce,userRefernce;

    List<ReservationClass> ListReservation;
    List<CarClass>ListCars;
    ListView listView1;
    Date date;
    String userEmail;
    String user_id;
    public fragment_complete() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.complete_fragment,container,false);
        mReference= FirebaseDatabase.getInstance().getReference("Confirmed_Reservation");
        CarRefernce=FirebaseDatabase.getInstance().getReference("Cars");
        userRefernce=FirebaseDatabase.getInstance().getReference("users");
        listView1=(ListView)v.findViewById(R.id.MyRents_listView2);

        ListReservation=new ArrayList<>();
        ListCars=new ArrayList<>();

        userEmail= FirebaseAuth.getInstance().getCurrentUser().getEmail();

        userRefernce.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot value : dataSnapshot.getChildren()) {
                    UserInformaton obj1=value.getValue(UserInformaton.class);
                    if(obj1.getEmail().equals(userEmail)) {
                        user_id = obj1.getId();

                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        date= new Date();
        String d1=date+"";

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot value : dataSnapshot.getChildren()) {
                    ReservationClass Obj1 = value.getValue(ReservationClass.class);
                    if(Obj1.getUserId().equals(user_id)) {
                        if (Obj1.getDropOFF_Date().before(new Date())){
                            ListReservation.add(Obj1);

                    }}
                }

                if(ListReservation.size()>0){
                reservation_info_adapter adapter=new reservation_info_adapter((Activity) v.getContext() ,ListReservation);
                listView1.setAdapter(adapter);}

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return v;
    }




}

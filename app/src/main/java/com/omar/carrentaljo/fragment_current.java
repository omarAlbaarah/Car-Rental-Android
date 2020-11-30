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
import java.util.List;

/**
 * Created by DELL on 14/03/2018.
 */

public class fragment_current  extends Fragment{

    View v;
    DatabaseReference mReference,CarRefernce,userRefernce;

    List<ReservationClass> ListReservation;
    List<CarClass>ListCars;
    ListView listView1;

    String userEmail;
    String user_id;
    public fragment_current(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.current_fragment,container,false);
        mReference= FirebaseDatabase.getInstance().getReference("Confirmed_Reservation");
        CarRefernce=FirebaseDatabase.getInstance().getReference("Cars");
      userRefernce=FirebaseDatabase.getInstance().getReference("users");
        listView1=(ListView)v.findViewById(R.id.MyRents_listView);

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

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot value : dataSnapshot.getChildren()) {
                    ReservationClass Obj1 = value.getValue(ReservationClass.class);
                    if(Obj1.getUserId().equals(user_id))
                        ListReservation.add(Obj1);
                }
                reservation_info_adapter adapter=new reservation_info_adapter((Activity) v.getContext() ,ListReservation);
                listView1.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return v;
    }




}

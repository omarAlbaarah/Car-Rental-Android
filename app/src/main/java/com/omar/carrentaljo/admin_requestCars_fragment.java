package com.omar.carrentaljo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by DELL on 02/04/2018.
 */

public class admin_requestCars_fragment extends Fragment {


    View v;
    ListView mListView;
    ArrayList<CarClass> list1;
    DatabaseReference mReference1,mReference2;
    String admin_id;
    ArrayList<ReservationClass> ListReservation;
    public admin_requestCars_fragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.admin_request,container,false);
        mListView=(ListView) v.findViewById(R.id.lv122);
        list1=new ArrayList<>();
        mReference1= FirebaseDatabase.getInstance().getReference("Reservation");
        admin_id= FirebaseAuth.getInstance().getCurrentUser().getUid();
        ListReservation=new ArrayList<>();

        mReference2=FirebaseDatabase.getInstance().getReference("Cars");



        mReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot value :dataSnapshot.getChildren())
                {

                        ReservationClass obj = value.getValue(ReservationClass.class);
                    if(obj.getAdminId().equals(admin_id))
                        ListReservation.add(obj);

                }
                reservation_info_adapter adapter=new reservation_info_adapter((Activity) v.getContext() ,ListReservation);
                mListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent mIntent=new Intent(getActivity(),Admin_confirmReservaionActivity.class);
                mIntent.putExtra("CarId",ListReservation.get(i).getCarId());
                startActivity(mIntent);
            }
        });


      /*  mReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot value :dataSnapshot.getChildren())
                {
                    CarClass obj=value.getValue(CarClass.class);
                    Boolean flag=false;
                    for(int i=0;i<ListReservation.size();i++)
                    {
                        if(obj.getId().equals(ListReservation.get(i).getCarId()))
                            list1.add(obj);


                    }


                }
                CarAdapter adapter = new CarAdapter((Activity) v.getContext(), list1);
               mListView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

*/



        return v;
    }
}

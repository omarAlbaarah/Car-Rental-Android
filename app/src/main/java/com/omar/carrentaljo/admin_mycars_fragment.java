package com.omar.carrentaljo;

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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 25/03/2018.
 */

public class admin_mycars_fragment extends Fragment {

    View v;
    List<CarClass> ListOfCars;
    FirebaseUser user;
    String uid;
    ListView mListView;

    DatabaseReference mDatabaseReference;

    public admin_mycars_fragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.admin_mycars,container,false);
        mListView=(ListView)v.findViewById(R.id.admin_listview);

        user= FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();

        mDatabaseReference= FirebaseDatabase.getInstance().getReference("Cars");

        ListOfCars=new ArrayList<>();


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent in=new Intent(getActivity(),CarEditActivity.class);
                in.putExtra("CarId",ListOfCars.get(i).getId());
                startActivity(in);

            }
        });




            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ListOfCars.clear();
                    for(DataSnapshot value :dataSnapshot.getChildren()){
                        CarClass Obj1=value.getValue(CarClass.class);

                        if(Obj1.getAdmin_id().equals(uid))
                            ListOfCars.add(Obj1);



                    }
                    CarAdapter adapter = new CarAdapter(getActivity(), ListOfCars);
                    mListView.setAdapter(adapter);




                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        return v;
    }
}

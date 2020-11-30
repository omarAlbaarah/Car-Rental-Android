package com.omar.carrentaljo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CityActivity extends AppCompatActivity {


    DatabaseReference mDatabaseReference,Reservaion_Reference;
    ListView mListView;
    List<CarClass>ListAmman;
    String CityName,CarName;
    double Price;
    String Pick_Time,Drop_Time;
    Date Pick_Date,Drop_Date;
    List<CarClass>ListIrbid;
    List<CarClass>ListAjloun;
    AutoCompleteTextView search;
    List<CarClass>search_list;
    List<CarClass> revarsedlist;
    List<ReservationClass>ListReserve;
    List<CarClass> normal;
    RelativeLayout r1;

    boolean f1=true;
    LinearLayout filter,sort;

    Query query;

    String search_txt;

   Toolbar toolbar1;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        mDatabaseReference= FirebaseDatabase.getInstance().getReference("Cars");
        Reservaion_Reference=FirebaseDatabase.getInstance().getReference("Reservation");
        mListView=(ListView)findViewById(R.id.city_list);
        ListAmman=new ArrayList<>();
        ListIrbid=new ArrayList<>();
        ListAjloun=new ArrayList<>();
        ListReserve=new ArrayList<>();
        search_list=new ArrayList<>();
        revarsedlist=new ArrayList<>();
        normal=new ArrayList<>();
        r1=(RelativeLayout)findViewById(R.id.anim11);
        search=(AutoCompleteTextView)findViewById(R.id.search1);

       filter=(LinearLayout)findViewById(R.id.filter_city1);
       sort=(LinearLayout)findViewById(R.id.sort_city1);

       filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CarAdapter adapter = new CarAdapter(CityActivity.this, normal);
                mListView.setAdapter(adapter);
            }
        });

       sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    CarAdapter adapter = new CarAdapter(CityActivity.this, revarsedlist);
                    mListView.setAdapter(adapter);



            }
        });




        /*setSupportActionBar(toolbar1);
        getSupportActionBar().setTitle(CityName);
        //toolbar1.setSubtitle("a");
        toolbar1.setLogo(R.drawable.search);
        toolbar1.setNavigationIcon(R.drawable.navigate);*/



        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent mIntent=new Intent(CityActivity.this,CarExtraActivity.class);
                    if (CityName.equalsIgnoreCase("Amman")) {
                        mIntent.putExtra("CarName", ListAmman.get(i).getCarName());
                        mIntent.putExtra("CarId",ListAmman.get(i).getId());
                    }
                    else if (CityName.equalsIgnoreCase("Irbid")) {
                        mIntent.putExtra("CarName", ListIrbid.get(i).getCarName());
                        mIntent.putExtra("CarId",ListIrbid.get(i).getId());
                    }
                  mIntent.putExtra("PickUpDate",Pick_Date);
                    mIntent.putExtra("PickupTime",Pick_Time);
                    mIntent.putExtra("DropDate",Drop_Date);
                    mIntent.putExtra("DropTime",Drop_Time);
                    mIntent.putExtra("CityName",CityName);
                    startActivity(mIntent);








            }
        });



        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                search_list.clear();
                search_txt=search.getText().toString();
                if (CityName.equalsIgnoreCase("Amman")) {
                    for(int i=0;i<ListAmman.size();i++)
                    {
                        if(ListAmman.get(i).getCarName().toLowerCase().contains(search_txt.toLowerCase()))
                            search_list.add(ListAmman.get(i));
                    }

                }
                else if (CityName.equalsIgnoreCase("Irbid")) {

                    for(int i=0;i<ListIrbid.size();i++)
                    {
                        if(ListIrbid.get(i).getCarName().toLowerCase().contains(search_txt.toLowerCase()))
                            search_list.add(ListIrbid.get(i));
                    }



                }

                CarAdapter adapter = new CarAdapter(CityActivity.this, search_list);
                mListView.setAdapter(adapter);
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.m1,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

        final Bundle mBundle=getIntent().getExtras();
        if(mBundle !=null)
        {
            CityName=mBundle.getString("CityName");
            Pick_Date= (Date) mBundle.get("Pick_Date");
            Pick_Time=mBundle.getString("Pick_Time");
            Drop_Date= (Date) mBundle.get("Drop_Date");
            Drop_Time=mBundle.getString("Drop_Time");

        }




       Reservaion_Reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ListReserve.clear();
                for(DataSnapshot value :dataSnapshot.getChildren()){

                    ReservationClass Obj1=value.getValue(ReservationClass.class);


                    if(!Pick_Date.after(Obj1.getDropOFF_Date())) {
                        ListReserve.add(Obj1);
                    }
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        query= mDatabaseReference.orderByChild("price");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    ListAmman.clear();
                    ListIrbid.clear();
                    ListAjloun.clear();



                    for(DataSnapshot value :dataSnapshot.getChildren()){
                        CarClass Obj1=value.getValue(CarClass.class);
                        boolean flag=true;

                        for(int i=0;i<ListReserve.size();i++) {
                            if (Obj1.getId().equals(ListReserve.get(i).getCarId()))
                                flag=false;
                        }

                        if (Obj1.getStatus() == true && flag==true  ) {
                            if (Obj1.getLocation().equalsIgnoreCase("Amman")) {
                                ListAmman.add(Obj1);
                            } else if (Obj1.getLocation().equalsIgnoreCase("Irbid")) {
                                ListIrbid.add(Obj1);
                            } else if (Obj1.getLocation().equalsIgnoreCase("Ajloun")) {
                                ListAjloun.add(Obj1);
                            }
                        }

                    }

                    if(CityName.equalsIgnoreCase("Amman")) {
                        CarAdapter adapter = new CarAdapter(CityActivity.this, ListAmman);
                        mListView.setAdapter(adapter);
                        for(int i=ListAmman.size()-1;i>=0;i--)
                            revarsedlist.add(ListAmman.get(i));
                        normal=ListAmman;

                    }
                    else if (CityName.equalsIgnoreCase("Irbid")) {
                        CarAdapter adapter = new CarAdapter(CityActivity.this, ListIrbid);
                        mListView.setAdapter(adapter);
                        for(int i=ListIrbid.size()-1;i>=0;i--)
                            revarsedlist.add(ListIrbid.get(i));
                        normal=ListIrbid;
                    }
                    else if (CityName.equalsIgnoreCase("Ajloun")) {
                        CarAdapter adapter = new CarAdapter(CityActivity.this, ListAjloun);
                        mListView.setAdapter(adapter);
                        for(int i=ListAjloun.size()-1;i>=0;i--)
                            revarsedlist.add(ListAjloun.get(i));
                        normal=ListAjloun;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



       /*mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ListAmman.clear();
                ListIrbid.clear();
                ListAjloun.clear();



                for(DataSnapshot value :dataSnapshot.getChildren()){
                    CarClass Obj1=value.getValue(CarClass.class);
                    boolean flag=true;

                    for(int i=0;i<ListReserve.size();i++) {
                        if (Obj1.getId().equals(ListReserve.get(i).getCarId()))
                            flag=false;
                    }

                        if (Obj1.getStatus() == true && flag==true  ) {
                            if (Obj1.getLocation().equalsIgnoreCase("Amman")) {
                                ListAmman.add(Obj1);
                            } else if (Obj1.getLocation().equalsIgnoreCase("Irbid")) {
                                ListIrbid.add(Obj1);
                            } else if (Obj1.getLocation().equalsIgnoreCase("Ajloun")) {
                                ListAjloun.add(Obj1);
                            }
                        }

                }

                if(CityName.equalsIgnoreCase("Amman")) {
                    CarAdapter adapter = new CarAdapter(CityActivity.this, ListAmman);
                    mListView.setAdapter(adapter);
                }
                else if (CityName.equalsIgnoreCase("Irbid")) {
                    CarAdapter adapter = new CarAdapter(CityActivity.this, ListIrbid);
                    mListView.setAdapter(adapter);
                }
                else if (CityName.equalsIgnoreCase("Ajloun")) {
                    CarAdapter adapter = new CarAdapter(CityActivity.this, ListAjloun);
                    mListView.setAdapter(adapter);
                }




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
*/
    }
}

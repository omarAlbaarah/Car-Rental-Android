package com.omar.carrentaljo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    TextView Email;
   int []arrayofphotos={R.mipmap.amman,R.mipmap.aqaba1,R.mipmap.irbid1,R.mipmap.ajloun1,R.mipmap.zarqa,R.mipmap.jerash,R.mipmap.salt1,R.mipmap.karak,R.mipmap.madaba,R.mipmap.maan,R.mipmap.mafraq,R.mipmap.tafeleh};
    String []arrayofnames={"Amman","Aqaba","Irbid","Ajloun","Az Zarqa","Jaresh","alsalt","alkarak","madaba","ma'an","almafraq","altafelah"};

    List<String> list1;
    DatabaseReference myRef,secondRef;
ListView lv;
FirebaseAuth auth;
    FirebaseUser user;
    String uEmail,uid;
    RatingBar userRate;
    DatabaseReference reference;

    Date d1,d2;
    Date date;
    TextView Date1,Time1,drop_date,drop_time;
    private DatePickerDialog.OnDateSetListener mDateSetListener1,mDateSetListener;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Date1=(TextView) findViewById(R.id.Confirm_date1);
        Time1=(TextView)findViewById(R.id.Confirm_time1);
        drop_date=(TextView)findViewById(R.id.drop_date1);
        drop_time=(TextView)findViewById(R.id.drop_time1);


         date= new Date();
        CharSequence s  = DateFormat.format("MM, d, yy ", date.getTime());


      user =FirebaseAuth.getInstance().getCurrentUser();
      uEmail=user.getEmail();
      uid=user.getUid();
      reference=FirebaseDatabase.getInstance().getReference("users");




        Date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal= Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int Month=cal.get(Calendar.MONTH);
                int Day=cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog=new DatePickerDialog(HomeActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener1,year,Month,Day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        mDateSetListener1 =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {




                d1=new Date();
                d1.setMonth(month);
                d1.setYear(year);
                d1.setDate(day);

                if(date.getMonth()>d1.getMonth() && date.getDate()>d1.getDate())
                 Toast.makeText(getApplicationContext(),"invalid date !!",Toast.LENGTH_SHORT).show();
                    else
                {
                    CharSequence s = DateFormat.format("MM d, yy ", d1.getTime());
                    Date1.setText(s);
                }




            }
        };
        Time1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal= Calendar.getInstance();
                int hour=cal.get(Calendar.HOUR);
                hour=hour+1;
                int minute=cal.get(Calendar.MINUTE);
                TimePickerDialog mTimePickerDialog= new TimePickerDialog(HomeActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour1, int minute1) {
                        Time1.setText(hour1+":"+minute1);
                    }
                },hour,minute,false);

                mTimePickerDialog.show();
            }
        });


        drop_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal= Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int Month=cal.get(Calendar.MONTH);
                int Day=cal.get(Calendar.DAY_OF_MONTH);
                Day=Day+1;
                DatePickerDialog dialog=new DatePickerDialog(HomeActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,year,Month,Day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        mDateSetListener =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                d2=new Date();
                d2.setMonth(month);
                d2.setYear(year);
                d2.setDate(day);

                if(date.getMonth()>d2.getMonth() && date.getDate()>d2.getDate())
                    Toast.makeText(getApplicationContext(),"invalid date !!",Toast.LENGTH_SHORT).show();
                else {
                    CharSequence s = DateFormat.format("MM, d, yy ", d2.getTime());
                    drop_date.setText(s);
                }


            }
        };
        drop_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal= Calendar.getInstance();
                int hour=cal.get(Calendar.HOUR);
                int minute=cal.get(Calendar.MINUTE);
                TimePickerDialog mTimePickerDialog= new TimePickerDialog(HomeActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour1, int minute1) {
                        drop_time.setText(hour1+":"+minute1);
                    }
                },hour,minute,false);

                mTimePickerDialog.show();
            }
        });






        lv=(ListView)findViewById(R.id.listview);
        CustomeAdapter myAdapter=new CustomeAdapter(this,arrayofnames,arrayofphotos);

        lv.setAdapter(myAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(Date1.getText().toString().equals("") || Time1.getText().toString().equals("") ||
                        drop_date.getText().toString().equals("") ||drop_time.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"you should select the reservation date !! ",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Intent mIntent = new Intent(HomeActivity.this, CityActivity.class);
                    mIntent.putExtra("CityName", arrayofnames[i]);
                    mIntent.putExtra("CityImage",arrayofphotos[i]);
                    mIntent.putExtra("Pick_Date",d1);
                    mIntent.putExtra("Pick_Time",Time1.getText().toString());
                    mIntent.putExtra("Drop_Date",d2);
                    mIntent.putExtra("Drop_Time",drop_time.getText().toString());
                    startActivity(mIntent);

                }
            }
        });




        myRef = FirebaseDatabase.getInstance().getReference();
        secondRef=myRef.child("users");

        list1= new ArrayList<>();












        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        Email = (TextView) headerView.findViewById(R.id.Email_txt);
        userRate=(RatingBar)headerView.findViewById(R.id.user_rate);
        userRate.setRating(3);
        Email.setText(uEmail);
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    protected void onStart() {
        super.onStart();

        myRef.child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                list1.clear();
               // for(DataSnapshot usersnapsot : dataSnapshot.getChildren() )
                    String value = dataSnapshot.getValue(String.class);
                    list1.add(value);










            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finish();
            System.exit(0);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

       if (id == R.id.Home) {

        } else if (id == R.id.MyRents) {
           Intent in1=new Intent(HomeActivity.this,MyRentsActivity.class);
           startActivity(in1);

        } else if (id == R.id.Myinfo) {
           Intent mIntent=new Intent(HomeActivity.this,MyInfoActivity.class);
           startActivity(mIntent);

        } else if (id == R.id.Settings) {
           Intent mIntent=new Intent(HomeActivity.this,SettingActivity.class);
           startActivity(mIntent);
        } else if (id == R.id.Logout) {

           FirebaseAuth.getInstance().signOut();
           finish();

           Intent in3=new Intent(HomeActivity.this,LoginActivity.class);
           startActivity(in3);

       }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

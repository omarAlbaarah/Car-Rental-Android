package com.omar.carrentaljo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class AdminActivity extends AppCompatActivity {

    private TabLayout mTableLayout;
    private ViewPager mViewPager;
    private ViewPagerAdapter adapter;
    Button signout;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        System.exit(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);



        mTableLayout=(TabLayout)findViewById(R.id.tableLayout_admin);
        mViewPager=(ViewPager)findViewById(R.id.viewpager_admin);
        adapter=new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new admin_addcar_fragment(),"Add Car");
        adapter.addFragment(new admin_mycars_fragment(),"My Cars");
        adapter.addFragment(new admin_requestCars_fragment(),"Requested reservation");


        signout=(Button)findViewById(R.id.admin_SignOut);

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                finish();

                Intent in3=new Intent(AdminActivity.this,LoginActivity.class);
                startActivity(in3);
            }
        });

        mViewPager.setAdapter(adapter);
        mTableLayout.setupWithViewPager(mViewPager);

    }







}

package com.omar.carrentaljo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MyRentsActivity extends AppCompatActivity {




    private TabLayout mTableLayout;
    private ViewPager mViewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_rents);


        mTableLayout=(TabLayout)findViewById(R.id.tableLayout1);
        mViewPager=(ViewPager)findViewById(R.id.viewpager1);
        adapter=new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new fragment_current(),"Current");
        adapter.addFragment(new fragment_upcoming(),"Upcoming");
        adapter.addFragment(new fragment_complete(),"Complete");


        mViewPager.setAdapter(adapter);
        mTableLayout.setupWithViewPager(mViewPager);














    }






}

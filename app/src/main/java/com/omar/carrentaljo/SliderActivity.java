package com.omar.carrentaljo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class SliderActivity extends AppCompatActivity {
    ViewPager viewPager;
    SlideAdapter slideAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);

        viewPager=(ViewPager)findViewById(R.id.viewpager2);
        slideAdapter=new SlideAdapter(getApplicationContext());
        viewPager.setAdapter(slideAdapter);

    }

}

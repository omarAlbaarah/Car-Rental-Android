package com.omar.carrentaljo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class SlideAdapter extends PagerAdapter {
    Context context;
    LayoutInflater inflater;
    TextView [] mDots;
    LinearLayout dotslayout;

    public String[] List_Description={
      "Our 3 Step Simple Proccess ...",
            "Choose the city location and the reservaiton date ",
            "Choose the car ...",
            "then add extra features if you want and then complete with payment and confirm the reservation"
    };

    public int[] list_colors={
            Color.rgb(55,55,55),
            Color.rgb(239,85,85),
            Color.rgb(110,49,89),
            Color.rgb(1,188,218)
    };

    public SlideAdapter(Context context) {
        this.context = context;

    }
    public int[] list_image={

            R.drawable.steps,
            R.drawable.location,
            R.drawable.rentcar_slide,
            R.drawable.rentcar_slide

    };

    @Override
    public int getCount() {
        return list_colors.length;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
       inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
       View view=inflater.inflate(R.layout.slide2,container,false);
        dotslayout=(LinearLayout)view.findViewById(R.id.dotsLayout1);
       LinearLayout layout=(LinearLayout) view.findViewById(R.id.linearlayout1);
        ImageView img=(ImageView)view.findViewById(R.id.slide_image);
        TextView title=(TextView)view.findViewById(R.id.slide_title);
        TextView desc=(TextView)view.findViewById(R.id.slide_desc_txt);
        TextView skip=(TextView)view.findViewById(R.id.slide_skip);
        layout.setBackgroundColor(list_colors[position]);
        img.setImageResource(list_image[position]);
        title.setText("Title");
        desc.setText(List_Description[position]);

        container.addView(view);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in=new Intent(context.getApplicationContext(),HomeActivity.class);
                context.startActivity(in);

            }
        });


        adddotsindicitor();
        return view;
    }
    public void adddotsindicitor(){


        mDots=new TextView[4];

        for(int i=0;i<mDots.length;i++){
            mDots[i]=new TextView(context);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(context.getResources().getColor(R.color.colorPrimary));

            dotslayout.addView(mDots[i]);

        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}

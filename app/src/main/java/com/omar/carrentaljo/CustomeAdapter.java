package com.omar.carrentaljo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CustomeAdapter extends ArrayAdapter<String>{

    String [] names;
    int [] images;
    Context mContext;

    public CustomeAdapter(@NonNull Context context, String[]CityNames,int[]CityImages) {
        super(context, R.layout.list_view);
        this.names=CityNames;
        this.images=CityImages;
        this.mContext=context;
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder mViewHolder=new ViewHolder();
        if(convertView==null) {
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_view, parent, false);
            mViewHolder.iv1 = (RelativeLayout) convertView.findViewById(R.id.rl1);
            mViewHolder.tx1 = (TextView) convertView.findViewById(R.id.list_tx1);
            convertView.setTag(mViewHolder);
        }
        else {
            mViewHolder=(ViewHolder)convertView.getTag();
        }
        mViewHolder.iv1.setBackgroundResource(images[position]);
        mViewHolder.tx1.setText(names[position]);

        return convertView;

    }
    static  class ViewHolder{
        RelativeLayout iv1;
        TextView tx1;
    }
}


package com.omar.carrentaljo;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;


public class CarAdapter extends ArrayAdapter<CarClass> {
    private Activity context;
    private List<CarClass> carlist;
    String carid;


    private FirebaseStorage storage=FirebaseStorage.getInstance();
    DatabaseReference mReference =FirebaseDatabase.getInstance().getReference("Cars");

    public CarAdapter(Activity context,List<CarClass> carlist){
        super(context,R.layout.car_listview,carlist);
        this.context=context;
        this.carlist=carlist;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem=inflater.inflate(R.layout.car_listview,null,true);
        ImageView iv1=(ImageView)listViewItem.findViewById(R.id.list2_iage);
        TextView txt1=(TextView)listViewItem.findViewById(R.id.list2_txt2);
        TextView txt2=(TextView)listViewItem.findViewById(R.id.list2_txt1);
        RatingBar rb1=(RatingBar)listViewItem.findViewById(R.id.car_rate);
        TextView txt3=(TextView)listViewItem.findViewById(R.id.list2_price);






            carid=carlist.get(position).getId();
            StorageReference storageRef = storage.getReference();
            StorageReference islandRef = storageRef.child(carid + ".jpg");

            iv1.setImageResource(R.mipmap.ig);

       /*   if(!islandRef.equals(null))
          {Glide.with(listViewItem.getContext())
                    .using(new FirebaseImageLoader())
                    .load(islandRef)
                    .into(iv1);

        }
        else
            iv1.setImageResource(R.drawable.rent1);*/







        iv1.setFocusable(false);
        txt1.setFocusable(false);
        txt2.setFocusable(false);
        rb1.setFocusable(false);
        CarClass obj1=carlist.get(position);

        String specification="";
        if(obj1.isAirCondition())
            specification="Air Conditioning \n"+obj1.getCarGear()+" \n";
        else
            specification=obj1.getCarGear();
        txt1.setText(obj1.getCarName());
        txt2.setText(obj1.getCarclassification()+" car with \n  Seats : "+obj1.getSeatNumber()+" | Doors : "+
                obj1.getDoorNumber()+"\n"+specification);
        rb1.setRating(3);
        txt3.setText("JOD : "+obj1.getPrice());



        return listViewItem;

    }
}

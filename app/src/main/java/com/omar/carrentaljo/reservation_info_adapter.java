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

/**
 * Created by DELL on 02/04/2018.
 */

public class reservation_info_adapter extends ArrayAdapter<ReservationClass> {
    private Activity Context;
    private List<ReservationClass> Reservationlist;
    String user_name,CarName;
    int user_phone;
    String userId;
    String CarId;

    private FirebaseStorage storage=FirebaseStorage.getInstance();
    DatabaseReference user_Ref;
    DatabaseReference Car_Ref;
    public reservation_info_adapter(Activity context,List<ReservationClass>reservationlist){
      super(context,R.layout.car_listview,reservationlist);
      this.Context=context;
      this.Reservationlist=reservationlist;
  }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater=Context.getLayoutInflater();
        View listViewItem=inflater.inflate(R.layout.car_listview,null,true);
        ImageView iv1=(ImageView)listViewItem.findViewById(R.id.list2_iage);
        TextView txt1=(TextView)listViewItem.findViewById(R.id.list2_txt2);
        TextView txt2=(TextView)listViewItem.findViewById(R.id.list2_txt1);
        RatingBar rb1=(RatingBar)listViewItem.findViewById(R.id.car_rate);
        TextView txt3=(TextView)listViewItem.findViewById(R.id.list2_price);

        user_Ref= FirebaseDatabase.getInstance().getReference("users");
        Car_Ref= FirebaseDatabase.getInstance().getReference("Cars");
        iv1.setFocusable(false);
        txt1.setFocusable(false);
        txt2.setFocusable(false);
        rb1.setFocusable(false);
        txt3.setFocusable(false);

     userId= Reservationlist.get(position).getUserId();
     int month1,month2;
     month1=Reservationlist.get(position).getPickUp_Date().getMonth();
     month1++;
        month2=Reservationlist.get(position).getDropOFF_Date().getMonth();
        month2++;
       final String pickdate=Reservationlist.get(position).getPickUp_Date().getDate()+"-"+month1+"-"+Reservationlist.get(position).getPickUp_Date().getYear();
       final String dropdate=Reservationlist.get(position).getDropOFF_Date().getDate()+"-"+month2+"-"+Reservationlist.get(position).getDropOFF_Date().getYear();
       final double price=Reservationlist.get(position).getTotalPrice();
       final String loc=Reservationlist.get(position).getLocation().toString();
       CarId= Reservationlist.get(position).getCarId();
        user_phone=Reservationlist.get(position).getUser_PhoneNumber();

        user_name=Reservationlist.get(position).getUserName();
        CarName=Reservationlist.get(position).getCarName();



        StorageReference storageRef = storage.getReference();
        StorageReference islandRef = storageRef.child(CarId + ".jpg");

            iv1.setImageResource(R.drawable.rent1);



        txt1.setText(CarName);
        txt2.setText(" name : "+user_name+"\n phone# : 0"+user_phone+"\n date : "+pickdate+" to "+dropdate+"\n location : "+loc);
        txt3.setText("TotalPrice : "+price);
        return listViewItem;
    }
}

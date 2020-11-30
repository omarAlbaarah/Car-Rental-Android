package com.omar.carrentaljo;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

/**
 * Created by DELL on 25/03/2018.
 */

public class admin_addcar_fragment extends Fragment {


    View v;

    DatabaseReference mDatabaseReference,databaseAdmin;

    EditText Carname,CarLocation,CarPrice;
    Button Add,delete,edit;
    FirebaseAuth mAuth;
    ListView mListView;
    FirebaseUser user;
    String uid;
    String id;
    ImageView carimage;
    Uri imagee;
    private StorageReference mStorageRef;
    Spinner mSpinnerSeat,SpinnerDoor,SpineerClass;
    CheckBox airCon;
    RadioGroup CarGear;
    int seatn;
    int doorn;
    String cartype;
    RadioButton auto,manual;
    Boolean seatflag=false,doorflag=false,cartypeFlaf=false;


    public admin_addcar_fragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.admin_addcar,container,false);

        auto=(RadioButton)v.findViewById(R.id.auto1);
        manual=(RadioButton)v.findViewById(R.id.manual);
        Carname=(EditText)v.findViewById(R.id.admin_CarName);
        CarLocation=(EditText)v.findViewById(R.id.admin_Loc);
        mSpinnerSeat=(Spinner)v.findViewById(R.id.spinner_seats);
        SpinnerDoor=(Spinner)v.findViewById(R.id.spinner_Doors);
        SpineerClass=(Spinner)v.findViewById(R.id.spinner_Class);
        CarPrice=(EditText)v.findViewById(R.id.admin_CarPrice);
        Add=(Button)v.findViewById(R.id.admin_add_button);
        mListView=(ListView)v.findViewById(R.id.admin_listview);
        carimage=(ImageView)v.findViewById(R.id.CarImage1);
        airCon=(CheckBox)v.findViewById(R.id.checkBox15);
        CarGear=(RadioGroup)v.findViewById(R.id.cargear);

        mDatabaseReference= FirebaseDatabase.getInstance().getReference("Cars");

        String listofSeats[]={"SeatNumber","2","4","6"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,listofSeats);
        mSpinnerSeat.setAdapter(adapter);

        String listofDoors[]={"DoorsNumber","2","4","6"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,listofDoors);
        SpinnerDoor.setAdapter(adapter2);

        String listofClass[]={"Car type","Luxury","Economy","Intermediate","Standard"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,listofClass);
        SpineerClass.setAdapter(adapter3);




        carimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker =new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(photoPicker,0);
            }
        });





        //////////////////////////////////////////////////////////////////////////
        databaseAdmin=FirebaseDatabase.getInstance().getReference("Admin");
        //////////////////////////////////////////////////////////////////////////
        mAuth=FirebaseAuth.getInstance();
        user= FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();







        mSpinnerSeat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i){

                    case 0:
                        seatflag=false;
                        break;

                    case 1:
                        seatn=2;
                        seatflag=true;
                        break;

                    case 2:
                        seatn=4;
                        seatflag=true;
                        break;

                    case 3:
                        seatn=6;
                        seatflag=true;
                        break;




                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        SpinnerDoor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){

                    case 0:
                        doorflag=false;
                        break;

                    case 1:
                        doorn=2;
                        doorflag=true;
                        break;

                    case 2:
                        doorn=4;
                        doorflag=true;
                        break;

                    case 3:
                        doorn=6;
                        doorflag=true;
                        break;





                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        SpineerClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch(i){

                    case 0:
                        cartypeFlaf=false;
                        break;

                    case 1:
                        cartype="Luxary";
                        cartypeFlaf=true;
                        break;

                    case 2:
                        cartype="Economy";
                        cartypeFlaf=true;
                        break;

                    case 3:
                        cartype="Intermediate";
                        cartypeFlaf=true;
                        break;
                    case 4:
                        cartype="Standard";
                        cartypeFlaf=true;
                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });






        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(!Carname.getText().equals("") && !CarLocation.getText().equals("")
                        && !CarPrice.getText().equals("") &&seatflag &&doorflag && cartypeFlaf   )

                {
                    Boolean AirCondition=false;
                    String carGearType;
                    if(airCon.isChecked())
                        AirCondition =true;

                    if(auto.isChecked()==true)
                        carGearType="Automatic";
                    else
                        carGearType="Manual";





                    String name=Carname.getText().toString();
                    String loc=CarLocation.getText().toString();
                    double price=0;
                    try {
                        price = Double.parseDouble(CarPrice.getText().toString());
                    }
                    catch (Exception ex)
                    {

                    }
                    id=mDatabaseReference.push().getKey();
                    final CarClass   obj1=new CarClass(id,name,loc,price,uid,carGearType,
                            AirCondition,cartype,seatn,doorn);

                    uploadImageToFirebase();

                    AlertDialog.Builder alrt=new AlertDialog.Builder(getActivity());
                    alrt.setMessage("are you sure abou the car information, and you want to add it ?");
                    alrt.setTitle("ahl");
                    alrt.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mDatabaseReference.child(id).setValue(obj1);
                            Toast.makeText(getActivity(),"successfully added a new car",Toast.LENGTH_SHORT).show();

                            Carname.setText("");
                            CarPrice.setText("");
                            CarLocation.setText("");
                            auto.setChecked(true);
                            airCon.setChecked(false);


                        }
                    });
                    alrt.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    alrt.create().show();






                }




            }
        });








        return v;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            imagee = data.getData();
            carimage.setImageURI(imagee);
        }
        catch (NullPointerException ex) {

        }

    }


    private void uploadImageToFirebase() {
        // Get the data from an ImageView as bytes


        this.carimage.setDrawingCacheEnabled(true);
        this.carimage.buildDrawingCache();
        Bitmap bitmap = this.carimage.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        StorageReference mountainsRef = mStorageRef.child(id+".jpg");
        UploadTask uploadTask = mountainsRef.putBytes(data);

        uploadTask.addOnFailureListener(new OnFailureListener() {

            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.i("MainActivity", "Upload failed");
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Uri downloadUrl = taskSnapshot.getDownloadUrl();

                Log.i("MainActivity", "Upload successful, downloadUrl = "+downloadUrl);
            }
        });

    }





}


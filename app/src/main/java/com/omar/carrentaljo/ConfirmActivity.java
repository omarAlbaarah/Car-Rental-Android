package com.omar.carrentaljo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class ConfirmActivity extends AppCompatActivity {

    Date d1,d2;

    int image;
    EditText Date1,Time1,drop_date,drop_time;
    Button ConfirmButton;
    String CityName;
    private DatePickerDialog.OnDateSetListener mDateSetListener1,mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        ConfirmButton=(Button)findViewById(R.id.Confirm_confirmButton);


        Date1=(EditText)findViewById(R.id.Confirm_date);
        Time1=(EditText)findViewById(R.id.Confirm_time);
        drop_date=(EditText)findViewById(R.id.drop_date);
        drop_time=(EditText)findViewById(R.id.drop_time);

        Bundle mBundle=getIntent().getExtras();
        if(mBundle !=null)
        {
           image=mBundle.getInt("CityImage");
            CityName=mBundle.getString("CityName");

        }

        Date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal= Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int Month=cal.get(Calendar.MONTH);
                int Day=cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog=new DatePickerDialog(ConfirmActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener1,year,Month,Day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        mDateSetListener1 =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month=month+1;

                String date= month+"/"+day+"/"+year;

                    d1=new Date();
                    d1.setMonth(month);
                    d1.setYear(year);
                    d1.setDate(day);
                    Date1.setText(d1.getMonth()+"/"+d1.getDate()+"/"+d1.getYear());





            }
        };
        Time1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal= Calendar.getInstance();
                int hour=cal.get(Calendar.HOUR);
                hour=hour+1;
                int minute=cal.get(Calendar.MINUTE);
                TimePickerDialog mTimePickerDialog= new TimePickerDialog(ConfirmActivity.this, new TimePickerDialog.OnTimeSetListener() {
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
                DatePickerDialog dialog=new DatePickerDialog(ConfirmActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,year,Month,Day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        mDateSetListener =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month=month+1;
                String date= month+"/"+day+"/"+year;
                 d2=new Date();
                d2.setMonth(month);
                d2.setYear(year);
                d2.setDate(day);
                drop_date.setText(d2.getMonth()+"/"+d2.getDate()+"/"+d2.getYear());



            }
        };
        drop_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal= Calendar.getInstance();
                int hour=cal.get(Calendar.HOUR);
                int minute=cal.get(Calendar.MINUTE);
                TimePickerDialog mTimePickerDialog= new TimePickerDialog(ConfirmActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour1, int minute1) {
                        drop_time.setText(hour1+":"+minute1);
                    }
                },hour,minute,false);

                mTimePickerDialog.show();
            }
        });





        /*Bundle mBundle=getIntent().getExtras();
        if(mBundle !=null)
        {
            CityName.setText(mBundle.getString("CityName"));
            CarName.setText(mBundle.getString("CarName"));
            CarPrice.setText(mBundle.getDouble("CarPrice")+" JD");

        }*/

        ConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Date1.getText().toString().equals("") || Time1.getText().toString().equals("") ||
                        drop_date.getText().toString().equals("") ||drop_time.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"you should select the reservation date !! ",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Intent mIntent = new Intent(ConfirmActivity.this, CityActivity.class);
                    mIntent.putExtra("CityName", CityName);
                    mIntent.putExtra("CityImage",image);
                    mIntent.putExtra("Pick_Date",d1);
                    mIntent.putExtra("Pick_Time",Time1.getText().toString());
                    mIntent.putExtra("Drop_Date",d2);
                    mIntent.putExtra("Drop_Time",drop_time.getText().toString());
                    startActivity(mIntent);

                }

            }
        });

    }
}

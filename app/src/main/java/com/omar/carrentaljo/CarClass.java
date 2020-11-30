package com.omar.carrentaljo;

import android.support.annotation.NonNull;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by DELL on 05/02/2018.
 */

@IgnoreExtraProperties
public class CarClass implements Comparable<CarClass>{
    private String carName;
    private String location;
    private String id;
    private double price;
    private String admin_id;
    private Boolean status;
    private double CarRate;
    private String carGear;
    private boolean AirCondition;
    private String Carclassification;
    private int SeatNumber;
    private int DoorNumber;

    public CarClass()  {
    }

    public CarClass(String id1,String carName1, String location1,double price1,String admin_id1,
                    String carGear1,Boolean AirCondition1,String Carclassification1, int SeatNumber1,int DoorNumber1) {
        this.id=id1;
        this.admin_id=admin_id1;
        this.carName = carName1;
        this.location = location1;
        this.price =price1;
        this.status=true;
        this.CarRate=3;
        this.carGear=carGear1;
        this.Carclassification=Carclassification1;
        this.AirCondition=AirCondition1;
        this.SeatNumber=SeatNumber1;
        this.DoorNumber=DoorNumber1;

    }

    public String getCarclassification() {
        return Carclassification;
    }

    public void setCarclassification(String carclassification) {
        Carclassification = carclassification;
    }

    public int getSeatNumber() {
        return SeatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        SeatNumber = seatNumber;
    }

    public int getDoorNumber() {
        return DoorNumber;
    }

    public void setDoorNumber(int doorNumber) {
        DoorNumber = doorNumber;
    }

    public boolean isAirCondition() {
        return AirCondition;
    }

    public void setAirCondition(boolean airCondition) {
        AirCondition = airCondition;
    }

    public String getCarGear() {
        return carGear;
    }

    public void setCarGear(String carGear) {
        this.carGear = carGear;
    }

    public double getCarRate() {
        return CarRate;
    }

    public void setCarRate(double carRate) {
        CarRate = carRate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }



    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }




    @Override
    public int compareTo(@NonNull CarClass carClass) {

        double x=((CarClass)carClass).getPrice();

         if (this.getPrice()>x)
        return 1;
         else
             return -1;
    }
}

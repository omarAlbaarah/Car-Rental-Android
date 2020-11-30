package com.omar.carrentaljo;

import java.util.Date;

/**
 * Created by DELL on 04/03/2018.
 */

public class ReservationClass {

    private String CarId;
    private String UserId;
    private String AdminId;
    private Date PickUp_Date;
    private Date DropOFF_Date;
    private double TotalPrice;
    private int User_PhoneNumber;
    private String Location;
    private String ReservationId;
    private String UserName;
    private String CarName;

    public ReservationClass() {
    }

    public ReservationClass(String reservationId,String carId, String userId, String adminId, Date pickUp_Date, Date dropOFF_Date, double totalPrice, int user_PhoneNumber, String location,String userName,String carName) {
        CarId = carId;
        UserId = userId;
        AdminId = adminId;
        PickUp_Date = pickUp_Date;
        DropOFF_Date = dropOFF_Date;
        TotalPrice = totalPrice;
        User_PhoneNumber = user_PhoneNumber;
        Location = location;
        ReservationId=reservationId;
        UserName=userName;
        CarName=carName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getCarName() {
        return CarName;
    }

    public void setCarName(String carName) {
        CarName = carName;
    }

    public String getReservationId() {
        return ReservationId;
    }

    public void setReservationId(String reservationId) {
        ReservationId = reservationId;
    }

    public String getCarId() {
        return CarId;
    }

    public void setCarId(String carId) {
        CarId = carId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getAdminId() {
        return AdminId;
    }

    public void setAdminId(String adminId) {
        AdminId = adminId;
    }

    public Date getPickUp_Date() {
        return PickUp_Date;
    }

    public void setPickUp_Date(Date pickUp_Date) {
        PickUp_Date = pickUp_Date;
    }

    public Date getDropOFF_Date() {
        return DropOFF_Date;
    }

    public void setDropOFF_Date(Date dropOFF_Date) {
        DropOFF_Date = dropOFF_Date;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        TotalPrice = totalPrice;
    }

    public int getUser_PhoneNumber() {
        return User_PhoneNumber;
    }

    public void setUser_PhoneNumber(int user_PhoneNumber) {
        User_PhoneNumber = user_PhoneNumber;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}

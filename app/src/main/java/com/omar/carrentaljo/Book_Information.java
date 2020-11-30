package com.omar.carrentaljo;

/**
 * Created by DELL on 18/02/2018.
 */

public class Book_Information {

    String Customer_name;
    String Customer_email;
    String Customer_phone;
    String Car_name;
    String Car_Owner_name;
    String Location;
    String date;
    String time;
    String extra_specificaton;
    double car_price;

    public Book_Information() {
    }

    public Book_Information(String customer_name, String customer_email, String customer_phone,
                            String car_name, String car_Owner_name, String location, String date,
                            String time, String extra_specificaton, double car_price) {
        Customer_name = customer_name;
        Customer_email = customer_email;
        Customer_phone = customer_phone;
        Car_name = car_name;
        Car_Owner_name = car_Owner_name;
        Location = location;
        this.date = date;
        this.time = time;
        this.extra_specificaton = extra_specificaton;
        this.car_price = car_price;
    }

    public String getCustomer_name() {
        return Customer_name;
    }

    public void setCustomer_name(String customer_name) {
        Customer_name = customer_name;
    }

    public String getCustomer_email() {
        return Customer_email;
    }

    public void setCustomer_email(String customer_email) {
        Customer_email = customer_email;
    }

    public String getCustomer_phone() {
        return Customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        Customer_phone = customer_phone;
    }

    public String getCar_name() {
        return Car_name;
    }

    public void setCar_name(String car_name) {
        Car_name = car_name;
    }

    public String getCar_Owner_name() {
        return Car_Owner_name;
    }

    public void setCar_Owner_name(String car_Owner_name) {
        Car_Owner_name = car_Owner_name;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getExtra_specificaton() {
        return extra_specificaton;
    }

    public void setExtra_specificaton(String extra_specificaton) {
        this.extra_specificaton = extra_specificaton;
    }

    public double getCar_price() {
        return car_price;
    }

    public void setCar_price(double car_price) {
        this.car_price = car_price;
    }

    @Override
    public String toString() {
        return "Book_Information{" +
                "Customer_name='" + Customer_name + '\'' +
                ", Customer_email='" + Customer_email + '\'' +
                ", Customer_phone='" + Customer_phone + '\'' +
                ", Car_name='" + Car_name + '\'' +
                ", Car_Owner_name='" + Car_Owner_name + '\'' +
                ", Location='" + Location + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", extra_specificaton='" + extra_specificaton + '\'' +
                ", car_price=" + car_price +
                '}';
    }
}

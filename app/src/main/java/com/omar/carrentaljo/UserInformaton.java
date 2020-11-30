package com.omar.carrentaljo;



public class UserInformaton {

    private String name;
    private String password;
    private String email;
    private String HomeAddress;
    private int Age;
    private String Id;
    private int PhoneNumber;


    public UserInformaton() {
    }

    public UserInformaton(String name,String id, String password, String email, String homeAddress, int age, int phoneNumber) {
        this.name = name;
        this.password = password;
        this.email = email;
        HomeAddress = homeAddress;
        Age = age;
        PhoneNumber = phoneNumber;
        Id=id;

    }


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getHomeAddress() {
        return HomeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        HomeAddress = homeAddress;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        PhoneNumber = phoneNumber;
    }





}

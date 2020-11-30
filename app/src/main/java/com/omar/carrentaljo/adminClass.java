package com.omar.carrentaljo;

/**
 * Created by DELL on 18/02/2018.
 */

public class adminClass {

    private String adminName;
    private String AdminEmail;
    private String OfficeLocation;
    private String OfficeName;
    private String Password;
    private String id;




    public adminClass() {
    }

    public adminClass(String id ,String adminName, String adminEmail,
                      String officeLocation, String officeName, String password) {
        this.id=id;
        this.adminName = adminName;
        AdminEmail = adminEmail;
        OfficeLocation = officeLocation;
        OfficeName = officeName;
        Password = password;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminEmail() {
        return AdminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        AdminEmail = adminEmail;
    }

    public String getOfficeLocation() {
        return OfficeLocation;
    }

    public void setOfficeLocation(String officeLocation) {
        OfficeLocation = officeLocation;
    }

    public String getOfficeName() {
        return OfficeName;
    }

    public void setOfficeName(String officeName) {
        OfficeName = officeName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}

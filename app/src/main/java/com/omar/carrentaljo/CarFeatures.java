package com.omar.carrentaljo;



public class CarFeatures {
    private Boolean CarFeatures_Cancel=true;
    private Boolean CarFeatures_Driver=false;
    private Boolean CarFeatures_RoadSide=false;
    private Boolean CarFeatures_BabySeat=false;
    private Boolean CarFeatures_RoofTop=false;

    public CarFeatures() {
    }

    public CarFeatures(Boolean carFeatures_Cancel,Boolean carFeatures_Driver, Boolean carFeatures_RoadSide, Boolean carFeatures_BabySeat, Boolean carFeatures_RoofTop) {
        CarFeatures_Cancel=carFeatures_Cancel;
        CarFeatures_Driver = carFeatures_Driver;
        CarFeatures_RoadSide = carFeatures_RoadSide;
        CarFeatures_BabySeat = carFeatures_BabySeat;
        CarFeatures_RoofTop = carFeatures_RoofTop;
    }


    public Boolean getCarFeatures_Driver() {
        return CarFeatures_Driver;
    }

    public void setCarFeatures_Driver(Boolean carFeatures_Driver) {
        CarFeatures_Driver = carFeatures_Driver;
    }

    public Boolean getCarFeatures_RoadSide() {
        return CarFeatures_RoadSide;
    }

    public void setCarFeatures_RoadSide(Boolean carFeatures_RoadSide) {
        CarFeatures_RoadSide = carFeatures_RoadSide;
    }

    public Boolean getCarFeatures_BabySeat() {
        return CarFeatures_BabySeat;
    }

    public void setCarFeatures_BabySeat(Boolean carFeatures_BabySeat) {
        CarFeatures_BabySeat = carFeatures_BabySeat;
    }

    public Boolean getCarFeatures_RoofTop() {
        return CarFeatures_RoofTop;
    }

    public void setCarFeatures_RoofTop(Boolean carFeatures_RoofTop) {
        CarFeatures_RoofTop = carFeatures_RoofTop;
    }
}

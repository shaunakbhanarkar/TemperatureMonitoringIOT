package com.example.temperature;

public class DataModel {


    String roomNo;
    String temperature;
    Integer icon;
    int color;

    public DataModel(String roomNo, String temperature, Integer icon,int color) {
        this.roomNo=roomNo;
        this.temperature=temperature;
        this.icon=icon;
        this.color = color;
    }


    public String getRoomNo() {
        return roomNo;
    }


    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public Integer getIcon() { return icon;}

    public int getcolor(){return color;}

    public void setColor(int color) {
        this.color = color;
    }
}

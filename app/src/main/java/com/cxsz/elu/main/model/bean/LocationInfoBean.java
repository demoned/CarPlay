package com.cxsz.elu.main.model.bean;

public class LocationInfoBean {
    private String cityCode;
    private String city;
    private String address;
    private double longitude;
    private double latitude;
    private float bearing;
    private float speed;
    private String street;
    private long time;

    public LocationInfoBean(String cityCode, String city, String address, double longitude, double latitude, float bearing, float speed, String street, long time) {
        this.cityCode = cityCode;
        this.city = city;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.bearing = bearing;
        this.speed = speed;
        this.street = street;
        this.time = time;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public float getBearing() {
        return bearing;
    }

    public void setBearing(float bearing) {
        this.bearing = bearing;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "LocationInfoBean{" +
                "cityCode='" + cityCode + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", bearing=" + bearing +
                ", speed=" + speed +
                ", street='" + street + '\'' +
                ", time=" + time +
                '}';
    }
}

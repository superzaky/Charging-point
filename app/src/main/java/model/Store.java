package model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yomac_000 on 16-12-2015.
 */
public class Store {
    @SerializedName("FACEBOOK ID") private String facebookID;
    @SerializedName("STORE NAME") private String name;
    @SerializedName("STREET") private String street;
    @SerializedName("ZIP CODE") private String zipCode;
    @SerializedName("CITY") private String city;
    @SerializedName("LONGITUDE") private double longitude;
    @SerializedName("LATITUDE") private double latitude;

    public Store(String facebookID, String name, String street, String zipCode, String city, double longitude, double latitude) {
        this.facebookID = facebookID;
        this.name = name;
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Store(){}

    public String getFacebookID() {
        return facebookID;
    }

    public void setFacebookID(String facebookID) {
        this.facebookID = facebookID;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
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
}

package model;

/**
 * Created by yomac_000 on 16-12-2015.
 */
public class Store {
    String FACEBOOK_ID;
    String STORE_NAME;
    String STREET;
    String ZIP_CODE;
    String CITY;
    double LONGITUDE;
    double LATITUDE;

    public String getSTORE_NAME() {
        return STORE_NAME;
    }

    public void setSTORE_NAME(String STORE_NAME) {
        this.STORE_NAME = STORE_NAME;
    }

    public double getLONGITUDE() {
        return LONGITUDE;
    }

    public void setLONGITUDE(double LONGITUDE) {
        this.LONGITUDE = LONGITUDE;
    }

    public double getLATITUDE() {
        return LATITUDE;
    }

    public void setLATITUDE(double LATITUDE) {
        this.LATITUDE = LATITUDE;
    }

    public String getCITY() {
        return CITY;
    }

    public void setCITY(String CITY) {
        this.CITY = CITY;
    }

    public String getZIP_CODE() {
        return ZIP_CODE;
    }

    public void setZIP_CODE(String ZIP_CODE) {
        this.ZIP_CODE = ZIP_CODE;
    }

    public String getFACEBOOK_ID() {
        return FACEBOOK_ID;
    }

    public void setFACEBOOK_ID(String FACEBOOK_ID) {
        this.FACEBOOK_ID = FACEBOOK_ID;
    }

    public String getSTREET() {
        return STREET;
    }

    public void setSTREET(String STREET) {
        this.STREET = STREET;
    }
}

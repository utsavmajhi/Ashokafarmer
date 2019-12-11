
package com.example.ashokafarmer.digitiselandmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sendnewlandformat {

    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("long")
    @Expose
    private String _long;

    public Sendnewlandformat(String area, String location, String lat, String _long) {
        this.area = area;
        this.location = location;
        this.lat = lat;
        this._long = _long;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLong() {
        return _long;
    }

    public void setLong(String _long) {
        this._long = _long;
    }

}

package com.example.ashokafarmer;

public class notpooleditem {

    private String _id;
    private String landname;
    private String landarea;
    private String location;

    public notpooleditem(String _id, String landname, String landarea, String location, String landlat, String landlong) {
        this._id = _id;
        this.landname = landname;
        this.landarea = landarea;
        this.location = location;
        this.landlat = landlat;
        this.landlong = landlong;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getLandname() {
        return landname;
    }

    public void setLandname(String landname) {
        this.landname = landname;
    }

    public String getLandarea() {
        return landarea;
    }

    public void setLandarea(String landarea) {
        this.landarea = landarea;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLandlat() {
        return landlat;
    }

    public void setLandlat(String landlat) {
        this.landlat = landlat;
    }

    public String getLandlong() {
        return landlong;
    }

    public void setLandlong(String landlong) {
        this.landlong = landlong;
    }

    private String landlat;
    private String landlong;
}

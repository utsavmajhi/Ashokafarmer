package com.example.ashokafarmer.rejectedrequestsmodel;

public class rejectedrequestitem {
    private String landname;
    private String landarea;
    private String landlocation;
    private String landlat;
    private String landlong;

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

    public String getLandlocation() {
        return landlocation;
    }

    public void setLandlocation(String landlocation) {
        this.landlocation = landlocation;
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

    public rejectedrequestitem(String landname, String landarea, String landlocation, String landlat, String landlong) {
        this.landname = landname;
        this.landarea = landarea;
        this.landlocation = landlocation;
        this.landlat = landlat;
        this.landlong = landlong;
    }
}

package com.example.ashokafarmer;

public class poolitems {

    private String _id;
    private String mImageurl;
    private String poolname;
    private String area;
    private String location;
    private String Report;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getmImageurl() {
        return mImageurl;
    }

    public void setmImageurl(String mImageurl) {
        this.mImageurl = mImageurl;
    }

    public String getPoolname() {
        return poolname;
    }

    public void setPoolname(String poolname) {
        this.poolname = poolname;
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

    public String getReport() {
        return Report;
    }

    public void setReport(String report) {
        Report = report;
    }

    public poolitems(String _id, String mImageurl, String poolname, String area, String location, String report) {
        this._id = _id;
        this.mImageurl = mImageurl;
        this.poolname = poolname;
        this.area = area;
        this.location = location;
        Report = report;
    }
}

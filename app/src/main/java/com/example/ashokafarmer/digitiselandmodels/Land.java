
package com.example.ashokafarmer.digitiselandmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Land {

    @SerializedName("_id")
    @Expose
    private String id;
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
    @SerializedName("farmerId")
    @Expose
    private String farmerId;
    @SerializedName("poolId")
    @Expose
    private String poolId;
    @SerializedName("adminAndEngApprov")
    @Expose
    private Integer adminAndEngApprov;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(String farmerId) {
        this.farmerId = farmerId;
    }

    public String getPoolId() {
        return poolId;
    }

    public void setPoolId(String poolId) {
        this.poolId = poolId;
    }

    public Integer getAdminAndEngApprov() {
        return adminAndEngApprov;
    }

    public void setAdminAndEngApprov(Integer adminAndEngApprov) {
        this.adminAndEngApprov = adminAndEngApprov;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}


package com.example.ashokafarmer;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pool {

    @SerializedName("totalInvestment")
    @Expose
    private Integer totalInvestment;
    @SerializedName("prevProfits")
    @Expose
    private List<Object> prevProfits = null;
    @SerializedName("investorsIds")
    @Expose
    private List<String> investorsIds = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("long")
    @Expose
    private String _long;
    @SerializedName("engineerId")
    @Expose
    private String engineerId;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public Integer getTotalInvestment() {
        return totalInvestment;
    }

    public void setTotalInvestment(Integer totalInvestment) {
        this.totalInvestment = totalInvestment;
    }

    public List<Object> getPrevProfits() {
        return prevProfits;
    }

    public void setPrevProfits(List<Object> prevProfits) {
        this.prevProfits = prevProfits;
    }

    public List<String> getInvestorsIds() {
        return investorsIds;
    }

    public void setInvestorsIds(List<String> investorsIds) {
        this.investorsIds = investorsIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getEngineerId() {
        return engineerId;
    }

    public void setEngineerId(String engineerId) {
        this.engineerId = engineerId;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}

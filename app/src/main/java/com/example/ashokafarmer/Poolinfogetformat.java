
package com.example.ashokafarmer;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Poolinfogetformat {

    @SerializedName("pool")
    @Expose
    private Pool pool;
    @SerializedName("reports")
    @Expose
    private List<Report> reports = null;

    public Pool getPool() {
        return pool;
    }

    public void setPool(Pool pool) {
        this.pool = pool;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

}

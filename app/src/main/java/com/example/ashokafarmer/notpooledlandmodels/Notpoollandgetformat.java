
package com.example.ashokafarmer.notpooledlandmodels;

import java.util.List;

import com.example.ashokafarmer.unknownclasses.Land;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notpoollandgetformat {

    @SerializedName("lands")
    @Expose
    private List<Land> lands = null;

    public List<Land> getLands() {
        return lands;
    }

    public void setLands(List<Land> lands) {
        this.lands = lands;
    }

}

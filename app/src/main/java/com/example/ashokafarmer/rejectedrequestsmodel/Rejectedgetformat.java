
package com.example.ashokafarmer.rejectedrequestsmodel;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rejectedgetformat {

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

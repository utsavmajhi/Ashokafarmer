
package com.example.ashokafarmer.joinpoolrecyclerdata;

import java.util.List;

import com.example.ashokafarmer.joinpoolrecyclerdata.Pool;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Getallpoolformat {

    @SerializedName("pools")
    @Expose
    private List<Pool> pools = null;

    public List<Pool> getPools() {
        return pools;
    }

    public void setPools(List<Pool> pools) {
        this.pools = pools;
    }

}

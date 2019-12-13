
package com.example.ashokafarmer.allradyjoinedpools;

import java.util.List;

import com.example.ashokafarmer.unknownclasses.Pool;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Getalreadyjoinpoolformat {

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

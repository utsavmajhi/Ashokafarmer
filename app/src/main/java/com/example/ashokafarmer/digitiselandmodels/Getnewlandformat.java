
package com.example.ashokafarmer.digitiselandmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Getnewlandformat {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("land")
    @Expose
    private Land land;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {
        this.land = land;
    }

}

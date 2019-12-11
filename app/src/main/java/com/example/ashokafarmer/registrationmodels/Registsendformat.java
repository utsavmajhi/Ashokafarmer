
package com.example.ashokafarmer.registrationmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Registsendformat {

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("aadhaar")
    @Expose
    private String aadhaar;
    @SerializedName("userType")
    @Expose
    private Integer userType;
    @SerializedName("phone")
    @Expose
    private String phone;

    public Registsendformat(String email, String password, String name, String aadhaar, Integer userType, String phone) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.aadhaar = aadhaar;
        this.userType = userType;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAadhaar() {
        return aadhaar;
    }

    public void setAadhaar(String aadhaar) {
        this.aadhaar = aadhaar;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}

package com.example.ashokafarmer.joinpoolrecyclerdata;

public class pooljoinitems {

    private String _id;
    private String poolname;
    private String poolarea;
    private String poollocation;
    private String pooldescr;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPoolname() {
        return poolname;
    }

    public void setPoolname(String poolname) {
        this.poolname = poolname;
    }

    public String getPoolarea() {
        return poolarea;
    }

    public void setPoolarea(String poolarea) {
        this.poolarea = poolarea;
    }

    public String getPoollocation() {
        return poollocation;
    }

    public void setPoollocation(String poollocation) {
        this.poollocation = poollocation;
    }

    public String getPooldescr() {
        return pooldescr;
    }

    public void setPooldescr(String pooldescr) {
        this.pooldescr = pooldescr;
    }

    public String getPooltotinvest() {
        return pooltotinvest;
    }

    public void setPooltotinvest(String pooltotinvest) {
        this.pooltotinvest = pooltotinvest;
    }

    public String getPrevprofits() {
        return prevprofits;
    }

    public void setPrevprofits(String prevprofits) {
        this.prevprofits = prevprofits;
    }

    public pooljoinitems(String _id, String poolname, String poolarea, String poollocation, String pooldescr, String pooltotinvest, String prevprofits) {
        this._id = _id;
        this.poolname = poolname;
        this.poolarea = poolarea;
        this.poollocation = poollocation;
        this.pooldescr = pooldescr;
        this.pooltotinvest = pooltotinvest;
        this.prevprofits = prevprofits;
    }

    private String pooltotinvest;
    private String prevprofits;





}

package com.example.ashokafarmer.joinpoolrequestbyfarmermodel;

public class Sendformatjoinpool {

    private String landId;
    private String poolId;

    public String getLandId() {
        return landId;
    }

    public void setLandId(String landId) {
        this.landId = landId;
    }

    public String getPoolId() {
        return poolId;
    }

    public void setPoolId(String poolId) {
        this.poolId = poolId;
    }

    public Sendformatjoinpool(String landId, String poolId) {
        this.landId = landId;
        this.poolId = poolId;
    }
}

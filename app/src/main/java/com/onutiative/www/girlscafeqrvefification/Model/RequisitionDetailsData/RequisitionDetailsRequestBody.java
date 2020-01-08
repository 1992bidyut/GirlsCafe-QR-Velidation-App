package com.onutiative.www.girlscafeqrvefification.Model.RequisitionDetailsData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequisitionDetailsRequestBody {

    @SerializedName("requesition_id")
    @Expose
    private String requesitionId;

    public RequisitionDetailsRequestBody(String requesitionId) {
        this.requesitionId = requesitionId;
    }

    public String getRequesitionId() {
        return requesitionId;
    }

    public void setRequesitionId(String requesitionId) {
        this.requesitionId = requesitionId;
    }
}

package com.onutiative.www.girlscafeqrvefification.Model.RequisitionData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequisitionRequersBody {
    @SerializedName("requisition_date")
    @Expose
    private String requisitionDate;

    public RequisitionRequersBody(String requisitionDate) {
        this.requisitionDate = requisitionDate;
    }

    public String getRequisitionDate() {
        return requisitionDate;
    }

    public void setRequisitionDate(String requisitionDate) {
        this.requisitionDate = requisitionDate;
    }
}

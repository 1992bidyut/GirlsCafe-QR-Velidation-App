package com.onutiative.www.girlscafeqrvefification.Model.SKRequisitionData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SKRequisitionRequestBody {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("requisition_date")
    @Expose
    private String requisitionDate;
    @SerializedName("store_id")
    @Expose
    private String storeId;
    @SerializedName("user_type")
    @Expose
    private Integer userType;

    public SKRequisitionRequestBody(String userId, String requisitionDate, String storeId, Integer userType) {
        this.userId = userId;
        this.requisitionDate = requisitionDate;
        this.storeId = storeId;
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRequisitionDate() {
        return requisitionDate;
    }

    public void setRequisitionDate(String requisitionDate) {
        this.requisitionDate = requisitionDate;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}

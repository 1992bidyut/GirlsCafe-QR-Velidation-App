package com.onutiative.www.girlscafeqrvefification.Model.RequisitionData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RequisitionResponse {

    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("data")
    @Expose
    private List<Data> data = null;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public class Status {

        @SerializedName("code")
        @Expose
        private Integer code;
        @SerializedName("details")
        @Expose
        private String details;
        @SerializedName("reason")
        @Expose
        private String reason;

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }
    public class Data {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("store_id")
        @Expose
        private String storeId;
        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("req_date")
        @Expose
        private String reqDate;
        @SerializedName("req_create_date")
        @Expose
        private String reqCreateDate;
        @SerializedName("store_name")
        @Expose
        private String storeName;
        @SerializedName("store_address")
        @Expose
        private String storeAddress;
        @SerializedName("default_status")
        @Expose
        private String defaultStatus;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getReqDate() {
            return reqDate;
        }

        public void setReqDate(String reqDate) {
            this.reqDate = reqDate;
        }

        public String getReqCreateDate() {
            return reqCreateDate;
        }

        public void setReqCreateDate(String reqCreateDate) {
            this.reqCreateDate = reqCreateDate;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getStoreAddress() {
            return storeAddress;
        }

        public void setStoreAddress(String storeAddress) {
            this.storeAddress = storeAddress;
        }

        public String getDefaultStatus() {
            return defaultStatus;
        }

        public void setDefaultStatus(String defaultStatus) {
            this.defaultStatus = defaultStatus;
        }

    }
}

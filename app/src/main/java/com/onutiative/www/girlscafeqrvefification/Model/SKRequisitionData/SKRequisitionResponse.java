package com.onutiative.www.girlscafeqrvefification.Model.SKRequisitionData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SKRequisitionResponse {

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
        @SerializedName("req_id")
        @Expose
        private String reqId;
        @SerializedName("product_id")
        @Expose
        private String productId;
        @SerializedName("req_quantity")
        @Expose
        private String reqQuantity;
        @SerializedName("delivery_quantity")
        @Expose
        private String deliveryQuantity;
        @SerializedName("received_quantity")
        @Expose
        private String receivedQuantity;

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        @SerializedName("product_name")
        @Expose


        private String productName;

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

        public String getReqId() {
            return reqId;
        }

        public void setReqId(String reqId) {
            this.reqId = reqId;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getReqQuantity() {
            return reqQuantity;
        }

        public void setReqQuantity(String reqQuantity) {
            this.reqQuantity = reqQuantity;
        }

        public String getDeliveryQuantity() {
            return deliveryQuantity;
        }

        public void setDeliveryQuantity(String deliveryQuantity) {
            this.deliveryQuantity = deliveryQuantity;
        }

        public String getReceivedQuantity() {
            return receivedQuantity;
        }

        public void setReceivedQuantity(String receivedQuantity) {
            this.receivedQuantity = receivedQuantity;
        }

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

}

package com.onutiative.www.girlscafeqrvefification.Model.QRData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QRPushRequestBody {
    @SerializedName("user_type")
    @Expose
    private Integer userType;
    @SerializedName("operation")
    @Expose
    private String operation;
    @SerializedName("store_id")
    @Expose
    private String storeId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("data")
    @Expose
    private List<Data> data = null;

    public QRPushRequestBody(Integer userType, String operation, String storeId, String userId, String date, List<Data> data) {
        this.userType = userType;
        this.operation = operation;
        this.storeId = storeId;
        this.userId = userId;
        this.date = date;
        this.data = data;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
    public static class Data {

        @SerializedName("qr")
        @Expose
        private List<Qr> qr = null;
        @SerializedName("requisition")
        @Expose
        private Requisition requisition;

        public Data(List<Qr> qr, Requisition requisition) {
            this.qr = qr;
            this.requisition = requisition;
        }

        public List<Qr> getQr() {
            return qr;
        }

        public void setQr(List<Qr> qr) {
            this.qr = qr;
        }

        public Requisition getRequisition() {
            return requisition;
        }

        public void setRequisition(Requisition requisition) {
            this.requisition = requisition;
        }
    }
    public static class Qr {

        @SerializedName("qr_code")
        @Expose
        private String qrCode;
        @SerializedName("using_status")
        @Expose
        private String usingStatus;
        @SerializedName("update_datetime")
        @Expose
        private String updateDatetime;
        @SerializedName("product_id")
        @Expose
        private String productId;
        @SerializedName("requistion_id")
        @Expose
        private String requistionId;
        @SerializedName("batch_id")
        @Expose
        private String batchId;
        @SerializedName("supply_id")
        @Expose
        private String supplyId;

        public Qr(String qrCode, String usingStatus, String updateDatetime, String productId, String requistionId, String batchId, String supplyId) {
            this.qrCode = qrCode;
            this.usingStatus = usingStatus;
            this.updateDatetime = updateDatetime;
            this.productId = productId;
            this.requistionId = requistionId;
            this.batchId = batchId;
            this.supplyId = supplyId;
        }

        public String getQrCode() {
            return qrCode;
        }

        public void setQrCode(String qrCode) {
            this.qrCode = qrCode;
        }

        public String getUsingStatus() {
            return usingStatus;
        }

        public void setUsingStatus(String usingStatus) {
            this.usingStatus = usingStatus;
        }

        public String getUpdateDatetime() {
            return updateDatetime;
        }

        public void setUpdateDatetime(String updateDatetime) {
            this.updateDatetime = updateDatetime;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getRequistionId() {
            return requistionId;
        }

        public void setRequistionId(String requistionId) {
            this.requistionId = requistionId;
        }

        public String getBatchId() {
            return batchId;
        }

        public void setBatchId(String batchId) {
            this.batchId = batchId;
        }

        public String getSupplyId() {
            return supplyId;
        }

        public void setSupplyId(String supplyId) {
            this.supplyId = supplyId;
        }

    }
    public static class Requisition {

        @SerializedName("requisition_id")
        @Expose
        private String requisitionId;
        @SerializedName("product_id")
        @Expose
        private String productId;
        @SerializedName("product_name")
        @Expose
        private String productName;
        @SerializedName("req_quantity")
        @Expose
        private String reqQuantity;
        @SerializedName("delivered_quantity")
        @Expose
        private String deliveredQuantity;
        @SerializedName("received_quantity")
        @Expose
        private String receivedQuantity;

        public Requisition(String requisitionId, String productId, String productName, String reqQuantity, String deliveredQuantity, String receivedQuantity) {
            this.requisitionId = requisitionId;
            this.productId = productId;
            this.productName = productName;
            this.reqQuantity = reqQuantity;
            this.deliveredQuantity = deliveredQuantity;
            this.receivedQuantity = receivedQuantity;
        }

        public String getRequisitionId() {
            return requisitionId;
        }

        public void setRequisitionId(String requisitionId) {
            this.requisitionId = requisitionId;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getReqQuantity() {
            return reqQuantity;
        }

        public void setReqQuantity(String reqQuantity) {
            this.reqQuantity = reqQuantity;
        }

        public String getDeliveredQuantity() {
            return deliveredQuantity;
        }

        public void setDeliveredQuantity(String deliveredQuantity) {
            this.deliveredQuantity = deliveredQuantity;
        }

        public String getReceivedQuantity() {
            return receivedQuantity;
        }

        public void setReceivedQuantity(String receivedQuantity) {
            this.receivedQuantity = receivedQuantity;
        }

    }
}

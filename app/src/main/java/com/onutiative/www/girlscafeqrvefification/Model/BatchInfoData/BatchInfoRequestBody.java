package com.onutiative.www.girlscafeqrvefification.Model.BatchInfoData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BatchInfoRequestBody {
    @SerializedName("batch_info")
    @Expose
    private BatchInfo batchInfo;
    @SerializedName("supplier_info")
    @Expose
    private SupplierInfo supplierInfo;

    public BatchInfo getBatchInfo() {
        return batchInfo;
    }

    public void setBatchInfo(BatchInfo batchInfo) {
        this.batchInfo = batchInfo;
    }

    public SupplierInfo getSupplierInfo() {
        return supplierInfo;
    }

    public void setSupplierInfo(SupplierInfo supplierInfo) {
        this.supplierInfo = supplierInfo;
    }
    public static class SupplierInfo {

        @SerializedName("supply_id")
        @Expose
        private String supplyId;
        @SerializedName("supplier_id")
        @Expose
        private String supplierId;
        @SerializedName("req_id")
        @Expose
        private String reqId;

        public SupplierInfo(String supplyId, String supplierId, String reqId) {
            this.supplyId = supplyId;
            this.supplierId = supplierId;
            this.reqId = reqId;
        }

        public String getSupplyId() {
            return supplyId;
        }

        public void setSupplyId(String supplyId) {
            this.supplyId = supplyId;
        }

        public String getSupplierId() {
            return supplierId;
        }

        public void setSupplierId(String supplierId) {
            this.supplierId = supplierId;
        }

        public String getReqId() {
            return reqId;
        }

        public void setReqId(String reqId) {
            this.reqId = reqId;
        }

    }
    public static class BatchInfo {

        @SerializedName("batch_code")
        @Expose
        private String batchCode;
        @SerializedName("batch_date")
        @Expose
        private String batchDate;
        @SerializedName("supplier_id")
        @Expose
        private String supplierId;

        public BatchInfo(String batchCode, String batchDate, String supplierId) {
            this.batchCode = batchCode;
            this.batchDate = batchDate;
            this.supplierId = supplierId;
        }

        public String getBatchCode() {
            return batchCode;
        }

        public void setBatchCode(String batchCode) {
            this.batchCode = batchCode;
        }

        public String getBatchDate() {
            return batchDate;
        }

        public void setBatchDate(String batchDate) {
            this.batchDate = batchDate;
        }

        public String getSupplierId() {
            return supplierId;
        }

        public void setSupplierId(String supplierId) {
            this.supplierId = supplierId;
        }

    }
}

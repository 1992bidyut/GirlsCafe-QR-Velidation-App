package com.onutiative.www.girlscafeqrvefification.Model.QRData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QRPushResponse {
    @SerializedName("status")
    @Expose
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

package com.onutiative.www.girlscafeqrvefification.Model.LoginData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("data")
    @Expose
    private Data data;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
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
    public static class Data {

        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("store_id")
        @Expose
        private String storeId;
        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("token")
        @Expose
        private String token;
        @SerializedName("user_type")
        @Expose
        private String userType;
        @SerializedName("security_code")
        @Expose
        private String securityCode;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("last_name")
        @Expose
        private String lastName;
        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("date_of_birth")
        @Expose
        private String dateOfBirth;
        @SerializedName("logo")
        @Expose
        private String logo;

        @SerializedName("contact_number")
        @Expose
        private String contact_number;

        public Data(String userId, String storeId, String username, String password, String token, String userType, String securityCode, String status, String lastName, String firstName, String gender, String dateOfBirth, String logo, String contact_number) {
            this.userId = userId;
            this.storeId = storeId;
            this.username = username;
            this.password = password;
            this.token = token;
            this.userType = userType;
            this.securityCode = securityCode;
            this.status = status;
            this.lastName = lastName;
            this.firstName = firstName;
            this.gender = gender;
            this.dateOfBirth = dateOfBirth;
            this.logo = logo;
            this.contact_number = contact_number;
        }

        public String getContact_number() {
            return contact_number;
        }

        public void setContact_number(String contact_number) {
            this.contact_number = contact_number;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public String getSecurityCode() {
            return securityCode;
        }

        public void setSecurityCode(String securityCode) {
            this.securityCode = securityCode;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

    }
}

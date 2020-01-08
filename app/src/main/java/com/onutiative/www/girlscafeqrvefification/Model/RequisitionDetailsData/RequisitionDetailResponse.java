package com.onutiative.www.girlscafeqrvefification.Model.RequisitionDetailsData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RequisitionDetailResponse {
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
        private Object deliveryQuantity;
        @SerializedName("received_quantity")
        @Expose
        private Object receivedQuantity;
        @SerializedName("supplier_id")
        @Expose
        private String supplierId;
        @SerializedName("category_id")
        @Expose
        private String categoryId;
        @SerializedName("product_name")
        @Expose
        private String productName;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("supplier_price")
        @Expose
        private String supplierPrice;
        @SerializedName("unit")
        @Expose
        private String unit;
        @SerializedName("product_model")
        @Expose
        private String productModel;
        @SerializedName("product_details")
        @Expose
        private String productDetails;
        @SerializedName("image_thumb")
        @Expose
        private String imageThumb;
        @SerializedName("brand_id")
        @Expose
        private String brandId;
        @SerializedName("variants")
        @Expose
        private String variants;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("best_sale")
        @Expose
        private String bestSale;
        @SerializedName("onsale")
        @Expose
        private String onsale;
        @SerializedName("onsale_price")
        @Expose
        private Object onsalePrice;
        @SerializedName("invoice_details")
        @Expose
        private String invoiceDetails;
        @SerializedName("image_large_details")
        @Expose
        private String imageLargeDetails;
        @SerializedName("review")
        @Expose
        private String review;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("tag")
        @Expose
        private String tag;
        @SerializedName("specification")
        @Expose
        private String specification;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("life_time")
        @Expose
        private String lifeTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public Object getDeliveryQuantity() {
            return deliveryQuantity;
        }

        public void setDeliveryQuantity(Object deliveryQuantity) {
            this.deliveryQuantity = deliveryQuantity;
        }

        public Object getReceivedQuantity() {
            return receivedQuantity;
        }

        public void setReceivedQuantity(Object receivedQuantity) {
            this.receivedQuantity = receivedQuantity;
        }

        public String getSupplierId() {
            return supplierId;
        }

        public void setSupplierId(String supplierId) {
            this.supplierId = supplierId;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSupplierPrice() {
            return supplierPrice;
        }

        public void setSupplierPrice(String supplierPrice) {
            this.supplierPrice = supplierPrice;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getProductModel() {
            return productModel;
        }

        public void setProductModel(String productModel) {
            this.productModel = productModel;
        }

        public String getProductDetails() {
            return productDetails;
        }

        public void setProductDetails(String productDetails) {
            this.productDetails = productDetails;
        }

        public String getImageThumb() {
            return imageThumb;
        }

        public void setImageThumb(String imageThumb) {
            this.imageThumb = imageThumb;
        }

        public String getBrandId() {
            return brandId;
        }

        public void setBrandId(String brandId) {
            this.brandId = brandId;
        }

        public String getVariants() {
            return variants;
        }

        public void setVariants(String variants) {
            this.variants = variants;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getBestSale() {
            return bestSale;
        }

        public void setBestSale(String bestSale) {
            this.bestSale = bestSale;
        }

        public String getOnsale() {
            return onsale;
        }

        public void setOnsale(String onsale) {
            this.onsale = onsale;
        }

        public Object getOnsalePrice() {
            return onsalePrice;
        }

        public void setOnsalePrice(Object onsalePrice) {
            this.onsalePrice = onsalePrice;
        }

        public String getInvoiceDetails() {
            return invoiceDetails;
        }

        public void setInvoiceDetails(String invoiceDetails) {
            this.invoiceDetails = invoiceDetails;
        }

        public String getImageLargeDetails() {
            return imageLargeDetails;
        }

        public void setImageLargeDetails(String imageLargeDetails) {
            this.imageLargeDetails = imageLargeDetails;
        }

        public String getReview() {
            return review;
        }

        public void setReview(String review) {
            this.review = review;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getSpecification() {
            return specification;
        }

        public void setSpecification(String specification) {
            this.specification = specification;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getLifeTime() {
            return lifeTime;
        }

        public void setLifeTime(String lifeTime) {
            this.lifeTime = lifeTime;
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

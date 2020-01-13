package com.onutiative.www.girlscafeqrvefification.Utility;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPrefManager {

    private static final String PREF_NAME = "onuPref";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    private static final String IS_LOGIN = "IsLoggedIn";
    private static final String USERNAME = "user_name";
    private static final String PASSWORD = "user_password";
    private static final String STORE_ID = "store_id";
    private static final String REQUISITION_ID = "requisition_id";
    private static final String BATCH_CODE = "batch_code";
    private static final String SUPPLY_CODE = "supply_code";
    private static final String PRODUCT_CODE = "product_code";
    private static final String PRODUCT_NAME = "product_name";
    private static final String REQ_QUANTITY = "req_quantity";
    private static final String USER_ID = "user_id";
    private static final String USER_TYPE = "user_type";
    private static final String DELI_QUANTITY = "delivered_quantity";
    private static final String DELIVERY_DATE = "delivery_date";
    private static final String STORE_NAME= "store_name";


    public SharedPrefManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        editor = preferences.edit();
    }

    public boolean isLoggedIn() {
        return preferences.getBoolean(IS_LOGIN, false);
    }
    public void setLoggedInFlag(boolean flag){
        editor.putBoolean(IS_LOGIN,flag);
        editor.commit();
    }
    /////////////////////
    public void setUserNane(String username){
        editor.putString(USERNAME,username);
        editor.commit();
    }
    public String getUsername(){
        return preferences.getString(USERNAME,null);
    }
    /////////////////////////
    public void setUserPassword(String password){
        editor.putString(PASSWORD,password);
        editor.commit();
    }
    public String getUserPassword(){
        return preferences.getString(PASSWORD,null);
    }
    ////////////////////////
    public void setStoreID(String id){
        editor.putString(STORE_ID,id);
        editor.commit();
    }
    public String getStoreID(){
        return preferences.getString(STORE_ID,null);
    }
    ////////////////////////////
    public void setRequisitionID(String id){
        editor.putString(REQUISITION_ID,id);
        editor.commit();
    }
    public String getRequisitionID(){
        return preferences.getString(REQUISITION_ID,null);
    }
    ////////////////////////////
    ////////////////////////////
    public void setBatchCode(String id){
        editor.putString(BATCH_CODE,id);
        editor.commit();
    }
    public String getBatchCode(){
        return preferences.getString(BATCH_CODE,null);
    }
    ////////////////////////////
    ////////////////////////////
    public void setSupplyCode(String id){
        editor.putString(SUPPLY_CODE,id);
        editor.commit();
    }
    public String getSupplyCode(){
        return preferences.getString(SUPPLY_CODE,null);
    }
    ////////////////////////////
    ////////////////////////////
    public void setProductCode(String id){
        editor.putString(PRODUCT_CODE,id);
        editor.commit();
    }
    public String getProductCode(){
        return preferences.getString(PRODUCT_CODE,null);
    }
    ////////////////////////////
    public void setProductName(String id){
        editor.putString(PRODUCT_NAME,id);
        editor.commit();
    }
    public String getProductName(){
        return preferences.getString(PRODUCT_NAME,null);
    }
    ////////////////////////////
    public void setReqQuantity(String id){
        editor.putString(REQ_QUANTITY,id);
        editor.commit();
    }
    public String getReqQuantity(){
        return preferences.getString(REQ_QUANTITY,null);
    }
    ////////////////////////////
    public void setUserID(String id){
        editor.putString(USER_ID,id);
        editor.commit();
    }
    public String getUserID(){
        return preferences.getString(USER_ID,null);
    }
    //////////////////////////
    public void setUserType(String id){
        editor.putString(USER_TYPE,id);
        editor.commit();
    }
    public String getUserType(){
        return preferences.getString(USER_TYPE,null);
    }
    ////////////////////////////
    public void setDeliQuantity(String id){
        editor.putString(DELI_QUANTITY,id);
        editor.commit();
    }
    public String getDeliQuantity(){
        return preferences.getString(DELI_QUANTITY,null);
    }
    ////////////////////////////
    public void setStoreName(String id){
        editor.putString(STORE_NAME,id);
        editor.commit();
    }
    public String getStoreName(){
        return preferences.getString(STORE_NAME,null);
    }
    ////////////////////////////
    public void setDeliveryDate(String id){
        editor.putString(DELIVERY_DATE,id);
        editor.commit();
    }
    public String getDeliveryDate(){
        return preferences.getString(DELIVERY_DATE,null);
    }
}

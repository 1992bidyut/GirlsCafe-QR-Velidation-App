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
}

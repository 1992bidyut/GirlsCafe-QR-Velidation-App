package com.onutiative.www.girlscafeqrvefification.Model.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.onutiative.www.girlscafeqrvefification.Model.LoginData.LoginResponse;
import com.onutiative.www.girlscafeqrvefification.Model.QRData.QRPushRequestBody;

import java.util.ArrayList;
import java.util.List;

public class DatabaseOperation {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;
    //private Helper helper;
    private Context context;
    public static final String TAG="DatabaseOperation";
    public DatabaseOperation(Context context) {
        databaseHelper = new DatabaseHelper(context);
       // helper = new Helper(context);
    }
    public void open(){

        db = databaseHelper.getWritableDatabase();
    }
    public void close(){
        db.close();
    }
    //user information operation
    //add data to user table
    public boolean insertUserData(LoginResponse.Data data){
        Log.i(TAG,"Name: "+data.getFirstName());
        this.open();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.USER_INFO_COL_FIRSTNAME, data.getFirstName());
        values.put(DatabaseHelper.USER_INFO_COL_LASTNAME, data.getLastName());
        values.put(DatabaseHelper.USER_INFO_COL_USERNAME,data.getUsername());
        values.put(DatabaseHelper.USER_INFO_COL_EMAIL,data.getUsername());
        values.put(DatabaseHelper.USER_INFO_COL_USER_ID, data.getUserId());
        values.put(DatabaseHelper.USER_INFO_COL_PASSWORD,data.getPassword());
        values.put(DatabaseHelper.USER_INFO_COL_LOGO,data.getLogo());
        values.put(DatabaseHelper.USER_INFO_COL_SECURITY_CODE,data.getSecurityCode());
        values.put(DatabaseHelper.USER_INFO_COL_STATUS,data.getStatus());
        values.put(DatabaseHelper.USER_INFO_COL_STORE_ID,data.getStoreId());
        values.put(DatabaseHelper.USER_INFO_COL_USER_TYPE,data.getUserType());
        values.put(DatabaseHelper.USER_INFO_COL_PHONE_NO,data.getContact_number());
        values.put(DatabaseHelper.USER_INFO_COL_LONGITUDE,"");
        values.put(DatabaseHelper.USER_INFO_COL_LATITUDE,"");
        // Inserting Row
        long code=db.insert(DatabaseHelper.TBL_USER_INFO, null, values);
        Log.i(TAG,"User info Insert code: "+code);
        db.close(); // Closing Database connection
        return true;
    }

    public LoginResponse.Data getUserInformation(){
        LoginResponse.Data userDataSet=null;
        this.open();

        Cursor cursor = db.query(DatabaseHelper.TBL_USER_INFO, null, null,null,null, null,null);
        if (cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            String userID = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_INFO_COL_USER_ID));
            String storeID = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_INFO_COL_STORE_ID));
            String userUsername = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_INFO_COL_USERNAME));
            String userPassword = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_INFO_COL_PASSWORD));
            String token = null;
            String userType = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_INFO_COL_USER_TYPE));
            String securityCode = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_INFO_COL_SECURITY_CODE));
            String status = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_INFO_COL_STATUS));
            String userFirstName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_INFO_COL_FIRSTNAME));
            String userLastName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_INFO_COL_LASTNAME));
            String gender="";
            String dateOfBirth = "";
            String logo = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_INFO_COL_LOGO));
            String userPhoneNumber = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_INFO_COL_PHONE_NO));

            //Data(String userId, String storeId, String username, String password, String token, String userType,
            // String securityCode, String status, String lastName, String firstName, String gender,
            // String dateOfBirth, String logo, String contact_number)
            userDataSet = new LoginResponse.Data(userID,storeID,userUsername,userPassword,token,userType,securityCode,status,userLastName,
                    userFirstName,gender,dateOfBirth,logo,userPhoneNumber);
        }
        cursor.close();
        Log.i(TAG, "Size: "+cursor.getCount());

        this.close();
        return userDataSet;
    }

    public void deleteUserData(){
        this.open();
        db.execSQL("delete from " + DatabaseHelper.TBL_USER_INFO);
        this.close();
    }
    //////////////////insert qr/////////////
    public boolean insertQR(QRPushRequestBody.Qr data){
        Log.i(TAG,"Name: "+data.getQrCode());
        this.open();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.QR_COL_QR_CODE, data.getQrCode());
        values.put(DatabaseHelper.QR_COL_USING_STATUS, data.getUsingStatus());
        values.put(DatabaseHelper.QR_COL_UPDATE_DATETIME,data.getUpdateDatetime());
        values.put(DatabaseHelper.QR_COL_PRODUCT_ID,data.getProductId());
        values.put(DatabaseHelper.QR_OL_REQUISITION_ID, data.getRequistionId());
        values.put(DatabaseHelper.QR_COL_BATCH_ID,data.getBatchId());
        values.put(DatabaseHelper.QR_COL_SUPPLY_ID,data.getSupplyId());

        // Inserting Row
        long code=db.insert(DatabaseHelper.TBL_QR_QUEUE, null, values);
        Log.i(TAG,"User info Insert code: "+code);
        db.close(); // Closing Database connection
        return true;
    }
    ////////////////////////////////////////////////

    public void deleteQrData(){
        this.open();
        db.execSQL("delete from " + DatabaseHelper.TBL_QR_QUEUE);
        this.close();
    }

    public List<QRPushRequestBody.Qr>getAllQrData(){
        List<QRPushRequestBody.Qr> qrList=new ArrayList<>();
        this.open();

        Cursor c=db.rawQuery("SELECT * FROM "+DatabaseHelper.TBL_QR_QUEUE,null);
        if (c!=null && c.getCount()>0){
            c.moveToFirst();
            do {
                String qrCode = c.getString(c.getColumnIndex(DatabaseHelper.QR_COL_QR_CODE));
                String usingStatus = c.getString(c.getColumnIndex(DatabaseHelper.QR_COL_USING_STATUS));
                String updateDateTime = c.getString(c.getColumnIndex(DatabaseHelper.QR_COL_UPDATE_DATETIME));
                String productID = c.getString(c.getColumnIndex(DatabaseHelper.QR_COL_PRODUCT_ID));
                String requisitionID = c.getString(c.getColumnIndex(DatabaseHelper.QR_OL_REQUISITION_ID));
                String batchID = c.getString(c.getColumnIndex(DatabaseHelper.QR_COL_BATCH_ID));
                String supplyID = c.getString(c.getColumnIndex(DatabaseHelper.QR_COL_SUPPLY_ID));

                QRPushRequestBody.Qr infraQue  = new QRPushRequestBody.Qr(qrCode,usingStatus,updateDateTime,productID,requisitionID,batchID,supplyID);
                qrList.add(infraQue);
            }while (c.moveToNext());
        }
        c.close();

        this.close();
        return qrList;
    }

    public int getCountQrData(){
        List<QRPushRequestBody.Qr> qrList=new ArrayList<>();
        this.open();

        Cursor c=db.rawQuery("SELECT * FROM "+DatabaseHelper.TBL_QR_QUEUE,null);
        int count = c.getCount();
        c.close();

        this.close();
        return count;
    }
}

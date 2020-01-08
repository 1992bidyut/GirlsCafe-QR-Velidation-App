package com.onutiative.www.girlscafeqrvefification.Model.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "GirlsCafe_QR_DB";
    public static int VERSION = 3;
    private String TAG= "DatabaseHelper";

    /////////////////////////////////Table User Information////////////////////////
    public static final String TBL_USER_INFO = "tbl_user_info";

    public static final String USER_INFO_COL_ID = "_id";
    public static final String USER_INFO_COL_FIRSTNAME= "user_first_name";
    public static final String USER_INFO_COL_LASTNAME= "user_last_name";
    public static final String USER_INFO_COL_USERNAME= "user_username";
    public static final String USER_INFO_COL_PASSWORD= "user_password";
    public static final String USER_INFO_COL_EMAIL= "user_email";
    public static final String USER_INFO_COL_LOGO= "logo";
    public static final String USER_INFO_COL_SECURITY_CODE= "security_code";
    public static final String USER_INFO_COL_STATUS= "status";
    public static final String USER_INFO_COL_STORE_ID= "store_id";
    public static final String USER_INFO_COL_USER_ID= "user_id";
    public static final String USER_INFO_COL_USER_TYPE= "user_type";
    public static final String USER_INFO_COL_LATITUDE= "user_latitude";
    public static final String USER_INFO_COL_LONGITUDE= "user_longitude";
    public static final String USER_INFO_COL_PHONE_NO= "user_phone_no";

    public static final String CREATE_TABLE_USER_INFO =
            "create table "+TBL_USER_INFO+"("+
            USER_INFO_COL_ID + " integer primary key autoincrement, "+
                    USER_INFO_COL_FIRSTNAME + " text, "+
                    USER_INFO_COL_LASTNAME + " text, "+
                    USER_INFO_COL_USERNAME + " text, "+
                    USER_INFO_COL_PASSWORD + " text, "+
                    USER_INFO_COL_EMAIL + " text, "+
                    USER_INFO_COL_LOGO + " text, "+
                    USER_INFO_COL_SECURITY_CODE + " text, "+
                    USER_INFO_COL_STATUS + " text, "+
                    USER_INFO_COL_STORE_ID + " text, "+
                    USER_INFO_COL_USER_ID + " text, "+
                    USER_INFO_COL_USER_TYPE + " text, "+
                    USER_INFO_COL_LATITUDE + " text, "+
                    USER_INFO_COL_LONGITUDE + " text, "+
                    USER_INFO_COL_PHONE_NO + " text);" ;

    ////////////////////////////////////Table User Information///////////////////////////////
    ///////////////////////////////////Table qr queue ////////////////////////////

    public static final String TBL_QR_QUEUE = "tbl_qr_queue";

    public static final String QR_COL_ID = "_id";
    public static final String QR_COL_QR_CODE= "qr_code";
    public static final String QR_COL_USING_STATUS= "using_status";
    public static final String QR_COL_UPDATE_DATETIME= "update_datetime";
    public static final String QR_COL_PRODUCT_ID= "product_id";
    public static final String QR_OL_REQUISITION_ID= "requistion_id";
    public static final String QR_COL_BATCH_ID= "batch_id";
    public static final String QR_COL_SUPPLY_ID= "supply_id";


//    "qr_code": "RBFSASAEFAE",
//            "using_status": "2",
//            "update_datetime": "2019-12-18",
//            "product_id": "ZXERGYQOSFVMN",
//            "requistion_id": "562",
//            "batch_id": "843123189",
//            "supply_id": "56254841613"


    public static final String CREATE_TABLE_QR_QUEUE =
            "create table "+TBL_QR_QUEUE+"("+
                    QR_COL_ID + " integer primary key autoincrement, "+
                    QR_COL_QR_CODE + " text, "+
                    QR_COL_USING_STATUS + " text, "+
                    QR_COL_UPDATE_DATETIME + " text, "+
                    QR_COL_PRODUCT_ID + " text, "+
                    QR_OL_REQUISITION_ID + " text, "+
                    QR_COL_BATCH_ID + " text, "+
                    QR_COL_SUPPLY_ID + " text)" ;

    //////////////////////////////Table qr queue////////////////////////


    public DatabaseHelper( Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i(TAG,"Database Helper onCreate Called");
        sqLiteDatabase.execSQL(CREATE_TABLE_USER_INFO);
        sqLiteDatabase.execSQL(CREATE_TABLE_QR_QUEUE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TBL_USER_INFO);
        db.execSQL("DROP TABLE IF EXISTS " + TBL_QR_QUEUE);
    }

}

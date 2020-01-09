package com.onutiative.www.girlscafeqrvefification.VIEW.PackageScanning;

import android.content.Context;
import android.util.Log;

import com.onutiative.www.girlscafeqrvefification.Model.BatchInfoData.BatchInfoRequestBody;
import com.onutiative.www.girlscafeqrvefification.Model.BatchInfoData.BatchInfoResponse;
import com.onutiative.www.girlscafeqrvefification.Model.Database.DatabaseOperation;
import com.onutiative.www.girlscafeqrvefification.Model.QRData.QRPushRequestBody;
import com.onutiative.www.girlscafeqrvefification.Model.QRData.QRPushResponse;
import com.onutiative.www.girlscafeqrvefification.Model.WScalling.BatchInfoCalling;
import com.onutiative.www.girlscafeqrvefification.Model.WScalling.QRTaggedAndConfirmation;
import com.onutiative.www.girlscafeqrvefification.Utility.Helper;
import com.onutiative.www.girlscafeqrvefification.Utility.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PresenterScanning implements ScanningCommunicator.ScanningViewPresenter,
        BatchInfoCalling.BatchInfoCallListener, QRTaggedAndConfirmation.QRCallListener {
    private Context context;
    private ScanningCommunicator.ScanningView view;
    private DatabaseOperation databaseOperation;
    private SharedPrefManager prefManager;
    private Helper helper;
    private String TAG="PresenterScanning";
    private BatchInfoCalling infoCalling;
    private BatchInfoRequestBody.BatchInfo batchInfo;
    private BatchInfoRequestBody.SupplierInfo supplierInfo;
    private String batchCode;
    private String supplyID;
    private QRTaggedAndConfirmation qrTaggedAndConfirmation;

    public PresenterScanning(Context context, ScanningCommunicator.ScanningView view) {
        this.context = context;
        this.view = view;
        databaseOperation=new DatabaseOperation(context);
        prefManager = new SharedPrefManager(context);
        helper= new Helper(context);
        infoCalling=new BatchInfoCalling(context,this);
    }

    @Override
    public void supplyHandler() {
        BatchInfoRequestBody requestBody=new BatchInfoRequestBody();
        batchCode=helper.makeUniqueID();
        supplyID=helper.makeUniqueID();

        String supplierID=databaseOperation.getUserInformation().getUserId();
        batchInfo=new BatchInfoRequestBody.BatchInfo(batchCode,helper.getDate(),supplierID);
        supplierInfo=new BatchInfoRequestBody.SupplierInfo(supplyID,supplierID,prefManager.getRequisitionID());
        requestBody.setBatchInfo(batchInfo);
        requestBody.setSupplierInfo(supplierInfo);
        if (helper.isInternetAvailable()){
            infoCalling.batchInfoCall(prefManager.getUsername(),prefManager.getUserPassword(),requestBody);
        }else {
            view.onFailed("No Internet!");
        }

    }

    @Override
    public void scanDataHandler(String data) {
        Log.i(TAG,"Scanned Value: "+data);
        String qrCode=getQR(data);
        Log.i(TAG, "QRCode: " + qrCode);
        if (!qrCode.isEmpty()){
            QRPushRequestBody.Qr qr=new QRPushRequestBody.Qr(qrCode,"2",helper.getDateTime(),prefManager.getProductCode(),
                    prefManager.getRequisitionID(),prefManager.getBatchCode(),prefManager.getSupplyCode());
            databaseOperation.insertQR(qr);
            view.showScanedData(qr);
        }
    }

    @Override
    public void submitScannedDate() {
        qrTaggedAndConfirmation = new QRTaggedAndConfirmation(context,this);

        List<QRPushRequestBody.Qr> qrList=databaseOperation.getAllQrData();
        QRPushRequestBody.Requisition requisition=new QRPushRequestBody.Requisition(prefManager.getRequisitionID(),prefManager.getProductCode(),
                prefManager.getProductName(),prefManager.getReqQuantity(),
                String.valueOf(qrList.size()+Integer.parseInt(prefManager.getDeliQuantity())),"0");

        List<QRPushRequestBody.Data> dataList =new ArrayList<>();
        QRPushRequestBody.Data data=new QRPushRequestBody.Data(qrList,requisition);
        dataList.add(data);

        QRPushRequestBody requestBody=new QRPushRequestBody(6,"delivered",prefManager.getStoreID(),
                prefManager.getUserID(),helper.getDate(),dataList);

        if (helper.isInternetAvailable()){
            qrTaggedAndConfirmation.apiCalling(prefManager.getUsername(),prefManager.getUserPassword(),requestBody);
        }else {
            view.onFailed("No Internet!");
        }
    }

    @Override
    public void onResponse(BatchInfoResponse response) {
        if (response!=null){
            if (response.getStatus().getCode()==202){
                Log.i(TAG,"Batch Code: "+batchCode);
                Log.i(TAG,"Supply Code: "+supplyID);
                prefManager.setBatchCode(batchCode);
                prefManager.setSupplyCode(supplyID);
                view.openScanner();
            }else {
                view.onFailed("Batch info not assigned!");
            }
        }else {
            view.onFailed("Server not responding! Please Check you connection.");
        }
    }
    private String getQR(String data){
        String qrCode="";
        Matcher matcher;
        Pattern patternPayment = Pattern.compile("qr=([A-Z]*)$");

        matcher = patternPayment.matcher(data);
        if (matcher.find()) {
            qrCode = matcher.group(1);
        } else {
            Log.i(TAG, "No match.");
        }
        return qrCode;
    }

    @Override
    public void onQRResponse(QRPushResponse response) {
        if (response!=null){
            if (response.getStatus().getCode()==202){
                view.onSuccessFull(response.getStatus().getReason());
                databaseOperation.deleteQrData();
            }else {
                view.onFailed(response.getStatus().getReason());
                databaseOperation.deleteQrData();
            }
        }else {
            view.onFailed("Data pushing failed!");
            databaseOperation.deleteQrData();
        }
    }
}

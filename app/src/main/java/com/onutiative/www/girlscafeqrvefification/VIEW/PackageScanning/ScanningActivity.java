package com.onutiative.www.girlscafeqrvefification.VIEW.PackageScanning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;
import com.onutiative.www.girlscafeqrvefification.Model.QRData.QRPushRequestBody;
import com.onutiative.www.girlscafeqrvefification.QRCode.BarcodeCaptureActivity;
import com.onutiative.www.girlscafeqrvefification.R;
import com.onutiative.www.girlscafeqrvefification.Utility.Helper;
import com.onutiative.www.girlscafeqrvefification.Utility.SharedPrefManager;

public class ScanningActivity extends AppCompatActivity implements ScanningCommunicator.ScanningView{
    private String TAG="ScanningActivity";
    private TextView product_name,qrCode,productCode,supplyCode,batchCode,requisitionCode;
    private static final int RC_BARCODE_CAPTURE = 9001;
    private SharedPrefManager prefManager;
    private Helper helper;
    private PresenterScanning presenterScanning;
    private Context context=this;
    private LinearLayout scannedData,startScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanning);

        qrCode=findViewById(R.id.qrCode);
        productCode=findViewById(R.id.productCode);
        supplyCode=findViewById(R.id.supplyCode);
        batchCode=findViewById(R.id.batchCode);
        requisitionCode=findViewById(R.id.requisitionCode);
        scannedData=findViewById(R.id.scannedData);
        startScan=findViewById(R.id.startScan);

        Intent intent=getIntent();
        String productName=intent.getStringExtra("product_name");
        String productID=intent.getStringExtra("product_id");
        String requisitionID=intent.getStringExtra("requisition_id");
        String requiredQuantity=intent.getStringExtra("requisition_quantity");

        helper=new Helper(this);
        prefManager=new SharedPrefManager(this);
        presenterScanning=new PresenterScanning(context,this);

        Log.i(TAG,"Name: "+productName+" ID: "+productID+" req_id: "+requisitionID+" Quantity: "+requiredQuantity+" Store: "+prefManager.getStoreID());
        product_name=findViewById(R.id.productName);
        product_name.setText(productName);
    }

    public void startScan(View view) {
        presenterScanning.supplyHandler();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_BARCODE_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
                    presenterScanning.scanDataHandler(barcode.displayValue);
                    Log.i(TAG, "Barcode read: " + barcode.displayValue);
                } else {
                    Log.i(TAG, "No barcode captured, intent data is null");
                }
            } else {
                Log.i(TAG,"Not Success Result Code: "+requestCode);
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onSuccessFull(String message) {

    }

    @Override
    public void onFailed(String message) {

    }

    @Override
    public void openScanner() {
        Intent intent = new Intent(this, BarcodeCaptureActivity.class);
        intent.putExtra(BarcodeCaptureActivity.AutoFocus, true);
        intent.putExtra(BarcodeCaptureActivity.UseFlash, false);
        startActivityForResult(intent, RC_BARCODE_CAPTURE);
    }

    @Override
    public void showScanedData(QRPushRequestBody.Qr qr) {
        startScan.setVisibility(View.GONE);
        scannedData.setVisibility(View.VISIBLE);
        qrCode.setText(qr.getQrCode());
        batchCode.setText(qr.getBatchId());
        productCode.setText(qr.getProductId());
        supplyCode.setText(qr.getSupplyId());
        batchCode.setText(qr.getBatchId());
    }

    public void next(View view) {
        openScanner();
    }

    public void submit(View view) {
        presenterScanning.submitScannedDate();
    }
}

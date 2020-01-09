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
import com.onutiative.www.girlscafeqrvefification.Model.Database.DatabaseOperation;
import com.onutiative.www.girlscafeqrvefification.Model.QRData.QRPushRequestBody;
import com.onutiative.www.girlscafeqrvefification.QRCode.BarcodeCaptureActivity;
import com.onutiative.www.girlscafeqrvefification.R;
import com.onutiative.www.girlscafeqrvefification.Utility.Helper;
import com.onutiative.www.girlscafeqrvefification.Utility.SharedPrefManager;

public class ScanningActivity extends AppCompatActivity implements ScanningCommunicator.ScanningView{
    private String TAG="ScanningActivity";
    private TextView product_name,qrCode,productCode,supplyCode,batchCode,requisitionCode,next;
    private static final int RC_BARCODE_CAPTURE = 9001;
    private SharedPrefManager prefManager;
    private Helper helper;
    private PresenterScanning presenterScanning;
    private Context context=this;
    private View containerView;
    private LinearLayout scannedData,startScan;
    private DatabaseOperation databaseOperation;
    private String productName,productID,requisitionID,deliveredQuantity,requiredQuantity;

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
        next=findViewById(R.id.next);
        containerView=findViewById(R.id.activityScanning);

        Intent intent=getIntent();
        productName=intent.getStringExtra("product_name");
        productID=intent.getStringExtra("product_id");
        requisitionID=intent.getStringExtra("requisition_id");
        requiredQuantity=intent.getStringExtra("requisition_quantity");
        deliveredQuantity=intent.getStringExtra("delivered_quantity");

        helper=new Helper(this);
        prefManager=new SharedPrefManager(this);
        presenterScanning=new PresenterScanning(context,this);
        databaseOperation=new DatabaseOperation(this);

        Log.i(TAG,"Name: "+productName+" ID: "+productID+" req_id: "
                +requisitionID+" Quantity: "+requiredQuantity+" Store: "
                +prefManager.getStoreID()+" Delivered: "+deliveredQuantity);
        product_name=findViewById(R.id.productName);
        product_name.setText(productName);
        Log.i(TAG,"QR Count: "+databaseOperation.getCountQrData());
    }

    public void startScan(View view) {
        if (requiredQuantity.equals(deliveredQuantity)){
            onSuccessFull("This product has already delivered!");
        }else {
            presenterScanning.supplyHandler();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_BARCODE_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
                    presenterScanning.scanDataHandler(barcode.displayValue);
                    if ((Integer.parseInt(requiredQuantity)-Integer.parseInt(deliveredQuantity))==databaseOperation.getCountQrData()){
                        next.setVisibility(View.GONE);
                    }
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
        helper.showSnakBar(containerView,message);
    }

    @Override
    public void onFailed(String message) {
        helper.showSnakBar(containerView,message);
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
        requisitionCode.setText("Requisition Code: "+qr.getRequistionId());
        qrCode.setText("QR Code: "+qr.getQrCode());
        batchCode.setText("Batch Code: "+qr.getBatchId());
        productCode.setText("Product ID: "+qr.getProductId());
        supplyCode.setText("Supply Code: "+qr.getSupplyId());
    }

    public void next(View view) {
        openScanner();
    }

    public void submit(View view) {
        presenterScanning.submitScannedDate();
    }
}

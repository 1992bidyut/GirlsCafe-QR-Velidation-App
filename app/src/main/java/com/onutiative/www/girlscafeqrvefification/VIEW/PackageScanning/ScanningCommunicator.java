package com.onutiative.www.girlscafeqrvefification.VIEW.PackageScanning;

import com.onutiative.www.girlscafeqrvefification.Model.QRData.QRPushRequestBody;
import com.onutiative.www.girlscafeqrvefification.Model.RequisitionData.RequisitionResponse;

import java.util.List;

public interface ScanningCommunicator {
    public interface ScanningView{
        public void onSuccessFull(String message);
        public void onFailed(String message);
        public void openScanner();
        public void showScanedData(QRPushRequestBody.Qr qr);
    }
    public interface ScanningViewPresenter{
        public void supplyHandler();
        public void scanDataHandler(String data);
        public void submitScannedDate();
    }
}

package com.onutiative.www.girlscafeqrvefification.VIEW.StoreKeeperRequisition;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;

import com.onutiative.www.girlscafeqrvefification.Model.SKRequisitionData.SKRequisitionRequestBody;
import com.onutiative.www.girlscafeqrvefification.Model.SKRequisitionData.SKRequisitionResponse;
import com.onutiative.www.girlscafeqrvefification.Model.WScalling.SKRequisitionCalling;
import com.onutiative.www.girlscafeqrvefification.Utility.Helper;
import com.onutiative.www.girlscafeqrvefification.Utility.SharedPrefManager;

public class PresenterSKRequisition implements SKRequisitionCommunicator.SKRequisitionViewPresenter,SKRequisitionCalling.SKRequisitionListener{
    private Context context;
    private SKRequisitionCommunicator.SKRequisitionView view;
    private Helper helper;
    private SKRequisitionCalling requisitionCalling;
    private String TAG="PresenterSKRequisition";
    private SharedPrefManager prefManager;

    public PresenterSKRequisition(Context context, SKRequisitionCommunicator.SKRequisitionView view) {
        this.context = context;
        this.view = view;
        helper=new Helper(context);
        requisitionCalling=new SKRequisitionCalling(context,this);
        prefManager=new SharedPrefManager(context);
    }

    @Override
    public void requisitionPullHandler() {
        SKRequisitionRequestBody requestBody = new SKRequisitionRequestBody(prefManager.getUserID(),helper.getDate(),
                prefManager.getStoreID(),Integer.parseInt(prefManager.getUserType()));
        if (helper.isInternetAvailable()){
            requisitionCalling.skRequisitionCall(prefManager.getUsername(),prefManager.getUserPassword(),requestBody);
        }else {
            view.onFailed("No Internet!");
        }
    }

    @Override
    public void onResponse(SKRequisitionResponse response) {
        Log.i(TAG,"Size: "+response.getData().size());
        if (response!=null){
            if (response.getStatus().getCode()==200){
                view.adapterHandler(response.getData());
            }else {
                view.onFailed(response.getStatus().getReason());
            }
        }else {
            view.onFailed("Server not responding!");
        }
    }
}

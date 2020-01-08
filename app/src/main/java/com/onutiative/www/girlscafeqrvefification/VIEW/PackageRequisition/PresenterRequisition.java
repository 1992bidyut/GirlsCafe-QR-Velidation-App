package com.onutiative.www.girlscafeqrvefification.VIEW.PackageRequisition;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.onutiative.www.girlscafeqrvefification.Model.Database.DatabaseOperation;
import com.onutiative.www.girlscafeqrvefification.Model.RequisitionData.RequisitionRequersBody;
import com.onutiative.www.girlscafeqrvefification.Model.RequisitionData.RequisitionResponse;
import com.onutiative.www.girlscafeqrvefification.Model.WScalling.RequisitionListCalling;
import com.onutiative.www.girlscafeqrvefification.Utility.Helper;
import com.onutiative.www.girlscafeqrvefification.Utility.SharedPrefManager;

public class PresenterRequisition implements RequisitionCommunicator.RequisitionViewPresenter,RequisitionListCalling.RequisitionListener{
    private Context context;
    private RequisitionCommunicator.RequisitionView requisitionView;
    private DatabaseOperation databaseOperation;
    private RequisitionListCalling requisitionListCalling;
    private Helper helper;
    private String TAG="PresenterRequisition";
    private SharedPrefManager prefManager;
    private Gson gson;

    public PresenterRequisition(Context context, RequisitionCommunicator.RequisitionView requisitionView) {
        this.context = context;
        this.requisitionView = requisitionView;
        databaseOperation=new DatabaseOperation(context);
        helper=new Helper(context);
        prefManager=new SharedPrefManager(context);
        requisitionListCalling=new RequisitionListCalling(context,this);
    }

    @Override
    public void reqListHandler() {
        String u_name=prefManager.getUsername();
        String u_password=prefManager.getUserPassword();
        Log.i(TAG,"Usernae: "+u_name+" Pass: "+u_password);
        RequisitionRequersBody requisitionRequersBody=new RequisitionRequersBody(helper.getDate());
        requisitionListCalling.requisitionCall(u_name,u_password,requisitionRequersBody);
    }

    @Override
    public void onResponse(RequisitionResponse response) {
        if (response!=null){
            gson=new Gson();
            String res= gson.toJson(response.getStatus());
            Log.i(TAG,"RequisitionActivity Response: "+res);
            if (response.getStatus().getCode()==204){
                requisitionView.onFailed(response.getStatus().getReason());
                return;
            }
            requisitionView.adapterHandler(response.getData());
        }else {
            requisitionView.onFailed("Something wrong!");
        }
    }
}
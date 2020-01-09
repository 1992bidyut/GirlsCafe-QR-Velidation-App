package com.onutiative.www.girlscafeqrvefification.VIEW.PackageRequisitionDetails;

import android.content.Context;

import com.onutiative.www.girlscafeqrvefification.Model.Database.DatabaseOperation;
import com.onutiative.www.girlscafeqrvefification.Model.RequisitionDetailsData.RequisitionDetailResponse;
import com.onutiative.www.girlscafeqrvefification.Model.RequisitionDetailsData.RequisitionDetailsRequestBody;
import com.onutiative.www.girlscafeqrvefification.Model.WScalling.RequisitionDetailsListCalling;
import com.onutiative.www.girlscafeqrvefification.Utility.Helper;
import com.onutiative.www.girlscafeqrvefification.Utility.SharedPrefManager;

public class PresenterRequisitionDetails implements RequisitionDetailsCommunicator.RequisitionDetailsViewPresenter,
        RequisitionDetailsListCalling.RequisitionDetailsListener {
    private Context context;
    private RequisitionDetailsCommunicator.RequisitionDetailsView view;
    private DatabaseOperation operation;
    private SharedPrefManager prefManager;
    private String TAG="PresenterRequisitionDetails";
    private Helper helper;
    private RequisitionDetailsListCalling detailsListCalling;

    public PresenterRequisitionDetails(Context context, RequisitionDetailsCommunicator.RequisitionDetailsView view) {
        this.context = context;
        this.view = view;
        operation=new DatabaseOperation(context);
        prefManager=new SharedPrefManager(context);
        helper=new Helper(context);
        detailsListCalling=new RequisitionDetailsListCalling(context,this);
    }

    @Override
    public void reqListHandler(String requisitionID) {
        RequisitionDetailsRequestBody requestBody=new RequisitionDetailsRequestBody(requisitionID);
        if (helper.isInternetAvailable()){
            detailsListCalling.requisitionDetailsCall(prefManager.getUsername(),prefManager.getUserPassword(),requestBody);
        }else {
            view.onFailed("No Internet!");
        }


    }

    @Override
    public void onResponse(RequisitionDetailResponse response) {
        if (response!=null){
            view.adapterHandler(response.getData());
        }else {
            view.onFailed("Server not responding!");
        }
    }
}

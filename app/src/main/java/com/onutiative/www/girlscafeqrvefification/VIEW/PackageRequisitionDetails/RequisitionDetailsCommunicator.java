package com.onutiative.www.girlscafeqrvefification.VIEW.PackageRequisitionDetails;

import com.onutiative.www.girlscafeqrvefification.Model.RequisitionData.RequisitionResponse;
import com.onutiative.www.girlscafeqrvefification.Model.RequisitionDetailsData.RequisitionDetailResponse;

import java.util.List;

public interface RequisitionDetailsCommunicator {
    public interface RequisitionDetailsView{
        public void onSuccessFull(String message);
        public void onFailed(String message);
        public void adapterHandler(List<RequisitionDetailResponse.Data> dataList);
    }
    public interface RequisitionDetailsViewPresenter{
        public void reqListHandler(String requisitionID);
    }
}

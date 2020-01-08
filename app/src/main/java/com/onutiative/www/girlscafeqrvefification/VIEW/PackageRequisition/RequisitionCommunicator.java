package com.onutiative.www.girlscafeqrvefification.VIEW.PackageRequisition;

import com.onutiative.www.girlscafeqrvefification.Model.RequisitionData.RequisitionResponse;

import java.util.List;

public interface RequisitionCommunicator {
    public interface RequisitionView{
        public void onSuccessFull(String message);
        public void onFailed(String message);
        public void adapterHandler(List<RequisitionResponse.Data> dataList);

    }
    public interface RequisitionViewPresenter{
        public void reqListHandler();
    }
}

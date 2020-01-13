package com.onutiative.www.girlscafeqrvefification.VIEW.StoreKeeperRequisition;

import com.onutiative.www.girlscafeqrvefification.Model.SKRequisitionData.SKRequisitionResponse;

import java.util.List;

public interface SKRequisitionCommunicator {
    public interface SKRequisitionView{
        public void onSuccessFull(String message);
        public void onFailed(String message);
        public void adapterHandler(List<SKRequisitionResponse.Data> dataList);
    }
    public interface SKRequisitionViewPresenter{
        public void requisitionPullHandler();
    }

    public interface SKRAdapterDataHandler{
        public void receivePushHandler();
    }

}

package com.onutiative.www.girlscafeqrvefification.Model.WScalling;

import com.onutiative.www.girlscafeqrvefification.Model.BatchInfoData.BatchInfoRequestBody;
import com.onutiative.www.girlscafeqrvefification.Model.BatchInfoData.BatchInfoResponse;
import com.onutiative.www.girlscafeqrvefification.Model.LoginData.LoginRequestBody;
import com.onutiative.www.girlscafeqrvefification.Model.LoginData.LoginResponse;
import com.onutiative.www.girlscafeqrvefification.Model.QRData.QRPushRequestBody;
import com.onutiative.www.girlscafeqrvefification.Model.QRData.QRPushResponse;
import com.onutiative.www.girlscafeqrvefification.Model.RequisitionData.RequisitionRequersBody;
import com.onutiative.www.girlscafeqrvefification.Model.RequisitionData.RequisitionResponse;
import com.onutiative.www.girlscafeqrvefification.Model.RequisitionDetailsData.RequisitionDetailResponse;
import com.onutiative.www.girlscafeqrvefification.Model.RequisitionDetailsData.RequisitionDetailsRequestBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIinterface {
    @POST("0v1/login")
    Call<LoginResponse> login(@Body LoginRequestBody body);

    @POST("0v1/getRequisitionList")
    Call<RequisitionResponse> getRequisitionList(@Body RequisitionRequersBody body);

    @POST("0v1/getRequisitionDetails")
    Call<RequisitionDetailResponse> getRequisitionDetailList(@Body RequisitionDetailsRequestBody body);

    @POST("0v1/createBatch")
    Call<BatchInfoResponse> postBatchinfo(@Body BatchInfoRequestBody body);

    @POST("0v1/rqTaggedAndConfirmation")
    Call<QRPushResponse> qrPush(@Body QRPushRequestBody body);
}

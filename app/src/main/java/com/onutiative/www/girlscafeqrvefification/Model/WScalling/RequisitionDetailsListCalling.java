package com.onutiative.www.girlscafeqrvefification.Model.WScalling;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.onutiative.www.girlscafeqrvefification.Model.RequisitionData.RequisitionRequersBody;
import com.onutiative.www.girlscafeqrvefification.Model.RequisitionData.RequisitionResponse;
import com.onutiative.www.girlscafeqrvefification.Model.RequisitionDetailsData.RequisitionDetailResponse;
import com.onutiative.www.girlscafeqrvefification.Model.RequisitionDetailsData.RequisitionDetailsRequestBody;
import com.onutiative.www.girlscafeqrvefification.Utility.Constant;
import com.onutiative.www.girlscafeqrvefification.VIEW.PackageRequisition.PresenterRequisition;
import com.onutiative.www.girlscafeqrvefification.VIEW.PackageRequisitionDetails.PresenterRequisitionDetails;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequisitionDetailsListCalling {
    private Context context;
    private PresenterRequisitionDetails presenterRequisition;
    private APIinterface apIinterface;
    private RequisitionDetailResponse detailResponse = null;
    private RequisitionDetailsListCalling.RequisitionDetailsListener listener;
    private Gson gson;
    private String TAG= "RequisitionDetailsListCalling";
    private ProgressDialog dialog;

    public RequisitionDetailsListCalling(Context context, PresenterRequisitionDetails presenterRequisition) {
        this.context = context;
        listener = presenterRequisition;

    }

    public void requisitionDetailsCall(String username, String password, final RequisitionDetailsRequestBody requestBody){
        // preparing interceptor for retrofit
        // interceptor for runtime data checking
        dialog = new ProgressDialog(context);
        dialog.setMessage("Pulling requisition details....");
        dialog.show();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //generate auth token
        final String authToken = Credentials.basic(username,password);
        //authentication interceptor
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Headers headers = request.headers().newBuilder().add("Authorization",authToken).build();
                        request=request.newBuilder().headers(headers).build();
                        return chain.proceed(request);
                    }
                }).addInterceptor(loggingInterceptor)
                .build();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        apIinterface=retrofit.create(APIinterface.class);
        final Call<RequisitionDetailResponse> detailResponseCall=apIinterface.getRequisitionDetailList(requestBody);
        detailResponseCall.enqueue(new Callback<RequisitionDetailResponse>() {
            @Override
            public void onResponse(Call<RequisitionDetailResponse> call, retrofit2.Response<RequisitionDetailResponse> response) {
                if (response.isSuccessful()){
                    detailResponse=response.body();
                    listener.onResponse(detailResponse);
                    dialog.cancel();
                    Log.i(TAG,"Successful");
                }
            }
            @Override
            public void onFailure(Call<RequisitionDetailResponse> call, Throwable t) {
                listener.onResponse(detailResponse);
                Log.i(TAG,"Failed"+t.getMessage());
                dialog.cancel();
            }
        });
    }

    public interface RequisitionDetailsListener{
        void onResponse(RequisitionDetailResponse response);
    }
}

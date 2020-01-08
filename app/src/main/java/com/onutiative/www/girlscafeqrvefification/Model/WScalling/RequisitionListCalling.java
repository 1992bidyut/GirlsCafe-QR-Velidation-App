package com.onutiative.www.girlscafeqrvefification.Model.WScalling;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.onutiative.www.girlscafeqrvefification.Model.RequisitionData.RequisitionRequersBody;
import com.onutiative.www.girlscafeqrvefification.Model.RequisitionData.RequisitionResponse;
import com.onutiative.www.girlscafeqrvefification.VIEW.PackageRequisition.PresenterRequisition;
import com.onutiative.www.girlscafeqrvefification.Utility.Constant;

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

public class RequisitionListCalling {
    private PresenterRequisition presenterRequisition;
    private APIinterface apIinterface;
    private RequisitionResponse requisitionResponse=null;
    private RequisitionListener listener;
    private Gson gson;
    private String TAG= "RequisitionListCalling";
    private ProgressDialog dialog;
    private Context context;

    public RequisitionListCalling(Context context,PresenterRequisition presenterRequisition) {
        listener = (RequisitionListener) presenterRequisition;
        this.context=context;
    }
    public void requisitionCall(String username, String password, final RequisitionRequersBody requestBody){
        // preparing interceptor for retrofit
        // interceptor for runtime data checking
        dialog = new ProgressDialog(context);
        dialog.setMessage("Pulling requisition....");
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
        final Call<RequisitionResponse> requisitionList=apIinterface.getRequisitionList(requestBody);
        requisitionList.enqueue(new Callback<RequisitionResponse>() {
            @Override
            public void onResponse(Call<RequisitionResponse> call, retrofit2.Response<RequisitionResponse> response) {
                if (response.isSuccessful()){
                    requisitionResponse=response.body();
                    listener.onResponse(requisitionResponse);
                    dialog.cancel();
                    Log.i(TAG,"Successful!");
                }
            }
            @Override
            public void onFailure(Call<RequisitionResponse> call, Throwable t) {
                listener.onResponse(requisitionResponse);
                dialog.cancel();
            }
        });
    }

    public interface RequisitionListener{
        void onResponse(RequisitionResponse response);
    }
}

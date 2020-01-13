package com.onutiative.www.girlscafeqrvefification.Model.WScalling;

import android.app.ProgressDialog;
import android.content.Context;
import android.nfc.Tag;
import android.util.Log;

import com.google.gson.Gson;
import com.onutiative.www.girlscafeqrvefification.Model.LoginData.LoginRequestBody;
import com.onutiative.www.girlscafeqrvefification.Model.LoginData.LoginResponse;
import com.onutiative.www.girlscafeqrvefification.Model.SKRequisitionData.SKRequisitionRequestBody;
import com.onutiative.www.girlscafeqrvefification.Model.SKRequisitionData.SKRequisitionResponse;
import com.onutiative.www.girlscafeqrvefification.Utility.Constant;
import com.onutiative.www.girlscafeqrvefification.VIEW.StoreKeeperRequisition.PresenterSKRequisition;

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

public class SKRequisitionCalling {
    private Context context;
    private PresenterSKRequisition presenterSKRequisition;
    private String TAG="SKRequisitionCalling";
    private APIinterface apIinterface;
    private ProgressDialog dialog;
    private SKRequisitionListener listener;
    private SKRequisitionResponse requisitionResponse=null;

    public SKRequisitionCalling(Context context, PresenterSKRequisition presenterSKRequisition) {
        this.context = context;
        listener = (SKRequisitionListener) presenterSKRequisition;
    }

    public void skRequisitionCall(String username, String password, final SKRequisitionRequestBody requestBody){
        // preparing interceptor for retrofit
        // interceptor for runtime data checking
        dialog = new ProgressDialog(context);
        dialog.setMessage("Requisition pulling...");
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
        final Call<SKRequisitionResponse> ResponseCall=apIinterface.pullSKRequisition(requestBody);
        ResponseCall.enqueue(new Callback<SKRequisitionResponse>() {
            @Override
            public void onResponse(Call<SKRequisitionResponse> call, retrofit2.Response<SKRequisitionResponse> response) {
                if (response.isSuccessful()){
                    requisitionResponse=response.body();
                    listener.onResponse(requisitionResponse);
                    dialog.cancel();
                }
            }
            @Override
            public void onFailure(Call<SKRequisitionResponse> call, Throwable t) {
                listener.onResponse(requisitionResponse);
                dialog.cancel();
            }
        });
    }

    public interface SKRequisitionListener{
        void onResponse(SKRequisitionResponse response);
    }
}

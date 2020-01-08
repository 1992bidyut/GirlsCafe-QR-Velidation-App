package com.onutiative.www.girlscafeqrvefification.Model.WScalling;

import android.app.ProgressDialog;
import android.content.Context;

import com.onutiative.www.girlscafeqrvefification.Model.BatchInfoData.BatchInfoRequestBody;
import com.onutiative.www.girlscafeqrvefification.Model.BatchInfoData.BatchInfoResponse;
import com.onutiative.www.girlscafeqrvefification.Model.QRData.QRPushRequestBody;
import com.onutiative.www.girlscafeqrvefification.Model.QRData.QRPushResponse;
import com.onutiative.www.girlscafeqrvefification.Utility.Constant;
import com.onutiative.www.girlscafeqrvefification.VIEW.PackageScanning.PresenterScanning;

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

public class QRTaggedAndConfirmation {
    private Context context;
    private PresenterScanning presenterScanning;
    private String TAG="QRTaggedAndConfirmation";
    private ProgressDialog dialog;
    private APIinterface apIinterface;
    private QRCallListener listener;
    private QRPushResponse qrPushResponse;

    public QRTaggedAndConfirmation(Context context, PresenterScanning presenterScanning) {
        this.context = context;
        listener= (QRCallListener) presenterScanning;
    }

    public void apiCalling(String username, String password, final QRPushRequestBody requestBody){
        dialog = new ProgressDialog(context);
        dialog.setMessage("Scanned data pushing....");
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
        final Call<QRPushResponse>qrResponse=apIinterface.qrPush(requestBody);
        qrResponse.enqueue(new Callback<QRPushResponse>() {
            @Override
            public void onResponse(Call<QRPushResponse> call, retrofit2.Response<QRPushResponse> response) {
                if (response.isSuccessful()){
                    qrPushResponse=response.body();
                    listener.onQRResponse(qrPushResponse);
                    dialog.cancel();
                }
            }
            @Override
            public void onFailure(Call<QRPushResponse> call, Throwable t) {
                listener.onQRResponse(qrPushResponse);
                dialog.cancel();
            }
        });
    }
    public interface QRCallListener{
        void onQRResponse(QRPushResponse response);
    }
}

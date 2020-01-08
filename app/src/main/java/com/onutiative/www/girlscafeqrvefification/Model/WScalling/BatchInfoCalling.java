package com.onutiative.www.girlscafeqrvefification.Model.WScalling;

import android.app.ProgressDialog;
import android.content.Context;

import com.onutiative.www.girlscafeqrvefification.Model.BatchInfoData.BatchInfoRequestBody;
import com.onutiative.www.girlscafeqrvefification.Model.BatchInfoData.BatchInfoResponse;
import com.onutiative.www.girlscafeqrvefification.Model.RequisitionData.RequisitionRequersBody;
import com.onutiative.www.girlscafeqrvefification.Model.RequisitionData.RequisitionResponse;
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

public class BatchInfoCalling {
    private Context context;
    private PresenterScanning presenterScanning;
    private String TAG="BatchInfoCalling";
    private ProgressDialog dialog;
    private APIinterface apIinterface;
    private BatchInfoResponse infoResponse=null;
    private BatchInfoCallListener listener;

    public BatchInfoCalling(Context context, PresenterScanning presenterScanning) {
        this.context = context;
        listener = (BatchInfoCallListener) presenterScanning;
    }
    public void batchInfoCall(String username, String password, final BatchInfoRequestBody requestBody){
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
        final Call<BatchInfoResponse> batchInfoResponse=apIinterface.postBatchinfo(requestBody);
        batchInfoResponse.enqueue(new Callback<BatchInfoResponse>() {
            @Override
            public void onResponse(Call<BatchInfoResponse> call, retrofit2.Response<BatchInfoResponse> response) {
                if (response.isSuccessful()){
                    infoResponse=response.body();
                    listener.onResponse(infoResponse);
                    dialog.cancel();
                }
            }
            @Override
            public void onFailure(Call<BatchInfoResponse> call, Throwable t) {
                listener.onResponse(infoResponse);
                dialog.cancel();
            }
        });
    }
    public interface BatchInfoCallListener{
        void onResponse(BatchInfoResponse response);
    }
}

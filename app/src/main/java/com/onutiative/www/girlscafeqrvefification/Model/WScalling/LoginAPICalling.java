package com.onutiative.www.girlscafeqrvefification.Model.WScalling;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.onutiative.www.girlscafeqrvefification.Model.LoginData.LoginRequestBody;
import com.onutiative.www.girlscafeqrvefification.Model.LoginData.LoginResponse;
import com.onutiative.www.girlscafeqrvefification.VIEW.PackageLogin.PresenterLogin;
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

public class LoginAPICalling {
    private String TAG="LoginAPICalling";
    private PresenterLogin presenterLogin;
    private APIinterface apIinterface;
    private LoginResponse loginResponse = null;
    private Gson gson;
    private LoginListener listener;
    private Context context;
    private ProgressDialog dialog;


    public LoginAPICalling(Context context,PresenterLogin presenterLogin) {
        listener= (LoginListener) presenterLogin;
        this.context=context;
    }

    public LoginResponse loginCall(String username, String password, final LoginRequestBody requestBody){
        // preparing interceptor for retrofit
        // interceptor for runtime data checking
        dialog = new ProgressDialog(context);
        dialog.setMessage("Login...");
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
        final Call<LoginResponse> loginResponseCall=apIinterface.login(requestBody);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    loginResponse=response.body();
                    gson=new Gson();
                    String res= gson.toJson(loginResponse);
                    Log.i(TAG,"Login Response: "+res);
                    listener.onResponse(loginResponse);
                    dialog.cancel();
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                listener.onResponse(loginResponse);
                dialog.cancel();
            }
        });
        return loginResponse;
    }

    public interface LoginListener{
        void onResponse(LoginResponse response);
    }
}
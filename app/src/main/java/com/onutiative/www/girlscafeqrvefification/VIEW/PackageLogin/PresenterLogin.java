package com.onutiative.www.girlscafeqrvefification.VIEW.PackageLogin;

import android.content.Context;
import android.util.Log;

import com.onutiative.www.girlscafeqrvefification.Model.Database.DatabaseOperation;
import com.onutiative.www.girlscafeqrvefification.Model.LoginData.LoginRequestBody;
import com.onutiative.www.girlscafeqrvefification.Model.LoginData.LoginResponse;
import com.onutiative.www.girlscafeqrvefification.Model.WScalling.LoginAPICalling;

public class PresenterLogin implements LoginCommunicator.LoginViewPresenter, LoginAPICalling.LoginListener{
    private String TAG="PresenterLogin";
    private Context context;
    private DatabaseOperation databaseOperation;
    private LoginCommunicator.LoginView loginView;
    private LoginAPICalling apiCalling;

    public PresenterLogin(Context context, LoginCommunicator.LoginView loginView) {
        this.context = context;
        this.loginView = loginView;
        databaseOperation=new DatabaseOperation(context);
        apiCalling=new LoginAPICalling(context,this);
        databaseOperation=new DatabaseOperation(context);
    }
    @Override
    public void loginHandler(String username, String password) {
        Log.i(TAG,"Username: "+username+" Password: "+password);
        apiCalling.loginCall(username,password,new LoginRequestBody("23.8103","90.4125"));
    }

    @Override
    public void onResponse(LoginResponse response) {
        if (response!=null){
            loginView.loginSuccessFull("Successful!");
            databaseOperation.insertUserData(response.getData());
        }else {
            loginView.loginFailed("Not successful!");
        }
    }
}

package com.onutiative.www.girlscafeqrvefification.VIEW.PackageLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.onutiative.www.girlscafeqrvefification.R;
import com.onutiative.www.girlscafeqrvefification.VIEW.PackageRequisition.RequisitionActivity;
import com.onutiative.www.girlscafeqrvefification.Utility.Helper;
import com.onutiative.www.girlscafeqrvefification.Utility.SharedPrefManager;

public class LoginActivity extends AppCompatActivity implements LoginCommunicator.LoginView{
    private EditText userName,password;
    private String TAG="LoginActivity";
    private PresenterLogin presenterLogin;
    private Context context = LoginActivity.this;
    private View containerView;
    private Helper helper = new Helper(this);
    private SharedPrefManager prefManager;
    private String u_name;
    private String u_pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        userName=findViewById(R.id.userName);
        password=findViewById(R.id.password);
        presenterLogin=new PresenterLogin(context,this);
        containerView=findViewById(R.id.activity_login);
        prefManager=new SharedPrefManager(context);
        if (prefManager.isLoggedIn()){
            Intent intent=new Intent(LoginActivity.this, RequisitionActivity.class);
            startActivity(intent);
            //finish();
        }
    }

    public void login(View view) {
        u_name=userName.getText().toString();
        u_pass=password.getText().toString();
        presenterLogin.loginHandler(u_name,u_pass);
    }

    @Override
    public void loginSuccessFull(String message) {
        //helper.showSnakBar(containerView,message);
        Intent intent=new Intent(LoginActivity.this, RequisitionActivity.class);
        startActivity(intent);
        prefManager.setLoggedInFlag(true);
        prefManager.setUserNane(u_name);
        prefManager.setUserPassword(u_pass);
    }
    @Override
    public void loginFailed(String message) {
        helper.showSnakBar(containerView,message);

    }
}

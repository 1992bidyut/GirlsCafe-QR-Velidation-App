package com.onutiative.www.girlscafeqrvefification.VIEW.StoreKeeperRequisition;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.onutiative.www.girlscafeqrvefification.Model.Database.DatabaseOperation;
import com.onutiative.www.girlscafeqrvefification.Model.SKRequisitionData.SKRequisitionResponse;
import com.onutiative.www.girlscafeqrvefification.R;
import com.onutiative.www.girlscafeqrvefification.Utility.Helper;
import com.onutiative.www.girlscafeqrvefification.Utility.SharedPrefManager;
import com.onutiative.www.girlscafeqrvefification.VIEW.PackageLogin.LoginActivity;
import com.onutiative.www.girlscafeqrvefification.VIEW.PackageRequisition.RequisitionActivity;
import com.onutiative.www.girlscafeqrvefification.VIEW.PackageRequisitionDetails.RequisitionDetailsAdapter;

import java.util.List;

public class SKRequisitionActivity extends AppCompatActivity implements SKRequisitionCommunicator.SKRequisitionView{
    private RecyclerView skRequisitionList;
    private Context context=this;
    private View containerView;
    private Helper helper;
    private PresenterSKRequisition presenter;
    private RecyclerView.LayoutManager layoutManager;
    private SKRequisitionAdapter adapter;
    private DatabaseOperation databaseOperation;
    private SharedPrefManager prefManager;
    private String TAG="SKRequisitionActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skrequisition);

        skRequisitionList=findViewById(R.id.skRequisitionList);
        containerView=findViewById(R.id.skRequisitionActivity);
        presenter=new PresenterSKRequisition(context,this);
        databaseOperation=new DatabaseOperation(this);
        prefManager=new SharedPrefManager(this);
        presenter.requisitionPullHandler();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
                databaseOperation.deleteUserData();
                databaseOperation.deleteQrData();
                prefManager.setLoggedInFlag(false);
                Intent intent=new Intent(SKRequisitionActivity.this, LoginActivity.class);
                startActivity(intent);
                Log.i(TAG,"clicked on logout");
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onSuccessFull(String message) {
        helper.showSnakBar(containerView,message);
    }

    @Override
    public void onFailed(String message) {
        helper.showSnakBar(containerView,message);
    }

    @Override
    public void adapterHandler(List<SKRequisitionResponse.Data> dataList) {
        adapter=new SKRequisitionAdapter(context,dataList);
        layoutManager=new LinearLayoutManager(context);
        skRequisitionList.setLayoutManager(layoutManager);
        skRequisitionList.setAdapter(adapter);
    }

    public void submit(View view) {
        adapter.receivePushHandler();
    }
}

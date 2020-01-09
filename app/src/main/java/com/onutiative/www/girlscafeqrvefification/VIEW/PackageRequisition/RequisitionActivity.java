package com.onutiative.www.girlscafeqrvefification.VIEW.PackageRequisition;

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
import com.onutiative.www.girlscafeqrvefification.Model.RequisitionData.RequisitionResponse;
import com.onutiative.www.girlscafeqrvefification.VIEW.PackageLogin.LoginActivity;
import com.onutiative.www.girlscafeqrvefification.R;
import com.onutiative.www.girlscafeqrvefification.Utility.Helper;
import com.onutiative.www.girlscafeqrvefification.Utility.SharedPrefManager;

import java.util.List;

public class RequisitionActivity extends AppCompatActivity implements RequisitionCommunicator.RequisitionView{
    private RecyclerView recyclerView;
    private PresenterRequisition presenterRequisition;
    private Context context=this;
    private View containerView;
    private Helper helper;
    private DatabaseOperation databaseOperation;
    private String TAG = "RequisitionActivity";
    private SharedPrefManager prefManager;

    private RequisitionAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todays_requisition);
        recyclerView=findViewById(R.id.requisitionList);
        containerView=findViewById(R.id.todaysRequisition);

        helper=new Helper(context);
        databaseOperation=new DatabaseOperation(context);
        prefManager=new SharedPrefManager(context);

        presenterRequisition = new PresenterRequisition(context,this);
        presenterRequisition.reqListHandler();

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
                Intent intent=new Intent(RequisitionActivity.this, LoginActivity.class);
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
    public void adapterHandler(List<RequisitionResponse.Data> dataList) {
        Log.i(TAG,"Size: "+dataList.size());
        adapter=new RequisitionAdapter(context,dataList);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
}

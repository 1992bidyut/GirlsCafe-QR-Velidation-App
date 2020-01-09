package com.onutiative.www.girlscafeqrvefification.VIEW.PackageRequisitionDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.onutiative.www.girlscafeqrvefification.Model.RequisitionDetailsData.RequisitionDetailResponse;
import com.onutiative.www.girlscafeqrvefification.R;
import com.onutiative.www.girlscafeqrvefification.Utility.Helper;

import java.util.List;

public class RequisitionDetailsActivity extends AppCompatActivity implements RequisitionDetailsCommunicator.RequisitionDetailsView{
    private String TAG="RequisitionDetailsActivity";
    private TextView store_name,requisition_ID,delivery_date;
    private View containerView;
    private Helper helper;
    private Context context=this;
    private PresenterRequisitionDetails presenterRequisitionDetails;
    private RequisitionDetailsAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requisition_details);
        containerView=findViewById(R.id.requisitionDetailsActivity);

        Intent intent=getIntent();
        String requisitionID=intent.getStringExtra("requisition_id");
        String storeID=intent.getStringExtra("store_id");
        String storeName=intent.getStringExtra("store_name");
        String deliveryDate=intent.getStringExtra("delivery_date");
        helper=new Helper(context);

        store_name=findViewById(R.id.storeName);
        requisition_ID=findViewById(R.id.requisitionID);
        delivery_date=findViewById(R.id.deliveryDate);
        recyclerView=findViewById(R.id.requisitionDetailList);
        presenterRequisitionDetails=new PresenterRequisitionDetails(context,this);
        presenterRequisitionDetails.reqListHandler(requisitionID);

        store_name.setText("Store: "+storeName);
        requisition_ID.setText("Requisition ID: "+requisitionID);
        delivery_date.setText("Delivery Date: "+deliveryDate);


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
    public void adapterHandler(List<RequisitionDetailResponse.Data> dataList) {
        Log.i(TAG,"Size: "+dataList.size());
        adapter=new RequisitionDetailsAdapter(context,dataList);
        layoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}

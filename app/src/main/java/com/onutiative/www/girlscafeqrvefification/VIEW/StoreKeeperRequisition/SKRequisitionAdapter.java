package com.onutiative.www.girlscafeqrvefification.VIEW.StoreKeeperRequisition;

import android.content.Context;
import android.nfc.Tag;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.onutiative.www.girlscafeqrvefification.Model.QRData.QRPushRequestBody;
import com.onutiative.www.girlscafeqrvefification.Model.QRData.QRPushResponse;
import com.onutiative.www.girlscafeqrvefification.Model.SKRequisitionData.SKRequisitionResponse;
import com.onutiative.www.girlscafeqrvefification.Model.WScalling.QRTaggedAndConfirmation;
import com.onutiative.www.girlscafeqrvefification.R;
import com.onutiative.www.girlscafeqrvefification.Utility.Helper;
import com.onutiative.www.girlscafeqrvefification.Utility.SharedPrefManager;
import com.onutiative.www.girlscafeqrvefification.VIEW.PackageRequisitionDetails.RequisitionDetailsAdapter;

import java.util.ArrayList;
import java.util.List;

public class SKRequisitionAdapter extends RecyclerView.Adapter<SKRequisitionAdapter.ViewHolder>
        implements SKRequisitionCommunicator.SKRAdapterDataHandler,QRTaggedAndConfirmation.QRCallListener{
    private Context context;
    private List<SKRequisitionResponse.Data> dataList;
    private String TAG="SKRequisitionAdapter";
    private Helper helper;
    private SharedPrefManager prefManager;
    PresenterSKRequisition presenter;
    SKRequisitionCommunicator.SKRequisitionView view;

    public SKRequisitionAdapter(Context context, List<SKRequisitionResponse.Data> dataList) {
        this.context = context;
        this.dataList = dataList;
        helper=new Helper(context);
        prefManager=new SharedPrefManager(context);
        view= (SKRequisitionCommunicator.SKRequisitionView) context;
        presenter=new PresenterSKRequisition(context,view);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.requisition_form_row, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.productName.setText(dataList.get(position).getProductName());
        String deliveryQuan="";
        if (dataList.get(position).getDeliveryQuantity()==null){
            deliveryQuan="0";
        }else {
            deliveryQuan=dataList.get(position).getDeliveryQuantity();
        }
        holder.deliveredQuan.setText(deliveryQuan);
        String receiveQuan="";
        if (dataList.get(position).getReceivedQuantity()==null){
            receiveQuan="";
        }else {
            receiveQuan=dataList.get(position).getReceivedQuantity();
        }
        holder.receiveQuan.setText(receiveQuan);

        holder.receiveQuan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String temp= holder.receiveQuan.getText().toString();
                //Log.i(TAG,"Temp: "+temp);
                dataList.get(position).setReceivedQuantity(temp);
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public void receivePushHandler() {
        QRTaggedAndConfirmation qrTaggedAndConfirmation = new QRTaggedAndConfirmation(context,this);
        List<QRPushRequestBody.Qr> qrList=null;
        List<QRPushRequestBody.Data> qrDataList =new ArrayList<>();

//        "requisition": {
//            "requisition_id": "4",
//                    "product_id": "26785964",
//                    "product_name": "Burger",
//                    "req_quantity": "50",
//                    "delivered_quantity": "50",
//                    "received_quantity": "10"
//        }

        for ( SKRequisitionResponse.Data data: dataList) {
            QRPushRequestBody.Requisition requisition=null;
            if (data.getReceivedQuantity()!=null && data.getDeliveryQuantity()!=null){
                //Log.i(TAG,data.getReceivedQuantity());
                requisition=new QRPushRequestBody.Requisition(data.getReqId(),data.getProductId(),
                        data.getProductName(),data.getReqQuantity(),data.getDeliveryQuantity(),data.getReceivedQuantity());
            }else if (data.getReceivedQuantity()==null && data.getDeliveryQuantity()!=null){
                //Log.i(TAG,data.getReceivedQuantity());
                requisition=new QRPushRequestBody.Requisition(data.getReqId(),data.getProductId(),
                        data.getProductName(),data.getReqQuantity(),data.getDeliveryQuantity(),"0");
            }else if (data.getReceivedQuantity()!=null && data.getDeliveryQuantity()==null){
                //Log.i(TAG,data.getReceivedQuantity());
                requisition=new QRPushRequestBody.Requisition(data.getReqId(),data.getProductId(),
                        data.getProductName(),data.getReqQuantity(),"0","0");
            }else if (data.getReceivedQuantity()==null && data.getDeliveryQuantity()==null){
                //Log.i(TAG,data.getReceivedQuantity());
                requisition=new QRPushRequestBody.Requisition(data.getReqId(),data.getProductId(),
                        data.getProductName(),data.getReqQuantity(),"0","0");
            }else if (data.getDeliveryQuantity().isEmpty()){
                //Log.i(TAG,data.getReceivedQuantity());
                requisition=new QRPushRequestBody.Requisition(data.getReqId(),data.getProductId(),
                        data.getProductName(),data.getReqQuantity(),"0","0");
            }

            QRPushRequestBody.Data qrdata=new QRPushRequestBody.Data(qrList,requisition);
            qrDataList.add(qrdata);
        }

        QRPushRequestBody requestBody=new QRPushRequestBody(6,"received",prefManager.getStoreID(),
                prefManager.getUserID(),helper.getDate(),qrDataList);

        Gson gson=new Gson();
        String req=gson.toJson(requestBody);
        Log.i(TAG,"Request Body: "+req);

        if (helper.isInternetAvailable()){
            qrTaggedAndConfirmation.apiCalling(prefManager.getUsername(),prefManager.getUserPassword(),requestBody);
        }else {

        }
    }

    @Override
    public void onQRResponse(QRPushResponse response) {
        if (response!=null){
            if (response.getStatus().getCode()==202){
                Toast.makeText(context,"Data accepted!",Toast.LENGTH_SHORT).show();
                presenter.requisitionPullHandler();
            }else {
                Toast.makeText(context,response.getStatus().getReason(),Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(context,"Server not responding!",Toast.LENGTH_SHORT).show();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView productName, deliveredQuan;
        EditText receiveQuan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName=itemView.findViewById(R.id.productName);
            deliveredQuan=itemView.findViewById(R.id.deliveryQuan);
            receiveQuan=itemView.findViewById(R.id.receivedQuan);
        }
    }
}

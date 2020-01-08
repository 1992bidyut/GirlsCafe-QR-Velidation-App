package com.onutiative.www.girlscafeqrvefification.VIEW.PackageRequisition;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.onutiative.www.girlscafeqrvefification.Model.RequisitionData.RequisitionResponse;
import com.onutiative.www.girlscafeqrvefification.Utility.SharedPrefManager;
import com.onutiative.www.girlscafeqrvefification.VIEW.PackageRequisitionDetails.RequisitionDetailsActivity;
import com.onutiative.www.girlscafeqrvefification.R;

import java.util.List;

public class RequisitionAdapter extends RecyclerView.Adapter<RequisitionAdapter.ViewHolder>{
    private Context context;
    private List<RequisitionResponse.Data> dataList;
    private String TAG="RequisitionAdapter";
    private SharedPrefManager prefManager;

    public RequisitionAdapter(Context context, List<RequisitionResponse.Data> dataList) {
        this.context = context;
        this.dataList = dataList;
        prefManager=new SharedPrefManager(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.requisition_row_view, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.storeName.setText("Store: "+dataList.get(position).getStoreName());
        holder.submittedDate.setText("Submitted Date: "+dataList.get(position).getReqCreateDate());
        holder.deliveryDate.setText("Delivery Date: "+dataList.get(position).getReqDate());
        holder.address.setText("Address: "+dataList.get(position).getStoreAddress());
        holder.requisitionRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG,"Requisition ID: "+dataList.get(position).getId());
                Log.i(TAG,"Store ID: "+dataList.get(position).getStoreId());

                prefManager.setStoreID(dataList.get(position).getStoreId());
                prefManager.setRequisitionID(dataList.get(position).getId());

                Intent intent= new Intent(context, RequisitionDetailsActivity.class);
                intent.putExtra("requisition_id",dataList.get(position).getId());
                intent.putExtra("store_id",dataList.get(position).getStoreId());
                intent.putExtra("store_name",dataList.get(position).getStoreName());
                intent.putExtra("delivery_date",dataList.get(position).getReqDate());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView storeName,submittedDate,deliveryDate,address;
        CardView requisitionRow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            storeName=itemView.findViewById(R.id.storeName);
            submittedDate=itemView.findViewById(R.id.submittedDate);
            deliveryDate=itemView.findViewById(R.id.deliveryDate);
            address=itemView.findViewById(R.id.address);
            requisitionRow=itemView.findViewById(R.id.requisitionRow);
        }
    }
}

package com.onutiative.www.girlscafeqrvefification.VIEW.PackageRequisitionDetails;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.onutiative.www.girlscafeqrvefification.Model.RequisitionDetailsData.RequisitionDetailResponse;
import com.onutiative.www.girlscafeqrvefification.R;
import com.onutiative.www.girlscafeqrvefification.Utility.SharedPrefManager;
import com.onutiative.www.girlscafeqrvefification.VIEW.PackageRequisition.RequisitionAdapter;
import com.onutiative.www.girlscafeqrvefification.VIEW.PackageScanning.ScanningActivity;

import java.util.List;

public class RequisitionDetailsAdapter extends RecyclerView.Adapter<RequisitionDetailsAdapter.ViewHolder>{
    private Context context;
    private List<RequisitionDetailResponse.Data> dataList;
    private SharedPrefManager prefManager;

    public RequisitionDetailsAdapter(Context context, List<RequisitionDetailResponse.Data> dataList) {
        this.context = context;
        this.dataList = dataList;
        prefManager=new SharedPrefManager(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.requisition_product_row_view, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.productName.setText("Product Name: "+dataList.get(position).getProductName());
        holder.productCode.setText("Product Code: "+dataList.get(position).getProductId());
        holder.productQuantity.setText("Required Quantity: "+dataList.get(position).getReqQuantity());
        holder.requisitionDetailsListRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ScanningActivity.class);
                intent.putExtra("product_name",dataList.get(position).getProductName());
                intent.putExtra("product_id",dataList.get(position).getProductId());
                intent.putExtra("requisition_id",dataList.get(position).getReqId());
                intent.putExtra("requisition_quantity",dataList.get(position).getReqQuantity());
                prefManager.setProductCode(dataList.get(position).getProductId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView productName,productCode,productQuantity;
        CardView requisitionDetailsListRow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName=itemView.findViewById(R.id.productName);
            productCode=itemView.findViewById(R.id.productCode);
            productQuantity=itemView.findViewById(R.id.productQuantity);
            requisitionDetailsListRow=itemView.findViewById(R.id.requisitionDetailsListRow);
        }
    }
}

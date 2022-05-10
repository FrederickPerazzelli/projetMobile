package com.example.projetmobile;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MyAdapterDemand extends RecyclerView.Adapter<MyAdapterDemand.MyViewHolder>{

    private Context context;
    private ArrayList<String> demandsId = new ArrayList<String>();
    private ArrayList<String> demandsTitle = new ArrayList<String>();
    private ArrayList<String> demandsStatus = new ArrayList<String>();
    private ArrayList<String> demandsComments = new ArrayList<String>();
    private ArrayList<String> demandsCategory = new ArrayList<String>();
    private ArrayList<String> demandsPublishInfo = new ArrayList<String>();


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.demands_general_info, parent, false);
        return new MyViewHolder(view);
    }

    public MyAdapterDemand(Context context,ArrayList<String> demandsId, ArrayList<String> demandsTitle, ArrayList<String> demandsStatus,
                           ArrayList<String> demandsComments, ArrayList<String> demandsCategory,
                           ArrayList<String> demandsPublishInfo){
        this.context = context;
        this.demandsId = demandsId;
        this.demandsTitle = demandsTitle;
        this.demandsStatus = demandsStatus;
        this.demandsComments = demandsComments;
        this.demandsCategory = demandsCategory;
        this.demandsPublishInfo = demandsPublishInfo;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull MyAdapterDemand.MyViewHolder holder, int position) {
        holder.demandsTitle.setText(demandsTitle.get(position));
        holder.demandsComments.setText(demandsComments.get(position));
        holder.demandsStatus.setText(demandsStatus.get(position));
        holder.demandsCategory.setText(demandsCategory.get(position));
        holder.demandsPublishInfo.setText(demandsPublishInfo.get(position));

        String id = demandsId.get(position);

        holder.consult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext() , RequestDemandInfoActivity.class);
                Bundle b = new Bundle();
                b.putString("id", id);
                intent.putExtra("bundle", b);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() { return demandsId.size(); }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView demandsTitle;
        TextView demandsComments;
        TextView demandsStatus;
        TextView demandsCategory;
        TextView demandsPublishInfo;
        TextView demandsId;
        Button consult;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            demandsTitle = (TextView)itemView.findViewById(R.id.demandsTitle);
            demandsComments = (TextView)itemView.findViewById(R.id.demandsComments);
            demandsStatus = (TextView)itemView.findViewById(R.id.demandsStatus);
            demandsCategory = (TextView)itemView.findViewById(R.id.demandsCategory);
            demandsPublishInfo = (TextView)itemView.findViewById(R.id.demandsPublishInfo);
            demandsId = (TextView)itemView.findViewById(R.id.demandsId);
            consult = (Button)itemView.findViewById(R.id.goToDemand);
        }
    }
}

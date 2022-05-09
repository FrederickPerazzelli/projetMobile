package com.example.projetmobile;

import android.content.Context;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapterDemand extends RecyclerView.Adapter<MyAdapterDemand.MyViewHolder>{

    private Context context;
    private Demand listDemand[];

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.demands_general_info, parent, false);
        return new MyViewHolder(view);
    }

    public MyAdapterDemand(Context context, Demand ListDemand[]){
        this.context = context;
        this.listDemand = ListDemand;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull MyAdapterDemand.MyViewHolder holder, int position) {
        holder.demandsTitle.setText(listDemand[position].get_title());
        holder.demandsComments.setText(listDemand[position].get_comments());
        holder.demandsStatus.setText(listDemand[position].get_status());
        holder.demandsCategory.setText(listDemand[position].get_category());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(listDemand[position].get_publishDate());
        holder.demandsPublishInfo.setText(strDate);
    }

    @Override
    public int getItemCount() { return listDemand.length; }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView demandsTitle;
        TextView demandsComments;
        TextView demandsStatus;
        TextView demandsCategory;
        TextView demandsPublishInfo;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            demandsTitle = (TextView)itemView.findViewById(R.id.demandsTitle);
            demandsComments = (TextView)itemView.findViewById(R.id.demandsComments);
            demandsStatus = (TextView)itemView.findViewById(R.id.demandsStatus);
            demandsCategory = (TextView)itemView.findViewById(R.id.demandsCategory);
            demandsPublishInfo = (TextView)itemView.findViewById(R.id.demandsPublishInfo);
        }
    }
}

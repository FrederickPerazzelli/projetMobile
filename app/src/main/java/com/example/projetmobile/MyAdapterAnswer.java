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

public class MyAdapterAnswer extends RecyclerView.Adapter<MyAdapterAnswer.MyViewHolder>{

    private Context context;
    private ArrayList<String> answerId = new ArrayList<String>();
    private ArrayList<String> answerDate = new ArrayList<String>();
    private ArrayList<String> answerComments = new ArrayList<String>();


    @NonNull
    @Override
    public MyAdapterAnswer.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.demands_general_info, parent, false);
        return new MyAdapterAnswer.MyViewHolder(view);
    }

    public MyAdapterAnswer(Context context, ArrayList<String> answerId, ArrayList<String> answerDate,
                           ArrayList<String> answerComments){
        this.context = context;
        this.answerId = answerId;
        this.answerDate = answerDate;
        this.answerComments= answerComments;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull MyAdapterAnswer.MyViewHolder holder, int position) {
        holder.answerDate.setText(answerDate.get(position));
        holder.answerComments.setText(answerComments.get(position));
    }

    @Override
    public int getItemCount() { return answerId.size(); }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView answerDate;
        TextView answerComments;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            answerDate = (TextView)itemView.findViewById(R.id.answerPublisher);
            answerComments = (TextView)itemView.findViewById(R.id.answerComment);

        }
    }
}
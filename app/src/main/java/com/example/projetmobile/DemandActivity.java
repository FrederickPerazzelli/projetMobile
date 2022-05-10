package com.example.projetmobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class DemandActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private ArrayList<String> answerId = new ArrayList<String>();
    private ArrayList<String> answerDate = new ArrayList<String>();
    private ArrayList<String> answerComments = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demand);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        String demandInfo = bundle.getString("demandInfo");
        String answerInfo = bundle.getString("answerInfo");

        // Set view
        TextView demandId = (TextView)findViewById(R.id.demandId);
        TextView demandTitle = (TextView)findViewById(R.id.titleDemand);
        TextView demandSubject = (TextView)findViewById(R.id.demandSujet);
        TextView demandPublishDate = (TextView)findViewById(R.id.demandPublisher);
        TextView demandStatus = (TextView)findViewById(R.id.demandStatus);
        TextView demandCategory = (TextView)findViewById(R.id.demandCategory);
        TextView demandComments = (TextView)findViewById(R.id.demandComments);

        Toast.makeText(this, bundle.getString("answerInfo"), Toast.LENGTH_SHORT).show();

        try {

            JSONArray jsonArrDemand = new JSONArray(demandInfo);
            JSONArray jsonArrAnswer = new JSONArray(answerInfo);
            recyclerView = (RecyclerView)findViewById(R.id.demandRecycler);

            // set text Demand
            String idDemand = ("#" + jsonArrDemand.getJSONObject(0).getString("id"));
            demandId.setText(idDemand);
            demandTitle.setText(jsonArrDemand.getJSONObject(0).getString("title"));
            String subjectDemand = (" Sujet : " + jsonArrDemand.getJSONObject(0).getString("subject"));
            demandSubject.setText(subjectDemand);
            String demandPublishInfo = ("Écrit par " + jsonArrDemand.getJSONObject(0).getString("first_name") + " " +
                    jsonArrDemand.getJSONObject(0).getString("last_name") + " le " +
                    jsonArrDemand.getJSONObject(0).getString("publish_date").substring(0,10));
            demandPublishDate.setText(demandPublishInfo);
            demandStatus.setText(jsonArrDemand.getJSONObject(0).getString("status"));
            String categoryDemand = (" Catégorie : " + jsonArrDemand.getJSONObject(0).getString("category"));
            demandCategory.setText(categoryDemand);
            demandComments.setText(jsonArrDemand.getJSONObject(0).getString("comments"));


            // Set Text answer
            for (int i = 0; i < jsonArrAnswer.length(); i++)
            {
                answerId.add(jsonArrAnswer.getJSONObject(i).getString("id"));
                answerComments.add(jsonArrAnswer.getJSONObject(i).getString("comments"));

                String publishInfo = ("Écrit par " + jsonArrAnswer.getJSONObject(i).getString("first_name") + " " +
                        jsonArrAnswer.getJSONObject(i).getString("last_name") + " le " +
                        jsonArrAnswer.getJSONObject(i).getString("answer_date").substring(0,10));
                answerDate.add(publishInfo);
            }

            MyAdapterAnswer myAdapter = new MyAdapterAnswer(this, answerId, answerDate, answerComments);
            recyclerView.setAdapter(myAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

        }catch (JSONException err){
            Log.d("Error", err.toString());
        }
    }

}

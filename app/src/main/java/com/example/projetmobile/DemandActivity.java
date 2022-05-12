package com.example.projetmobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
        setContentView(R.layout.activity_demand);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        String id = bundle.getString("id");
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

        //Toast.makeText(this, bundle.getString("answerInfo"), Toast.LENGTH_SHORT).show();

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

        Button buttonAnswer = (Button) findViewById(R.id.AnswerButton);
        buttonAnswer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext() , NewAnswerActivity.class);
                Bundle b = new Bundle();
                b.putString("demandInfo", demandInfo);
                intent.putExtra("bundle", b);

                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profil:
                Intent intentProfil = new Intent(this, ProfileActivity.class);
                startActivity(intentProfil);
                return true;
            case R.id.demands:
                Intent intentDemands = new Intent(this, RequestDemandsActivity.class);
                startActivity(intentDemands);
                return true;
            case R.id.newDemands:
                Intent intentNewDemands = new Intent(this, NewDemandActivity.class);
                startActivity(intentNewDemands);
                return true;
            case R.id.complaints:
                Intent intentComplaint = new Intent(this, ComplaintActivity.class);
                startActivity(intentComplaint);
                return true;
            default:
                return true;

        }
    }

}

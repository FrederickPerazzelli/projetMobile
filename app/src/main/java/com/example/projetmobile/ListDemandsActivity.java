package com.example.projetmobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;


public class ListDemandsActivity extends AppCompatActivity{

    RecyclerView recyclerView;
    private ArrayList<String> demandsId = new ArrayList<String>();
    private ArrayList<String> demandsTitle = new ArrayList<String>();
    private ArrayList<String> demandsStatus = new ArrayList<String>();
    private ArrayList<String> demandsComments = new ArrayList<String>();
    private ArrayList<String> demandsCategory = new ArrayList<String>();
    private ArrayList<String> demandsPublishInfo = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_demands);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        String requestReceived = bundle.getString("listDemands");

        try {

            JSONArray jsonArr = new JSONArray(requestReceived);

            recyclerView = (RecyclerView)findViewById(R.id.listDemandsRecycler);

            //Toast.makeText(this, demandsPublishInfo.get(0), Toast.LENGTH_SHORT).show();

            for (int i = 0; i < jsonArr.length(); i++)
            {
                demandsId.add(jsonArr.getJSONObject(i).getString("id"));
                demandsTitle.add(jsonArr.getJSONObject(i).getString("title"));
                demandsStatus.add(jsonArr.getJSONObject(i).getString("status"));
                demandsComments.add(jsonArr.getJSONObject(i).getString("comments"));
                demandsCategory.add(jsonArr.getJSONObject(i).getString("category"));

                String publishInfo = ("Écrit par " + jsonArr.getJSONObject(i).getString("first_name") + " " +
                        jsonArr.getJSONObject(i).getString("last_name") + " le " +
                        jsonArr.getJSONObject(i).getString("publish_date").substring(0,10));

                demandsPublishInfo.add(publishInfo);
            }

            MyAdapterDemand myAdapter = new MyAdapterDemand(this, demandsId, demandsTitle, demandsStatus,
                    demandsComments, demandsCategory, demandsPublishInfo);
            recyclerView.setAdapter(myAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

        }catch (JSONException err){
            Log.d("Error", err.toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch(item.getItemId()){
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

package com.example.projetmobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


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
        setContentView(R.layout.list_demands);

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

}

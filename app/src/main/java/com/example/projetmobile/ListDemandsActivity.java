package com.example.projetmobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.json.JSONException;



public class ListDemandsActivity extends AppCompatActivity {

    private Demand ListDemands[];
    //RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_demands);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        String requestReceived = bundle.getString("listDemands");


        Toast.makeText(this, requestReceived, Toast.LENGTH_LONG ).show();
        //recyclerView = (RecyclerView)findViewById(R.id.listDemandsRecycler);

    }

}

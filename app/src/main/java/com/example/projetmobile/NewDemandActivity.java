package com.example.projetmobile;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewDemandActivity extends AppCompatActivity {

    private ArrayList<String> categoryId = new ArrayList<String>();
    private ArrayList<String> categoryName = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_demand);


        RequestQueue queue = Volley.newRequestQueue(this);
        String url = ("http://192.168.159.1:8000/api/categoryList");

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String responseReceived = response.substring(response.indexOf("["));

                try {
                    JSONArray jsonArrCategory = new JSONArray(responseReceived);

                    for (int i = 0; i < jsonArrCategory.length(); i++)
                    {
                        categoryId.add(jsonArrCategory.getJSONObject(i).getString("id"));
                        categoryName.add(jsonArrCategory.getJSONObject(i).getString("name"));
                    }

                    populateSpinner(categoryName);

                }catch (JSONException err){
                    Log.d("Error", err.toString());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(NewDemandActivity.this, "Erreur", Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(stringRequest);


        Button buttonSend = (Button) findViewById(R.id.sendDemand);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onClick(View v) {
                try {

                    // Url pour aller envoyer l'objet json
                    RequestQueue queue = Volley.newRequestQueue(NewDemandActivity.this);
                    String url = "http://192.168.159.1:8000/api/addDemand";

                    // Build string pour envoyer en json la réponse
                    JSONObject jsonBody = new JSONObject();

                    // Title
                    EditText newTitle = (EditText)findViewById(R.id.sendTitle);
                    String title = newTitle.getText().toString();
                    jsonBody.put("title", title);

                    // subject
                    EditText newSubject = (EditText)findViewById(R.id.sendSubject);
                    String subject = newSubject.getText().toString();
                    jsonBody.put("subject", subject);

                    // category




                    // Date time
                    String localDate = (java.time.LocalDate.now()).toString();
                    String time = (java.time.LocalTime.now()).toString().substring(0,8);
                    String dateTime = (localDate + " " + time);
                    jsonBody.put("dateTime", dateTime);

                    // Nouvelle question
                    EditText newQuestion = (EditText)findViewById(R.id.sendQuestion);
                    String question = newQuestion.getText().toString();
                    jsonBody.put("comment", question);

                    // User
                    jsonBody.put("user", "1");

                    // Status, par defaut placer a "En Cours"
                    jsonBody.put("status", "9");


                    JsonObjectRequest objectRequest = new JsonObjectRequest (Request.Method.POST, url, jsonBody,
                            new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {

                                    Toast.makeText(getApplicationContext(), "Réponse envoyer !", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(v.getContext() , RequestDemandsActivity.class);
                                    startActivity(intent);
                                    finish();

                                }
                            }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(getApplicationContext(), "Erreur" , Toast.LENGTH_SHORT).show();
                        }
                    });

                    queue.add(objectRequest);

                }catch (JSONException err){
                    Log.d("Error", err.toString());
                }
            }

        });


    }

    // Met les information dans le spinner
    public void populateSpinner(ArrayList<String> data){
        Spinner categorySpinner = (Spinner) findViewById(R.id.category_spinner);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, data);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(dataAdapter);
    }

}

/****************************************
 Fichier : NewDemandActivity
 Auteur : Frédérick Perazzelli-Delorme
 Fonctionnalité : C4 - Publie une demande d'aide publique
 Date : 9 mai 2022
 Techniques de l’informatique
 Vérification :
 Date Nom Approuvé
 =========================================================
 Historique de modifications :
 Date Nom Description
 =========================================================
 ****************************************/
package com.example.projetmobile;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
    private String idCategory = "1";
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_demand);

        Spinner categorySpinner = (Spinner) findViewById(R.id.category_spinner);
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

                    populateSpinner(categorySpinner, categoryName);

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
                    if (!title.equals("")){
                        jsonBody.put("title", title);
                    }

                    // subject
                    EditText newSubject = (EditText)findViewById(R.id.sendSubject);
                    String subject = newSubject.getText().toString();
                    if(!subject.equals("")){
                        jsonBody.put("subject", subject);
                    }

                    // category
                    categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                            idCategory = categoryId.get(position);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            
                        }
                    });
                    jsonBody.put("category", idCategory);


                    // Date time
                    String localDate = (java.time.LocalDate.now()).toString();
                    String time = (java.time.LocalTime.now()).toString().substring(0,8);
                    String dateTime = (localDate + " " + time);
                    jsonBody.put("dateTime", dateTime);

                    // Nouvelle question
                    EditText newQuestion = (EditText)findViewById(R.id.sendQuestion);
                    String question = newQuestion.getText().toString();
                    if(!question.equals("")){
                        jsonBody.put("comment", question);
                    }

                    // User
                    DBManager dbManager = new DBManager();
                    dbManager.setupDBConnection(NewDemandActivity.this);
                    currentUser = dbManager.getCurrentUser();
                    jsonBody.put("user", currentUser.getId());

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

                            if(title.equals("")){
                                Toast.makeText(getApplicationContext(), "Entrez un Titre" , Toast.LENGTH_SHORT).show();
                            }else if (subject.equals("")){
                                Toast.makeText(getApplicationContext(), "Entrez un sujet" , Toast.LENGTH_SHORT).show();
                            }else if (question.equals("")) {
                                Toast.makeText(getApplicationContext(), "Entrez une question", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getApplicationContext(), "Erreur" , Toast.LENGTH_SHORT).show();
                            }

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
    public void populateSpinner(Spinner categorySpinner, ArrayList<String> data){


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, data);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(dataAdapter);
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

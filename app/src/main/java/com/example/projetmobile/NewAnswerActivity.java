/****************************************
 Fichier : newAnswerActivity
 Auteur : Frédérick Perazzelli-Delorme
 Fonctionnalité : C7 - Répond a une demande d'aide
 Date : 10 mai 2022
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NewAnswerActivity extends AppCompatActivity {

        private User currentUser;

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_new_answer);

            Intent intent = getIntent();
            Bundle bundle = intent.getBundleExtra("bundle");
            String demandInfo = bundle.getString("demandInfo");


            // Set view
            TextView demandComment = (TextView)findViewById(R.id.newAnswerComment);
            TextView demandTitle = (TextView)findViewById(R.id.titleOfDemand);

            try {
                JSONArray jsonArrDemand = new JSONArray(demandInfo);

                String id = jsonArrDemand.getJSONObject(0).getString("id");
                demandTitle.setText(jsonArrDemand.getJSONObject(0).getString("title"));
                demandComment.setText(jsonArrDemand.getJSONObject(0).getString("comments"));

                Button buttonSend = (Button) findViewById(R.id.buttonSend);
                buttonSend.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        try {

                            // Url pour aller envoyer l'objet json
                            RequestQueue queue = Volley.newRequestQueue(NewAnswerActivity.this);
                            String url = "http://192.168.159.1:8000/api/addAnswer";

                            // Build string pour envoyer en json la réponse
                            JSONObject jsonBody = new JSONObject();

                            // id de la demande
                            jsonBody.put("demand", id);
                            //Toast.makeText(getApplicationContext(), id , Toast.LENGTH_SHORT).show();

                            // id du user (dataBase)
                            DBManager dbManager = new DBManager();
                            dbManager.setupDBConnection(NewAnswerActivity.this);
                            currentUser = dbManager.getCurrentUser();
                            jsonBody.put("user", currentUser.getId());

                            // Date time
                            String localDate = (java.time.LocalDate.now()).toString();
                            String time = (java.time.LocalTime.now()).toString().substring(0,8);
                            String dateTime = (localDate + " " + time);
                            jsonBody.put("dateTime", dateTime);

                            // Nouvelle réponse
                            EditText newAnswer = (EditText)findViewById(R.id.sendAnswer);
                            String comment = newAnswer.getText().toString();
                            if(!comment.equals("")){
                                jsonBody.put("comment", comment);
                            }



                        JsonObjectRequest objectRequest = new JsonObjectRequest (Request.Method.POST, url, jsonBody,
                                new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {

                                Toast.makeText(getApplicationContext(), "Réponse envoyer !", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(v.getContext() , RequestDemandInfoActivity.class);
                                Bundle b = new Bundle();
                                b.putString("id", id);
                                intent.putExtra("bundle", b);

                                startActivity(intent);
                                finish();

                            }
                            }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {

                                if(comment.equals("")){
                                    Toast.makeText(getApplicationContext(), "Entrez une réponse" , Toast.LENGTH_SHORT).show();
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

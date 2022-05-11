package com.example.projetmobile;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
                            jsonBody.put("user", "1");

                            // Date time
                            String localDate = (java.time.LocalDate.now()).toString();
                            String time = (java.time.LocalTime.now()).toString().substring(0,8);
                            String dateTime = (localDate + " " + time);
                            jsonBody.put("dateTime", dateTime);

                            // Nouvelle réponse
                            EditText newAnswer = (EditText)findViewById(R.id.sendAnswer);
                            String comment = newAnswer.getText().toString();
                            jsonBody.put("comment", comment);


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

                                Toast.makeText(getApplicationContext(), "Erreur" , Toast.LENGTH_SHORT).show();
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
}

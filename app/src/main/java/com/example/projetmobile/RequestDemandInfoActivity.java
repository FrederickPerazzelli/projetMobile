/****************************************
 Fichier : RequestDemandInfoActivity
 Auteur : Frédérick Perazzelli-Delorme
 Fonctionnalité : C6 - Request API pour la liste des demandes
 Date : 6 mai 2022
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
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class RequestDemandInfoActivity extends AppCompatActivity {

    ProgressBar circularProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar_demand_info);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        String id = bundle.getString("id");

        circularProgressBar = (ProgressBar) findViewById(R.id.circular_progress_info);
        circularProgressBar.setVisibility(View.INVISIBLE);

        RequestDemandInfoActivity.ShowProgressBarTask showTask = new RequestDemandInfoActivity.ShowProgressBarTask();
        showTask.execute();

        final TextView responseReceived = (TextView) findViewById(R.id.stringRequests);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = ("http://192.168.2.13:8000/api/demand/" + id);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                String responseReceived = response.substring(response.indexOf("["));
                getAnswer(id, responseReceived );

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                responseReceived.setText(error.toString());
            }
        });

        queue.add(stringRequest);
    }

    private class ShowProgressBarTask extends AsyncTask<Void, Integer, Integer> {

        @Override
        protected void onPreExecute(){
            circularProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Integer doInBackground(Void... params){
            for (int i = 0; i < 10; i++){
                try{
                    Thread.sleep(100);
                    publishProgress(i * 10);
                }catch (InterruptedException e){
                    return -1;
                }
            }
            return 100;
        }

        @Override
        protected void onProgressUpdate(Integer... values){
            int progress = values[0];
            circularProgressBar.setProgress(progress);
        }

        @Override
        protected void onPostExecute (Integer result){
            circularProgressBar.setProgress(View.INVISIBLE);
        }
    }

    public void getAnswer(String id, String demand){
        final TextView responseReceived = (TextView) findViewById(R.id.stringRequests);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = ("http://192.168.2.13:8000/api/answerListDemand/" + id);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                String responseReceived = response.substring(response.indexOf("["));

                Intent intent = new Intent(RequestDemandInfoActivity.this , DemandActivity.class);
                Bundle b = new Bundle();
                b.putString("id", id);
                b.putString("demandInfo", demand);
                b.putString("answerInfo", responseReceived);
                intent.putExtra("bundle", b);

                startActivity(intent);
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                responseReceived.setText(error.toString());
            }
        });

        queue.add(stringRequest);
    }

}

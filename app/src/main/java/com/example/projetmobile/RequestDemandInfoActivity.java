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
        String url = ("http://192.168.159.1:8000/api/demand/" + id);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                String responseReceived = response.substring(response.indexOf("["));

                Intent intent = new Intent(RequestDemandInfoActivity.this , RequestDemandAnswersActivity.class);

                Bundle b = new Bundle();
                b.putString("id", id);
                b.putString("demandInfo", responseReceived);
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

}

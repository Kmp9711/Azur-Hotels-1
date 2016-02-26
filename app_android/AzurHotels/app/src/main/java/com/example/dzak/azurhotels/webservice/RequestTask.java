package com.example.dzak.azurhotels.webservice;

// Import java io modules

import android.os.AsyncTask;
import android.util.Log;

import com.example.dzak.azurhotels.MVCPattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;

/**
 * Created by dzak on 19/02/16.
 */
public class RequestTask extends AsyncTask<String, Void, String> {

    public static final String TAG ="Mon_TAG";

    public enum RESULT {OK, ERROR};

    private RESULT response = RESULT.ERROR;
    private String table = "hotels";
    private int action = 0;

    private RequestListener listener;

    public RequestTask(int action, RequestListener listener){
        super();
        this.action = action;
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... urls){
        response = RESULT.ERROR;
        HttpClient httpclient = new DefaultHttpClient();

        for(int i = 0; i < urls.length; i++){
            String url = urls[i];

            try {
                HttpGet httpGet = new HttpGet(url);
                HttpResponse httpresponse=httpclient.execute(httpGet);
                HttpEntity httpentity=httpresponse.getEntity();

                if (httpentity!=null){
                    InputStream inputstream=httpentity.getContent();
                    BufferedReader bufferedreader = new BufferedReader( new InputStreamReader(inputstream));
                    StringBuilder strinbuilder = new StringBuilder();
                    String ligne = bufferedreader.readLine();

                    while (ligne!=null){
                        strinbuilder.append(ligne+"n");
                        ligne=bufferedreader.readLine();
                    }

                    bufferedreader.close();

                    // Switch
                    switch(action){
                        case RequestAction.GET_HOTELS:
                            // Array
                            JSONObject jso = new JSONObject(strinbuilder.toString());
                            JSONArray jsonArray = jso.getJSONArray(table);

                            for (int j = 0; j < jsonArray.length(); j++) {
                                JSONObject obj1 = jsonArray.getJSONObject(j);
                                //JSONArray results = patient.getJSONArray("results");
                                //String indexForPhone = patientProfile.getJSONObject(0).getString("indexForPhone");
                                int id = obj1.getInt("id");
                                String nom = obj1.getString("nom");
                                String tel = obj1.getString("tel");
                                String description = obj1.getString("description");
                                double prix = obj1.getDouble("prix");

                                MVCPattern.model.addHotel(id, nom, tel, description, (float) prix);
                                response = RESULT.OK;

                            }
                        break;
                    }
                }
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

        }
        return "";
    }

    @Override protected void onPostExecute(String result) {
        if (listener != null) {
            listener.whenFinish();
        }
    }
}

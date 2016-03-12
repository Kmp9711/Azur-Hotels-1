package com.example.dzak.azurhotels.webservice;

import android.os.AsyncTask;
import android.util.Log;

import com.example.dzak.azurhotels.FormRequest;
import com.example.dzak.azurhotels.utils.UrlUtils;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dzak on 04/03/16.
 */
public class RequestPost extends AsyncTask<String, Void, String> {

    // Response
    String responseServer;
    private FormRequest request;
    private RequestListener listener;

    public RequestPost(FormRequest request, RequestListener listener){
        this.request = request;
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... urls) {
        for(int i = 0; i < urls.length; i++){
            String url = urls[i];

            // On récupere les paramètres de l'url
            Map<String, List<String>> params = UrlUtils.getQueryParams(url);
            String action = params.get("var").get(0);

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);

            try {

                JSONObject jsonobj = new JSONObject();
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                switch(action){
                    case RequestAction.Post.REQ_REGISTRE:

                        // Exemple
                        for (String key : request.data.keySet()){
                            jsonobj.put(key, request.data.get(key));
                        }

                        nameValuePairs.add(new BasicNameValuePair(action, jsonobj.toString()));

                        break;

                    case RequestAction.Post.REQ_LOGIN:

                        // Exemple
                        for (String key : request.data.keySet()){
                            jsonobj.put(key, request.data.get(key));
                        }

                        nameValuePairs.add(new BasicNameValuePair(action, jsonobj.toString()));

                        break;
                }

                // Si l'objet json n'est pas vide
                if (jsonobj.length() > 0 ) {

                    Log.e(
                            "mainToPost", "mainToPost" + nameValuePairs.toString());

                    // Use UrlEncodedFormEntity to send in proper format which we need
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    // Execute HTTP Post Request
                    HttpResponse response = httpclient.execute(httppost);
                    InputStream inputStream = response.getEntity().getContent();
                    InputStreamToStringExample str = new InputStreamToStringExample();
                    responseServer = str.getStringFromInputStream(inputStream);
                    Log.e("response", "response -----" + responseServer);

                    // Appel la methode "whenFinish"
                    if (listener != null) {
                        listener.whenFinish();
                    }

                } else{
                    Log.i("JSON_POST", "Erreur, l'objet JSON est vide");
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static class InputStreamToStringExample {

        // convert InputStream to String
        private static String getStringFromInputStream(InputStream is) {

            BufferedReader br = null;
            StringBuilder sb = new StringBuilder();

            String line;
            try {

                br = new BufferedReader(new InputStreamReader(is));
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return sb.toString();
        }
    }
}

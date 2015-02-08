package de.risikous.model.connector;

import android.os.AsyncTask;
import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.concurrent.ExecutionException;

public class HTMLReciever{

    private String[] responesString;

    public String[] execute(String url) {
        Log.v("INFO", "Vor aufruf von Task ");
        try {
            this.responesString = new GetDataFromService().execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return this.responesString;
    }

    public String getURL(String url) {

        String[] response = this.execute(url);
        if (response == null || response.length == 0 || response[0] == null || response[0].isEmpty()) {
            return "No Response";
        }
        return response[0];
    }

    public String[] getURLs(String[] url) {
        String[] responses = new String[1];
        if (url.length < 1) {

            responses[1] = "No URLs given!";

        } else {
            responses = this.execute(url[0]);
        }
        return responses;
    }


    private class GetDataFromService extends AsyncTask<String, String, String[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String[] doInBackground(String... params) {

            String[] result;

            if (params.length < 1) {
                result = new String[1];
                result[0] = "ERROR";
                return result;
            } else {
                result = new String[params.length];
                for (int i = 0; i < params.length; i++) {
                    String url = params[i];
                    HttpClient client = new DefaultHttpClient();
                    try {
                        HttpGet request = new HttpGet(url);
                        request.addHeader("Accept", "text/xml");
                        request.addHeader("Content-Type", "application/xml");
                        HttpResponse response = client.execute(request);
                        HttpEntity entity = response.getEntity();
                        result[i] = EntityUtils.toString(entity, HTTP.UTF_8);
                    } catch (Exception ex) {
                    }
                }
                return result;
            }
        }


        @Override
        protected void onPostExecute(String... result) {
            responesString = result;
        }
    }


}
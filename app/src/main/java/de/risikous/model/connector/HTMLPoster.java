package de.risikous.model.connector;


import android.os.AsyncTask;
import android.util.Log;
import de.risikous.model.entitys.HTMLResponseCode;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

public class HTMLPoster{

    private HTMLResponseCode response = new HTMLResponseCode();

    public HTMLResponseCode persistQuestionaire(String url, String xmlData) {
        String[] data = new String[2];
        data[0] = url;
        data[1] = xmlData;
        return this.postData(data);
    }

    public HTMLResponseCode postData(String[] data) {
        try {
            this.response.setResponseString(new PostDataToService().execute(data).get());
        } catch (InterruptedException e) {
            Log.v("INFO", e.getMessage());
        } catch (ExecutionException e) {
            Log.v("INFO", e.getMessage());
        }
        return this.response;
    }

    private class PostDataToService extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            Log.v("INFO", "begin PreExecute");
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String[] params) {
            HTMLResponseCode resp=new HTMLResponseCode();
            String result = "ERROR";
            if (params.length == 2) {
                String url = params[0];
                String postBody = params[1];

                HttpClient client = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(url);
                httppost.addHeader("Content-Type", "application/xml");
                httppost.addHeader("text", "xml");
                try {

                    StringEntity entity = new StringEntity(postBody, "UTF-8");
                    entity.setContentType("application/xml");
                    entity.setContentEncoding("application/xml");
                    httppost.setEntity(entity);

                    HttpResponse response = client.execute(httppost);

                    StatusLine statusLine = response.getStatusLine();
                    int statusCode = statusLine.getStatusCode();
                    resp.setStatusCode(statusCode);
                    Log.v("INFO", String.valueOf(statusCode));

                    HttpEntity entityRes = response.getEntity();
                    InputStream content = entityRes.getContent();

                    InputStreamReader inputstream = new InputStreamReader(content, "UTF-8");
                    BufferedReader reader = new BufferedReader(inputstream);

                    result = reader.readLine();

                    Log.v("INFO", result);
                } catch (Exception e) {
                    Log.v("INFO", e.getMessage());
                    result = e.getMessage();

                } finally {
                    Log.v("INFO", result);
                    return result;
                }
            }
            return result;
        }


        @Override
        protected void onPostExecute(String result) {
        }
    }


}
package de.risikous.model.connector;

import java.net.*;
import java.io.*;
/**
 * Created by Franz on 07.01.2015.
 */
public class HTMLReciever {
    public String getHTML(String url)throws IOException{
        URL oracle = new URL(url);
        BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
        String inputLine;
        String result = "";
        while ((inputLine = in.readLine()) != null)
            result = result + inputLine ;
        in.close();
        return result;
    }
}

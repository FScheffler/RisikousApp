package de.risikous.model.entitys;

/**
 * Created by Franz on 25.01.2015.
 */
public class HTMLResponseCode {
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    int statusCode;

    public String getResponseString() {
        return responseString;
    }

    public void setResponseString(String responseString) {
        this.responseString = responseString;
    }

    String responseString="empty";
}

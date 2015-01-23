package de.risikous.model.xml.parser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Franz on 09.01.2015.
 */
public class OverviewEntryParser {
    public ArrayList<String> getOverviewEntryXMLStrings(String toSplit){
        String trimmedPublications = toSplit.replace("</publication>", "");
        String[] publicationOverview = trimmedPublications.split("<publication>");
        ArrayList<String> result=new ArrayList<String>(Arrays.asList(publicationOverview));
        result.remove(0);
        return result;
    }
    public String parseId(String toParse) {
        String[] withoutStartTag = toParse.split("<id>");
        String[] withoutEndTag = withoutStartTag[1].split("</id>");
        return withoutEndTag[0];
    }
    public Date parseRevisionDate(String toParse) {
        String[] withoutStartTag = toParse.split("<revisionDate>");
        String[] withoutEndTag = withoutStartTag[1].split("</revisionDate>");
        DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
        try{
            Date date = format.parse(withoutEndTag[0]);
            return date;
        }catch(Exception e){
            System.out.println("error while converting parsed xml-date to Date. Occured for :"+toParse);
            return null;
        }
    }
    public String parseStatus(String toParse) {
        String[] withoutStartTag = toParse.split("<status>");
        String[] withoutEndTag = withoutStartTag[1].split("</status>");
        return withoutEndTag[0];
    }
    public String parseTitle(String toParse) {
        String[] withoutStartTag = toParse.split("<title>");
        String[] withoutEndTag = withoutStartTag[1].split("</title>");
        return withoutEndTag[0];
    }
}

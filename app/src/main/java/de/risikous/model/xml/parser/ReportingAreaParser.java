package de.risikous.model.xml.parser;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Franz on 09.01.2015.
 */
public class ReportingAreaParser {
    public ArrayList<String> getReportingAreaXMLStrings(String toSplit){
        String trimmedReportings = toSplit.replace("</reportingArea>", "");
        String[] reportingAreas = trimmedReportings.split("<reportingArea>");
        ArrayList<String> result=new ArrayList<String>(Arrays.asList(reportingAreas));
        result.remove(0);//Damit der XML-Header entfällt
        return result;
    }
    public String parseName(String toParse) {
        String[] withoutStartTag = toParse.split("<name>");
        String[] withoutEndTag = withoutStartTag[1].split("</name>");
        return withoutEndTag[0];
    }
    public String parseShortcut(String toParse) {
        String[] withoutStartTag = toParse.split("<shortcut>");
        String[] withoutEndTag = withoutStartTag[1].split("</shortcut>");
        return withoutEndTag[0];
    }

}

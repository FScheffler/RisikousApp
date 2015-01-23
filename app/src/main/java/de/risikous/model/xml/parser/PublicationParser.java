package de.risikous.model.xml.parser;

/**
 * Created by Franz on 09.01.2015.
 */
public class PublicationParser {
    public String getPublicationXMLString(String toSplit){
        String publicationWithoutEndtag = toSplit.replace("</publication>", "");
        String[] splittedPublication = publicationWithoutEndtag.split("<publication>");
        return splittedPublication[1];//Damit der Header entfällt
    }

    public String parseIncidentReport(String toParse) {
        String[] withoutStartTag = toParse.split("<incidentReport>");
        String[] withoutEndTag = withoutStartTag[1].split("</incidentReport>");
        return withoutEndTag[0];
    }
    public String parseMinRPZofReporter(String toParse) {
        String[] withoutStartTag = toParse.split("<minRPZofReporter>");
        String[] withoutEndTag = withoutStartTag[1].split("</minRPZofReporter>");
        return withoutEndTag[0];
    }
    public String parseAvgRPZofReporter(String toParse) {
        String[] withoutStartTag = toParse.split("<avgRPZofReporter>");
        String[] withoutEndTag = withoutStartTag[1].split("</avgRPZofReporter>");
        return withoutEndTag[0];
    }
    public String parseMaxRPZofReporter(String toParse) {
        String[] withoutStartTag = toParse.split("<maxRPZofReporter>");
        String[] withoutEndTag = withoutStartTag[1].split("</maxRPZofReporter>");
        return withoutEndTag[0];
    }
    public String parseMinRPZofQMB(String toParse) {
        String[] withoutStartTag = toParse.split("<minRPZofQMB>");
        String[] withoutEndTag = withoutStartTag[1].split("</minRPZofQMB>");
        return withoutEndTag[0];
    }
    public String parseAvgRPZofQMB(String toParse) {
        String[] withoutStartTag = toParse.split("<avgRPZofQMB>");
        String[] withoutEndTag = withoutStartTag[1].split("</avgRPZofQMB>");
        return withoutEndTag[0];
    }
    public String parseMaxRPZofQMB(String toParse) {
        String[] withoutStartTag = toParse.split("<maxRPZofQMB>");
        String[] withoutEndTag = withoutStartTag[1].split("</maxRPZofQMB>");
        return withoutEndTag[0];
    }
    public String parseCategory(String toParse) {
        String[] withoutStartTag = toParse.split("<category>");
        String[] withoutEndTag = withoutStartTag[1].split("</category>");
        return withoutEndTag[0];
    }
    public String parseAssignedReports(String toParse) {
        String[] withoutStartTag = toParse.split("<assignedReports>");
        String[] withoutEndTag = withoutStartTag[1].split("</assignedReports>");
        return withoutEndTag[0];
    }
    public String parseAction(String toParse) {
        if(toParse.contains("<action>")) {
            String[] withoutStartTag = toParse.split("<action>");
            String[] withoutEndTag = withoutStartTag[1].split("</action>");
            return withoutEndTag[0];
        }else{
            return "Es wurden keine Angaben gemacht";
        }
    }
    public String parseDifferenceStatement(String toParse) {
        if(toParse.contains("<differenceStatement>")) {
            String[] withoutStartTag = toParse.split("<differenceStatement>");
            String[] withoutEndTag = withoutStartTag[1].split("</differenceStatement>");
            return withoutEndTag[0];
        }else{
            return "Es wurden keine Angaben gemacht";
        }
    }
    public String parseTitle(String toParse) {
        String[] withoutStartTag = toParse.split("<title>");
        String[] withoutEndTag = withoutStartTag[1].split("</title>");
        return withoutEndTag[0];
    }
}

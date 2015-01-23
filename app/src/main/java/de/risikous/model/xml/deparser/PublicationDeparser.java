package de.risikous.model.xml.deparser;

import de.risikous.model.entitys.File;
import de.risikous.model.entitys.Questionaire;
import de.risikous.model.entitys.RiskEstimation;

import java.util.List;

/**
 * Created by Franz on 09.01.2015.
 */
public class PublicationDeparser {
    public String getHeaderXMLHeader(){
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><questionnaire>";
    }
    public String getRequiredFieldsAsXMLString(Questionaire q){
        return getReportingArea(q)+getIncidentDescription(q)+getRiskEstimation(q.getRiskEstimation());
    }
    private String getReportingArea(Questionaire q){
        return "<reportingArea>"+q.getReportingArea()+"</reportingArea>";
    }
    private String getIncidentDescription(Questionaire q){
        return "<incidentDescription>"+q.getIncidentDescription()+"</incidentDescription>";
    }
    private String getRiskEstimation(RiskEstimation r){
        return "<riskEstimation><occurrenceRating>"+r.getDetectionRating()+"</occurrenceRating><detectionRating>"+r.getDetectionRating()+"</detectionRating><significance>"+r.getSignificance()+"</significance></riskEstimation>";
    }
    public String getOptionalFieldsAsXMLString(Questionaire q){
        return getPointOfTime(q)+getLocation(q)+getImmediateMeasure(q)+getConsequences(q)+getOpinionOfReporter(q)+getFiles(q)+getContactInformation(q);
    }
    private String getPointOfTime(Questionaire q){
        String result="";
        if(q.getPointOfTime()!=null) {
            result += "<pointOfTime>";
            if (q.getPointOfTime().getDate() != null)
                result += "<date>" + q.getPointOfTime().getDate() + "</date>";
            if (q.getPointOfTime().getTime() != null)
                result += "<time>" + q.getPointOfTime().getTime() + "</time>";
            result += "</pointOfTime>";
        }
        return result;
    }
    private String getLocation(Questionaire q){
        String result="";
        if(q.getLocation()!=null)
            result+="<location>"+q.getLocation()+"</location>";
        return result;
    }
    private String getImmediateMeasure(Questionaire q){
        String result="";
        if(q.getImmediateMeasure()!=null)
            result+="<immediateMeasure>"+q.getImmediateMeasure()+"</immediateMeasure>";
        return result;
    }
    private String getConsequences(Questionaire q){
        String result="";
        if(q.getConsequences()!=null)
            result+="<consequences>"+q.getImmediateMeasure()+"</consequences>";
        return result;
    }
    private String getOpinionOfReporter(Questionaire q){
        String result="";
        if(q.getOpinionOfReporter()!=null) {
            result += "<opinionOfReporter>";
            if (q.getOpinionOfReporter().getPersonalFactors() != null)
                result += "<personalFactors>" + q.getOpinionOfReporter().getPersonalFactors() + "</personalFactors>";
            if (q.getOpinionOfReporter().getOrganisationalFactors() != null)
                result += "<organisationalFactors>" + q.getOpinionOfReporter().getOrganisationalFactors() + "</organisationalFactors>";
            if (q.getOpinionOfReporter().getAdditionalNotes() != null)
                result += "<additionalNotes>" + q.getOpinionOfReporter().getAdditionalNotes() + "</additionalNotes>";
            result += "</opinionOfReporter>";
        }
        return result;
    }
    private String getFiles(Questionaire q){
        String result="";
        if(q.getFiles()!=null) {
            result += "<files>";
            List<File> files=q.getFiles();
            for(int i=0;i<files.size();i++)
                result+="<file name=\""+files.get(i).getFileName()+"\" encoding=\""+files.get(i).getFileEncoding()+"\">"+files.get(i).getFileContent()+"</file>";
            result += "</files>";
        }
        return result;
    }
    private String getContactInformation(Questionaire q){
        String result="";
        if(q.getContactInformation()!=null)
            result+="<contactInformation>"+q.getContactInformation()+"</contactInformation>";
        return result;
    }
    public String getXMLEnd(){
        return "</questionnaire>";
    }
}

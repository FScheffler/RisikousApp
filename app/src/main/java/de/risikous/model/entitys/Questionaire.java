package de.risikous.model.entitys;

import java.util.List;

/**
 * Created by Franz on 08.01.2015.
 */
public class Questionaire {
    String reportingArea;
    String incidentDescription;// (maximal 1000 Zeichen)
    RiskEstimation riskEstimation;
    PointOfTime pointOfTime=null;//optional
    String location=null;// (maximal 50 Zeichen), optional
    String immediateMeasure=null;//(maximal 1000 Zeichen), optional
    String consequences=null;//(maximal 1000 Zeichen), optional
    OpinionOfReporter opinionOfReporter=null;//optional
    List<File> files=null;//optional
    String contactInformation=null;// (maximal 1000 Zeichen), optional

    public String getReportingArea() {
        return reportingArea;
    }
    public void setReportingArea(String reportingArea) {
        this.reportingArea = reportingArea;
    }
    public String getIncidentDescription() {
        return incidentDescription;
    }
    public void setIncidentDescription(String incidentDescription) {
        this.incidentDescription = incidentDescription;
    }
    public RiskEstimation getRiskEstimation() {
        return riskEstimation;
    }
    public void setRiskEstimation(RiskEstimation riskEstimation) {
        this.riskEstimation = riskEstimation;
    }
    public PointOfTime getPointOfTime() {
        return pointOfTime;
    }
    public void setPointOfTime(PointOfTime pointOfTime) {
        this.pointOfTime = pointOfTime;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getImmediateMeasure() {
        return immediateMeasure;
    }
    public void setImmediateMeasure(String immediateMeasure) {
        this.immediateMeasure = immediateMeasure;
    }
    public String getConsequences() {
        return consequences;
    }
    public void setConsequences(String consequences) {
        this.consequences = consequences;
    }
    public OpinionOfReporter getOpinionOfReporter() {
        return opinionOfReporter;
    }
    public void setOpinionOfReporter(OpinionOfReporter opinionOfReporter) {
        this.opinionOfReporter = opinionOfReporter;
    }
    public String getContactInformation() {
        return contactInformation;
    }
    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }
    public List<File> getFiles() {
        return files;
    }
    public void setFiles(List<File> file) {
        this.files = file;
    }

}

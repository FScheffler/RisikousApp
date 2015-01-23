package de.risikous.model.entitys;

import java.util.Date;

/**
 * Created by Franz on 07.01.2015.
 */
public class Publication {
    String title;
    String incidentReport;
    String minRPZofReporter;
    String avgRPZofReporter;
    String maxRPZofReporter;
    String minRPZofQMB;
    String avgRPZofQMB;
    String maxRPZofQMB;
    String differenceStatement; //optional
    String category;
    String action;
    String assignedReports;
    public String getAssignedReports() {
        return assignedReports;
    }
    public void setAssignedReports(String assignedReports) {
        this.assignedReports = assignedReports;
    }
    public void setIncidentReport(String incidentReport) {
        this.incidentReport = incidentReport;
    }
    public String getIncidentReport() {
        return incidentReport;
    }
    public String getCategory() {
        return category;
    }
    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getDifferenceStatement() {
        return differenceStatement;
    }
    public void setDifferenceStatement(String differenceStatement) {
        this.differenceStatement = differenceStatement;
    }
    public String getAvgRPZofQMB() {
        return avgRPZofQMB;
    }
    public void setAvgRPZofQMB(String avgRPZofQMB) {
        this.avgRPZofQMB = avgRPZofQMB;
    }
    public String getMaxRPZofQMB() {
        return maxRPZofQMB;
    }
    public void setMaxRPZofQMB(String maxRPZofQMB) {
        this.maxRPZofQMB = maxRPZofQMB;
    }
    public String getMaxRPZofReporter() {
        return maxRPZofReporter;
    }
    public void setMaxRPZofReporter(String maxRPZofReporter) {
        this.maxRPZofReporter = maxRPZofReporter;
    }
    public String getMinRPZofQMB() {
        return minRPZofQMB;
    }
    public void setMinRPZofQMB(String minRPZofQMB) {
        this.minRPZofQMB = minRPZofQMB;
    }
    public String getMinRPZofReporter() {
        return minRPZofReporter;
    }
    public void setMinRPZofReporter(String minRPZofReporter) {
        this.minRPZofReporter = minRPZofReporter;
    }
    public String getAvgRPZofReporter() {
        return avgRPZofReporter;
    }
    public void setAvgRPZofReporter(String avgRPZofReporter) {
        this.avgRPZofReporter = avgRPZofReporter;
    }
    public void setTitle (String title){
        this.title=title;
    }
    public String getTitle (){
        return title;
    }
}

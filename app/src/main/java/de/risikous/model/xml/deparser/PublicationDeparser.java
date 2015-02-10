package de.risikous.model.xml.deparser;

import android.util.Base64;
import android.util.Log;
import de.risikous.model.entitys.File;
import de.risikous.model.entitys.Questionaire;
import de.risikous.model.entitys.RiskEstimation;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Franz on 09.01.2015.
 */
public class PublicationDeparser {
    public String getHeaderXMLHeader() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><questionnaire>";
    }

    public String getRequiredFieldsAsXMLString(Questionaire q) {
        return getReportingArea(q) + getIncidentDescription(q) + getRiskEstimation(q.getRiskEstimation());
    }

    private String getReportingArea(Questionaire q) {
        return "<reportingArea>" + q.getReportingArea() + "</reportingArea>";
    }

    private String getIncidentDescription(Questionaire q) {
        return "<incidentDescription>" + q.getIncidentDescription() + "</incidentDescription>";
    }

    private String getRiskEstimation(RiskEstimation r) {
        return "<riskEstimation><occurrenceRating>" + r.getDetectionRating() + "</occurrenceRating><detectionRating>" + r.getDetectionRating() + "</detectionRating><significance>" + r.getSignificance() + "</significance></riskEstimation>";
    }

    public String getOptionalFieldsAsXMLString(Questionaire q) {
        return getPointOfTime(q) + getLocation(q) + getImmediateMeasure(q) + getConsequences(q) + getOpinionOfReporter(q) + getFiles(q) + getContactInformation(q);
    }

    private String getPointOfTime(Questionaire q) {
        String result = "";
        if (q.getPointOfTime() != null) {
            result += "<pointOfTime>";
            if (q.getPointOfTime().getDate() != null)
                result += "<date>" + q.getPointOfTime().getDate() + "</date>";
            if (q.getPointOfTime().getTime() != null)
                result += "<time>" + q.getPointOfTime().getTime() + "</time>";
            result += "</pointOfTime>";
        }
        return result;
    }

    private String getLocation(Questionaire q) {
        String result = "";
        if (q.getLocation() != null)
            result += "<location>" + q.getLocation() + "</location>";
        return result;
    }

    private String getImmediateMeasure(Questionaire q) {
        String result = "";
        if (q.getImmediateMeasure() != null)
            result += "<immediateMeasure>" + q.getImmediateMeasure() + "</immediateMeasure>";
        return result;
    }

    private String getConsequences(Questionaire q) {
        String result = "";
        if (q.getConsequences() != null)
            result += "<consequences>" + q.getConsequences() + "</consequences>";
        return result;
    }

    private String getOpinionOfReporter(Questionaire q) {
        String result = "";
        if (q.getOpinionOfReporter() != null) {
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

    private String getFiles(Questionaire q) {
        String result = "";
        try {

            if (q.getFiles() != null) {
                result += "<files>";
                List<File> files = q.getFiles();
                for (int i = 0; i < files.size(); i++) {
                    java.io.File file = new java.io.File(files.get(i).getFileContent());
                    byte[] bytes = loadFile(file);
                    String encodedString = Base64.encodeToString(bytes, Base64.DEFAULT);
                    result += "<file name=\"" + files.get(i).getFileName() +
                            "\" encoding=\"" + files.get(i).getFileEncoding() + "\">" +
                            "" + encodedString + "</file>";
                    result += "</files>";
                }
            }
        } catch (Exception e) {
            Log.v("ERROR", e.getMessage());
        } finally {
            return result;
        }
    }

    private String getContactInformation(Questionaire q) {
        String result = "";
        if (q.getContactInformation() != null)
            result += "<contactInformation>" + q.getContactInformation() + "</contactInformation>";
        return result;
    }

    public String getXMLEnd() {
        return "</questionnaire>";
    }

    private static byte[] loadFile(java.io.File file) throws IOException {
        InputStream is = new FileInputStream(file);

        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
        byte[] bytes = new byte[(int) length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }

        is.close();
        return bytes;
    }
}

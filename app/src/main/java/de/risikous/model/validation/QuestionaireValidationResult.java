package de.risikous.model.validation;

import java.util.ArrayList;

/**
 * Created by Franz on 09.01.2015.
 */
public class QuestionaireValidationResult {
    boolean errorsOccured=false;
    boolean reportingAreaError=false;
    String reportingAreaErrorMessage;
    boolean occurrenceRatingError=false;
    String occurrenceRatingErrorMessage;
    boolean detectionRatingError=false;
    String detectionRatingErrorMessage;
    boolean significanceError=false;
    String significanceErrorMessage;
    boolean incidentDescriptionError=false;
    String incidentDescriptionErrorMessage;// (maximal 1000 Zeichen)
    boolean dateError=false;
    String dateErrorMessage=null;//	Datum, Hinweis: dieser Tag ist optional, dd.mm.yyyy
    boolean timeError=false;
    String timeErrorMessage=null;//	hh:mm, Uhrzeit, Hinweis: dieser Tag ist optional
    boolean locationError=false;
    String locationErrorMessage=null;// (maximal 50 Zeichen), optional
    boolean immediateMeasurError=false;
    String immediateMeasureErrorMessage=null;//(maximal 1000 Zeichen), optional
    boolean consequencesError=false;
    String consequencesErrorMessage=null;//(maximal 1000 Zeichen), optional
    boolean personalFactorsError=false;
    String personalFactorsErrorMessage=null;	// (maximal 1000 Zeichen), optional
    boolean organisationalFactorsError=false;
    String organisationalFactorsErrorMessage=null;// (maximal 1000 Zeichen), optional
    boolean additionalNotesError=false;
    String additionalNotesErrorMessage=null;// (maximal 1000 Zeichen), optional
    boolean filesError=false;
    String fileErrorMessage=null;
    boolean contactInformationError=false;
    String contactInformationErrorMessage=null;// (maximal 1000 Zeichen), optional


    public String getSignificanceErrorMessage() {
        return significanceErrorMessage;
    }

    public void setSignificanceErrorMessage(String significanceErrorMessage) {
        this.significanceErrorMessage = significanceErrorMessage;
    }

    public boolean isOccurrenceRatingError() {
        return occurrenceRatingError;
    }

    public void setOccurrenceRatingError(boolean occurrenceRatingError) {
        this.occurrenceRatingError = occurrenceRatingError;
    }

    public String getOccurrenceRatingErrorMessage() {
        return occurrenceRatingErrorMessage;
    }

    public void setOccurrenceRatingErrorMessage(String occurrenceRatingErrorMessage) {
        this.occurrenceRatingErrorMessage = occurrenceRatingErrorMessage;
    }

    public boolean isDetectionRatingError() {
        return detectionRatingError;
    }

    public void setDetectionRatingError(boolean detectionRatingError) {
        this.detectionRatingError = detectionRatingError;
    }

    public String getDetectionRatingErrorMessage() {
        return detectionRatingErrorMessage;
    }

    public void setDetectionRatingErrorMessage(String detectionRatingErrorMessage) {
        this.detectionRatingErrorMessage = detectionRatingErrorMessage;
    }

    public boolean isSignificanceError() {
        return significanceError;
    }

    public void setSignificanceError(boolean significanceError) {
        this.significanceError = significanceError;
    }
    public String getReportingAreaErrorMessage() {
        return reportingAreaErrorMessage;
    }

    public void setReportingAreaErrorMessage(String reportingAreaErrorMessage) {
        this.reportingAreaErrorMessage = reportingAreaErrorMessage;
    }

    public boolean isErrorsOccured() {
        return errorsOccured;
    }

    public void setErrorsOccured(boolean errorsOccured) {
        this.errorsOccured = errorsOccured;
    }

    public boolean isReportingAreaError() {
        return reportingAreaError;
    }

    public void setReportingAreaError(boolean reportingAreaError) {
        this.reportingAreaError = reportingAreaError;
    }

    public boolean hasErrors() {
        return errorsOccured;
    }

    public void setHasErrors(boolean hasErrors) {
        this.errorsOccured = hasErrors;
    }

    public boolean isIncidentDescriptionError() {
        return incidentDescriptionError;
    }

    public void setIncidentDescriptionError(boolean incidentDescriptionError) {
        this.incidentDescriptionError = incidentDescriptionError;
    }

    public String getIncidentDescriptionErrorMessage() {
        return incidentDescriptionErrorMessage;
    }

    public void setIncidentDescriptionErrorMessage(String incidentDescriptionErrorMessage) {
        this.incidentDescriptionErrorMessage = incidentDescriptionErrorMessage;
    }

    public boolean isDateError() {
        return dateError;
    }

    public void setDateError(boolean dateError) {
        this.dateError = dateError;
    }

    public String getDateErrorMessage() {
        return dateErrorMessage;
    }

    public void setDateErrorMessage(String dateErrorMessage) {
        this.dateErrorMessage = dateErrorMessage;
    }

    public boolean isTimeError() {
        return timeError;
    }

    public void setTimeError(boolean timeError) {
        this.timeError = timeError;
    }

    public String getTimeErrorMessage() {
        return timeErrorMessage;
    }

    public void setTimeErrorMessage(String timeErrorMessage) {
        this.timeErrorMessage = timeErrorMessage;
    }

    public boolean isLocationError() {
        return locationError;
    }

    public void setLocationError(boolean locationError) {
        this.locationError = locationError;
    }

    public String getLocationErrorMessage() {
        return locationErrorMessage;
    }

    public void setLocationErrorMessage(String locationErrorMessage) {
        this.locationErrorMessage = locationErrorMessage;
    }

    public boolean isImmediateMeasurError() {
        return immediateMeasurError;
    }

    public void setImmediateMeasurError(boolean immediateMeasurError) {
        this.immediateMeasurError = immediateMeasurError;
    }

    public String getImmediateMeasureErrorMessage() {
        return immediateMeasureErrorMessage;
    }

    public void setImmediateMeasureErrorMessage(String immediateMeasureErrorMessage) {
        this.immediateMeasureErrorMessage = immediateMeasureErrorMessage;
    }

    public boolean isConsequencesError() {
        return consequencesError;
    }

    public void setConsequencesError(boolean consequencesError) {
        this.consequencesError = consequencesError;
    }

    public String getConsequencesErrorMessage() {
        return consequencesErrorMessage;
    }

    public void setConsequencesErrorMessage(String consequencesErrorMessage) {
        this.consequencesErrorMessage = consequencesErrorMessage;
    }

    public boolean isPersonalFactorsError() {
        return personalFactorsError;
    }

    public void setPersonalFactorsError(boolean personalFactorsError) {
        this.personalFactorsError = personalFactorsError;
    }

    public String getPersonalFactorsErrorMessage() {
        return personalFactorsErrorMessage;
    }

    public void setPersonalFactorsErrorMessage(String personalFactorsErrorMessage) {
        this.personalFactorsErrorMessage = personalFactorsErrorMessage;
    }

    public boolean isOrganisationalFactorsError() {
        return organisationalFactorsError;
    }

    public void setOrganisationalFactorsError(boolean organisationalFactorsError) {
        this.organisationalFactorsError = organisationalFactorsError;
    }

    public String getOrganisationalFactorsErrorMessage() {
        return organisationalFactorsErrorMessage;
    }

    public void setOrganisationalFactorsErrorMessage(String organisationalFactorsErrorMessage) {
        this.organisationalFactorsErrorMessage = organisationalFactorsErrorMessage;
    }

    public boolean isAdditionalNotesError() {
        return additionalNotesError;
    }

    public void setAdditionalNotesError(boolean additionalNotesError) {
        this.additionalNotesError = additionalNotesError;
    }

    public String getAdditionalNotesErrorMessage() {
        return additionalNotesErrorMessage;
    }

    public void setAdditionalNotesErrorMessage(String additionalNotesErrorMessage) {
        this.additionalNotesErrorMessage = additionalNotesErrorMessage;
    }

    public boolean isFilesError() {
        return filesError;
    }

    public void setFilesError(boolean filesError) {
        this.filesError = filesError;
    }

    public String getFileErrorMessage() {
        return fileErrorMessage;
    }

    public void setFileErrorMessage(String fileMessage) {
        this.fileErrorMessage = fileMessage;
    }

    public boolean isContactInformationError() {
        return contactInformationError;
    }

    public void setContactInformationError(boolean contactInformationError) {
        this.contactInformationError = contactInformationError;
    }

    public String getContactInformationErrorMessage() {
        return contactInformationErrorMessage;
    }

    public void setContactInformationErrorMessage(String contactInformationErrorMessage) {
        this.contactInformationErrorMessage = contactInformationErrorMessage;
    }

    public ArrayList<String> getAllErrorMessages(){
        ArrayList<String>result= new ArrayList<String>();
        if(this.isIncidentDescriptionError())
            result.add(this.getIncidentDescriptionErrorMessage());
        if(this.isDateError())
            result.add(this.getDateErrorMessage());
        if(this.isTimeError())
            result.add(this.getTimeErrorMessage());
        if(this.isLocationError())
            result.add(this.getLocationErrorMessage());
        if(this.isImmediateMeasurError())
            result.add(this.getImmediateMeasureErrorMessage());
        if(this.isConsequencesError())
            result.add(this.getConsequencesErrorMessage());
        if(this.isPersonalFactorsError())
            result.add(this.getPersonalFactorsErrorMessage());
        if(this.isOrganisationalFactorsError())
            result.add(this.getOrganisationalFactorsErrorMessage());
        if(this.isAdditionalNotesError())
            result.add(this.getAdditionalNotesErrorMessage());
        if(this.isFilesError())
            result.add(this.getFileErrorMessage());
        if(this.isContactInformationError())
            result.add(this.getContactInformationErrorMessage());
        return result;
    }

}

package de.risikous.model.validation;

import de.risikous.model.entitys.File;
import de.risikous.model.entitys.OpinionOfReporter;
import de.risikous.model.entitys.PointOfTime;
import de.risikous.model.entitys.Questionaire;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Franz on 09.01.2015.
 */
public class QuestionaireValidation {
    QuestionaireValidationResult errors=new QuestionaireValidationResult();
    Questionaire q;
    QuestionaireValidationRules r;
    char invalideCharacters[];

    public QuestionaireValidationResult validate(Questionaire q, QuestionaireValidationRules rules){
        this.q=q;
        this.r=rules;
        invalideCharacters=r.getInvalideCharacters();
        validateRequiredFields();
        validateOptionalFields();
        setHasErrors();
        return errors;
    }
    private void validateRequiredFields(){
        validateReportingArea();
        validateRiskEstimations();
        validateIncidentDescription();
    }

    private void validateOptionalFields(){
        validatePointOfTime();
        validateLocation();
        validateImmediateMeasure();
        validateConsequences();
        validateOpinionOfReporter();
        validateContactInformation();
        validateFiles();
    }

    private void validateFiles() {
        if(q.getFiles() != null && !q.getFiles().isEmpty()) {
            List<File> files = q.getFiles();
            for (File currentFile : files) {
                try {
                    java.io.File file = new java.io.File(currentFile.getFileContent());
                    if (!file.exists()) {
                        this.errors.setFilesError(true);
                        this.errors.setFileErrorMessage("Datei existiert nicht!");
                    }else{
                        double bytes = file.length();
                        double kilo = (bytes/1024);
                        double mega = (kilo/1024);
                        if(mega > 5){
                            this.errors.setFilesError(true);
                            this.errors.setFileErrorMessage("Datei ist zu groß!");
                        }
                    }
                } catch (Exception e) {
                    this.errors.setFilesError(true);
                    this.errors.setFileErrorMessage(e.getMessage());
                }
            }
        }
    }

    private void validateReportingArea() {
        if(q.getReportingArea().length()==0) {
            errors.setReportingAreaError(true);
            errors.setReportingAreaErrorMessage("Dieses Pflichtfeld fehlt");
        }
    }
    private void validateRiskEstimations(){
        validateOccurrenceRating();
        ValidateDetectionRating();
        validateSignificance();
    }

    private void validateOccurrenceRating() {
        if(q.getRiskEstimation().getOccurrenceRating()!=null) {
            if(hasInvalideOccurenceValues(q.getRiskEstimation().getOccurrenceRating())){
                errors.setOccurrenceRatingError(true);
                errors.setOccurrenceRatingErrorMessage("Dieses Pflichtfeld fehlt");
            }
        }else{
            errors.setOccurrenceRatingError(true);
            errors.setOccurrenceRatingErrorMessage("Dieses Pflichtfeld fehlt");
        }
    }
    private void ValidateDetectionRating() {
        if(q.getRiskEstimation().getDetectionRating()!=null) {
            if(hasInvalideDetectionValues(q.getRiskEstimation().getDetectionRating())){
                errors.setDetectionRatingError(true);
                errors.setDetectionRatingErrorMessage("Dieses Pflichtfeld fehlt");
            }
        }else{
            errors.setDetectionRatingError(true);
            errors.setDetectionRatingErrorMessage("Dieses Pflichtfeld fehlt");
        }
    }
    private void validateSignificance() {
        if(q.getRiskEstimation().getSignificance()!=null) {
            if(hasInvalideSignificanceValues(q.getRiskEstimation().getSignificance())){
                errors.setSignificanceError(true);
                errors.setSignificanceErrorMessage("Dieses Pflichtfeld fehlt");
            }
        }else{
            errors.setSignificanceError(true);
            errors.setSignificanceErrorMessage("Dieses Pflichtfeld fehlt");
        }
    }


    private void validateIncidentDescription(){
        if(q.getIncidentDescription().length()!=0) {
            String toValidate = q.getIncidentDescription();
            if (hasInvalideCharacters(toValidate)) {
                errors.setIncidentDescriptionError(true);
                errors.setIncidentDescriptionErrorMessage("Die Zeichen '<','>' und ';' sind nicht erlaubt");
            }
            if (toValidate.length() > r.getIncidentDescriptionLength()) {
                errors.setIncidentDescriptionError(true);
                errors.setIncidentDescriptionErrorMessage("Sie haben die maximale Zeichenlänge von" + r.getIncidentDescriptionLength() + " überschritten");
            }
        }else{
            errors.setIncidentDescriptionError(true);
            errors.setIncidentDescriptionErrorMessage("Dieses Pflichtfeld fehlt");
        }
    }
    private void validatePointOfTime(){
        if(q.getPointOfTime()!=null) {
            PointOfTime p = q.getPointOfTime();
            validateDate(p);
            validateTime(p);
        }
    }
    private void validateDate(PointOfTime p){
        if(p.getDate()!=null) {
            String toValidate = p.getDate();
            if (!toValidate.matches(r.getPointOfTimeDateFormat())) {
                errors.setDateError(true);
                errors.setDateErrorMessage("Das von Ihnen eingetragene Format für das Datum war unzulässig");
            }
        }
    }
    private void validateTime(PointOfTime p){
        if(p.getTime()!=null) {
            String toValidate = p.getTime();
            if (!toValidate.matches(r.getPointOfTimeTimeFormat())) {
                errors.setTimeError(true);
                errors.setTimeErrorMessage("Das von Ihnen eingetragene Format für die Uhrzeit war unzulässig");
            }
        }
    }
    private void validateLocation(){
        if(q.getLocation().length()!=0) {
            String toValidate = q.getLocation();
            if (hasInvalideCharacters(toValidate)) {
                errors.setLocationError(true);
                errors.setLocationErrorMessage("Die Zeichen '<','>' und ';' sind nicht erlaubt");
            }
            if (toValidate.length() > r.getLocationLength()) {
                errors.setLocationError(true);
                errors.setLocationErrorMessage("Sie haben die maximale Zeichenlänge von" + r.getLocationLength() + " überschritten");
            }
        }
    }
    private void validateImmediateMeasure(){
        if(q.getImmediateMeasure().length()!=0) {
            String toValidate = q.getImmediateMeasure();
            if (hasInvalideCharacters(toValidate)) {
                errors.setImmediateMeasurError(true);
                errors.setImmediateMeasureErrorMessage("Die Zeichen '<','>' und ';' sind nicht erlaubt");
            }
            if (toValidate.length() > r.getLocationLength()) {
                errors.setImmediateMeasurError(true);
                errors.setImmediateMeasureErrorMessage("Sie haben die maximale Zeichenlänge von" + r.getImmediateMeasureLength() + " überschritten");
            }
        }
    }
    private void validateConsequences(){
        if(q.getConsequences().length()!=0) {
            String toValidate = q.getConsequences();
            if (hasInvalideCharacters(toValidate)) {
                errors.setConsequencesError(true);
                errors.setConsequencesErrorMessage("Die Zeichen '<','>' und ';' sind nicht erlaubt");
            }
            if (toValidate.length() > r.getLocationLength()) {
                errors.setConsequencesError(true);
                errors.setConsequencesErrorMessage("Sie haben die maximale Zeichenlänge von" + r.getConsequencesLength() + " überschritten");
            }
        }
    }
    private void validateOpinionOfReporter(){
        if(q.getOpinionOfReporter()!=null) {
            OpinionOfReporter op = q.getOpinionOfReporter();
            validatePersonalFactors(op);
            validateOrganisationalFactors(op);
            validateAdditionalNotes(op);
        }
    }
    private void validatePersonalFactors(OpinionOfReporter op){
        if(op.getPersonalFactors().length()!=0) {
            String toValidate = op.getPersonalFactors();
            if (hasInvalideCharacters(toValidate)) {
                errors.setPersonalFactorsError(true);
                errors.setPersonalFactorsErrorMessage("Die Zeichen '<','>' und ';' sind nicht erlaubt");
            }
            if (toValidate.length() > r.getPersonalFactorsLength()) {
                errors.setPersonalFactorsError(true);
                errors.setPersonalFactorsErrorMessage("Sie haben die maximale Zeichenlänge von" + r.getPersonalFactorsLength() + " überschritten");
            }
        }
    }
    private void validateOrganisationalFactors(OpinionOfReporter op){
        if(op.getOrganisationalFactors().length()!=0) {
            String toValidate = op.getOrganisationalFactors();
            if (hasInvalideCharacters(toValidate)) {
                errors.setOrganisationalFactorsError(true);
                errors.setOrganisationalFactorsErrorMessage("Die Zeichen '<','>' und ';' sind nicht erlaubt");
            }
            if (toValidate.length() > r.getOrganisationalFactorsLength()) {
                errors.setOrganisationalFactorsError(true);
                errors.setOrganisationalFactorsErrorMessage("Sie haben die maximale Zeichenlänge von" + r.getOrganisationalFactorsLength() + " überschritten");
            }
        }
    }
    private void validateAdditionalNotes(OpinionOfReporter op){
        if(op.getAdditionalNotes().length()!=0) {
            String toValidate = op.getAdditionalNotes();
            if (hasInvalideCharacters(toValidate)) {
                errors.setAdditionalNotesError(true);
                errors.setAdditionalNotesErrorMessage("Die Zeichen '<','>' und ';' sind nicht erlaubt");
            }
            if (toValidate.length() > r.getAdditionalNotesLength()) {
                errors.setAdditionalNotesError(true);
                errors.setAdditionalNotesErrorMessage("Sie haben die maximale Zeichenlänge von" + r.getAdditionalNotesLength() + " überschritten");
            }
        }
    }
    private void validateContactInformation(){
        if(q.getContactInformation().length()!=0) {
            String toValidate = q.getContactInformation();
            if (hasInvalideCharacters(toValidate)) {
                errors.setContactInformationError(true);
                errors.setContactInformationErrorMessage("Die Zeichen '<','>' und ';' sind nicht erlaubt");
            }
            if (toValidate.length() > r.getContactInformationLength()) {
                errors.setContactInformationError(true);
                errors.setContactInformationErrorMessage("Sie haben die maximale Zeichenlänge von" + r.getContactInformationLength() + " überschritten");
            }
        }
    }
    private boolean hasInvalideOccurenceValues(String toValidate) {
        String[] valideValues=r.getValidOccurrenceRatingValues();
        for(int i =0; i<valideValues.length;i++)
            if(toValidate.contains(String.valueOf(valideValues[i])))
                return false;
        return true;
    }
    private boolean hasInvalideSignificanceValues(String toValidate) {
        String[] valideValues=r.getValidSignificanceValues();
        for(int i =0; i<valideValues.length;i++)
            if(toValidate.contains(String.valueOf(valideValues[i])))
                return false;
        return true;
    }
    private boolean hasInvalideDetectionValues(String toValidate) {
        String[] valideValues=r.getValidDetectionRatingValues();
        for(int i =0; i<valideValues.length;i++)
            if(toValidate.equals(String.valueOf(valideValues[i])))
                return false;
        return true;
    }
    private boolean hasInvalideCharacters(String toValidate){
        char[] invalideCharacters=r.getInvalideCharacters();
        for(int i =0; i<invalideCharacters.length;i++)
            if(toValidate.contains(String.valueOf(invalideCharacters[i])))
                return true;
        return false;
    }

    private void setHasErrors(){
        if(     errors.isReportingAreaError()||errors.isSignificanceError()||errors.isOccurrenceRatingError()||errors.isDetectionRatingError()||
                errors.immediateMeasurError||errors.isIncidentDescriptionError()||errors.isDateError()
                ||errors.isTimeError()||errors.isLocationError()||errors.isImmediateMeasurError()
                ||errors.isConsequencesError()||errors.isPersonalFactorsError()||errors.isOrganisationalFactorsError()
                ||errors.isAdditionalNotesError()||errors.isFilesError()||errors.isContactInformationError())
            errors.setHasErrors(true);
    }

}

package de.risikous.model.validation;

import de.risikous.model.entitys.OpinionOfReporter;
import de.risikous.model.entitys.PointOfTime;
import de.risikous.model.entitys.Questionaire;

/**
 * Created by Franz on 09.01.2015.
 */
public class QuestionaireValidation {
    char[] invalideCharacters={'<','>',';'};
    ValidationResult errors=new ValidationResult();
    Questionaire q=new Questionaire();
    QuestionaireValidationRules r=new QuestionaireValidationRules ();

    public ValidationResult validate(Questionaire q, QuestionaireValidationRules rules){
            this.q=q;
            this.r=rules;
            validateRequiredFields();
            validateOptionalFields();
            setHasErrors();
        return errors;
    }
    private void validateRequiredFields(){
        validateIncidentDescription();
    }
    private void validateOptionalFields(){
        validatePointOfTime();
        validateLocation();
        validateImmediateMeasure();
        validateConsequences();
        validateOpinionOfReporter();
        validateContactInformation();
    }
    private void validateIncidentDescription(){
        String toValidate=q.getIncidentDescription();
        if(hasInvalideCharacters(toValidate)){
            errors.setIncidentDescriptionError(true);
            errors.setIncidentDescriptionErrorMessage("Die Zeichen '<','>' und ';' sind nicht erlaubt");
        }
        if(toValidate.length()>r.getIncidentDescriptionLength()){
            errors.setIncidentDescriptionError(true);
            errors.setIncidentDescriptionErrorMessage("Sie haben die maximale Zeichenlänge von"+r.getIncidentDescriptionLength()+" überschritten");
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
        if(q.getLocation()!=null) {
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
        if(q.getImmediateMeasure()!=null) {
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
        if(q.getConsequences()!=null) {
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
        if(op.getPersonalFactors()!=null) {
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
        if(op.getOrganisationalFactors()!=null) {
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
        if(op.getAdditionalNotes()!=null) {
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
        if(q.getContactInformation()!=null) {
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
    private boolean hasInvalideCharacters(String toValidate){
        for(int i =0; i<invalideCharacters.length;i++)
            if(toValidate.contains(String.valueOf(invalideCharacters[i])))
                return true;
        return false;
    }
    private void setHasErrors(){
        if(errors.immediateMeasurError||errors.isIncidentDescriptionError()||errors.isDateError()
                ||errors.isTimeError()||errors.isLocationError()||errors.isImmediateMeasurError()
                ||errors.isConsequencesError()||errors.isPersonalFactorsError()||errors.isOrganisationalFactorsError()
                ||errors.isAdditionalNotesError()||errors.isFilesError()||errors.isContactInformationError())
                errors.setHasErrors(true);
    }
}

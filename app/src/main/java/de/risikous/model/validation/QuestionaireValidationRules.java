package de.risikous.model.validation;

/**
 * Created by Franz on 09.01.2015.
 */
public class QuestionaireValidationRules {
    int incidentDescriptionLength=1000;
    String pointOfTimeDateFormat="([0-9]{2}).([0-9]{2}).([0-9]{4})";
    String pointOfTimeTimeFormat="([01]?[0-9]|2[0-3]):[0-5][0-9]";
    int locationLength=50;
    int immediateMeasureLength=1000;
    int consequencesLength=1000;
    int personalFactorsLength=1000;
    int organisationalFactorsLength=1000;
    int additionalNotesLength=1000;
    int contactInformationLength=1000;

    public int getIncidentDescriptionLength() {
        return incidentDescriptionLength;
    }

    public void setIncidentDescriptionLength(int incidentDescriptionLength) {
        this.incidentDescriptionLength = incidentDescriptionLength;
    }

    public String getPointOfTimeDateFormat() {
        return pointOfTimeDateFormat;
    }

    public void setPointOfTimeDateFormat(String pointOfTimeDateFormat) {
        this.pointOfTimeDateFormat = pointOfTimeDateFormat;
    }

    public String getPointOfTimeTimeFormat() {
        return pointOfTimeTimeFormat;
    }

    public void setPointOfTimeTimeFormat(String pointOfTimeTimeFormat) {
        this.pointOfTimeTimeFormat = pointOfTimeTimeFormat;
    }

    public int getLocationLength() {
        return locationLength;
    }

    public void setLocationLength(int locationLength) {
        this.locationLength = locationLength;
    }

    public int getImmediateMeasureLength() {
        return immediateMeasureLength;
    }

    public void setImmediateMeasureLength(int immediateMeasureLength) {
        this.immediateMeasureLength = immediateMeasureLength;
    }

    public int getConsequencesLength() {
        return consequencesLength;
    }

    public void setConsequencesLength(int consequencesLength) {
        this.consequencesLength = consequencesLength;
    }

    public int getPersonalFactorsLength() {
        return personalFactorsLength;
    }

    public void setPersonalFactorsLength(int personalFactorsLength) {
        this.personalFactorsLength = personalFactorsLength;
    }

    public int getOrganisationalFactorsLength() {
        return organisationalFactorsLength;
    }

    public void setOrganisationalFactorsLength(int organisationalFactorsLength) {
        this.organisationalFactorsLength = organisationalFactorsLength;
    }

    public int getAdditionalNotesLength() {
        return additionalNotesLength;
    }

    public void setAdditionalNotesLength(int additionalNotesLength) {
        this.additionalNotesLength = additionalNotesLength;
    }

    public int getContactInformationLength() {
        return contactInformationLength;
    }

    public void setContactInformationLength(int contactInformationLength) {
        this.contactInformationLength = contactInformationLength;
    }


}

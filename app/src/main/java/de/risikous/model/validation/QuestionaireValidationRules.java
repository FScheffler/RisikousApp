package de.risikous.model.validation;

/**
 * Created by Franz on 09.01.2015.
 */
public class QuestionaireValidationRules {
    int incidentDescriptionLength=1000;
    String pointOfTimeDateFormat="([0-9]{2}).([0-9]{2}).([0-9]{4})";
    String pointOfTimeTimeFormat="([01]?[0-9]|2[0-3]):[0-5][0-9]";
    String validOccurrenceRatingValues[]={"1","2","3"};
    String validDetectionRatingValues[]={"1","2","3"};
    String validSignificanceValues[]={"1","2","3"};
    int locationLength=50;
    int immediateMeasureLength=1000;
    int consequencesLength=1000;
    int personalFactorsLength=1000;
    int organisationalFactorsLength=1000;
    int additionalNotesLength=1000;
    int contactInformationLength=1000;

    public char[] getInvalideCharacters() {
        return invalideCharacters;
    }
    public String[] getValidSignificanceValues() {
        return validSignificanceValues;
    }

    public String[] getValidDetectionRatingValues() {
        return validDetectionRatingValues;
    }
    char[] invalideCharacters={'<','>',';'};

    public String[] getValidOccurrenceRatingValues() {
        return validOccurrenceRatingValues;
    }
    public int getIncidentDescriptionLength() {
        return incidentDescriptionLength;
    }

    public String getPointOfTimeDateFormat() {
        return pointOfTimeDateFormat;
    }

    public String getPointOfTimeTimeFormat() {
        return pointOfTimeTimeFormat;
    }

    public int getLocationLength() {
        return locationLength;
    }

    public int getImmediateMeasureLength() {
        return immediateMeasureLength;
    }

    public int getConsequencesLength() {
        return consequencesLength;
    }

    public int getPersonalFactorsLength() {
        return personalFactorsLength;
    }

    public int getOrganisationalFactorsLength() {
        return organisationalFactorsLength;
    }

    public int getAdditionalNotesLength() {
        return additionalNotesLength;
    }

    public int getContactInformationLength() {
        return contactInformationLength;
    }
}

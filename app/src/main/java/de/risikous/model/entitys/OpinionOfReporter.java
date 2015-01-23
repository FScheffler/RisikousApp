package de.risikous.model.entitys;

/**
 * Created by Franz on 08.01.2015.
 */
public class OpinionOfReporter {
    String personalFactors=null;	// (maximal 1000 Zeichen), optional
    String organisationalFactors=null;// (maximal 1000 Zeichen), optional
    String additionalNotes=null;// (maximal 1000 Zeichen), optional
    public String getPersonalFactors() {
        return personalFactors;
    }
    public void setPersonalFactors(String personalFactors) {
        this.personalFactors = personalFactors;
    }
    public String getOrganisationalFactors() {
        return organisationalFactors;
    }
    public void setOrganisationalFactors(String organisationalFactors) {
        this.organisationalFactors = organisationalFactors;
    }
    public String getAdditionalNotes() {
        return additionalNotes;
    }
    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }
}

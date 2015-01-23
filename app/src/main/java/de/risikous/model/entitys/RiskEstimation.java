package de.risikous.model.entitys;

/**
 * Created by Franz on 08.01.2015.
 */
public class RiskEstimation {
    String occurrenceRating;//	Eintrittswahrscheinlichkeit (mögliche Werte: "1","2","3")
    String detectionRating;//	Entdeckungswahrscheinlichkeit (mögliche Werte: "1","2","3")
    String significance;//	Bedeutung (mögliche Werte: "1","2","3")
    public String getOccurrenceRating() {
        return occurrenceRating;
    }
    public void setOccurrenceRating(String occurrenceRating) {
        this.occurrenceRating = occurrenceRating;
    }
    public String getDetectionRating() {
        return detectionRating;
    }
    public void setDetectionRating(String detectionRating) {
        this.detectionRating = detectionRating;
    }
    public String getSignificance() {
        return significance;
    }
    public void setSignificance(String significance) {
        this.significance = significance;
    }


}

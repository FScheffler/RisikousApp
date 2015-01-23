package de.risikous.util;

import de.risikous.model.entitys.OpinionOfReporter;
import de.risikous.model.entitys.PointOfTime;
import de.risikous.model.entitys.Questionaire;
import de.risikous.model.entitys.RiskEstimation;

/**
 * Created by Franz on 08.01.2015.
 */
public class QuestionaireDummyFactory {

    public Questionaire getCorrectQuestionaireJustRequiredFields() {
        RiskEstimation riskEstimation=new RiskEstimation();
        Questionaire quest=new Questionaire();
        riskEstimation.setDetectionRating("1");
        riskEstimation.setOccurrenceRating("1");
        riskEstimation.setSignificance("1");
        quest.setReportingArea("1");
        quest.setIncidentDescription("TestPost");
        quest.setRiskEstimation(riskEstimation);
        return quest;
    }
    public Questionaire getCompleteFalseQuestionaire() {
        Questionaire quest=new Questionaire();
        quest.setReportingArea("1");
        quest.setIncidentDescription("<;>");
        quest.setRiskEstimation(getCorrectRiskEstimation());
        quest.setPointOfTime(getCompleteWrongPointOfTime());
        quest.setIncidentDescription("<;>");
        quest.setLocation("<;>");
        quest.setImmediateMeasure("<;>");
        quest.setConsequences("<;>");
        quest.setOpinionOfReporter(getCompleteWrongOpinionOfReporter());
        quest.setContactInformation("<;>");
        return quest;
    }
    private RiskEstimation getCorrectRiskEstimation(){
        RiskEstimation result=new RiskEstimation();
        result.setDetectionRating("1");
        result.setOccurrenceRating("1");
        result.setSignificance("1");
        return result;
    }

    private PointOfTime getCompleteWrongPointOfTime(){
        PointOfTime result=new PointOfTime();
        result.setDate("a.12.d");
        result.setTime("a.3");
        return result;
    }
    private OpinionOfReporter getCompleteWrongOpinionOfReporter(){
        OpinionOfReporter result=new OpinionOfReporter();
        result.setPersonalFactors("<;>");
        result.setAdditionalNotes("<;>");
        result.setOrganisationalFactors("<;>");
        return result;
    }
}

package de.risikous.util;

import de.risikous.model.entitys.*;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

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

    public ArrayList<OverviewEntry> getOverviewDummys() {
        ArrayList<OverviewEntry>result=new ArrayList<>();
        for(int i =0; i<4;i++){
            OverviewEntry toAdd=new OverviewEntry();
            toAdd.setId(String.valueOf(i));
            toAdd.setStatus("abgeschlossen");
            DateFormat format = new SimpleDateFormat("dd.mm.yyyy", Locale.GERMAN);
            String datum=i + ".2.2015";

            try {
               Date date = format.parse(datum);
                toAdd.setRevisionDate(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            toAdd.setTitle("fehler");
            result.add(toAdd);
        }
        return result;
    }
}

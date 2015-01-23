package de.risikous.util;

import de.risikous.model.entitys.*;
import de.risikous.model.validation.QuestionaireValidation;
import de.risikous.model.validation.QuestionaireValidationRules;
import de.risikous.model.validation.ValidationResult;
import de.risikous.model.xml.Object2XML;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Franz on 07.01.2015.
 */
public class XMLTestInitializer {

    public static void main(String[ ] args) {
        testSpecificPublication("26");
        //testSpecificPublicationAsString("26");
        //testOverviewEntrys();
        //testQuestionaireSkeleton();
        //testPost();
        //testGetAllReportingAreas();
        //testQuestionaireValidation();
        //testGetCommentsForSpecificPublification("10");
        //testGetCommentsForSpecificPublificationAsString("10");
    }
    public static void testSpecificPublicationAsString(String id){
        EntityManager em = new EntityManager();
        System.out.println(em.getSpecificPublificationAsString("26"));
    }
    public static void testGetCommentsForSpecificPublificationAsString(String id){
        EntityManager em = new EntityManager();
        System.out.println(em.getCommentsForSpecificPublictionAsString(id));
    }
    public static void testGetCommentsForSpecificPublification(String id){
        EntityManager em = new EntityManager();
        ArrayList<Comment> comments= em.getCommentsForSpecificPubliction(id);
        for(int i = 0; i< comments.size();i++)
        System.out.println("id: "+comments.get(i).getId()+" author: "+comments.get(i).getAuthor()+" text: "+comments.get(i).getText()+" timestamp: "+comments.get(i).getTimeStamp());
    }
    public static void testGetAllReportingAreas(){
        EntityManager em = new EntityManager();
        List<ReportingArea>list=em.getAllReportingArea();
        for(int i=0; i<list.size();i++)
        System.out.println("shortcut: "+list.get(i).getShortcut()+" name: "+list.get(i).getName());
    }
    public static void testQuestionaireValidation(){
        QuestionaireDummyFactory df= new QuestionaireDummyFactory();
        Questionaire qs=df.getCompleteFalseQuestionaire();
        QuestionaireValidation validation = new QuestionaireValidation();
        QuestionaireValidationRules rules= new QuestionaireValidationRules();
        ValidationResult validationResult=validation.validate(qs, rules);
        if(validationResult.hasErrors()){
            List<String>errorMessages=validationResult.getAllErrorMessages();
            for(int i = 0; i<errorMessages.size();i++)
                System.out.println(errorMessages.get(i));
        }else{
            System.out.println("Keine Fehler gefunden");
        }
    }
    public static void testSpecificPublication(String id){
        EntityManager em = new EntityManager();
        Publication publictaion=em.getSpecificPublification(id);
        System.out.println(em.getSpecificPublificationAsString(id));
        System.out.println("title: "+publictaion.getTitle());
        System.out.println("incidentReport: "+publictaion.getIncidentReport());
        System.out.println("minRPZofReporter: "+publictaion.getMinRPZofQMB());
        System.out.println("avgRPZofReporter: "+publictaion.getAvgRPZofReporter());
        System.out.println("maxRPZofReporter: "+publictaion.getMaxRPZofReporter());
        System.out.println("minRPZofQMB: "+publictaion.getMinRPZofQMB());
        System.out.println("avgRPZofQMB: "+publictaion.getAvgRPZofQMB());
        System.out.println("maxRPZofQMB: "+publictaion.getMaxRPZofQMB());
        System.out.println("differenceStatement: "+publictaion.getDifferenceStatement());
        System.out.println("category: "+publictaion.getCategory());
        System.out.println("action: "+publictaion.getAction());
        System.out.println("assignedReports: "+publictaion.getAssignedReports());
    }
    public static void testOverviewEntrys(){
        EntityManager em = new EntityManager();
        List<OverviewEntry> overviewEntries=em.getOverviewEntrys();
        for(int i = 0; i<overviewEntries.size();i++)
        System.out.println("id: "+overviewEntries.get(i).getId()+" title: "+overviewEntries.get(i).getTitle()+" revisionDate: "+overviewEntries.get(i).getRevisionDate()+" status: "+overviewEntries.get(i).getStatus());
    }
    public static void testQuestionaireSkeleton(){
        EntityManager em = new EntityManager();
        System.out.println(em.getQuestionaireSkeletonAsString());
    }/*
    public static void testPost(){
        EntityManager em = new EntityManager();
        QuestionaireDummyFactory dum= new QuestionaireDummyFactory();
        Object2XML conv=new Object2XML();
        try{
            System.out.println(em.persistQuestionaire(dum.getCorrectQuestionaireJustRequiredFields()));
        }catch(Exception e){
            System.out.println("fehler");
        }

    }
    */
}

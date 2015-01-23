package de.risikous.model.entitys;

//import de.risikous.model.connector.HTMLPoster;
import de.risikous.model.connector.HTMLReciever;
import de.risikous.model.xml.Object2XML;
import de.risikous.model.xml.XML2Object;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Franz on 07.01.2015.
 */
public class EntityManager {
    HTMLReciever reciever = new HTMLReciever();
    XML2Object xml2object=new XML2Object();

    public ArrayList<ReportingArea> getAllReportingArea(){
        try{
            String restResult=reciever.getHTML("http://94.101.38.155/RisikousRESTful/rest/reportingareas");
            return xml2object.getReportingAreas(restResult);
        }catch(IOException e){
            return null;
        }
    }
    public ArrayList<OverviewEntry> getOverviewEntrys(){
        try{
            String restResult=reciever.getHTML("http://94.101.38.155/RisikousRESTful/rest/publications");
            return xml2object.getOverviewEntrys(restResult);
        }catch(IOException e){
            return null;
        }
    }

    public Publication getSpecificPublification(String id){
        try{
            String restResult= reciever.getHTML("http://94.101.38.155/RisikousRESTful/rest/publication/id/" + id);
            return xml2object.getPublication(restResult);
        }catch(IOException e){
            return null;
        }
    }
    public ArrayList<Comment> getCommentsForSpecificPubliction(String id){
    try{
        String restResult=reciever.getHTML("http://94.101.38.155/RisikousRESTful/rest/comments/id/"+id);
        return xml2object.getCommentList(restResult);
    }catch(IOException e){
        return null;
    }
}
    public String getCommentsForSpecificPublictionAsString(String id){
        try{
            return reciever.getHTML("http://94.101.38.155/RisikousRESTful/rest/comments/id/"+id);
        }catch(IOException e){
            return null;
        }
    }
    public String getSpecificPublificationAsString(String id){
        try{
            return reciever.getHTML("http://94.101.38.155/RisikousRESTful/rest/publication/id/"+id);
        }catch(IOException e){
            return null;
        }
    } public String getQuestionaireSkeletonAsString(){
        try{
            return reciever.getHTML("http://94.101.38.155/RisikousRESTful/rest/questionnaire");
        }catch(IOException e){
            return null;
        }
    }
    /*
    public String persistQuestionaire(Questionaire q){
        Object2XML conv= new Object2XML();
        HTMLPoster poster= new HTMLPoster();
        try {
            return poster.persist("http://94.101.38.155/RisikousRESTful/rest/questionnaire/addQuestionnaire",conv.questionaire2XMLString(q));
        }catch(Exception e){
            return e.getMessage();
        }
    }
*/
}

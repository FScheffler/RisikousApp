package de.risikous.model.entitys;

//import de.risikous.model.connector.HTMLPoster;
import android.util.Log;
import de.risikous.model.connector.HTMLPoster;
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

    public ArrayList<ReportingArea> getAllReportingArea ()throws IOException {//XMLParserException
        HTMLReciever reciever = new HTMLReciever();
        XML2Object xml2object=new XML2Object();
        String restResult=reciever.getURL("http://94.101.38.155/RisikousRESTful/rest/reportingareas");
        return xml2object.getReportingAreas(restResult);
    }
    public ArrayList<OverviewEntry> getOverviewEntrys(){
        HTMLReciever reciever = new HTMLReciever();
        String restResult=reciever.getURL("http://94.101.38.155/RisikousRESTful/rest/publications");
        Log.v("INFO", restResult);
        return xml2object.getOverviewEntrys(restResult);
    }
    public String getOverviewEntrysAsString(){
        HTMLReciever reciever = new HTMLReciever();
        String restResult=reciever.getURL("http://94.101.38.155/RisikousRESTful/rest/publications");
        Log.v("INFO", restResult);
        return restResult;
    }

    public Publication getSpecificPublification(String id){
            HTMLReciever reciever = new HTMLReciever();
            String restResult= reciever.getURL("http://94.101.38.155/RisikousRESTful/rest/publication/id/" + id);
            return xml2object.getPublication(restResult);
    }
    public ArrayList<Comment> getCommentsForSpecificPubliction(String id){
        String restResult=reciever.getURL("http://94.101.38.155/RisikousRESTful/rest/comments/id/"+id);
        return xml2object.getCommentList(restResult);
}
    public String getCommentsForSpecificPublictionAsString(String id){
            return reciever.getURL("http://94.101.38.155/RisikousRESTful/rest/comments/id/"+id);
    }
    public String getSpecificPublificationAsString(String id){
            return reciever.getURL("http://94.101.38.155/RisikousRESTful/rest/publication/id/"+id);
    }
    public String getQuestionaireSkeletonAsString(){
            return reciever.getURL("http://94.101.38.155/RisikousRESTful/rest/questionnaire");
    }

    public HTMLResponseCode persistQuestionaire(Questionaire q)throws IOException{
        Object2XML conv= new Object2XML();
        HTMLPoster poster= new HTMLPoster();
        return null;
       // return poster.persistQuestionaire("http://94.101.38.155/RisikousRESTful/rest/questionnaire/addQuestionnaire",conv.questionaire2XMLString(q));
    }
}

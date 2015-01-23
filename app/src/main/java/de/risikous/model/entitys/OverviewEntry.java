package de.risikous.model.entitys;

import java.util.Date;

public class OverviewEntry {
    String id;
    Date revisionDate;
    String title;
    String status;

    public void setId (String id){
        this.id=id;
    }
    public void setStatus (String status){
        this.status=status;
    }
    public void setRevisionDate (Date revisionDate){
        this.revisionDate=revisionDate;
    }
    public void setTitle (String title){
        this.title=title;
    }
    public String getId (){
        return id;
    }
    public String getStatus (){
        return status;
    }
    public Date getRevisionDate (){
        return revisionDate;
    }
    public String getTitle (){
        return title;
    }
}

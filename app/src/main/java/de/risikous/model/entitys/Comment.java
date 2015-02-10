package de.risikous.model.entitys;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Franz on 08.01.2015.
 */
public class Comment {
    String id=null;	//optional
    String author;
    String text;
    String timeStamp;
    List<Comment> listOfAnswers=null;//optional

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
    public List<Comment> getListOfAnswers() {
        if(this.listOfAnswers == null){
            this.listOfAnswers = new ArrayList<>();
        }
        return listOfAnswers;
    }
    public void setListOfAnswers(List<Comment> listOfAnswers) {
        this.listOfAnswers = listOfAnswers;
    }
}

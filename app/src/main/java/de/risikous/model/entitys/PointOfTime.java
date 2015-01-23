package de.risikous.model.entitys;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Franz on 08.01.2015.
 */
public class PointOfTime {
    String date=null;//	Datum, Hinweis: dieser Tag ist optional, dd.mm.yyyy
    String time=null;//	hh:mm, Uhrzeit, Hinweis: dieser Tag ist optional
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
}

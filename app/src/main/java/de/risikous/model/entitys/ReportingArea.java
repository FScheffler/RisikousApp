package de.risikous.model.entitys;

import java.lang.String; /**
 * Created by Franz on 09.01.2015.
 */
public class ReportingArea {
    String shortcut=null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortcut() {
        return shortcut;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }

    String name=null;
}

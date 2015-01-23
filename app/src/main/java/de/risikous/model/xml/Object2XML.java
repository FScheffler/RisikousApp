package de.risikous.model.xml;

import de.risikous.model.entitys.Questionaire;
import de.risikous.model.xml.deparser.PublicationDeparser;

/**
 * Created by Franz on 08.01.2015.
 */
public class Object2XML {
    public String questionaire2XMLString(Questionaire q){
        PublicationDeparser deparser= new PublicationDeparser();
        String result="";
        result+=deparser.getHeaderXMLHeader();
        result+=deparser.getRequiredFieldsAsXMLString(q);
        result+=deparser.getOptionalFieldsAsXMLString(q);
        result+=deparser.getXMLEnd();
        return result;
    }
}

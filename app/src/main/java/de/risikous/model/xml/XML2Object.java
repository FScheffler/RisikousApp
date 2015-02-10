package de.risikous.model.xml;

import de.risikous.model.entitys.Comment;
import de.risikous.model.entitys.OverviewEntry;
import de.risikous.model.entitys.Publication;
import de.risikous.model.entitys.ReportingArea;
import de.risikous.model.xml.parser.*;

import java.util.ArrayList;

/**
 * Created by Franz on 07.01.2015.
 */
public class XML2Object {
    public ArrayList<ReportingArea>getReportingAreas(String restResult) {
        ReportingAreaParser parser=new ReportingAreaParser();
        ArrayList<String> reportingAreasAsString=parser.getReportingAreaXMLStrings(restResult);
        ArrayList<ReportingArea>result=new ArrayList<ReportingArea>();

        for (int i = 0; i < reportingAreasAsString.size(); i++) {
            ReportingArea reportingArea = new ReportingArea();
            reportingArea.setName(parser.parseName(reportingAreasAsString.get(i)));
            reportingArea.setShortcut(parser.parseShortcut(reportingAreasAsString.get(i)));
            result.add(reportingArea);
        }
        return result;
    }
    public ArrayList<OverviewEntry>getOverviewEntrys(String restResult) {
        OverviewEntryParser parser = new OverviewEntryParser();
        ArrayList<String> publicationOverviewEntrys=parser.getOverviewEntryXMLStrings(restResult);
        ArrayList<OverviewEntry>result=new ArrayList<OverviewEntry>();

        for (int i = 0; i < publicationOverviewEntrys.size(); i++) {
            OverviewEntry eintrag = new OverviewEntry();
            eintrag.setId(parser.parseId(publicationOverviewEntrys.get(i)));
            eintrag.setRevisionDate(parser.parseRevisionDate(publicationOverviewEntrys.get(i)));
            eintrag.setStatus(parser.parseStatus(publicationOverviewEntrys.get(i)));
            eintrag.setTitle(parser.parseTitle(publicationOverviewEntrys.get(i)));
            result.add(eintrag);
        }
        return result;
    }
    public Publication getPublication(String restResult) {
        PublicationParser parser= new PublicationParser();
        String publicationAsXMLString=parser.getPublicationXMLString(restResult);
        Publication result=new Publication();
        result.setTitle(parser.parseTitle(publicationAsXMLString));
        result.setIncidentReport(parser.parseIncidentReport(publicationAsXMLString));
        result.setMinRPZofReporter(parser.parseMinRPZofReporter(publicationAsXMLString));
        result.setAvgRPZofReporter(parser.parseAvgRPZofReporter(publicationAsXMLString));
        result.setMaxRPZofReporter(parser.parseMaxRPZofReporter(publicationAsXMLString));
        result.setMinRPZofQMB(parser.parseMinRPZofQMB(publicationAsXMLString));
        result.setAvgRPZofQMB(parser.parseAvgRPZofQMB(publicationAsXMLString));
        result.setMaxRPZofQMB(parser.parseMaxRPZofQMB(publicationAsXMLString));
        result.setDifferenceStatement(parser.parseDifferenceStatement(publicationAsXMLString)); //optional
        result.setCategory(parser.parseCategory(publicationAsXMLString));
        result.setAction(parser.parseAction(publicationAsXMLString));
        result.setAssignedReports(parser.parseAssignedReports(publicationAsXMLString));
        return result;
    }
    public ArrayList<Comment> getCommentList(String restResult){
        CommentParser parser= new CommentParser();
        ArrayList<Comment>result = new ArrayList<Comment>();
        CommentRekursiv commentRekursiv = new CommentRekursiv();
        result = commentRekursiv.readComments(restResult);

        return result;
    }
}

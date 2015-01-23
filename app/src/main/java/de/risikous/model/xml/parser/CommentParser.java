package de.risikous.model.xml.parser;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Franz on 09.01.2015.
 */
public class CommentParser {

    public ArrayList<String> getCommentXMLString(String toSplit) {
        String endTrimmedComments = toSplit.replace("</comments>", "");
        String[] frontsplittedComments = endTrimmedComments.split("<comments>");
        ArrayList<String> result=new ArrayList<String>(Arrays.asList(frontsplittedComments));
        result.remove(0);
        return result;
    }
    public String parseId(String toParse) {
        if(toParse.contains("<id>")) {
            String[] withoutStartTag = toParse.split("<id>");
            String[] withoutEndTag = withoutStartTag[1].split("</id>");
            return withoutEndTag[0];
        }else{
            return null;
        }
    }
    public String parseAuthor(String toParse) {
        String[] withoutStartTag = toParse.split("<author>");
        String[] withoutEndTag = withoutStartTag[1].split("</author>");
        return withoutEndTag[0];
    }
    public String parseText(String toParse) {
        String[] withoutStartTag = toParse.split("<text>");
        String[] withoutEndTag = withoutStartTag[1].split("</text>");
        return withoutEndTag[0];
    }
    public String parseTimeStamp(String toParse) {
        String[] withoutStartTag = toParse.split("<timeStamp>");
        String[] withoutEndTag = withoutStartTag[1].split("</timeStamp>");
        return withoutEndTag[0];
    }
}

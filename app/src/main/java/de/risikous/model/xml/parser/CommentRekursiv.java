package de.risikous.model.xml.parser;

import de.risikous.model.entitys.Comment;

import java.util.ArrayList;
import java.util.List;


public class CommentRekursiv {

    public ArrayList<Comment> readComments(String xml) {
        ArrayList<Comment> comments = new ArrayList<>();
        String[] withoutCommentsStartTag = xml.split("<comments>");
        String[] withoutCommentsEndTag = withoutCommentsStartTag[1].split("</comments>");
        String[] splitOnTagStartEndSymbol = withoutCommentsEndTag[0].split("><");
        for (int i = 0; i < splitOnTagStartEndSymbol.length; i++) {
            splitOnTagStartEndSymbol[i] = splitOnTagStartEndSymbol[i].replaceAll("<", "#");
            splitOnTagStartEndSymbol[i] = splitOnTagStartEndSymbol[i].replaceAll(">", "#");
        }
        if (splitOnTagStartEndSymbol[0].startsWith("#")) {
            splitOnTagStartEndSymbol[0] = splitOnTagStartEndSymbol[0].replaceAll("#", "");
        }
        if (splitOnTagStartEndSymbol[splitOnTagStartEndSymbol.length - 1].endsWith("#")) {
            splitOnTagStartEndSymbol[splitOnTagStartEndSymbol.length - 1] =
                    splitOnTagStartEndSymbol[splitOnTagStartEndSymbol.length - 1].replaceAll("#", "");
        }
        List<List<String>> CommentStrings = new ArrayList<>();
        int i = 0;
        for (i = 0; i < splitOnTagStartEndSymbol.length; i++) {
            if (splitOnTagStartEndSymbol[i].equals("comment")) {
                List<String> currentCommentEntery = new ArrayList<>();
                int j = 0;
                for (j = i + 1; j < splitOnTagStartEndSymbol.length; j++) {
                    if (splitOnTagStartEndSymbol[j].equals("/comment")) {
                        break;
                    } else if (splitOnTagStartEndSymbol[j].equals("listOfAnswers")) {
                        String listOfAnswers = "";
                        int k = 0;
                        for (k = j + 1; k < splitOnTagStartEndSymbol.length; ++k) {
                            if (splitOnTagStartEndSymbol[k].equals("/listOfAnswers")) {
                                listOfAnswers += "#" + splitOnTagStartEndSymbol[k];
                                break;
                            } else if (splitOnTagStartEndSymbol[k].equals("comment")) {
                                splitOnTagStartEndSymbol[k] = "A" + splitOnTagStartEndSymbol[k];
                                listOfAnswers += "#" + splitOnTagStartEndSymbol[k];
                            } else {
                                listOfAnswers += "#" + splitOnTagStartEndSymbol[k];
                            }
                        }
                        if (!listOfAnswers.isEmpty()) {
                            String list = splitOnTagStartEndSymbol[j] + listOfAnswers;
                            currentCommentEntery.add(list);
                        }
                    } else {
                        currentCommentEntery.add(splitOnTagStartEndSymbol[j]);
                    }
                }
                CommentStrings.add(currentCommentEntery);
            }
        }
        int count = 0;
        for (List<String> comment : CommentStrings) {
            Comment currentComment = new Comment();
            for (String commentText : comment) {
                if (commentText.contains("id")) {
                    String[] split = commentText.split("#");
                    currentComment.setId(split[1]);
                }
                if (commentText.contains("author")) {
                    String[] split = commentText.split("#");
                    currentComment.setAuthor(split[1]);
                }
                if (commentText.contains("text")) {
                    String[] split = commentText.split("#");
                    currentComment.setText(split[1]);
                }
                if (commentText.contains("timeStamp")) {
                    String[] split = commentText.split("#");
                    currentComment.setTimeStamp(split[1]);
                }
                if (commentText.contains("listOfAnswers")) {
                    List<Comment> answers = getAnswers(commentText);
                    currentComment.setListOfAnswers(answers);
                }
            }
            comments.add(currentComment);
            System.out.println(count + ":  " + currentComment);
            count++;
        }
        return comments;
    }

    private ArrayList<Comment> getAnswers(String commentText) {
        ArrayList<Comment> ansers = new ArrayList<>();
        String[] split = commentText.split("#");
        for (int i = 0; i < split.length; i++) {
            if (split[i].equals("Acomment")) {
                Comment anser = new Comment();
                for (int j = 0; j < split.length; j++) {
                    if (split[j].equals("author")) {
                        anser.setAuthor(split[j]);
                    }
                    if (split[j].equals("text")) {
                        anser.setText(split[j]);
                    }
                    if (split[j].equals("timeStamp")) {
                        anser.setTimeStamp(split[j]);
                    }
                }
                ansers.add(anser);
            }
        }
        return ansers;
    }
}

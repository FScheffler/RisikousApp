package de.risikous.views;

import android.content.Context;
import android.content.Intent;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import de.risikous.app.R;
import de.risikous.model.entitys.EntityManager;
import de.risikous.model.entitys.Comment;

import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class PublicationActivity extends Activity {

    private String id = "";
    private String pubTitle = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publication);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Bundle extras = getIntent().getExtras();
        this.id = (String) extras.get("ID");

        getInformation((TableLayout) findViewById(R.id.table1));
        getComments((TableLayout) findViewById(R.id.table2));


        Button meldung = (Button) findViewById(R.id.Meldung);
        meldung.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent ques = new Intent(getApplicationContext(), QuestionnaireActivity.class);
                ques.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(ques);

            }
        });

        Button veröffentlichung = (Button) findViewById(R.id.Veröffentlichung);
        veröffentlichung.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent pub = new Intent(getApplicationContext(), PublicationsActivity.class);
                pub.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(pub);
                finish();
            }
        });
    }

    /**
     * gets the comments of a publication
     * @param table to which the comments are added
     * */
    private void getComments(TableLayout table) {

       // EntityManager entityManager = new EntityManager();
        //entityManager.getCommentsForSpecificPubliction(String id);

        //String xml = commentGet.getXml();


        //LinkedList<Comment> comments = commentsParser.getComments();
        String[] names = new String[]{"Autor: ", "Datum: ", "Text: "};
        /*
        if(comments.size() == 0) {
            TableRow row = new TableRow(this);
            row.setGravity(Gravity.CENTER);

            TextView error = new TextView(this);
            error.setSingleLine(false);
            error.setMaxLines(3);
            error.setText("Fehler, keine Kommentare gefunden.");
            error.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
            error.setTextColor(getResources().getColor(R.color.errorRed));
            error.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            error.setGravity(Gravity.CENTER);

            row.addView(error);
            table.addView(row);

        } else {
            for(int i=0; i<comments.size(); i++) {
                for(int j=0; j<names.length; j++) {
                    TableRow row = new TableRow(this);
                    row.setGravity(Gravity.LEFT);

                    TextView textLeft = new TextView(row.getContext());
                    setStyleLeft(textLeft);
                    textLeft.setText(names[j]);

                    TextView textRight = new TextView(row.getContext());
                    setStyleRight(textRight);
                    switch (j) {
                        case 0: textRight.setText(comments.get(i).getAuthor()); break;
                        case 1: textRight.setText(comments.get(i).getTimestamp()); break;
                        case 2: textRight.setText(comments.get(i).getText()); break;
                    }

                    TableRow space = new TableRow(this);
                    space.setPadding(0, 1, 0, 0);
                    space.setBackgroundColor(getResources().getColor(R.color.risikousBlack));

                    row.addView(textLeft);
                    row.addView(textRight);
                    table.addView(row);

                    if((comments.get(i).getId() != "-1") && (comments.get(i).getId() != "-2") && (j==2)) {
                        TableRow buttonRow = new TableRow(this);
                        buttonRow.setGravity(Gravity.CENTER_HORIZONTAL);



                        final Button answerbutton = new Button(buttonRow.getContext(), comments.get(i).getId());
                        answerbutton.setText("Antworten");
                        //answerbutton.setOnClickListener(new View.OnClickListener() {

                            /* TODO
                          //  }

                        //});

                        buttonRow.addView(answerbutton);
                        table.addView(buttonRow);
                    }

                    table.addView(space);
                }
                TableRow spaceLarge = new TableRow(this);
                spaceLarge.setPadding(0, 3, 0, 0);
                spaceLarge.setBackgroundColor(getResources().getColor(R.color.risikousBlack));
                table.addView(spaceLarge);
            }

        }*/
    }

    /**
     * applies a style to a TextView
     * @param view to which the style is applied
     * */
    private void setStyleLeft(TextView view) {
        view.setTextColor(getResources().getColor(R.color.publicationsWhite));
        view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
        view.setGravity(Gravity.LEFT);
        view.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
    }

    /**
     * applies a style to a TextView
     * @param view to which the style is applied
     * */
    private void setStyleRight(TextView view) {
        view.setTextColor(getResources().getColor(R.color.publicationsWhite));
        view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
        view.setGravity(Gravity.LEFT);
        view.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
    }

    /**
     * adds the information of a publication and adds it to the TableLayout
     * @param table to which the information are added
     * */
    private void getInformation(TableLayout table) {

        // EntityManager entityManager = new EntityManager();
        //entityManager.getSpecificPublification(String id);

        //String xml = publicationGet.getXml();


        //LinkedList<String> infos = publicationParser.getPublicationInformation();
        String[] names = new String[]{"Titel: ", "Bericht: ", "Kategorie: ", "Lösung: "};
        /*
        if(infos.size() == 0) {
            TableRow row = new TableRow(this);
            row.setGravity(Gravity.CENTER);

            TextView error = new TextView(this);
            error.setSingleLine(false);
            error.setMaxLines(3);
            error.setText("Es ist ein Fehler aufgetreten. Die Meldungen wurde nicht gefunden.");
            error.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
            error.setTextColor(getResources().getColor(R.color.errorRed));
            error.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            error.setGravity(Gravity.CENTER);

            row.addView(error);
            table.addView(row);

        } else {
            setPubTitle(infos.get(0));
            for(int i=0; i<infos.size(); i++) {
                TableRow row = new TableRow(this);
                row.setGravity(Gravity.LEFT);

                TextView textLeft = new TextView(this);
                textLeft.setSingleLine(true);
                textLeft.setText(names[i]);
                textLeft.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
                textLeft.setTextColor(getResources().getColor(R.color.risikousWhite));
                textLeft.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
                textLeft.setGravity(Gravity.LEFT);

                TextView textRight = new TextView(this);
                textRight.setSingleLine(false);
                textRight.setText(infos.get(i));
                textRight.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
                textRight.setTextColor(getResources().getColor(R.color.risikousWhite));
                textRight.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                textRight.setGravity(Gravity.LEFT);

                row.addView(textLeft);
                row.addView(textRight);
                table.addView(row);
            }
        }*/
    }


    /**
     * sends the comment to the server
     * @param view of the calling class
     * */
    public void sendComment(View view) {
        EditText edit1 = (EditText) findViewById(R.id.Name);
        EditText edit2 = (EditText) findViewById(R.id.Kommentar);

        if(edit1.getText().toString().trim().equals("")) {
            edit1.setError("Eingabe erforderlich!");
            Toast.makeText(getBaseContext(), "Validierungsfehler", Toast.LENGTH_LONG).show();
        } else if(edit2.getText().toString().trim().equals("")) {
            edit2.setError("Eingabe erforderlich!");
            Toast.makeText(getBaseContext(), "Validierungsfehler", Toast.LENGTH_LONG).show();
        } else {
            String id = getId();
            String author = edit1.getText().toString();
            String text = edit2.getText().toString();

            Toast.makeText(getBaseContext(), "Upload läuft...", Toast.LENGTH_LONG).show();
            //CommentGenerator generator = new CommentGenerator();
            //RestPost post = new RestPost();
            //post.execute(new String[]{"1", generator.generateComment(id, author, text)});
            //Toast.makeText(getBaseContext(), "Upload erfolgreich", Toast.LENGTH_LONG).show();
        }
    }

    private String getId() {
        return id;
    }

    private String getPubTitle() {
        return pubTitle;
    }

    private void setPubTitle(String title) {
        this.pubTitle = title;
    }
}
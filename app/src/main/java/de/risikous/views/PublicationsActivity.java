package de.risikous.views;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.content.Intent;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import de.risikous.app.R;
import de.risikous.model.entitys.EntityManager;
import de.risikous.model.entitys.PublicationsGenerator;

import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class PublicationsActivity extends Activity {

    private int screenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publications);


        Button meldung = (Button) findViewById(R.id.Meldung);
        meldung.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                Intent ques = new Intent(getApplicationContext(), QuestionnaireActivity.class);
                ques.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(ques);

            }
        });

        Button veröffentlichung = (Button) findViewById(R.id.Veröffentlichung);
        veröffentlichung.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                Intent pub = new Intent(getApplicationContext(), PublicationsActivity.class);
                pub.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(pub);
            }
        });

        TableLayout table = (TableLayout) findViewById(R.id.pubTable1);
        table.setStretchAllColumns(true);
        table.setShrinkAllColumns(true);
        fillTable(table);
    }


    /**
     * fills the table with all accessible publications
     * @param table to which the publications are added
     * */
    private void fillTable(TableLayout table) {
        TableRow head = new TableRow(this);
        head.setGravity(Gravity.CENTER_HORIZONTAL);
        createTableHead(head);
        table.addView(head);

        //??? von REST-SChnittstelle
        EntityManager entityManager = new EntityManager();
        entityManager.getOverviewEntrys();

        //String xml = get.getXml();
        PublicationsGenerator generator = new PublicationsGenerator(PublicationsActivity.this);
        LinkedList<TableRow> rows = generator.getPublications(xml, head.getContext(), getScreenWidth());

        if(rows.size() > 0) {
            for(int i=0; i<rows.size(); i++) {
                table.addView(rows.get(i));
            }
        } else {
            TableRow row = new TableRow(this);
            row.setGravity(Gravity.CENTER_HORIZONTAL);

            TextView error = new TextView(this);
            error.setSingleLine(true);
            error.setText("Es wurden keine Meldungen gefunden.");
            error.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
            error.setTextColor(getResources().getColor(R.color.errorRed));
            error.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            error.setGravity(Gravity.CENTER);

            row.addView(error);
            table.addView(row);
        }
    }

    /**
     * creates the table head
     * @param head to which the items are added
     * */
    private void createTableHead(TableRow head) {

        TextView changedText = new TextView(this);
        changedText.setText("Letzte Änderung");
        setLayout(changedText);

        TextView titleText = new TextView(this);
        titleText.setText("Titel");
        setLayout(titleText);

        TextView statusText = new TextView(this);
        titleText.setText("Status");
        setLayout(statusText);


        head.addView(changedText);
        head.addView(titleText);
        head.addView(statusText);
    }

    private void setLayout(TextView view) {
        view.setTextColor(view.getContext().getResources().getColor(R.color.publicationsWhite));
        view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
        view.setGravity(Gravity.CENTER);
        view.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
    }

    private int getScreenWidth() {
        return screenWidth;
    }

    /**
     * gets the screen width of the display
     * */
    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    private void setScreenWidth() {
        int width = 100;
        if(Integer.valueOf(android.os.Build.VERSION.SDK) < 13) {
            Display display = getWindowManager().getDefaultDisplay();
            width = display.getWidth();
        } else {
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            width = size.x;
        }

        this.screenWidth = width;
    }
}
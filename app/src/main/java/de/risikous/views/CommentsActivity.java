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
import de.risikous.model.entitys.Publication;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CommentsActivity extends Activity {

    private String id = "";
    private String pubTitle = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publication);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Bundle extras = getIntent().getExtras();
        this.id = (String) extras.get("ID");

        //getInformation((TableLayout) findViewById(R.id.table1));
        //getComments((TableLayout) findViewById(R.id.table2));

        EntityManager em = new EntityManager();
        Publication pub = em.getSpecificPublification(id);
        List<Comment> comments = em.getCommentsForSpecificPubliction(id);

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


        ExpandableListView commentsList = (ExpandableListView) findViewById(R.id.commentsList);
        CommentsAdapter commentsAdapter = new CommentsAdapter(this,comments);
        commentsList.setAdapter(commentsAdapter);

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
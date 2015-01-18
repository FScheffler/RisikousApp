package de.risikous.views;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import de.risikous.app.R;

public class QuestionnaireActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

        Button Meldung = (Button) findViewById(R.id.Meldung);
        Meldung.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //Neues Intent anlegen
                Intent ques = new Intent(getApplicationContext(), QuestionnaireActivity.class);
                ques.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(ques);

            }
        });

        Button Veröffentlichung = (Button) findViewById(R.id.Veröffentlichung);
        Veröffentlichung.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //Neues Intent anlegen
                Intent pub = new Intent(getApplicationContext(), PublicationsActivity.class);
                pub.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(pub);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_questionnaire, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
/**
    // Intent for the activity to open when user selects the notification
    Intent detailsIntent = new Intent(this, QuestionnaireActivity.class);

    // Use TaskStackBuilder to build the back stack and get the PendingIntent
    PendingIntent pendingIntent =
            TaskStackBuilder.create(this)
                    // add all of DetailsActivity's parents to the stack,
                    // followed by DetailsActivity itself
                    .addNextIntentWithParentStack(detailsIntent)
                    .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

    NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
    builder.setContentIntent(pendingIntent);
*/
}
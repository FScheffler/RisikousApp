package de.risikous.views;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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
                startActivity(ques);
                finish();
            }
        });

        Button Veröffentlichung = (Button) findViewById(R.id.Veröffentlichung);
        Veröffentlichung.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //Neues Intent anlegen
                Intent pub = new Intent(getApplicationContext(), PublicationsActivity.class);
                startActivity(pub);
                finish();
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
}
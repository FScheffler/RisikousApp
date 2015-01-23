package de.risikous.views;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.*;
import de.risikous.app.R;
import de.risikous.model.entitys.*;
import de.risikous.model.validation.QuestionaireValidation;
import de.risikous.model.validation.QuestionaireValidationRules;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class QuestionnaireActivity extends Activity {

    Spinner meldekreise;
    String currentMeldekreis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

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

            }
        });

        meldekreise = (Spinner) findViewById(R.id.spinner);
        addEntries(meldekreise);

        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker1);
        timePicker.setIs24HourView(true);
    }

    /**
     * sends the questionnaire data to the server
     * @param view of the calling class
     * */
        public void SendClicked(View view) {
            Toast.makeText(getBaseContext(), "Validierung läuft...\nEinen Moment bitte.", Toast.LENGTH_SHORT).show();

            Questionaire ques = new Questionaire();
            QuestionaireValidation quesval = new QuestionaireValidation();
            QuestionaireValidationRules rules = new QuestionaireValidationRules();


            String reportingArea = getCurrentMeldekreis();
            ques.setReportingArea(reportingArea);
            EditText incidentDescription = (EditText) findViewById(R.id.Ereigniseingabe);
            ques.setIncidentDescription(incidentDescription.getText().toString());

            RiskEstimation riskEstimation = new RiskEstimation();
            ques.setRiskEstimation(riskEstimation);
            RadioGroup radio1 = (RadioGroup) findViewById(R.id.radioGroup1);
            addSelectedRadioButton(riskEstimation, radio1);
           // riskEstimation.setOccurrenceRating();
            RadioGroup radio2 = (RadioGroup) findViewById(R.id.radioGroup2);
            addSelectedRadioButton(riskEstimation, radio2);
            RadioGroup radio3 = (RadioGroup) findViewById(R.id.radioGroup3);
            addSelectedRadioButton(riskEstimation, radio3);

            PointOfTime pointOfTime = new PointOfTime();
            //if(pointOfTime !=null)
            ques.setPointOfTime(pointOfTime);

            DatePicker date = (DatePicker) findViewById(R.id.datePicker1);
            @SuppressWarnings("deprecation")
            Date pickedDate = new Date((date.getYear()-1900), (date.getMonth()+1), date.getDayOfMonth());
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.mm.yyyy");
            String dateString = dateFormatter.format(pickedDate);
            pointOfTime.setDate(dateString);

            TimePicker time = (TimePicker) findViewById(R.id.timePicker1);
            String timeString = String.format("hh:mm", time.getCurrentHour(), time.getCurrentMinute()); //"%02d:%02d"
            pointOfTime.setTime(timeString);

            EditText location = (EditText) findViewById(R.id.Orteingabe);
            //if(location.getText()!=null)
            ques.setLocation(location.getText().toString());
            EditText immediateMeasure = (EditText) findViewById(R.id.Maßnahmeeingabe);
           // if(immediateMeasure.getText()!=null)
            ques.setImmediateMeasure(immediateMeasure.getText().toString());
            EditText consequences = (EditText) findViewById(R.id.Folgeneingabe);
           // if(consequences.getText()!=null)
            ques.setConsequences(consequences.getText().toString());

            OpinionOfReporter opinion = new OpinionOfReporter();
            ques.setOpinionOfReporter(opinion);
            EditText personalFactors = (EditText) findViewById(R.id.PFaktoreneingabe);
            //if(personalFactors.getText()!=null)
            opinion.setPersonalFactors(personalFactors.getText().toString());
            EditText organisationalFactors = (EditText) findViewById(R.id.OFaktoreneingabe);
            //if(organisationalFactors.getText()!=null)
            opinion.setOrganisationalFactors(organisationalFactors.getText().toString());
            EditText additionalNotes = (EditText) findViewById(R.id.Anmerkungeneingabe);
            //if(additionalNotes.getText()!=null)
            opinion.setAdditionalNotes(additionalNotes.getText().toString());

            EditText contactInformation = (EditText) findViewById(R.id.Kontakteingabe);
            //if(contactInformation.getText()!=null)
            ques.setContactInformation(contactInformation.getText().toString());

            quesval.validate(ques, rules);
        }

    /**
     * adds the parsed reporting areas to the spinner
     * @param spinner Spinner to which the entries are added
     * */
    public void addEntries(Spinner spinner) {
        List<String> list = new ArrayList<String>();
        String[] entries = getMeldekreise();

        for (int i = 0; i < entries.length; i++) {
            list.add(entries[i]);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                String selected =  arg0.getItemAtPosition(arg2).toString();
                selected = selected.substring(0, selected.indexOf(" "));
                setCurrentMeldekreis(selected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
    }

    /**???
     * gets reporting areas and collects all parsed areas
     * */
    public String[] getMeldekreise() {
        String[] areas = {};


        EntityManager entityManager = new EntityManager();
        entityManager.getAllReportingArea();
        //areas...


        if(areas.length == 0) {
            areas = new String[]{"Keine Einträge"};
        }

        return areas;
    }

    private String getCurrentMeldekreis() {
        return currentMeldekreis;
    }

    private void setCurrentMeldekreis(String currentMeldekreis) {
        this.currentMeldekreis = currentMeldekreis;
    }



    /**???
     * adds the selected radiobutton to the list for questionnaire generation
     * @param riskEst RiskEstimation with occurrenceRating, detectionRating, significance
     * @param group RadioGroup to search for the selected RadioButton
     * */
    private void addSelectedRadioButton(RiskEstimation riskEst, RadioGroup group) {
        RadioButton radio = (RadioButton) group.findViewById(group.getCheckedRadioButtonId());
        int index = group.indexOfChild(radio) + 1;
       // riskEstimation.add(""+index);
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

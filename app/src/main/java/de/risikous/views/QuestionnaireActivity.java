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
import de.risikous.model.validation.QuestionaireValidationResult;
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

            ques.setReportingArea(getCurrentMeldekreis());
            ques.setIncidentDescription(getIncidentDiscription());
            ques.setRiskEstimation(getRiskestimationValues());
            ques.setPointOfTime(getPointOfTimeValues());//optional
            ques.setLocation(getLocationValue());// (maximal 50 Zeichen), optional
            ques.setImmediateMeasure(getImmediateMeasureValue());//(maximal 1000 Zeichen), optional
            ques.setConsequences(getConsequencesValue());//(maximal 1000 Zeichen), optional
            ques.setOpinionOfReporter(getOpinionOfReporter());//opinionOfReporter=null;//optional
            //List<File> files=null;//optional
            ques.setContactInformation(getContactInformation());// (maximal 1000 Zeichen), optional

            QuestionaireValidationResult validationResult= quesval.validate(ques, rules);
            if(validationResult.hasErrors()){
                //Fehlerhandling hier
            }else{
                try {
                    EntityManager em = new EntityManager();
                    em.persistQuestionaire(ques);
                }catch(Exception e){

                }
            }
        }

    /**
     * adds the parsed reporting areas to the spinner
     * @param spinner Spinner to which the entries are added
     * */
    public void addEntries(Spinner spinner) {
        List<String> areas = getMeldekreiseAsStringList();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, areas);
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
    public ArrayList<String> getMeldekreiseAsStringList() {
        ArrayList<String> result = new ArrayList<>();
        try{
            EntityManager entityManager = new EntityManager();
            ArrayList<ReportingArea>areaList=entityManager.getAllReportingArea();
            for(int i =0;i<areaList.size();i++)
                result.add(areaList.get(i).getShortcut());

        }catch(Exception e){
        }
        return result;
    }

    private String getCurrentMeldekreis() {
        return currentMeldekreis;
    }

    private void setCurrentMeldekreis(String currentMeldekreis) {
        this.currentMeldekreis = currentMeldekreis;
    }


    private String getIncidentDiscription(){
        EditText incidentDescription = (EditText) findViewById(R.id.Ereigniseingabe);
        if(incidentDescription.getText()!=null)
            return incidentDescription.getText().toString();
        return null;
    }
    private String getLocationValue() {
        EditText location = (EditText) findViewById(R.id.Orteingabe);
        if(location.getText()!=null)
            return location.getText().toString();
        return null;
    }
    private String getImmediateMeasureValue() {
        EditText immediateMeasure = (EditText) findViewById(R.id.Maßnahmeeingabe);
        if(immediateMeasure.getText()!=null)
            return immediateMeasure.getText().toString();
        return null;
    }
    private String getConsequencesValue() {
        EditText consequences = (EditText) findViewById(R.id.Folgeneingabe);
        if(consequences.getText()!=null)
            return consequences.getText().toString();
        return null;
    }
    private String getContactInformation(){
        EditText contactInformation = (EditText) findViewById(R.id.Kontakteingabe);
        if(contactInformation.getText()!=null)
            return contactInformation.getText().toString();
        return null;
    }

    private RiskEstimation getRiskestimationValues(){
        RiskEstimation result=new RiskEstimation();
        RadioGroup radio1 = (RadioGroup) findViewById(R.id.radioGroup1);
        RadioGroup radio2 = (RadioGroup) findViewById(R.id.radioGroup2);
        RadioGroup radio3 = (RadioGroup) findViewById(R.id.radioGroup3);

        if(radio1.getCheckedRadioButtonId()!=-1){
        RadioButton checkedOccurenceRadioButton = (RadioButton) radio1.findViewById(radio1.getCheckedRadioButtonId());
        int occurenceRate = radio1.indexOfChild(checkedOccurenceRadioButton) + 1;
        result.setOccurrenceRating(String.valueOf(occurenceRate));
        }

        if(radio2.getCheckedRadioButtonId()!=-1) {
            RadioButton checkedDetectionRadioButton = (RadioButton) radio1.findViewById(radio2.getCheckedRadioButtonId());
            int detectionRate = radio1.indexOfChild(checkedDetectionRadioButton) + 1;
            result.setDetectionRating(String.valueOf(detectionRate));
        }

        if(radio3.getCheckedRadioButtonId()!=-1) {
            RadioButton checkedSignificanceRadioButton = (RadioButton) radio1.findViewById(radio3.getCheckedRadioButtonId());
            int significanceRate = radio1.indexOfChild(checkedSignificanceRadioButton) + 1;
            result.setSignificance(String.valueOf(significanceRate));
        }
        return result;
    }

    private OpinionOfReporter getOpinionOfReporter(){
        OpinionOfReporter opinion = new OpinionOfReporter();

        EditText personalFactors = (EditText) findViewById(R.id.PFaktoreneingabe);
        if(personalFactors.getText()!=null)
        opinion.setPersonalFactors(personalFactors.getText().toString());
        EditText organisationalFactors = (EditText) findViewById(R.id.OFaktoreneingabe);
        if(organisationalFactors.getText()!=null)
        opinion.setOrganisationalFactors(organisationalFactors.getText().toString());
        EditText additionalNotes = (EditText) findViewById(R.id.Anmerkungeneingabe);
        if(additionalNotes.getText()!=null)
        opinion.setAdditionalNotes(additionalNotes.getText().toString());

        return opinion;
    }

    private PointOfTime getPointOfTimeValues(){
        PointOfTime result=new PointOfTime();
        DatePicker date = (DatePicker) findViewById(R.id.datePicker1);
        @SuppressWarnings("deprecation")
        Date pickedDate = new Date((date.getYear()-1900), (date.getMonth()+1), date.getDayOfMonth());
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.mm.yyyy");
        String dateString = dateFormatter.format(pickedDate);
        result.setDate(dateString);

        TimePicker time = (TimePicker) findViewById(R.id.timePicker1);
        String timeString = String.format("hh:mm", time.getCurrentHour(), time.getCurrentMinute()); //"%02d:%02d"
        result.setTime(timeString);

        return result;
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

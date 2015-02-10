package de.risikous.views;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.*;
import de.risikous.app.R;
import de.risikous.model.entitys.*;
import java.io.File;
import de.risikous.model.validation.QuestionaireValidation;
import de.risikous.model.validation.QuestionaireValidationResult;
import de.risikous.model.validation.QuestionaireValidationRules;
import de.risikous.views.util.FileChooser.FileChooserDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuestionnaireActivity extends Activity {

    Spinner meldekreise;
    String currentMeldekreis;
    ArrayList<String> filePaths=new ArrayList<String>();
    ArrayAdapter<String> files;
    private static final int FILE_CHOOSER = 11;

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

        Button fileUpload = (Button) findViewById(R.id.fileUpload);
        final FileChooserDialog dialog = new FileChooserDialog(this);
        fileUpload.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                dialog.loadFolder(Environment.getExternalStorageDirectory() + "/Download/");

                dialog.addListener(new FileChooserDialog.OnFileSelectedListener() {
                    public void onFileSelected(Dialog source, File file) {
                        source.hide();
                        filePaths.add(file.getAbsolutePath());
                        files.notifyDataSetChanged();
                    }
                    public void onFileSelected(Dialog source, File folder, String name) {
                        source.hide();
                        filePaths.add(folder.getAbsolutePath());
                    }
                });
                dialog.show();
            }
        });
        ListView filesView = (ListView) findViewById(R.id.filesList);
        this.files = new ArrayAdapter<String>(this,R.layout.daidalos_file_item, R.id.textViewLabel,filePaths);
        filesView.setAdapter(files);

        meldekreise = (Spinner) findViewById(R.id.spinner);
        addMeldekreisEntriesToSpinner(meldekreise);

        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker1);
        timePicker.setIs24HourView(true);
    }
    public void addMeldekreisEntriesToSpinner(Spinner spinner) {
        List<String> areas = getMeldekreiseAsStringList();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, areas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                String selected =  arg0.getItemAtPosition(arg2).toString();
                setCurrentMeldekreis(selected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
    }
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
    private void setCurrentMeldekreis(String currentMeldekreis) {
        this.currentMeldekreis = currentMeldekreis;
    }


    public void SendClicked(View view) {
        Questionaire ques = getUnvalidatedQuestionaireFromInput();
        QuestionaireValidationResult validationErrors=validateQuestioniare(ques);

        if(validationErrors.hasErrors()){
            resolveErrorMessages(validationErrors);
        }else{
            persistQuestionaire(ques);
        }
    }

    private Questionaire getUnvalidatedQuestionaireFromInput(){
        Questionaire ques=new Questionaire();
        ques.setReportingArea(getCurrentMeldekreis());
        ques.setIncidentDescription(getIncidentDiscription());
        ques.setRiskEstimation(getRiskestimationValues());
        ques.setPointOfTime(getPointOfTimeValues());
        ques.setLocation(getLocationValue());
        ques.setImmediateMeasure(getImmediateMeasureValue());
        ques.setConsequences(getConsequencesValue());
        ques.setOpinionOfReporter(getOpinionOfReporter());
        //List<File> files=null;//optional
        ques.setContactInformation(getContactInformation());
        return ques;
    }
    private String getCurrentMeldekreis() {
        return currentMeldekreis;
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
            RadioButton checkedDetectionRadioButton = (RadioButton) radio2.findViewById(radio2.getCheckedRadioButtonId());
            int detectionRate = radio2.indexOfChild(checkedDetectionRadioButton) + 1;
            result.setDetectionRating(String.valueOf(detectionRate));
        }

        if(radio3.getCheckedRadioButtonId()!=-1) {
            RadioButton checkedSignificanceRadioButton = (RadioButton) radio3.findViewById(radio3.getCheckedRadioButtonId());
            int significanceRate = radio3.indexOfChild(checkedSignificanceRadioButton) + 1;
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
        String timeString = time.getCurrentHour()+":"+ time.getCurrentMinute(); //"%02d:%02d"
        result.setTime(timeString.trim());

        return result;
    }

    private QuestionaireValidationResult validateQuestioniare(Questionaire q){
        QuestionaireValidation quesval = new QuestionaireValidation();
        QuestionaireValidationRules rules = new QuestionaireValidationRules();
        return quesval.validate(q, rules);
    }
    private void resolveErrorMessages(QuestionaireValidationResult errors){
        Toast.makeText(getBaseContext(), "Sie haben ungültige Daten eingegeben", Toast.LENGTH_LONG).show();
        TextView reportinAreaError = (TextView) findViewById(R.id.reportingAreaError);
        TextView incidentError = (TextView) findViewById(R.id.incidentError);
        TextView isOccurrenceRatingError = (TextView) findViewById(R.id.occurenceRatingError);
        TextView detectionRatingError = (TextView) findViewById(R.id.detectionRatingError);
        TextView significanceError = (TextView) findViewById(R.id.significanceRatingError);
        TextView dateError = (TextView) findViewById(R.id.dateError);
        TextView timeError = (TextView) findViewById(R.id.timeError);
        TextView locationError = (TextView) findViewById(R.id.locationError);
        TextView immediateMeasurError = (TextView) findViewById(R.id.immidiateMeassureError);
        TextView consequencesError = (TextView) findViewById(R.id.consequencesError);
        TextView personalFactorsError = (TextView) findViewById(R.id.personalFactorsError);
        TextView organisationalFactorsError = (TextView) findViewById(R.id.organisationalFactorError);
        TextView contactInformationError = (TextView) findViewById(R.id.contactError);
        TextView additionalNotesError = (TextView) findViewById(R.id.aditionalNotesError);
        if(errors.isReportingAreaError()) {
            reportinAreaError.setText(errors.getReportingAreaErrorMessage());
            reportinAreaError.setVisibility(View.VISIBLE);
            reportinAreaError.invalidate();
        }else{
            reportinAreaError.setVisibility(View.INVISIBLE);
        }
        if(errors.isIncidentDescriptionError()){
            incidentError.setText(errors.getIncidentDescriptionErrorMessage());
            incidentError.setVisibility(View.VISIBLE);
            incidentError.invalidate();
        }else{
            incidentError.setVisibility(View.INVISIBLE);
        }
        if(errors.isOccurrenceRatingError()){
            isOccurrenceRatingError.setText(errors.getOccurrenceRatingErrorMessage());
            isOccurrenceRatingError.setVisibility(View.VISIBLE);
            isOccurrenceRatingError.invalidate();
        }else{
            isOccurrenceRatingError.setVisibility(View.INVISIBLE);
        }
        if(errors.isDetectionRatingError()){
            detectionRatingError.setText(errors.getDetectionRatingErrorMessage());
            detectionRatingError.setVisibility(View.VISIBLE);
            detectionRatingError.invalidate();
        }else{
            detectionRatingError.setVisibility(View.INVISIBLE);
        }
        if(errors.isSignificanceError()){
            significanceError.setText(errors.getSignificanceErrorMessage());
            significanceError.setVisibility(View.VISIBLE);
            significanceError.invalidate();
        }else{
            significanceError.setVisibility(View.INVISIBLE);
        }
        if(errors.isDateError()){
            dateError.setText(errors.getDateErrorMessage());
            dateError.setVisibility(View.VISIBLE);
            dateError.invalidate();
        }else{
            dateError.setVisibility(View.INVISIBLE);
        }
        if(errors.isTimeError()){
            timeError.setText(errors.getTimeErrorMessage());
            timeError.setVisibility(View.VISIBLE);
            timeError.invalidate();
        }else{
            timeError.setVisibility(View.INVISIBLE);
        }
        if(errors.isLocationError()){
            locationError.setText(errors.getLocationErrorMessage());
            locationError.setVisibility(View.VISIBLE);
            locationError.invalidate();
        }else{
            locationError.setVisibility(View.INVISIBLE);
        }
        if(errors.isImmediateMeasurError()){
            immediateMeasurError.setText(errors.getImmediateMeasureErrorMessage());
            immediateMeasurError.setVisibility(View.VISIBLE);
            immediateMeasurError.invalidate();
        }else{
            immediateMeasurError.setVisibility(View.INVISIBLE);
        }
        if(errors.isConsequencesError()){
            consequencesError.setText(errors.getConsequencesErrorMessage());
            consequencesError.setVisibility(View.VISIBLE);
            consequencesError.invalidate();
        }else{
            consequencesError.setVisibility(View.INVISIBLE);
        }
        if(errors.isPersonalFactorsError()){
            personalFactorsError.setText(errors.getPersonalFactorsErrorMessage());
            personalFactorsError.setVisibility(View.VISIBLE);
            personalFactorsError.invalidate();
        }else{
            personalFactorsError.setVisibility(View.INVISIBLE);
        }
        if(errors.isOrganisationalFactorsError()){
            organisationalFactorsError.setText(errors.getOrganisationalFactorsErrorMessage());
            organisationalFactorsError.setVisibility(View.VISIBLE);
            organisationalFactorsError.invalidate();
        }else{
            organisationalFactorsError.setVisibility(View.INVISIBLE);
        }
        if(errors.isAdditionalNotesError()){
            additionalNotesError.setText(errors.getAdditionalNotesErrorMessage());
            additionalNotesError.setVisibility(View.VISIBLE);
            additionalNotesError.invalidate();
        }else{
            additionalNotesError.setVisibility(View.INVISIBLE);
        }
        if(errors.isContactInformationError()){
            contactInformationError.setText(errors.getContactInformationErrorMessage());
            contactInformationError.setVisibility(View.VISIBLE);
            contactInformationError.invalidate();
        }else{
            contactInformationError.setVisibility(View.INVISIBLE);
        }

    }

    private void persistQuestionaire(Questionaire ques){
        try {
            EntityManager em = new EntityManager();
            HTMLResponseCode response=em.persistQuestionaire(ques);
            //Toast.makeText(getBaseContext(), response.getResponseString()+" "+response.getStatusCode(), Toast.LENGTH_LONG).show();
            Toast.makeText(getBaseContext(), "Sie haben Ihren Fragebogen erfolgreich abgesendet", Toast.LENGTH_LONG).show();
            Intent pub = new Intent(getApplicationContext(), PublicationsActivity.class);
            pub.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(pub);
        }catch(Exception e){

        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == FILE_CHOOSER) && (resultCode == -1)) {
            String fileSelected = data.getStringExtra("fileSelected");
            Toast.makeText(this, fileSelected, Toast.LENGTH_SHORT).show();
        }
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

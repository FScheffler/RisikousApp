package de.risikous.views.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableRow;
import android.widget.TextView;
import de.risikous.model.entitys.EntityManager;
import de.risikous.model.entitys.OverviewEntry;
import de.risikous.views.PublicationActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;


public class OverviewEntryRowGenerator {

	private int screenWidth;
	private Context context;
	private Context activityContext;
	
	public OverviewEntryRowGenerator(Context context) {
		this.activityContext = context;
	}

	/**
	 * gets all Publications
	 * @param context context of the calling class
	 * @param screenWidth width of screen
	 * @return TableRows to be added to a TableLayout
	 * */
	@SuppressLint("ResourceAsColor")
	public LinkedList<TableRow> getPublications(ArrayList<OverviewEntry> entrys, Context context, int screenWidth) {
		setScreenWidth(screenWidth);
		setContext(context);
		LinkedList<TableRow> rows = new LinkedList<TableRow>();

        LinkedList<String> revisionDate = new LinkedList<String>();
		LinkedList<String> title = new LinkedList<String>();
		LinkedList<String> status = new LinkedList<String>();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

			for(int i=0; i<entrys.size(); i++) {
				TableRow space = new TableRow(context);
				space.setPadding(0, 1, 0, 0);
				space.setBackgroundColor(getContext().getResources().getColor(android.R.color.black));
				rows.add(space);
				
				final TableRow row = new TableRow(getContext());
				row.setGravity(Gravity.CENTER_HORIZONTAL);
                String reportDate = df.format(entrys.get(i).getRevisionDate());
				addEntry(row,reportDate);
				addTitle(row, entrys.get(i).getTitle(),entrys.get(i).getId());
				addEntry(row, entrys.get(i).getStatus());

				
				rows.add(row);
			}

		
		return rows;
	}
	
	/**
	 * adds an entry to a TableRow and sets the text
	 * @param row TableRow the TextView is added
	 * @param entry text to be set
	 * */
	private void addEntry(TableRow row, String entry) {
		TextView view = getTextView(row, true);
		view.setText(entry);
		row.addView(view);		
	}
	
	/**
	 * adds a title to a TableRow
	 * @param row TableRow the TextView is added
	 * @param title to be set
	 * */
	private void addTitle(TableRow row, String title, String id) {
		final TextView view = getTextView(row, false);
		view.setText(title);
		view.setOnClickListener(new OverViewRowClickListener(id,getActivityContext()));
		row.addView(view);		
	}
	
	/**
	 * defines a standard TextView and sets its style
	 * @param row TableRow for context
	 * @param singleLine sets single line mode or not
	 * */
	private TextView getTextView(TableRow row, boolean singleLine) {
		TextView view = new TextView(row.getContext());
		view.setTextColor(getContext().getResources().getColor(android.R.color.white));
		view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
		view.setTypeface(Typeface.DEFAULT);
		view.setGravity(Gravity.CENTER);
				
		if(singleLine) {
			view.setSingleLine(true);
		} else {
			view.setSingleLine(false);
			view.setMaxLines(10);
			view.setMaxWidth((int) (getScreenWidth()*0.5));
		}
		
		return view;
	}
	
	private int getScreenWidth() {
		return screenWidth;
	}
	
	private void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}
	
	private Context getContext() {
		return context;
	}

	private void setContext(Context context) {
		this.context = context;
	}
	
	private Context getActivityContext() {
		return activityContext;
	}
}
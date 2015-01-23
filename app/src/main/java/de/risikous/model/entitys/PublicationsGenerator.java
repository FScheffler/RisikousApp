package de.risikous.model.entitys;

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
import de.risikous.views.PublicationActivity;

import java.util.LinkedList;


public class PublicationsGenerator {

	private int screenWidth;
	private Context context;
	private Context activityContext;
	
	public PublicationsGenerator(Context context) {
		this.activityContext = context;
	}

	/**
	 * gets all Publications
	 * @param xml structure to be parsed
	 * @param context context of the calling class
	 * @param screenWidth width of screen
	 * @return TableRows to be added to a TableLayout
	 * */
	@SuppressLint("ResourceAsColor")
	public LinkedList<TableRow> getPublications(String xml, Context context, int screenWidth) {
		setScreenWidth(screenWidth);
		setContext(context);
		LinkedList<TableRow> rows = new LinkedList<TableRow>();

		EntityManager entityManager = new EntityManager();
		entityManager.getOverviewEntrys();


		// ??? publications parsen?
		//Parser parser = new Parser();
		//parser.getPublications(xml);

		//LinkedList<String> ids = entityManager.get...

		LinkedList<String> id = new LinkedList<String>();
		LinkedList<String> title = new LinkedList<String>();
		LinkedList<String> revisionDate = new LinkedList<String>();
		LinkedList<String> status = new LinkedList<String>();
		
		//LinkedList<String> ids = parser.getIds();
		//LinkedList<String> titles = parser.getTitles();
		//LinkedList<String> changed = parser.getChanged();

		
		if(id.size() != title.size() && revisionDate.size() != status.size() && id.size() != status.size()) {
			Log.d("FEHLER", "ids:" + id.size() + " titles:" + title.size() + " created:" + revisionDate.size() + " changed:" + status.size());
		}
		else {
			for(int i=0; i<id.size(); i++) {
				TableRow space = new TableRow(context);
				space.setPadding(0, 1, 0, 0);
				space.setBackgroundColor(getContext().getResources().getColor(android.R.color.black));
				rows.add(space);
				
				final TableRow row = new TableRow(getContext());
				row.setGravity(Gravity.CENTER_HORIZONTAL);
				addEntry(row, id.get(i));
				addEntry(row, revisionDate.get(i));
				addTitle(row, title.get(i));
				addEntry(row, status.get(i));

				
				rows.add(row);
			}
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
	private void addTitle(TableRow row, String title) {
		final TextView view = getTextView(row, false);
		view.setText(title);
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				TableRow row = (TableRow) view.getParent();
				TextView id = (TextView) row.getChildAt(0);
				Log.d("TOUCH", "ID: " + id.getText());
				
				Intent intent = new Intent(getActivityContext(), PublicationActivity.class);
				intent.putExtra("ID", id.getText());
				activityContext.startActivity(intent);
			}
			
		});
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
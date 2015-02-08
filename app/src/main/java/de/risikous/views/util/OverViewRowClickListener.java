package de.risikous.views.util;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;
import de.risikous.views.PublicationActivity;

/**
 * Created by Franz on 08.02.2015.
 */

public class OverViewRowClickListener implements View.OnClickListener
{

    String id;
    Context activityContext;
    public OverViewRowClickListener(String id,Context activityContext) {
        this.id = id;
        this.activityContext=activityContext;
    }

    @Override
    public void onClick(View v)
    {
        Log.d("TOUCH", "ID: " + id);
        Intent intent = new Intent(activityContext, PublicationActivity.class);
        intent.putExtra("ID", id);
        activityContext.startActivity(intent);
    }

};

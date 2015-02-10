package de.risikous.views;

/**
 * Created by Franz on 09.02.2015.
 */
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import de.risikous.app.R;
import de.risikous.model.entitys.Comment;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Franz on 11.01.2015.
 */
public class CommentsAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<Comment> listComment;

    public CommentsAdapter(Context context, List<Comment> listComment) {
        this._context = context;
        this.listComment = listComment;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.listComment.get(groupPosition).getListOfAnswers().get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final Comment childText = (Comment) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.comment_layout, null);
        }

        TextView author = (TextView) convertView.findViewById(R.id.author);
        TextView text = (TextView) convertView.findViewById(R.id.text);
        TextView timeStamp = (TextView) convertView.findViewById(R.id.timestamp_comment);

        convertView.findViewById(R.id.comm4).setVisibility(View.GONE);

        author.setText(childText.getAuthor());
        text.setText(childText.getText());
        timeStamp.setText(childText.getTimeStamp());
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listComment.get(groupPosition).getListOfAnswers().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listComment.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.listComment.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        Comment headerTitle = (Comment) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.comment_layout, null);
        }

        TextView author = (TextView) convertView.findViewById(R.id.author);
        TextView text = (TextView) convertView.findViewById(R.id.text);
        TextView timeStamp = (TextView) convertView.findViewById(R.id.timestamp_comment);

        author.setText(headerTitle.getAuthor());
        text.setText(headerTitle.getText());
        timeStamp.setText(headerTitle.getTimeStamp());

        convertView.findViewById(R.id.answercomment_Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Antworten wird noch nicht unterstützt.", Toast.LENGTH_SHORT).show();
            }
        });


        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
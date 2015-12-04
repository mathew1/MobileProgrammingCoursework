package com.example.mathew.mobileprogrammingcoursework;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by Mathew on 04/12/2015.
 */
public class TrainAdapter extends ArrayAdapter<trainData> {

    private LinkedList<trainData> alist;

    public TrainAdapter(Context context, int textViewResourceId, LinkedList<trainData> alist)
    {
     super(context, textViewResourceId, alist);
        this.alist = alist;
    }
    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        RelativeLayout row = (RelativeLayout) convertView;
        if (null == row) {
            //No recycled View, we have to inflate one.
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = (RelativeLayout) inflater.inflate(R.layout.train_layout, parent, false);
        }

        TextView trainTitle = (TextView)row.findViewById(R.id.train_Title);
        trainTitle.setText(getItem(pos).getTrainTitle());

        TextView texttrainDescription = (TextView)row.findViewById(R.id.Description);
        texttrainDescription.setText("Description:");
        TextView trainDescription = (TextView)row.findViewById(R.id.train_Description);
        trainDescription.setText(getItem(pos).getTrainDescription());

        TextView textpubDate = (TextView)row.findViewById(R.id.pubDate);
        textpubDate.setText("Publish Date:");
        TextView pubDate = (TextView)row.findViewById(R.id.pub_Date);
        pubDate.setText(getItem(pos).getTrainPubdate());

        return row;
    }
}

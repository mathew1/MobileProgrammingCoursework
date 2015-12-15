package com.example.mathew.mobileprogrammingcoursework;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Mathew on 04/12/2015.
 */
public class TrainAdapter extends ArrayAdapter<trainData> {
//Variables
    private Context context;
    private ArrayList<trainData> alist;

    //Constructor
    public TrainAdapter(Context context, ArrayList<trainData> alist)
    {
     super(context,R.layout.train_layout ,alist);
     this.context = context;
     this.alist = alist;
    }
    //Method to Get and Set positions of Data In the Rows of the ListView
@Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.train_layout, parent, false);

        TextView trainTitle = (TextView)row.findViewById(R.id.trainTitle);
        trainTitle.setText(alist.get(pos).getTrainTitle());

        TextView trainDescription = (TextView)row.findViewById(R.id.trainDescription);
        trainDescription.setText(alist.get(pos).getTrainDescription());

        TextView pubDate = (TextView)row.findViewById(R.id.trainPubDate);
        pubDate.setText(alist.get(pos).getTrainPubdate());

        return row;
    }
}

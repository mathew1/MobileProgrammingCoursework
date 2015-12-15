package com.example.mathew.mobileprogrammingcoursework;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * Created by Mathew on 08/12/2015.
 */
public class mAsyncRSSParser extends AsyncTask<String,
        Integer, ArrayList<trainData>> {
    //variables
    private Context appContext;
    private String urlRSSToParse;
//constructor
    public mAsyncRSSParser(Context currentAppContext,
                            String urlRSS)
    {
        appContext = currentAppContext;
        urlRSSToParse = urlRSS;
    }

    // A callback method executed on UI thread on
    //starting the task
    @Override
    protected void onPreExecute() {
        // Message to indicate start of parsing
        Toast.makeText(appContext, "Parsing started!",
                Toast.LENGTH_SHORT).show();
    }
//Parsing in Background
   // @Override
    protected ArrayList<trainData> doInBackground(String...
                                                   params)
    {
        ArrayList<trainData> trainItem = new ArrayList<trainData>();
        mRSSParser rssParser = new mRSSParser();
        try {
           trainItem = rssParser.parseRSSData(urlRSSToParse);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return trainItem;
    }


    // A callback method executed on UI thread, invoked
    //after the completion of the task
    // When doInbackground has completed, the return
    //value from that method is passed into this event
    // handler.
   // @Override
    protected void onPostExecute(trainData result) {
        // Message to indicate end of parsing
        Toast.makeText(appContext,"Parsing finished!",
                Toast.LENGTH_SHORT).show();
    }
}

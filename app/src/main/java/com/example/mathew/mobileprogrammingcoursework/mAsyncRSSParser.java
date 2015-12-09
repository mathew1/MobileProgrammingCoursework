package com.example.mathew.mobileprogrammingcoursework;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.net.MalformedURLException;

/**
 * Created by Mathew on 08/12/2015.
 */
public class mAsyncRSSParser extends AsyncTask<String,
        Integer, trainData> {
    private Context appContext;
    private String urlRSSToParse;

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

    @Override
    protected trainData doInBackground(String...
                                                   params)
    {
       trainData parsedData;
        mRSSParser rssParser = new mRSSParser();
        try {
            rssParser.parseRSSData(urlRSSToParse);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        parsedData = rssParser.getRSSDataItem();

        return parsedData;
    }


    // A callback method executed on UI thread, invoked
    //after the completion of the task
    // When doInbackground has completed, the return
    //value from that method is passed into this event
    // handler.
    @Override
    protected void onPostExecute(trainData result) {
        // Message to indicate end of parsing
        Toast.makeText(appContext,"Parsing finished!",
                Toast.LENGTH_SHORT).show();
    }
}

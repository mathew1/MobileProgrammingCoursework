package com.example.mathew.mobileprogrammingcoursework;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Mathew on 02/12/2015.
 */
public class parserOutputScreen extends Activity {
//Variables
    private ListView trainStationStatus;
    //private String sourceListingURL = "http://rss.journeycheck.com/scotrail/?action=search&from=&to=&period=today&formTubeUpdateLocation=&formTubeUpdatePeriod";
    ArrayList<trainData> alist;

// When the class is called to
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parser_output);


        //Get TrainStationInfo from RSS Feed
        ArrayList<trainData> alist = null;
        ArrayList<trainData> trainStation = new ArrayList<trainData>();
        String RSSFeedURL = "http://rss.journeycheck.com/scotrail/?action=search&from=&to=&period=today&formTubeUpdateLocation=&formTubeUpdatePeriod";
        mAsyncRSSParser rssAsyncParse = new mAsyncRSSParser(this,RSSFeedURL);
        try{
            alist = rssAsyncParse.execute("").get();
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        }

        // Construct the data source

// Create the adapter to convert the array to views

        trainStationStatus = (ListView) findViewById(R.id.listView);
        TrainAdapter adapter = new TrainAdapter(getApplicationContext(), alist);
        trainStationStatus.setAdapter(adapter);


    }
}

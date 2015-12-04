package com.example.mathew.mobileprogrammingcoursework;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
import java.util.LinkedList;

/**
 * Created by Mathew on 02/12/2015.
 */
public class parserOutputScreen extends Activity implements View.OnClickListener {


    private TextView response;
    private TextView errorText;
    private ListView trainStationStatus;
    private String sourceListingURL = "http://rss.journeycheck.com/scotrail/?action=search&from=&to=&period=today&formTubeUpdateLocation=&formTubeUpdatePeriod=&savedRoute=";
    LinkedList <trainData> alist;
    private String text;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parser_output);

        LinkedList <trainData> alist = null;

        trainStationStatus = (ListView) findViewById(R.id.listView);

        alist = parseData(sourceListingURL);

        ArrayAdapter<trainData> adapter = new TrainAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, alist);

        trainStationStatus.setAdapter(adapter);

        // Write list to Log for testing
        if (alist != null)
        {
            Log.e("MyTag","List not null");
            for (Object o : alist)
            {
                Log.e("MyTag",o.toString());
            }
        }
        else
        {
            Log.e("MyTag", "List is null");
        }

    }

    public LinkedList<trainData> getTrainData()
    {
        return alist;
    }

    private LinkedList<trainData> parseData(String dataToParse)
    {
        trainData train = null;
        alist = new LinkedList<trainData>();

        try
        {
            dataToParse = sourceListingString(sourceListingURL);
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(dataToParse));
            int eventType = xpp.getEventType();

            while(eventType != XmlPullParser.END_DOCUMENT){

                String tagname = xpp.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:

                        if(tagname.equalsIgnoreCase("item")){
                            train = new trainData();
                        }
                        break;

                    case XmlPullParser.TEXT:

                        text = xpp.getText();
                        break;

                    case XmlPullParser.END_TAG:

                        if(tagname.equalsIgnoreCase("item")) {
                            Log.e("MyTag","Item Start Tag found");
                            alist.add(train);
                        }else if(tagname.equalsIgnoreCase("title")) {

                            train.setTrainTitle(text);
                        }else if(tagname.equalsIgnoreCase("description")) {

                            train.setTrainDescription(text);
                        }else if(tagname.equalsIgnoreCase("pubDate")) {

                            train.setTrainPubdate(text);
                        }
                        break;
                    default:
                        break;
                }
                eventType = xpp.next();
                }
            }

        catch(XmlPullParserException ae1)
        {
            Log.e("MyTag","Parsing error" + ae1.toString());
        }
        catch(IOException ae)
        {
            // Handle error
            response.setText("Error");
            // Add error info to log for diagnostics
            errorText.setText(ae.toString());
            Log.e("MyTag","IO error during parsing");
        }
        Log.e("MyTag","End document");

        return alist;
    }



    private static String sourceListingString(String urlString)throws IOException
    {
        String result = "";
        InputStream anInStream = null;
        int response = -1;
        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();

        // Check that the connection can be opened
        if (!(conn instanceof HttpURLConnection))
            throw new IOException("Not an HTTP connection");
        try
        {
            // Open connection
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            response = httpConn.getResponseCode();
            // Check that connection is Ok
            if (response == HttpURLConnection.HTTP_OK)
            {
                // Connection is Ok so open a reader
                anInStream = httpConn.getInputStream();
                InputStreamReader in= new InputStreamReader(anInStream);
                BufferedReader bin= new BufferedReader(in);

                // Read in the data from the XML stream
                bin.readLine(); // Throw away the header
                String line = new String();
                while (( (line = bin.readLine())) != null)
                {
                    result = result + "\n" + line;
                }
            }
        }
        catch (Exception ex)
        {
            throw new IOException("Error connecting");
        }

        // Return result as a string for further processing
        return result;

    } // End of sourceListingString

    @Override
    public void onClick(View v) {

    }
}

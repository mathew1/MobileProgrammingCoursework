package com.example.mathew.mobileprogrammingcoursework;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Mathew on 08/12/2015.
 */
public class mRSSParser {
    private trainData RSSDataItem;
   ArrayList<trainData> trainItems = new ArrayList<trainData>();

    public void setRSSDataItem(String sItemData)
    {
        RSSDataItem.setTrainTitle(sItemData);
        RSSDataItem.setTrainDescription(sItemData);
        RSSDataItem.setTrainPubdate(sItemData);
    }

    public trainData getRSSDataItem()
    {
        return this.RSSDataItem;
    }

    public mRSSParser()
    {
        this.RSSDataItem =  new trainData();
        setRSSDataItem(null);
    }

    public ArrayList<trainData> parseRSSDataItem(XmlPullParser theParser, int theEventType)
    {
        RSSDataItem = new trainData();
        try
        {
            while (theEventType != XmlPullParser.END_DOCUMENT)
            {
                if(RSSDataItem.getTrainPubdate() != "")
                {
                    trainItems.add(RSSDataItem);
                    RSSDataItem = new trainData();
                }
                // Found a start tag
                if(theEventType == XmlPullParser.START_TAG)
                {
                    // Check which Tag has been found
                    if (theParser.getName().equalsIgnoreCase("title"))
                    {
                        // Now just get the associated text
                        String temp = theParser.nextText();
                        // store data in class
                        RSSDataItem.setTrainTitle(temp);
                        Log.e("MyTag", "trainTitle is" + temp);
                    }
                    else
                        // Check which Tag we have
                        if (theParser.getName().equalsIgnoreCase("description"))
                        {
                            // Now just get the associated text
                            String temp = theParser.nextText();
                            // store data in class
                            RSSDataItem.setTrainDescription(temp);
                            Log.e("MyTag","trainDescription is" + temp);
                        }
                        else
                            // Check which Tag we have
                            if (theParser.getName().equalsIgnoreCase("pubDate"))
                            {
                                // Now just get the associated text
                                String temp = theParser.nextText();
                                // store data in class
                                RSSDataItem.setTrainPubdate(temp);
                                Log.e("MyTag", "trainPubdate is" + temp);
                            }
                }

                // Get the next event
                theEventType = theParser.next();

            } // End of while

        }
        catch (XmlPullParserException parserExp1)
        {
            Log.e("MyTag", "Parsing error" + parserExp1.toString());
        }

        catch (IOException parserExp1)
        {
            Log.e("MyTag","IO error during parsing");
        }
     return trainItems;
    }

    public ArrayList<trainData> parseRSSData(String RSSItemsToParse) throws MalformedURLException {
        URL rssURL = new URL(RSSItemsToParse);
        InputStream rssInputStream;
        ArrayList<trainData> temp = new ArrayList<trainData>();
        try
        {
            XmlPullParserFactory parseRSSfactory = XmlPullParserFactory.newInstance();
            parseRSSfactory.setNamespaceAware(true);
            XmlPullParser RSSxmlPP = parseRSSfactory.newPullParser();
            String xmlRSS = getStringFromInputStream(getInputStream(rssURL), "UTF-8");
            RSSxmlPP.setInput(new StringReader(xmlRSS));
            int eventType = RSSxmlPP.getEventType();

            temp = parseRSSDataItem(RSSxmlPP,eventType);

        }
        catch (XmlPullParserException ae1)
        {
            Log.e("MyTag","Parsing error" + ae1.toString());
        }
        catch (IOException ae1)
        {
            Log.e("MyTag","IO error during parsing");
        }


        Log.e("MyTag","End document");
        return temp;
    }

    public InputStream getInputStream(URL url) throws IOException
    {
        return url.openConnection().getInputStream();
    }

    public static String getStringFromInputStream(InputStream stream, String charsetName) throws IOException
    {
        int n = 0;
        char[] buffer = new char[1024 * 4];
        InputStreamReader reader = new InputStreamReader(stream, charsetName);
        StringWriter writer = new StringWriter();
        while (-1 != (n = reader.read(buffer))) writer.write(buffer, 0, n);
        return writer.toString();
    }
}

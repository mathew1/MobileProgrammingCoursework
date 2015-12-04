package com.example.mathew.mobileprogrammingcoursework;

/**
 * Created by Mathew on 04/12/2015.
 */
public class trainData {

    public String trainTitle;
    public String trainDescription;
    public String trainPubdate;

    public trainData()
    {
        trainTitle = "";
        trainDescription = "";
        trainPubdate = "";
    }

    public trainData(String aTrainTitle, String aTrianDescription, String aTrainPubdate)
    {
        trainTitle = aTrainTitle;
        trainDescription = aTrianDescription;
        trainPubdate = aTrainPubdate;
    }

    public String getTrainTitle()
    {
        return trainTitle;
    }

    public void setTrainTitle(String aTrainTitle)
    {
        trainTitle = aTrainTitle;
    }

    public String getTrainDescription()
    {
        return trainDescription;
    }

    public void setTrainDescription(String aTrainDescription)
    {
        trainDescription = aTrainDescription;
    }

    public String getTrainPubdate()
    {
        return trainPubdate;
    }

    public void setTrainPubdate(String aTrainPubdate)
    {
        trainPubdate = aTrainPubdate;
    }

    public String toString()
    {
        String temp;

        temp = trainTitle + "" + trainDescription + "" + trainPubdate;

        return temp;
    }
}

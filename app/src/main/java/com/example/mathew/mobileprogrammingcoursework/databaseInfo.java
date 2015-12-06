package com.example.mathew.mobileprogrammingcoursework;

import java.io.Serializable;

/**
 * Created by Mathew on 05/12/2015.
 */
public class databaseInfo implements Serializable {

    private int trainID;
    private String trainName;
    private String trainAddress;
    private String trainImage;

    private static final long serialVersionUID = 0L;

    public int getTrainID()
    {
        return trainID;
    }

    public void setTrainID(int trainID){this.trainID = trainID;}

    public String getTrainName(){ return trainName;}

    public void setTrainName(String trainName){this.trainName = trainName;}

    public String getTrainAddress(){return trainAddress;}

    public void setTrainAddress(String trainAddress){this.trainAddress = trainAddress;}

    public String getTrainImage(){return trainImage;}

    public void setTrainImage(String trainImage){this.trainImage = trainImage;}

    @Override
    public String toString(){
        String trainData;
        trainData = "databaseInfo [trainID=" + trainID;
        trainData = ", trainName=" + trainName;
        trainData = ", trainAddress=" + trainAddress;
        trainData = ", trainImage=" + trainImage;
        return trainData;
    }

}

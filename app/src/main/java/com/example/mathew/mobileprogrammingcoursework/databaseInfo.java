package com.example.mathew.mobileprogrammingcoursework;

import java.io.Serializable;

/**
 * Created by Mathew on 05/12/2015.
 */
public class databaseInfo implements Serializable {
// Variables
    private int trainID;
    private String trainName;
    private String trainAddress;
    private String trainPostCode;
    private String trainCity;
    private float Latitude;
    private float Longitude;
//Getters and Setters
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

    public String getTrainPostCode() { return trainPostCode; }

    public void setTrainPostCode(String trainName){this.trainName = trainName;}

    public String getTrainCity(){ return trainCity;}

    public void setTrainCity(String trainCity) { this.trainCity = trainCity;}

    public float getLatitude(){
        return Latitude;
    }

    public void setLatitude(float Lat)
    {
        this.Latitude = Lat;
    }

    public float getLongitude()
    {
        return Longitude;
    }

    public void setLongitude(float Flongitude)
    {
        this.Longitude = Flongitude;
    }
//Coverting to String
    @Override
    public String toString(){
        String trainInfo;
        trainInfo = "databaseInfo [trainstationID=" + trainID;
        trainInfo = ", TrainStation=" + trainName;
        trainInfo = ", TrainStationAddress=" + trainAddress;
        trainInfo = ", TrainPostCode=" + trainPostCode;
        trainInfo = ". TrainCity=" + trainCity;
        trainInfo = ", latitude=" + Latitude;
        trainInfo = ", longitude=" + Longitude +"]";
        return trainInfo;
    }

}

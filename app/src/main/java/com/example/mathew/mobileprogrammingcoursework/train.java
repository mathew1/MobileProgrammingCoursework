package com.example.mathew.mobileprogrammingcoursework;

/**
 * Created by Mathew on 08/12/2015.
 */
public class train {

    private int iTrain;

    private String sTrain;

    public void setiTrain(int iTrain) { this.iTrain = iTrain;}

    public int getiTrain(){ return iTrain;}

    public void setsTrain(String sTrain) { this.sTrain = sTrain;}

    public String getsTrain() { return sTrain;}

    public train()
    {
        determineTrainStation(0);
    }
    public train(int aTrain){determineTrainStation(aTrain);}

    public void determineTrainStation(int ssTrain)
    {
        switch(ssTrain)
        {
            case 1:
                if (ssTrain == 0 )
                {
                    sTrain = "Central Station";
                }
                break;
            case 2:
                if (ssTrain == 1)
                {
                    sTrain = "Queen Street";
                }
                break;
            default:
                sTrain = "Sorry I dont recognise this data";
        }
    }

}

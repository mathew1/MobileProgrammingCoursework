package com.example.mathew.mobileprogrammingcoursework;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Mathew on 04/12/2015.
 */
public class databaseOutputScreen extends Activity implements View.OnClickListener {

    Button mainScreen;
    ImageView trainImage;
    TextView trianStationName;
    TextView trainStationAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_output);

        mainScreen = (Button) findViewById(R.id.btnMainPage);
        mainScreen.setOnClickListener(this);

        trainImage = (ImageView) findViewById(R.id.imgViewTrainStation);
        trianStationName = (TextView) findViewById(R.id.trainStationTitle);
        trainStationAddress = (TextView) findViewById(R.id.trainStationAddress);
    }


    @Override
    public void onClick(View v) {
        setResult(Activity.RESULT_OK);
        finish();
    }
}

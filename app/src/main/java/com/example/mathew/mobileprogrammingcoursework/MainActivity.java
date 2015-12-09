package com.example.mathew.mobileprogrammingcoursework;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.io.IOException;
import java.util.zip.Inflater;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    Button trainStatusBtn;
    Button trainInfoBtn;
    FragmentManager fmAboutDialogue;
    Spinner spinnerTrain;
    databaseInfo trainDatabaseInfo;
    String[] trains;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerTrain = (Spinner) findViewById(R.id.spinner);


        trains = getResources().getStringArray(R.array.train_stations);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, trains);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerTrain.setAdapter(dataAdapter);
        spinnerTrain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        trainStatusBtn = (Button) findViewById(R.id.trainStatus);
        trainStatusBtn.setOnClickListener(this);

        trainInfoBtn = (Button) findViewById(R.id.trainInfo);
        trainInfoBtn.setOnClickListener(this);

        fmAboutDialogue = this.getFragmentManager();

        trainDatabaseInfo = new databaseInfo();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.trainStatus:
            Intent parserOutput_Screen = new Intent(getApplicationContext(), parserOutputScreen.class);
            startActivity(parserOutput_Screen);
                break;
            case R.id.trainInfo:
                databaseInfoDBMgr dbTrainMgr = new databaseInfoDBMgr(this, "trainstations.s3db", null,1);
                try{
                    dbTrainMgr.dbCreate();
                } catch (IOException e){
                    e.printStackTrace();
                }

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
       inflater.inflate(R.menu.mp_menu, menu);
        return true;

       // getMenuInflater().inflate(R.menu.mp_menu, menu);
        //return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
      switch (item.getItemId()){
          case R.id.mMap:
              Intent mcMap = new Intent(this, mapActivity.class);
              this.startActivity(mcMap);
              return true;
          case R.id.mBio:
              Intent mcBioDraw = new Intent(this, surfaceActivity.class);
              this.startActivity(mcBioDraw);
              return true;
          case R.id.mQuit:
              finish();
              return true;
          case R.id.mAbout:
              //About Dialog
              DialogFragment mcAboutDlg = new mcAbout();
              mcAboutDlg.show(fmAboutDialogue,"mc_About_Dlg");
              return true;
          default:
              return super.onOptionsItemSelected(item);
      }



    }



}

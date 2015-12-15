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

    // Variables
    Button trainStatusBtn;
    Button trainInfoBtn;
    FragmentManager fmAboutDialogue;

    databaseInfo trainDatabaseInfo;
    //Creating the Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trainStatusBtn = (Button) findViewById(R.id.trainStatus);
        trainStatusBtn.setOnClickListener(this);

        trainInfoBtn = (Button) findViewById(R.id.trainInfo);
        trainInfoBtn.setOnClickListener(this);

        fmAboutDialogue = this.getFragmentManager();

        trainDatabaseInfo = new databaseInfo();
    }
    //Functions when buttons are pressed
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.trainStatus:
            Intent parserOutput_Screen = new Intent(getApplicationContext(), parserOutputScreen.class);
            startActivity(parserOutput_Screen);
                break;
            case R.id.trainInfo:
            Intent mapActivity_Screen = new Intent(getApplicationContext(),mapActivity.class);
            startActivity(mapActivity_Screen);
                break;
        }
    }
    //Inflating mp_menu to create options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
       inflater.inflate(R.menu.mp_menu, menu);
        return true;

       // getMenuInflater().inflate(R.menu.mp_menu, menu);
        //return true;
    }
    //Functions when items are selected.
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

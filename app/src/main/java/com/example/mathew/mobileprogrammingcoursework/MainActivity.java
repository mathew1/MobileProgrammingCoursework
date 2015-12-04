package com.example.mathew.mobileprogrammingcoursework;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.zip.Inflater;

public class MainActivity extends Activity implements View.OnClickListener {

    Button trainStatus;
    FragmentManager fmAboutDialogue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trainStatus = (Button) findViewById(R.id.trainStatus);
        trainStatus.setOnClickListener(this);

        fmAboutDialogue = this.getFragmentManager();
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

    @Override
    public void onClick(View v) {
        Intent parserOutput_Screen = new Intent(getApplicationContext(), parserOutputScreen.class);
        startActivity(parserOutput_Screen);
    }
}

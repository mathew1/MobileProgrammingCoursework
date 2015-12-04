package com.example.mathew.mobileprogrammingcoursework;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

/**
 * Created by Mathew on 02/12/2015.
 */

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class mcAbout extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder mcAboutDialog = new AlertDialog.Builder(getActivity());
        mcAboutDialog.setMessage("This app wil give you information about train stations in Glasgow, It will also give the status of travelling trains in Scotland, A map displaying the location of the train stations will also be available....")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        mcAboutDialog.setTitle("About");
        mcAboutDialog.setIcon(R.drawable.ic_menu_action_about);
        // Create the AlertDialog object and return it
        return mcAboutDialog.create();
    }
}

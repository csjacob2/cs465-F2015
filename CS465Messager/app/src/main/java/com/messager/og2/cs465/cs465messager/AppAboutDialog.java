package com.messager.og2.cs465.cs465messager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

/**
 * Created by mpc on 11/29/2015.
 */
public class AppAboutDialog extends DialogFragment {
    private Context context;
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.app_about_layout, null);

        //*******************************************************************************
        //******************************* AlertBuilder begin ****************************
        //*******************************************************************************
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(promptsView);
        builder//.setMessage(R.string.notify)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });        return builder.create();
        //*******************************************************************************
        //******************************* AlertBuilder end ******************************
        //*******************************************************************************
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }

}

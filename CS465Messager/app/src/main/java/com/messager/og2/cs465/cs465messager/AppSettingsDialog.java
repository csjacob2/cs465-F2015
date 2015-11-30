package com.messager.og2.cs465.cs465messager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by mpc on 11/29/2015.
 */
public class AppSettingsDialog extends DialogFragment {
    private Context context;
    NumberPicker np;
    ToggleButton tb;
    ImageView iv;
    View promptsView;
    private final int CONTACT_PICKER_ACTION = 1001;


    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        LayoutInflater li = LayoutInflater.from(context);
        promptsView = li.inflate(R.layout.app_settings_layout, null);

        np = (NumberPicker) promptsView.findViewById(R.id.settings_np);
        np.setMaxValue(10);
        np.setMinValue(1);

        tb = (ToggleButton) promptsView.findViewById(R.id.settings_alerts_toggle);
        iv = (ImageView) promptsView.findViewById(R.id.user_profile_pic);

        np.setValue(Constants.appSettings.contactViewLimit);
        iv.setImageBitmap(Constants.appSettings.profilePicture);
        tb.setChecked(Constants.appSettings.alertsFlag);
        //*******************************************************************************
        //******************************* AlertBuilder begin ****************************
        //*******************************************************************************
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(promptsView);
        builder//.setMessage(R.string.notify)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Constants.appSettings.contactViewLimit = np.getValue();
                        Constants.appSettings.alertsFlag = tb.isChecked();
                        Constants.appSettings.profilePicture = ((BitmapDrawable)iv.getDrawable()).getBitmap();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        return builder.create();
        //*******************************************************************************
        //******************************* AlertBuilder end ******************************
        //*******************************************************************************
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }

    @Override
    public void onStart() {
        super.onStart();
        iv = (ImageView) promptsView.findViewById(R.id.user_profile_pic);
        iv.setImageBitmap(Constants.appSettings.profilePicture);

    }

    public void update() {
        promptsView.invalidate();
    }
}

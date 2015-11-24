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
import java.util.Collections;

import java.util.HashMap;
import java.util.Map;
import android.widget.TextView;

/**
 * Created by beer on 11/23/15.
 */
public class NotificationFreqDialog extends DialogFragment {
    private Context context;
    private NumberPicker np;
    private Contact contact;
    private final static String[] options = {
        "24 hrs",
        "2 days",
        "3 days",
        "4 days",
        "5 days",
        "6 days",
        "1 week",
        "2 weeks",
        "3 weeks",
        "4 weeks"
    };
    private static final Map<String, Integer> reminder_freq;        // Key is string in options array, value is reminder frequency in days
    static {
        Map<String, Integer> temp = new HashMap<String, Integer>();
        temp.put(options[0], 1);
        temp.put(options[1], 2);
        temp.put(options[2], 3);
        temp.put(options[3], 4);
        temp.put(options[4], 5);
        temp.put(options[5], 6);
        temp.put(options[6], 7);
        temp.put(options[7], 14);
        temp.put(options[8], 21);
        temp.put(options[9], 28);
        reminder_freq = Collections.unmodifiableMap(temp);
    }
    private static final Map<Integer, Integer> options_lookup;            // Key is reminder frequency in days, value is integer index of options array for associated string
    static {
        Map<Integer, Integer> temp = new HashMap<Integer, Integer>();
        temp.put(1, 0);
        temp.put(2, 1);
        temp.put(3, 2);
        temp.put(4, 3);
        temp.put(5, 4);
        temp.put(6, 5);
        temp.put(7, 6);
        temp.put(14, 7);
        temp.put(21, 8);
        temp.put(28, 9);
        options_lookup = Collections.unmodifiableMap(temp);
    }

    public NotificationFreqDialog(Contact person) {
        super();
        this.contact = person;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.fragment_notification_freq, null);

        //*******************************************************************************
        //******************************* Numpicker begin *******************************
        //*******************************************************************************
        np = (NumberPicker) promptsView.findViewById(R.id.np);
        np.setMaxValue(options.length - 1);
        np.setMinValue(0);
        np.setWrapSelectorWheel(true);
        np.setDisplayedValues(options);
        np.setValue(options_lookup.get(contact.getReminder_freq())); //Sets initial value to previously selected value
        //*******************************************************************************
        //******************************* Numpicker end *********************************
        //*******************************************************************************

        // Set profile picture and name
        TextView contact_name  = (TextView) promptsView.findViewById(R.id.contact_name_textview);
        contact_name.append(" " + contact.getName());
        ImageView img= (ImageView) promptsView.findViewById(R.id.contact_image);
        img.setImageResource(contact.getProfile_image());

        //*******************************************************************************
        //******************************* AlertBuilder begin ****************************
        //*******************************************************************************
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(promptsView);
        builder//.setMessage(R.string.notify)
                .setPositiveButton(R.string.set, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        int val = np.getValue();
                        contact.setReminder_freq(reminder_freq.get(options[val]));
                        System.out.println("Value: " + contact.getReminder_freq());
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
        context=activity;
    }
}
package com.messager.og2.cs465.cs465messager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ContactSettingsActivity extends Activity {
    ArrayList<Integer> defaultImages;
    private ImageView profilePic;
    private ImageView mapPic;
    EditText nameEditText;
    EditText phoneEditText;
    EditText emailEditText;


    private final int PHOTO_PICKER_ACTION = 1000;
    private final int CONTACT_PICKER_ACTION = 1001;

    private Random rand = new Random();
    Person person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_settings);

        //grab references to appropriate elements
        profilePic = (ImageView) this.findViewById(R.id.profilePic);
        nameEditText = (EditText) this.findViewById(R.id.name_edit_text);
        phoneEditText = (EditText) this.findViewById(R.id.phone_edit_text);
        emailEditText = (EditText) this.findViewById(R.id.email_edit_text);

        //just some setup for default images
        defaultImages = new ArrayList<Integer>();
        defaultImages.add(R.drawable.def01);
        defaultImages.add(R.drawable.def02);
        defaultImages.add(R.drawable.def03);
        defaultImages.add(R.drawable.def04);
        defaultImages.add(R.drawable.def05);
        defaultImages.add(R.drawable.def06);
        defaultImages.add(R.drawable.def07);
        defaultImages.add(R.drawable.def08);
        defaultImages.add(R.drawable.def09);
        defaultImages.add(R.drawable.def10);
        defaultImages.add(R.drawable.def11);
        int defImage = Math.abs(rand.nextInt() % 11);

        //setting up contact based off info passed in
        person = new Person(
            getIntent().getStringExtra("contactName"),
            getIntent().getIntExtra("contactProfileImage", defaultImages.get(defImage)),
            getIntent().getStringExtra("contactPhoneNumber"),
            getIntent().getStringExtra("contactEmail")
        );

        person.profilePic = (Bitmap) getIntent().getParcelableExtra("contactProfileImageBitmap");
        person.contactPicMapping = (HashMap<String, Bitmap>)getIntent().getSerializableExtra("contactMapping");

        if (person.contactPicMapping!= null && person.contactPicMapping.size() > 0) {
            Set<String> keySet = person.contactPicMapping.keySet();
            String[] keys = keySet.toArray(new String[keySet.size()]);
            EditText actionText1 = (EditText)this.findViewById(R.id.action_text_1);
            EditText actionText2 = (EditText)this.findViewById(R.id.action_text_2);
            EditText actionText3 = (EditText)this.findViewById(R.id.action_text_3);

            ImageView actionImage1 = (ImageView)this.findViewById(R.id.action_image_1);
            ImageView actionImage2 = (ImageView)this.findViewById(R.id.action_image_2);
            ImageView actionImage3 = (ImageView)this.findViewById(R.id.action_image_3);

            if (person.contactPicMapping.size() >= 1) {
                actionText1.setText(keys[0]);
                actionImage1.setImageBitmap(person.contactPicMapping.get(keys[0]));
            }

            if (person.contactPicMapping.size() >= 2) {
                actionText2.setText(keys[1]);
                actionImage2.setImageBitmap(person.contactPicMapping.get(keys[1]));
            }

            if (person.contactPicMapping.size() == 3) {
                actionText3.setText(keys[2]);
                actionImage3.setImageBitmap(person.contactPicMapping.get(keys[2]));
            }
        }

        //setup contact to be shown
        nameEditText.setText(person.name);
        phoneEditText.setText(person.phoneNumber);
        emailEditText.setText(person.email);
        profilePic.setImageBitmap(person.profilePic);

    }

    public void changeProfilePic(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        mapPic = (ImageView)view;
        startActivityForResult(intent, CONTACT_PICKER_ACTION);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode)
        {
            case PHOTO_PICKER_ACTION:
                if (resultCode == RESULT_OK)
                {
                    try {
                        final Uri imageUri = data.getData();
                        final InputStream imgStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imgStream);
                        profilePic.setImageBitmap(selectedImage);
                    }
                    catch (FileNotFoundException fnfEx) {

                        fnfEx.printStackTrace();
                    }
                }
                break;
            case CONTACT_PICKER_ACTION:
                if (resultCode == RESULT_OK)
                {
                    try {
                        final Uri imageUri = data.getData();
                        final InputStream imgStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imgStream);
                        mapPic.setImageBitmap(selectedImage);
                    }
                    catch (FileNotFoundException fnfEx) {

                        fnfEx.printStackTrace();
                    }
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        this.setActivityResult();
    }

    private void setActivityResult() {
        Intent intent = new Intent();
        intent.putExtra("contactName", nameEditText.getText() + "");
        intent.putExtra("contactEmail", emailEditText.getText() + "");
        intent.putExtra("contactPhoneNumber", phoneEditText.getText() + "");
        Bitmap bm = ((BitmapDrawable)profilePic.getDrawable()).getBitmap();
        intent.putExtra("contactProfileImageBitmap", bm);
        if (person.contactPicMapping.size() > 0) {
            intent.putExtra("contactMapping", person.contactPicMapping);
        }
        this.setResult(RESULT_OK, intent);
        this.finish();
    }

    public void viewChatPicMappingClicked(View v) {
        this.toggleViews(false);
    }

    public void frequencySettingsClicked(View v) {
        NotificationFreqDialog nf = new NotificationFreqDialog(person);
        nf.show(getFragmentManager(), "notification_freq_dialog");
    }

    public void saveContactSettingsClicked(View v) {
        this.setActivityResult();
    }

    public void cancelContactSettingsClicked(View v) {
        this.setResult(RESULT_CANCELED);
        this.finish();
    }

    public void saveContactMappingsClicked(View v) {
        person.contactPicMapping.clear();

        EditText actionText1 = (EditText)this.findViewById(R.id.action_text_1);
        EditText actionText2 = (EditText)this.findViewById(R.id.action_text_2);
        EditText actionText3 = (EditText)this.findViewById(R.id.action_text_3);

        ImageView actionImage1 = (ImageView)this.findViewById(R.id.action_image_1);
        ImageView actionImage2 = (ImageView)this.findViewById(R.id.action_image_2);
        ImageView actionImage3 = (ImageView)this.findViewById(R.id.action_image_3);

        if (!actionText1.getText().toString().matches("")) {
            person.contactPicMapping.put(actionText1.getText().toString(), ((BitmapDrawable)actionImage1.getDrawable()).getBitmap());
        }

        if (!actionText2.getText().toString().matches("")) {
            person.contactPicMapping.put(actionText2.getText().toString(), ((BitmapDrawable)actionImage2.getDrawable()).getBitmap());
        }

        if (!actionText3.getText().toString().matches("")) {
            person.contactPicMapping.put(actionText3.getText().toString(), ((BitmapDrawable)actionImage3.getDrawable()).getBitmap());
        }

        this.toggleViews(true);
    }

    public void cancelContactMappingsClicked(View v) {
        this.toggleViews(true);
    }

    private void toggleViews(boolean showOverallSettings) {
        if (showOverallSettings) {
            ScrollView svOverallSettings = (ScrollView)this.findViewById(R.id.contact_overall_settings);
            svOverallSettings.setVisibility(View.VISIBLE);

            ScrollView svChatPicMappings = (ScrollView)this.findViewById(R.id.contact_pic_mappings);
            svChatPicMappings.setVisibility(View.GONE);
        }
        else {
            ScrollView svOverallSettings = (ScrollView)this.findViewById(R.id.contact_overall_settings);
            svOverallSettings.setVisibility(View.GONE);

            ScrollView svChatPicMappings = (ScrollView)this.findViewById(R.id.contact_pic_mappings);
            svChatPicMappings.setVisibility(View.VISIBLE);
        }
    }

}




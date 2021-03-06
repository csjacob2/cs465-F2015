package com.messager.og2.cs465.cs465messager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener{
    Spinner spinner;
    RelativeLayout mainLayout;

    private final int CONTACT_PICKER_ACTION = 1001;
    private final int USER_PROFILE_PICKER_ACTION = 1002;

    static Person me = new Person("Me",  R.drawable.ppc1);
    static List<Person> contacts = new LinkedList<Person>();
    ImageView iv;
    AppSettingsDialog appSettingsDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contacts = new LinkedList<Person>(Arrays.asList(
                new Person("Bob", R.drawable.ppc2, BitmapFactory.decodeResource(this.getResources(), R.drawable.ppc2)),
                new Person("Mom", R.drawable.ppc3, BitmapFactory.decodeResource(this.getResources(), R.drawable.ppc3))
        ));
        mainLayout = (RelativeLayout)this.findViewById(R.id.content_layout);
        spinner = (Spinner)this.findViewById(R.id.group_spinner);
        spinner.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, Constants.spinnerList));

        // Add the contacts/seeds to the main view in predefined locations.
        addSeed(R.id.diag_far, contacts.get(0));    // Bob
        addSeed(R.id.bottom_near, contacts.get(1)); // Mom

        if (Constants.appSettings.profilePicture == null) {
            Constants.appSettings.profilePicture = BitmapFactory.decodeResource(this.getResources(), R.drawable.ppc1);
        }

        ImageView iv = (ImageView)this.findViewById(R.id.button);
        iv.setImageBitmap(Constants.appSettings.profilePicture);
    }

    // Instantiates a seed view and adds it to the main screen. layoutId is the ID of some
    // layout on the main screen that you want to add the seed to.
    private void addSeed(int layoutId, final Person person)
    {
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout)findViewById(layoutId);
        View seed = inflater.inflate(R.layout.seed, null);

        ((TextView)seed.findViewById(R.id.name)).setText(person.name);
        ((ImageView)seed.findViewById(R.id.image)).setImageBitmap(person.profilePic);

        // When the user clicks on a contact/seed, open the conversation with the contact.
        seed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConversationActivity.class);
                intent.putExtra("CONVERSATION_IDX", contacts.indexOf(person));
                startActivity(intent);
            }
        });

        // When the user long-clicks on a contact/seed, open the notification configuration dialog.
        seed.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                NotificationFreqDialog nf = new NotificationFreqDialog(person);
                nf.show(getFragmentManager(), "notification_freq_dialog");
                return true;
            }
        });

        layout.addView(seed);
        person.seedLocation = layoutId;
    }

    // Instantiates a seed view and adds it to the main screen. layoutId is the ID of some
    // app_settings_layout on the main screen that you want to add the seed to.
    private void moveSeed(int newLayoutId, final Person person)
    {
        LinearLayout layout = (LinearLayout)findViewById(person.seedLocation);


        layout.removeAllViews();

        addSeed(newLayoutId, person);
    }

    public void clickcheck(View v) {
        System.out.println("###############h:" + mainLayout.getMeasuredHeight() + "|w:" + mainLayout.getMeasuredWidth());
        System.out.println("###############h:" + mainLayout.getHeight() + "|w:" + mainLayout.getWidth());
    }

    // Handles the user clicking the button in the top bar to open the MessageViewActivity.
    public void openMessageView(View v) {
        Intent myIntent = new Intent(MainActivity.this, MessageViewActivity.class);
        this.startActivity(myIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch(position) {
            case 3:
                Toast.makeText(this, "clicked new group", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onResume() {
        super.onResume();

        // If the user has sent a message to Bob, move him near the dandelion.
        if (MessageViewActivity.conversations[0].timestamp == 0) {
            moveSeed(R.id.diag_near, contacts.get(0));
        }
        else {
            moveSeed(R.id.diag_far, contacts.get(0));
        }

        if (Constants.appSettings.profilePicture == null) {
            Constants.appSettings.profilePicture = BitmapFactory.decodeResource(this.getResources(), R.drawable.ppc1);
        }

        ImageView iv = (ImageView)this.findViewById(R.id.button);
        iv.setImageBitmap(Constants.appSettings.profilePicture);

        // TODO: Move people around for other reasons?
    }

    public void getContactsClicked(View v) {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, CONTACT_PICKER_ACTION);
    }

    public void getSettingsClicked(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.action_addcontact:
                        getContactsClicked(null);
                        break;
                    case R.id.action_settings:
                        userProfilePicClicked(null);
                        break;
                    case R.id.action_about:
                        AppAboutDialog appAboutDialog = new AppAboutDialog();
                        appAboutDialog.show(getFragmentManager(), "app about dialog");
                        break;


                }
                return true;
            }
        });
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_main, popup.getMenu());
        popup.show();    }

    public void userProfilePicClicked(View v) {
        appSettingsDialog = new AppSettingsDialog();
        appSettingsDialog.show(getFragmentManager(), "app settings dialog");
        iv = (ImageView) v;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode)
        {
            case CONTACT_PICKER_ACTION:
                if (resultCode == RESULT_OK)
                {

                }
                break;
            case USER_PROFILE_PICKER_ACTION:
                if (resultCode == RESULT_OK)
                {
                    try {
                        final Uri imageUri = data.getData();
                        final InputStream imgStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imgStream);
                        if (iv != null)
                            iv.setImageBitmap(selectedImage);
                        appSettingsDialog.update();
                    }
                    catch (FileNotFoundException fnfEx) {

                        fnfEx.printStackTrace();
                    }
                }
                break;

        }
    }

    public void settingsUserProfilePicClicked(View v) {
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType("image/*");
//        startActivityForResult(intent, this.USER_PROFILE_PICKER_ACTION);
    }


}

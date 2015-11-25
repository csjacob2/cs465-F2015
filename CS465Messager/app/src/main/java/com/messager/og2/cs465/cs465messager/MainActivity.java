package com.messager.og2.cs465.cs465messager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener{
    Spinner spinner;
    RelativeLayout mainLayout;

    static Person me = new Person("Me",  R.drawable.ppc1);
    static List<Person> contacts = new LinkedList<Person>(Arrays.asList(
            new Person("Bob", R.drawable.ppc2),
            new Person("Mom", R.drawable.ppc3)
    ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = (RelativeLayout)this.findViewById(R.id.content_layout);
        spinner = (Spinner)this.findViewById(R.id.group_spinner);
        spinner.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, Constants.spinnerList));

        // Add the contacts/seeds to the main view in predefined locations.
        addSeed(R.id.diag_far, contacts.get(0));    // Bob
        addSeed(R.id.bottom_near, contacts.get(1)); // Mom
    }

    // Instantiates a seed view and adds it to the main screen. layoutId is the ID of some
    // layout on the main screen that you want to add the seed to.
    private void addSeed(int layoutId, final Person person)
    {
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout)findViewById(layoutId);
        View seed = inflater.inflate(R.layout.seed, null);

        ((TextView)seed.findViewById(R.id.name)).setText(person.name);
        ((ImageView)seed.findViewById(R.id.image)).setImageResource(person.image);

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
    // layout on the main screen that you want to add the seed to.
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

        // TODO: Move people around for other reasons?
    }
}

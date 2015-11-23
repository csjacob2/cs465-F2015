package com.messager.og2.cs465.cs465messager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends Activity {
    Spinner spinner;
    RelativeLayout mainLayout;

    final static String[] spinnerList = {
            "Family",
            "Friends",
            "Co-Workers",
            "+ Create Group..."
    };

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
        spinner.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, spinnerList));

        // There are a few hardcoded places in activity_main.xml where seeds may appear.
        addSeed(R.id.bob, contacts.get(0));
        addSeed(R.id.mom, contacts.get(1));
    }

    private void addSeed(int layoutId, final Person person)
    {
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout)findViewById(layoutId);
        View seed = inflater.inflate(R.layout.seed, null);

        ((TextView)seed.findViewById(R.id.name)).setText(person.name);
        ((ImageView)seed.findViewById(R.id.image)).setImageResource(person.image);

        seed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConversationActivity.class);
                intent.putExtra("CONVERSATION_IDX", contacts.indexOf(person));
                startActivity(intent);
            }
        });

        // TODO
        seed.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                System.out.println("Long clicked on " + person.name);
                return true;
            }
        });

        layout.addView(seed);
    }

    public void clickcheck(View v) {
        System.out.println("###############h:" + mainLayout.getMeasuredHeight() + "|w:" + mainLayout.getMeasuredWidth());
        System.out.println("###############h:" + mainLayout.getHeight() + "|w:" + mainLayout.getWidth());
    }

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
}

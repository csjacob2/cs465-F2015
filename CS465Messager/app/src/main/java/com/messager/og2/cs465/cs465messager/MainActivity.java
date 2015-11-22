package com.messager.og2.cs465.cs465messager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
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
    static Person[] people = {
            new Person("Me",  R.drawable.ppc1),
            new Person("Bob", R.drawable.ppc2),
            new Person("Mom", R.drawable.ppc3)
    };
    static Conversation[] conversations = {
            new Conversation(people[1],
                    new ConversationEntry[] {
                            new ConversationEntry(people[0], "Hey"),
                            new ConversationEntry(people[1], "What's up?"),
                            new ConversationEntry(people[0], "Nothing! Blah blah blah blah this is a very long line.")
                    },
                    true,
                    "7 days ago"),
            new Conversation(people[2],
                    new ConversationEntry[] {
                            new ConversationEntry(people[0], "How are you?"),
                            new ConversationEntry(people[1], "Good, you?"),
                            new ConversationEntry(people[0], "Fine")
                    },
                    false,
                    "Just now")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLayout = (RelativeLayout)this.findViewById(R.id.content_layout);
        spinner = (Spinner)this.findViewById(R.id.group_spinner);
        spinner.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, spinnerList));
    }

    public void clickcheck(View v) {
        System.out.println("###############h:" + mainLayout.getMeasuredHeight() + "|w:" + mainLayout.getMeasuredWidth());
        System.out.println("###############h:" + mainLayout.getHeight() + "|w:" + mainLayout.getWidth());
    }

    public void openMessageView(View v) {
        Intent myIntent = new Intent(this, MessageViewActivity.class);
        //myIntent.putExtra("key", value); //Optional parameters
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

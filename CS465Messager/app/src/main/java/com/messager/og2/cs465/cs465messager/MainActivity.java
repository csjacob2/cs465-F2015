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

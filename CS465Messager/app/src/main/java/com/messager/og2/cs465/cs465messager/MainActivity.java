package com.messager.og2.cs465.cs465messager;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Button;

public class MainActivity extends Activity {
    Spinner spinner;
    RelativeLayout mainLayout;
    private Contact mom = new Contact("Mom", R.drawable.ppc3), bob = new Contact("Bob", R.drawable.ppc2);

    final static String[] spinnerList = {
            "Family",
            "Friends",
            "Co-Workers",
            "+ Create Group..."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLayout = (RelativeLayout)this.findViewById(R.id.content_layout);
        spinner = (Spinner)this.findViewById(R.id.group_spinner);
        spinner.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, spinnerList));

        final Button mom_button = (Button) findViewById(R.id.mom_button);
        mom_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                NotificationFreqDialog nf = new NotificationFreqDialog(mom);
                nf.show(getFragmentManager(), "notification_freq_dialog");
            }
        });

        final Button bob_button = (Button) findViewById(R.id.bob_button);
        bob_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                NotificationFreqDialog nf = new NotificationFreqDialog(bob);
                nf.show(getFragmentManager(), "notification_freq_dialog");
            }
        });
    }

    public void clickcheck(View v) {
        System.out.println("###############h:" + mainLayout.getMeasuredHeight() + "|w:" + mainLayout.getMeasuredWidth());
        System.out.println("###############h:" + mainLayout.getHeight() + "|w:" + mainLayout.getWidth());
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

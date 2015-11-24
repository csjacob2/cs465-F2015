package com.messager.og2.cs465.cs465messager;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Button;
import android.view.View.OnLongClickListener;

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

        addContactButtonListener(mom, R.id.mom_button);
        addContactButtonListener(bob, R.id.bob_button);
    }

    public void addContactButtonListener (final Contact contact, int button_id) {
        final Button button = (Button) findViewById(button_id);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Short click on: " + contact.getName());
            }
        });
        button.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                NotificationFreqDialog nf = new NotificationFreqDialog(contact);
                nf.show(getFragmentManager(), "notification_freq_dialog");
                return true;
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

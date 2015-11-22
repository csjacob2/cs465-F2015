package com.messager.og2.cs465.cs465messager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ConversationActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation_view);

        Intent intent = getIntent();
        int conversationIdx = intent.getIntExtra("CONVERSATION_IDX", 0);
        Conversation conversation = MainActivity.conversations[conversationIdx];
        Person withPerson = conversation.withPerson;

        TextView urgent = (TextView)this.findViewById(R.id.urgent);
        TextView name = (TextView)this.findViewById(R.id.name);
        TextView timestamp = (TextView)this.findViewById(R.id.timestamp);

        urgent.setText(conversation.urgent ? "!" : "");
        name.setText(withPerson.name);
        timestamp.setText(conversation.timestamp);

        final String color = "#ff69b4";

        urgent.setTextColor(Color.parseColor(color));

        if (conversation.urgent)
        {
            timestamp.setTextColor(Color.parseColor(color));
        }
        else
        {
            timestamp.setTextColor(name.getCurrentTextColor());
        }

        ConversationEntryAdapter adapter = new ConversationEntryAdapter(this, R.layout.conversation_entry_list_item, conversation.entries, withPerson);

        ListView msgListView;
        msgListView = (ListView)findViewById(R.id.conversation_entry_list);
        msgListView.setAdapter(adapter);
    }

    public void goBack(View v) {
        onBackPressed();
    }
}

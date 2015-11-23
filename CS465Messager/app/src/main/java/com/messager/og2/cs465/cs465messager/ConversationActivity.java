package com.messager.og2.cs465.cs465messager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class ConversationActivity extends Activity {

    Conversation conversation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation_view);

        Intent intent = getIntent();
        int conversationIdx = intent.getIntExtra("CONVERSATION_IDX", 0);
        conversation = MessageViewActivity.conversations[conversationIdx];

        ConversationEntryAdapter adapter = new ConversationEntryAdapter(this, R.layout.conversation_entry_list_item, conversation.entries);
        getMessageListView().setAdapter(adapter);

        update();
    }

    public void update() {
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

        ((ConversationEntryAdapter)getMessageListView().getAdapter()).notifyDataSetChanged();
    }

    public void goBack(View v) {
        onBackPressed();
    }

    public void send(View v) {
        EditText editText = (EditText)this.findViewById(R.id.editText);
        String text = editText.getText().toString();

        conversation.entries.add(new ConversationEntry(MainActivity.me, text));
        conversation.urgent = false;
        conversation.timestamp = "Just now";

        editText.setText("");
        InputMethodManager inputMethodManager = (InputMethodManager)this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);

        update();
    }

    private ListView getMessageListView() {
        return (ListView)findViewById(R.id.conversation_entry_list);
    }
}

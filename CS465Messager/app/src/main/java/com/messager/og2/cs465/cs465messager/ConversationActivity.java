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

    // Updates the widgets on the screen. This will reflect any changes that have been made to the
    // conversation data. I.e., call this after the user sends a message.
    public void update() {
        Person withPerson = conversation.withPerson;

        TextView urgent = (TextView)this.findViewById(R.id.urgent);
        TextView name = (TextView)this.findViewById(R.id.name);
        TextView timestamp = (TextView)this.findViewById(R.id.timestamp);

        urgent.setText(conversation.urgent ? "!" : "");
        name.setText(withPerson.name);
        timestamp.setText(conversation.timestampString());

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

        // Causes the conversation list view to refresh itself.
        ((ConversationEntryAdapter)getMessageListView().getAdapter()).notifyDataSetChanged();
    }

    // Handles the user pressing the back button at the top left of the screen.
    public void goBack(View v) {
        onBackPressed();
    }

    // "Sends" the message that the user has typed to the conversation. Make sure to update the
    // conversation to no longer be urgent and show the timestamp as "Just now".
    public void send(View v) {
        EditText editText = (EditText)this.findViewById(R.id.editText);
        String text = editText.getText().toString();

        conversation.entries.add(new ConversationEntry(MainActivity.me, text));
        conversation.urgent = false;
        conversation.timestamp = 0;

        // Clear the text entry and close the keyboard.
        editText.setText("");
        InputMethodManager inputMethodManager = (InputMethodManager)this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);

        update();
    }

    // Convenience method for getting the conversation list view.
    private ListView getMessageListView() {
        return (ListView)findViewById(R.id.conversation_entry_list);
    }

    public void contactProfileClick(View v) {

    }
}

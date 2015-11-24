package com.messager.og2.cs465.cs465messager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MessageViewActivity extends Activity {

    // The list of all conversations that the user has ongoing.
    static Conversation[] conversations = {
            new Conversation(MainActivity.contacts.get(0), // Who the conversation is with.
                    new ConversationEntry[] {              // Individual chat entries.
                            new ConversationEntry(MainActivity.me, "Hey"),
                            new ConversationEntry(MainActivity.contacts.get(0), "What's up?"),
                            new ConversationEntry(MainActivity.me, "Nothing! Blah blah blah blah this is a very long line.")
                    },
                    true,
                    "7 days ago"),
            new Conversation(MainActivity.contacts.get(1), // Who the conversation is with.
                    new ConversationEntry[] {              // Individual chat entries.
                            new ConversationEntry(MainActivity.me, "How are you?"),
                            new ConversationEntry(MainActivity.contacts.get(1), "Good, you?"),
                            new ConversationEntry(MainActivity.me, "Fine")
                    },
                    false,
                    "Just now")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_view);

        MessageViewAdapter adapter = new MessageViewAdapter(this, R.layout.message_list_item, conversations);

        ListView msgListView = getMessageListView();
        msgListView.setAdapter(adapter);

        // Handle the user clicking one of the conversations. Opens the ConversationActivity with
        // the selected conversation.
        msgListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MessageViewActivity.this, ConversationActivity.class);
                intent.putExtra("CONVERSATION_IDX", position);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        // When the activity resumes, refresh the conversation list in case the user chatted
        // with one of their contacts.
        ((MessageViewAdapter)getMessageListView().getAdapter()).notifyDataSetChanged();
    }

    // Handles the user clicking the back button in the top bar.
    public void goBack(View v) {
        onBackPressed();
    }

    // Convenience method for getting the list of conversations.
    private ListView getMessageListView() {
        return (ListView)findViewById(R.id.message_list);
    }
};
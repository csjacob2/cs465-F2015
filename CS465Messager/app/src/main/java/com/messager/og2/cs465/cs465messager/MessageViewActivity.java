package com.messager.og2.cs465.cs465messager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MessageViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_view);

        MessageViewAdapter adapter = new MessageViewAdapter(this, R.layout.message_list_item, MainActivity.conversations);

        ListView msgListView;
        msgListView = (ListView)findViewById(R.id.message_list);
        msgListView.setAdapter(adapter);

        msgListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MessageViewActivity.this, ConversationActivity.class);
                intent.putExtra("CONVERSATION_IDX", position);
                startActivity(intent);
            }
        });
    }

    public void goBack(View v) {
        onBackPressed();
    }
};
package com.messager.og2.cs465.cs465messager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ConversationAdapter extends ArrayAdapter<Conversation> {
    private Context context;
    private int layoutResourceId;
    private Conversation[] conversations;

    public ConversationAdapter(Context context, int resource, Conversation[] conversations) {
        super(context, resource, conversations);
        this.context = context;
        this.layoutResourceId = resource;
        this.conversations = conversations;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        TextView urgent;
        ImageView image;
        TextView name;
        TextView timestamp;
        TextView text;

        if (row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
        }

        urgent = (TextView)row.findViewById(R.id.urgent);
        image = (ImageView)row.findViewById(R.id.image);
        name = (TextView)row.findViewById(R.id.name);
        timestamp = (TextView)row.findViewById(R.id.timestamp);
        text = (TextView)row.findViewById(R.id.text);

        final Conversation conversation = conversations[position];

        urgent.setText(conversation.urgent ? "!" : "");

        image.setImageBitmap(conversation.withPerson.profilePic);
        name.setText(conversation.withPerson.name);
        timestamp.setText(conversation.timestamp);
        text.setText(conversation.entries.get(conversation.entries.size() - 1).chatText);

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

        return row;
    }
}

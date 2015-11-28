package com.messager.og2.cs465.cs465messager;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Set;

public class ConversationEntryAdapter extends ArrayAdapter<ConversationEntry> {
    private Context context;
    private int layoutResourceId;
    private List<ConversationEntry> conversationEntries;

    public ConversationEntryAdapter(Context context, int resource, List<ConversationEntry> conversationEntries) {
        super(context, resource, conversationEntries);
        this.context = context;
        this.layoutResourceId = resource;
        this.conversationEntries = conversationEntries;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ImageView left_image;
        TextView text;
        ImageView right_image;

        if (row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
        }

        left_image = (ImageView)row.findViewById(R.id.left_image);
        text = (TextView)row.findViewById(R.id.text);
        right_image = (ImageView)row.findViewById(R.id.right_image);

        ConversationEntry conversationEntry = conversationEntries.get(position);

        // If the person who sent this chat message is me, then show my image on the left hand side
        // of the message. If it was the person I'm talking to, then show their image on the right
        // side. Hide the other one so it doesn't show nor take up space.
        if (conversationEntry.person != MainActivity.me)
        {
            left_image.setVisibility(View.GONE);
            Bitmap bm = conversationEntry.person.profilePic;
            if (conversationEntry.person != null && conversationEntry.person.contactPicMapping != null) {
                Set<String> keys = conversationEntry.person.contactPicMapping.keySet();
                for (String key : keys) {
                    if (conversationEntry.chatText.contains(key)) {
                        bm = conversationEntry.person.contactPicMapping.get(key);
                        break;
                    }
                }
            }
            right_image.setImageBitmap(bm);
            right_image.setVisibility(View.VISIBLE);
        } else {
            left_image.setImageResource(conversationEntry.person.image);
            left_image.setVisibility(View.VISIBLE);

            right_image.setVisibility(View.GONE);
        }

        text.setText(conversationEntry.chatText);

        return row;
    }
}

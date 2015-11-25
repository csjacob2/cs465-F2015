package com.messager.og2.cs465.cs465messager;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// Represents a conversation with someone.
public class Conversation {

    // The Person we're holding a conversation with.
    public Person withPerson;

    // The chat messages.
    public List<ConversationEntry> entries;

    // Whether or not the conversation should be shown as "urgent". This will cause a pink
    // exclamation point to be shown next to the conversation in the relevant screens.
    public boolean urgent;

    // The number of days since the last message in this conversation.
    public int timestamp;

    public Conversation(Person withPerson, ConversationEntry[] entries, boolean urgent, int timestamp) {
        this.withPerson = withPerson;
        this.entries = new LinkedList<ConversationEntry>(Arrays.asList(entries));
        this.urgent = urgent;
        this.timestamp = timestamp;
    }

    public String timestampString() {
        if (timestamp == 0) {
            return "Just now";
        } else {
            return Integer.toString(timestamp) + " days ago";
        }
    }
}

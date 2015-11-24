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

    // A string to display as the timestamp. This isn't a real timestamp, just hardcoded strings.
    // E.g., "Just now", "7 days ago".
    public String timestamp;

    public Conversation(Person withPerson, ConversationEntry[] entries, boolean urgent, String timestamp) {
        this.withPerson = withPerson;
        this.entries = new LinkedList<ConversationEntry>(Arrays.asList(entries));
        this.urgent = urgent;
        this.timestamp = timestamp;
    }
}

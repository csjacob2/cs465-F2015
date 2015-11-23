package com.messager.og2.cs465.cs465messager;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Conversation {
    public Person withPerson;
    public List<ConversationEntry> entries;
    public boolean urgent;
    public String timestamp;

    public Conversation(Person withPerson, ConversationEntry[] entries, boolean urgent, String timestamp) {
        this.withPerson = withPerson;
        this.entries = new LinkedList<ConversationEntry>(Arrays.asList(entries));
        this.urgent = urgent;
        this.timestamp = timestamp;
    }
}

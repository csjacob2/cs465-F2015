package com.messager.og2.cs465.cs465messager;

import java.util.List;

public class Conversation {
    public Person withPerson;
    public ConversationEntry[] entries;
    public boolean urgent;
    public String timestamp;

    public Conversation(Person withPerson, ConversationEntry[] entries, boolean urgent, String timestamp) {
        this.withPerson = withPerson;
        this.entries = entries;
        this.urgent = urgent;
        this.timestamp = timestamp;
    }
}

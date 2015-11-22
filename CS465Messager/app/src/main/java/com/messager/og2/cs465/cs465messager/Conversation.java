package com.messager.og2.cs465.cs465messager;

import java.util.List;

public class Conversation {
    public ConversationEntry[] entries;
    public boolean urgent;

    public Conversation(ConversationEntry[] entries) {
        this(entries, false);
    }

    public Conversation(ConversationEntry[] entries, boolean urgent) {
        this.urgent = urgent;
        this.entries = entries;
    }
}

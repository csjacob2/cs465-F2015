package com.messager.og2.cs465.cs465messager;

public class ConversationEntry {
    public Person person;
    public String chatText;

    public ConversationEntry(Person person, String chatText) {
        this.person = person;
        this.chatText = chatText;
    }
}

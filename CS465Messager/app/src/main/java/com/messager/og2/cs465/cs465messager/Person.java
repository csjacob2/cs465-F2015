package com.messager.og2.cs465.cs465messager;

public class Person {
    public String name;
    public int image;
    public int reminderFreq = 7;
    public int seedLocation = -1;

    public Person(String name, int image) {
        this.name = name;
        this.image = image;
    }
}

package com.messager.og2.cs465.cs465messager;

/**
 * Created by beer on 11/23/15.
 */
public class Contact {
    private String name;
    private int reminder_freq; //Reminder frequency in days
    private int profile_image;

    Contact(String name, int profile_image) {
        this.name = name;
        this.profile_image = profile_image;
        reminder_freq = 7;  //Default reminder frequency of 7 days
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReminder_freq() {
        return reminder_freq;
    }

    public void setReminder_freq(int reminder_freq) {
        this.reminder_freq = reminder_freq;
    }

    public int getProfile_image() {
        return profile_image;
    }

    public void setProfile_image (int profile_image) {
        this.profile_image = profile_image;
    }
}

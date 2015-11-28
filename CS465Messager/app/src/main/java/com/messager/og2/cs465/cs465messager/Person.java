package com.messager.og2.cs465.cs465messager;

import android.graphics.Bitmap;

import java.util.HashMap;
import java.util.Random;

public class Person {
    public String name;
    public int image;
    public Bitmap profilePic;
    public int reminderFreq = 7;
    public int seedLocation = -1;
    public String phoneNumber;
    public HashMap<String, Bitmap> contactPicMapping = new HashMap<String, Bitmap>();
    public String email;
    private Random rand = new Random();
    private String[] emailPartOne = {
        "awesome", "cool", "the", "some", "amazing"
    };
    private String[] emailPartTwo = {
        "person", "salamander", "foodstuff", "crazy"
    };
    private String[] emailPartThree = {
        "1584", "5291", "1234", "8766", "2315"
    };
    private String[] emailPartFour = {
        "iuvo", "messenger", "cs465", "something", "emailplace"
    };
    private String[] emailPartFive = {
        ".com", ".org", ".net", ".gov", ".edu"

    };

    public Person(String name, int image) {
        this.name = name;
        this.image = image;
        this.phoneNumber = "555-555-" + (rand.nextInt(9999-1000) + 1000);
        this.email = emailPartOne[Math.abs(rand.nextInt()%emailPartOne.length)] +
                emailPartTwo[Math.abs(rand.nextInt()%emailPartTwo.length)] +
                emailPartThree[Math.abs(rand.nextInt()%emailPartThree.length)] +
                "@" +
                emailPartFour[Math.abs(rand.nextInt()%emailPartFour.length)] +
                emailPartFive[Math.abs(rand.nextInt()%emailPartFive.length)];

    }

    public Person(String name, int image, Bitmap profilePic) {
        this.name = name;
        this.image = image;
        this.phoneNumber = "555-555-" + (rand.nextInt(9999-1000) + 1000);
        this.email = emailPartOne[Math.abs(rand.nextInt()%emailPartOne.length)] +
                emailPartTwo[Math.abs(rand.nextInt()%emailPartTwo.length)] +
                emailPartThree[Math.abs(rand.nextInt()%emailPartThree.length)] +
                "@" +
                emailPartFour[Math.abs(rand.nextInt()%emailPartFour.length)] +
                emailPartFive[Math.abs(rand.nextInt()%emailPartFive.length)];
        this.profilePic = profilePic;
    }


    public Person(String name, int image, String phoneNumber, String email)
    {
        this.name = name;
        this.image = image;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public void addMapping(String key, Bitmap pic) {
        contactPicMapping.put(key, pic);
    }
}

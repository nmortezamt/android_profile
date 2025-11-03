package com.example.v2.tasks.dialog;

public class UserModel {

    private int image;
    private String name;
    private int age;

    public UserModel(int image, String name, int age) {
        this.image = image;
        this.name = name;
        this.age = age;
    }

    public int getImage() { return image; }
    public String getName() { return name; }
    public int getAge() { return age; }

}

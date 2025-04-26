package com.example.profile;

public class UserData {
    private String firstName;
    private String lastName;
    private String age;
    private String bio;

    // Constructor
    public UserData(String firstName, String lastName, String age, String bio) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.bio = bio;
    }

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    // Method to get full name
    public String getFullName() {
        return firstName + " " + lastName;
    }
}

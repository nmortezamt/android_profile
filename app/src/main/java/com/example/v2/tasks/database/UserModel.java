package com.example.v2.tasks.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class UserModel {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public String family;
    public String email;

    public UserModel(String name, String family, String email) {
        this.name = name;
        this.family = family;
        this.email = email;
    }
}
package com.example.v2.tasks.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertUser(UserModel user);

    @Update
    void updateUser(UserModel user);

    @Delete
    void deleteUser(UserModel user);

    @Query("SELECT * FROM users")
    List<UserModel> getAllUsers();

    @Query("SELECT * FROM users WHERE id = :id")
    UserModel getUserById(int id);
}
package com.example.v2.tasks.guessword;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WordDao {

    @Insert
    void insertWord(WordEntity word);

    @Query("SELECT * FROM words")
    List<WordEntity> getAllWords();
}

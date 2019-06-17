package com.example.celebritydatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CelebrityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Celebrity celebrities);



    @Query("DELETE FROM celebrities")
    void deleteAll();

    @Query("SELECT * from celebrities ORDER BY `First Name` ASC")
    LiveData<List<Celebrity>> getAllCelebrities();

    @Query("SELECT * FROM celebrities WHERE `First Name` = :name")
    Celebrity getCelebrityByField(String name);

    @Update
    void updateCelebrities(Celebrity... celebrities);

}

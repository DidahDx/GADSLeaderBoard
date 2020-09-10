package com.didahdx.gadsleaderboard.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.didahdx.gadsleaderboard.data.db.entities.HoursLeaderDb;

import java.util.List;

@Dao
public interface HourLeaderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(HoursLeaderDb hours);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<HoursLeaderDb> hours);

    @Update
    void update(HoursLeaderDb note);

    @Delete
    void delete(HoursLeaderDb note);

    @Query("DELETE FROM hoursLeaderDb")
    void deleteAllHourLeaders();

    @Query("SELECT * FROM hoursLeaderDb ORDER BY id ASC")
    LiveData<List<HoursLeaderDb>> getAllHoursLeaders();
}

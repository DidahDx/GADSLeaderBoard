package com.didahdx.gadsleaderboard.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.didahdx.gadsleaderboard.data.db.entities.IQLeaderDb;

import java.util.List;

@Dao
public interface IQLeaderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(IQLeaderDb hours);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<IQLeaderDb> hours);

    @Update
    void update(IQLeaderDb note);

    @Delete
    void delete(IQLeaderDb note);

    @Query("DELETE FROM iQLeaderDb")
    void deleteAllIQLeaders();

    @Query("SELECT * FROM IQLeaderDb ORDER BY id ASC")
    LiveData<List<IQLeaderDb>> getAllIQLeaders();
}

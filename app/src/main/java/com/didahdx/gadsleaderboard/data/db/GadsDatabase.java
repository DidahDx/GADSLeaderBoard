package com.didahdx.gadsleaderboard.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.didahdx.gadsleaderboard.data.db.entities.HoursLeaderDb;
import com.didahdx.gadsleaderboard.data.db.entities.IQLeaderDb;


@Database(entities = {HoursLeaderDb.class, IQLeaderDb.class} , version = 1,exportSchema =false)
public abstract class GadsDatabase  extends RoomDatabase {

    private static GadsDatabase instance;

    public abstract HourLeaderDao hourLeaderDao();
    public abstract IQLeaderDao iqLeaderDao();

    public static synchronized GadsDatabase getInstance(Context context){
        if (instance == null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    GadsDatabase.class,"gads_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }


}

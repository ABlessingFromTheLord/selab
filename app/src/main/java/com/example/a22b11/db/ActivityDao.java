package com.example.a22b11.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

@Dao
public interface ActivityDao {
    @Query("SELECT * FROM activities")
    ListenableFuture<List<Activity>> getAll();

    @Query("SELECT * FROM activities WHERE user_id = :userId")
    ListenableFuture<List<Activity>> getAllByUserId(long userId);

    @Query("SELECT * FROM activities")
    ListenableFuture<List<Activity>> getAllFuture();

    @Query("SELECT * FROM activities")
    List<Activity> getAllSync();

    @Query("SELECT * FROM activities WHERE id IS NULL")
    List<Activity> getNewSync();

    @Query("SELECT * FROM activities WHERE is_modified")
    List<Activity> getModifiedSync();

    @Update
    void updateSync(Activity activity);

    @Update
    void updateSync(List<Activity> activity);

    @Query("SELECT `local_id` FROM `activities` WHERE `id` = :id")
    long getLocalIdById(long id);

    /**
     * Insert a new activity
     * @param activity new activity
     * @return autogenerated local activity id
     */
    @Insert
    ListenableFuture<Long> insert(Activity activity);

    /**
     * Insert new activities
     * @param activities new activities
     * @return list of autogenerated local activity ids
     */
    @Insert
    ListenableFuture<List<Long>> insertAll(Activity... activities);

    @Insert
    void insertSync(Activity mood);

    @Insert
    void insertSync(List<Activity> mood);

    @Delete
    ListenableFuture<Void> delete(Activity activity);
}

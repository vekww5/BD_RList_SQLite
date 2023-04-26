package com.example.bd_rlist_sqlite.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DiaDao {

    @Query("SELECT * FROM table_dia")
    LiveData<List<Dia>> getAll();

    @Query("SELECT * FROM table_dia WHERE uid IN (:diaIds)")
    LiveData<List<Dia>> loadAllByIds(int[] diaIds);

    //@Query("SELECT * FROM user WHERE first_name LIKE :first AND " + "last_name LIKE :last LIMIT 1")
    //User findByName(String first, String last);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Dia... dias);

    @Delete
    void delete(Dia dia);

    @Query("DELETE FROM table_dia WHERE uid = :id")
    void deleteID(int id);

    @Query("DELETE FROM table_dia")
    void deleteAll();
}

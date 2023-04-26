package com.example.bd_rlist_sqlite;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/**
 * DAO (объект доступа к данным) проверяет ваш SQL во время компиляции и связывает его с методом.
 * В вашем Room DAO вы используете удобные аннотации, такие как , для представления наиболее распространенных операций с базой данных!
 * Room использует DAO для создания чистого API для вашего кода.@Insert
 * DAO должен быть интерфейсом или абстрактным классом. По умолчанию все запросы должны выполняться в отдельном потоке.
 */

@Dao
public interface WordDao {

    // LiveData is a data holder class that can be observed within a given lifecycle.
    // Always holds/caches latest version of data. Notifies its active observers when the
    // data has changed. Since we are getting all the contents of the database,
    // we are notified whenever any of the database contents have changed.

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy

    //Объявляет метод для вставки одного слова:
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Word word);

    @Query("DELETE FROM word_table")
    void deleteAll();

    @Query("SELECT * FROM word_table ORDER BY word ASC")
    LiveData<List<Word>> getAlphabetizedWords();
}

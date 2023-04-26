package com.example.bd_rlist_sqlite;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


/**
 Базовый класс, представляющий сущность, которая является строкой в одностолбцовой таблице базы данных.
 @ Entity - Вы должны аннотировать класс как сущность и предоставить имя таблицы, если оно отличается от имени класса.
 @ PrimaryKey - Вы должны определить первичный ключ.
 @ ColumnInfo - Необходимо указать имя столбца, если оно отличается от имени переменной.
 См. документацию для полного набора аннотаций: https://developer.android.com/topic/libraries/architecture/room.html
 */

@Entity(tableName = "word_table")
public class Word {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String mWord; // @PrimaryKey(autoGenerate = true) auto-generated primary key

    public Word(@NonNull String word) {this.mWord = word;}

    public String getWord(){return this.mWord;}
}

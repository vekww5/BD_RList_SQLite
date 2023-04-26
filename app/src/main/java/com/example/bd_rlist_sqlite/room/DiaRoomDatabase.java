package com.example.bd_rlist_sqlite.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.bd_rlist_sqlite.Word;
import com.example.bd_rlist_sqlite.WordDao;
import com.example.bd_rlist_sqlite.WordRoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Dia.class}, version = 1, exportSchema = false)
public abstract class DiaRoomDatabase extends RoomDatabase {

    public abstract DiaDao diaDao();
    private static volatile DiaRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static DiaRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DiaRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    DiaRoomDatabase.class, "dia_database")
                            .addCallback(sRoomDatabaseCallback2)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback2 = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                DiaDao dao = INSTANCE.diaDao();
                dao.deleteAll();

                //Dia dia = new Dia(1, 2, 3, 4, 23423424);
                //dao.insert(dia);
            });
        }
    };
}

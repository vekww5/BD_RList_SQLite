package com.example.bd_rlist_sqlite.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_dia")
public class Dia {

    //TODO Что делать с этим ?
    //private String  string_date, string_time;

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "inject_long")
    public float injectLong;

    @ColumnInfo(name = "inject_short")
    public float injectShort;
    @ColumnInfo(name = "glucose")
    public float glucose;

    @ColumnInfo(name = "xe")
    public float xe;

    @ColumnInfo(name = "date_time")
    public long timestamp;

    public Dia(float injectLong, float injectShort, float glucose, float xe, long timestamp) {
        this.injectLong = injectLong;
        this.injectShort = injectShort;
        this.glucose = glucose;
        this.xe = xe;
        this.timestamp = timestamp;
    }

    public float getInjectLong() {
        return injectLong;
    }

    public void setInjectLong(float injectLong) {
        this.injectLong = injectLong;
    }
    public String getInjectLongString() {return String.valueOf(injectLong);}

    public float getInjectShort() {
        return injectShort;
    }

    public void setInjectShort(float injectShort) {
        this.injectShort = injectShort;
    }

    public String getInjectShortString() {return String.valueOf(injectShort);}

    public float getGlucose() {return glucose;}

    public String getGlucoseString() {return String.valueOf(glucose);}

    public void setGlucose(float glucose) {
        this.glucose = glucose;
    }

    public float getXe() {
        return xe;
    }

    public String getXeString() {
        return String.valueOf(xe);
    }

    public void setXe(float xe) {this.xe = xe;}

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }


}

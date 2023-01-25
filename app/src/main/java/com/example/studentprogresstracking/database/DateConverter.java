package com.example.studentprogresstracking.database;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverter {
    @TypeConverter
    public static Long toTimestamp(Date date){
        return date==null?null:date.getTime();
    }

    @TypeConverter
    public static Date toDate(Long ts){
        return ts==null?null:new Date(ts);
    }

}

package com.example.studentprogresstracking.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.studentprogresstracking.dao.*;
import com.example.studentprogresstracking.entity.*;

@Database(entities = {Courses.class, OneTerm.class,Instructor.class,CourseNotes.class,CourseAssessment.class},version = 20,exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class DatabaseBuilder extends RoomDatabase {
    public abstract CoursesDAO CoursesDAO();
    public abstract termDAO  termDAO();
    public abstract InstructorDAO InstructorDAO();
    public abstract CourseAssessmentDAO CourseAssessmentDao();
    public abstract CourseNotesDAO CourseNotesDAO();
    private static volatile DatabaseBuilder INSTANCE;
    static DatabaseBuilder getDatabase(final Context c){
        if (INSTANCE == null) {
            synchronized (DatabaseBuilder.class){
                if (INSTANCE == null) {
                    INSTANCE= Room.databaseBuilder(c.getApplicationContext(),DatabaseBuilder.class,"StudentProgress.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }return INSTANCE;
        
    }
}

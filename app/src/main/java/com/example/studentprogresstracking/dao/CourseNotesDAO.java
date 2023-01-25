package com.example.studentprogresstracking.dao;

import androidx.room.*;

import com.example.studentprogresstracking.entity.CourseNotes;

import java.util.List;

@Dao
public interface CourseNotesDAO{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(CourseNotes courseNotes);

    @Update
    void update(CourseNotes courseNotes);

    @Delete
    void delete(CourseNotes courseNotes);

    @Query("SELECT * FROM notes")
    List<CourseNotes> getAllNotes();

    @Query("SELECT * FROM notes WHERE id = :id")
    CourseNotes getNoteById(int id);

    @Query("DELETE FROM notes WHERE courseId = :courseId")
    void deleteNotesByCourseId(int courseId);
}


package com.example.studentprogresstracking.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.studentprogresstracking.entity.Courses;


import java.util.List;

@Dao
public interface CoursesDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertCourse(Courses T);
    @Update
    void UpdateCourse(Courses T);

    @Delete
    void DeleteCourse(Courses T);

    @Query("Delete FROM Courses where id=:id")
    void DeleteCoursesById(int id);

    @Query("select * FROM Courses where Termid=:tid")
    List<Courses> GetAllCoursesByTerm(int tid);

    @Query("select * FROM Courses")
    List<Courses> GetAllCourses();

}

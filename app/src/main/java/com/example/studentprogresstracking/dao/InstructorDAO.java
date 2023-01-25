package com.example.studentprogresstracking.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.studentprogresstracking.entity.Courses;
import com.example.studentprogresstracking.entity.Instructor;

import java.util.List;

@Dao
public interface InstructorDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Instructor T);
    @Update
    void Update(Instructor T);
    @Delete
    void Delete(Instructor T);
    @Query("Delete FROM Instructor where CourseID=:Cid")
    void DeleteInstructorByCourse(int Cid);


    @Query("select * FROM Instructor where CourseID=:Cid")
    List<Instructor> GetInstructorByCourse(int Cid);


//    @Query("select * FROM Courses")
//    List<Courses> GetAllCourses();
}

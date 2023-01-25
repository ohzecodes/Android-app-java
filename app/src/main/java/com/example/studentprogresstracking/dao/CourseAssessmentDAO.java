package com.example.studentprogresstracking.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.studentprogresstracking.entity.CourseAssessment;

import java.util.List;

@Dao
public interface CourseAssessmentDAO {

    @Insert
    void insert(CourseAssessment courseAssessment);

    @Update
     void update(CourseAssessment courseAssessment) ;

    @Delete
    void delete(CourseAssessment courseAssessment);
    @Query("Delete FROM CourseAssessment Where courseId=:cid")
    void DeleteAssessmentsByCourseId(int cid);



    @Query("SELECT * FROM CourseAssessment")
    List<CourseAssessment> getAllAssessments();

    @Query("SELECT * FROM CourseAssessment WHERE courseId = :courseId")
    List<CourseAssessment> getAssessmentsByCourseId(int courseId);

    @Query("SELECT * FROM CourseAssessment WHERE id = :id")
    CourseAssessment getAssessmentById(int id);
}

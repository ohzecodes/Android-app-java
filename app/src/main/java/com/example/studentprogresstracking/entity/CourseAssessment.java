package com.example.studentprogresstracking.entity;

import androidx.room.*;

import java.util.Date;

@Entity(tableName = "CourseAssessment")
public class CourseAssessment {
    @PrimaryKey(autoGenerate = true)
    private int id;


    private int courseId;
    private String title;
    private boolean ObjectiveAssessment;
    private Date StartDate;

    private Date endDate;
    public CourseAssessment(){}
    public CourseAssessment(String title, boolean isObjectiveAssessment, Date endDate, int courseId, Date startDate) {
        this.courseId = courseId;
        this.title = title;
        this.ObjectiveAssessment = isObjectiveAssessment;
        this.endDate = endDate;
        StartDate = startDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    public boolean getObjectiveAssessment() {
        return ObjectiveAssessment;
    }

    public void setObjectiveAssessment(boolean objectiveAssessment) {
        ObjectiveAssessment = objectiveAssessment;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }
}


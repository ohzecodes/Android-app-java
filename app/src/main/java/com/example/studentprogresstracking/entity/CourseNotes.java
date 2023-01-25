package com.example.studentprogresstracking.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class CourseNotes {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String text;
    private  int courseId;
    public CourseNotes(String title, String text,int courseId ) {
        this.title = title;
        this.text = text;
        this.courseId=courseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
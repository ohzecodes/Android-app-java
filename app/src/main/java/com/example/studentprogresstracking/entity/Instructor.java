package com.example.studentprogresstracking.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Instructor {


    @PrimaryKey(autoGenerate = true)
    private int InstructorID;
    private String email;
    private String name;
    private String phoneNumber;
    private int CourseID;
    public Instructor(){}
    public Instructor(int instructorID, String email, String name, String phoneNumber, int CourseID) {
        InstructorID = instructorID;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.CourseID=CourseID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getInstructorID() {
        return InstructorID;
    }

    public void setInstructorID(int instructorID) {
        InstructorID = instructorID;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int courseID) {
        CourseID = courseID;
    }
}


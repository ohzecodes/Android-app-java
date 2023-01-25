package com.example.studentprogresstracking.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "courses")
public class Courses {
    @PrimaryKey(autoGenerate = true)
    private int Id;
    private String Name;
    private Date  start_date;
    private Date end_date;
    private String status;
    private  int TermId;


    public Courses(){}
    public Courses(int id, String name, Date start_date, Date end_date, String status,int TermId) {
        this.TermId=TermId;
        Id = id;
        Name = name;
        this.start_date = start_date;
        this.end_date = end_date;
        this.status = status;
    }


    public int getId() {return Id;}

    public void setId(int id) {Id = id;}

    public String getName() {return Name;}

    public void setName(String name) {Name = name;}



    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}
    public int getTermId() {return TermId;}
    public void setTermId(int termId) { TermId = termId;}

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }


//    The start date
//•  The end date
//•  The status (in progress, completed, dropped, plan to take)
//•  The course instructors’ names, phone numbers, and e-mail addresses
}

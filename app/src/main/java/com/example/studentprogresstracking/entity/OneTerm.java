package com.example.studentprogresstracking.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;
@Entity(tableName = "Term")
public class OneTerm  {








    @PrimaryKey(autoGenerate = true)
    private int termid;
    private  String Title;
    private  Date StartDate;
    private  Date EndDate;

    protected OneTerm(Parcel in) {
        termid = in.readInt();
        Title = in.readString();
    }


    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }



    public OneTerm(int termid, String title, Date startDate, Date endDate) {
        termid=termid;
        Title = title;
        StartDate = startDate;
        EndDate = endDate;
    }
    public OneTerm(){}

    public int getTermid() {
        return termid;
    }

    public void setTermid(int termid) {
        this.termid = termid;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }

}

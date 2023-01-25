package com.example.studentprogresstracking.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.studentprogresstracking.entity.OneTerm;


import java.util.List;

@Dao
public interface termDAO {

@Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertTerm(OneTerm T);
    @Update     void UpdateTerm(OneTerm T);
    @Delete void DeleteTerm(OneTerm T);
    @Query("select * FROM Term")
    List<OneTerm> GetAllterms();

    @Query("DELETE FROM Term WHERE termid = :termId")
    void deleteTermById(int termId);

}

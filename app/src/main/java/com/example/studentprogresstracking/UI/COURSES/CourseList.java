package com.example.studentprogresstracking.UI.COURSES;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.example.studentprogresstracking.R;
import com.example.studentprogresstracking.UI.adpaters.CourseRVAdpater;
import com.example.studentprogresstracking.database.Repository;
import com.example.studentprogresstracking.entity.Courses;

import java.util.ArrayList;

public class CourseList extends AppCompatActivity {
    ArrayList<Courses> CoursesList;
    Repository repo;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_course);
         repo =   new Repository(getApplication());

        initrv();

    }


    @Override
    protected void onResume() {
        super.onResume();
        initrv();
    }

    private void initrv() {
        CoursesList= repo.getAllCourse();
        RecyclerView RvCourses=findViewById(R.id.rvcourselist);
        RvCourses.setLayoutManager(new LinearLayoutManager(this));
        RvCourses.setAdapter(new CourseRVAdpater(this, CoursesList));
        findViewById(R.id.FLB_AddCourses).setOnClickListener(v->startActivity(new Intent(this, AddOrModACourse.class)));
    }
}
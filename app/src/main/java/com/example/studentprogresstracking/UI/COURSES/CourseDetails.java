package com.example.studentprogresstracking.UI.COURSES;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.studentprogresstracking.R;
import com.example.studentprogresstracking.UI.COURSES.parts.AddOrModifyInstructor;
import com.example.studentprogresstracking.UI.COURSES.parts.AddOrModAssesments;
import com.example.studentprogresstracking.UI.COURSES.parts.AddOrModNotes;
import com.example.studentprogresstracking.UI.adpaters.TabCourseAdapter;
import com.example.studentprogresstracking.database.Repository;
import com.example.studentprogresstracking.entity.CourseNotes;
import com.google.android.material.tabs.TabLayout;


import java.util.Date;

public class CourseDetails extends AppCompatActivity {
    int CourseId, termid;
    String courseName,CourseStatus;
    Date startDate, endDate;
    TabLayout tabLayout;
    ViewPager2 vp;
    Repository repo;
    TabCourseAdapter tca;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_course);

        CourseId = getIntent().getIntExtra("CourseId", -1);
        courseName = getIntent().getStringExtra("CourseName");
        CourseStatus=getIntent().getStringExtra("CourseStatus");
        startDate = (Date) getIntent().getSerializableExtra("CourseStartDate");
        endDate = (Date) getIntent().getSerializableExtra("CourseEndDate");
        termid = getIntent().getIntExtra("termId", -1);

        repo=new Repository(getApplication());
        tca= new TabCourseAdapter(this, CourseId,repo);
        tabLayout =findViewById(R.id.coursesTabs);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        vp=findViewById(R.id.viewpager2);
        vp.setUserInputEnabled(false);
        vp.setAdapter(tca);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.course_menu, menu);

/*
*  <item   android:id="@+id/editCourseDetailsMenuOption" android:title="Item" />
    <item android:id="@+id/deleteCourseMenuOption"  android:title="Item" />
    <item android:id="@+id/AddInstructorMenuOption" android:title="Item" />
    <item android:id="addAssessmentMenuOption" android:title="Item" />
    <item android:id="addNotesMenuOption" android:title="Item" />*/
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.editCourseDetailsMenuOption:

                Intent i =new Intent(this, AddOrModACourse.class);

                i.putExtra("CourseName", courseName);
                i.putExtra("CourseId",CourseId);
                i.putExtra( "CourseStatus",CourseStatus);
                i.putExtra("termid",termid);
                i.putExtra("CourseStartDate",startDate);
                i.putExtra("CourseEndDate",endDate);
                startActivity(i);
                return true;
            case R.id.deleteCourseMenuOption:
              Delete();
                return true;

            case R.id.AddInstructorMenuOption:
                Intent in =new Intent(this, AddOrModifyInstructor.class);
                in.putExtra("Cid",CourseId);
                startActivity(in);
                return true;
            case R.id.addNotesMenuOption:
                Intent notesIntent =new Intent(this, AddOrModNotes.class);
                notesIntent.putExtra("cid",CourseId);
                startActivity(notesIntent);
                return true;
            case  R.id.addAssessmentMenuOption:
                Intent inte =new Intent(this, AddOrModAssesments.class);
                inte.putExtra("cid",CourseId);
                startActivity(inte);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void Delete() {

        android.app.AlertDialog.Builder bu = new android.app.AlertDialog.Builder(this);
        bu.setTitle("Confirm Delete");
        bu.setMessage("Are you sure you want to delete " + "?");
        bu.setPositiveButton("Yes", (dialogInterface, i1) -> {
            repo.DeleteCourseByID(CourseId);
            finish();
        });
        bu.setNegativeButton("No, Please", (dialogInterface, i1) -> {
            dialogInterface.dismiss();
        });
        bu.create();
        bu.show();
    }


}
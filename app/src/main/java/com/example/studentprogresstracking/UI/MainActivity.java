package com.example.studentprogresstracking.UI;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;

import com.example.studentprogresstracking.R;
import com.example.studentprogresstracking.UI.COURSES.CourseList;
import com.example.studentprogresstracking.UI.TERM.TermList;
import com.example.studentprogresstracking.database.Repository;
import com.example.studentprogresstracking.entity.CourseAssessment;
import com.example.studentprogresstracking.entity.Courses;
import com.example.studentprogresstracking.entity.Instructor;
import com.example.studentprogresstracking.entity.OneTerm;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<String> TermsArray=new ArrayList<>();
    boolean b ;
    @SuppressLint({"MissingInflatedId", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*        Repository repo=new Repository(getApplication());
        new Instructor(0,"billjing@wgu.edu","Bill Jing","12345678",2);
    repo.insert(  new CourseAssessment("new",false,new Date(),1,new Date()));
        repo.insert(new Insrtructor(0,"billjing@wgu.edu","Bill Jing","12345678",2));
        repo.insert(new Instructor(0,"billjing@wgu.edu","Bill Jing","12345678",3));
        repo.insert(new Instructor(0,"billjing@wgu.edu","Bill Jing","12345678",3));
   */
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        findViewById(R.id.term_see).setOnClickListener(v->startActivity(new Intent(this, TermList.class)));
        findViewById(R.id.course_list).setOnClickListener(v->startActivity(new Intent(this, CourseList.class)));

//        repo.insert(new CourseAssessment("new",false,new Date(),1));
    }




}
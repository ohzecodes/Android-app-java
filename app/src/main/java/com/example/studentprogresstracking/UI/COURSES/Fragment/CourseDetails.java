package com.example.studentprogresstracking.UI.COURSES.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.studentprogresstracking.R;
import com.example.studentprogresstracking.UI.util;
import com.example.studentprogresstracking.database.Repository;
import com.example.studentprogresstracking.entity.Courses;

import java.util.Date;


public class CourseDetails extends Fragment {



    int CourseId;
    int termid;
    String courseName;
    String CourseStatus;
    Date startDate;
    Date endDate;

    public CourseDetails(Courses course) {
        CourseId = course.getId();
        this.termid = course.getTermId();
        this.courseName = course.getName();
        CourseStatus = course.getStatus();
        this.startDate = course.getStart_date();
        this.endDate = course.getEnd_date();
    }

    @Override
    public void onResume() {
        super.onResume();
        View v =getView();
        if(v!=null){
            inittextfields(v,true);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course_details, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        View v =getView();
        if(v!=null){
            inittextfields(v,false);
        }

    }

    private void inittextfields(View v,boolean r) {
        if(r){
            Repository repo=new Repository(this.getActivity().getApplication());
            Courses c=repo.getCoursebyId(CourseId);
            courseName=c.getName();
            CourseStatus=c.getStatus();
            startDate=c.getStart_date();
            endDate=c.getEnd_date();

        }
        ((TextView) v.findViewById(R.id.CDcourseid)).setText("Course Id: "+CourseId);
        ((TextView) v.findViewById(R.id.CDCourseName)).setText(util.capitalize(courseName));
        ((TextView) v.findViewById(R.id.CDcoursestatus)).setText("Course Status: "+util.capitalize(CourseStatus));
        ((TextView) v.findViewById(R.id.CDStatDate)).setText("Start Date:" + util.DatetoNiceString(startDate));
        ((TextView) v.findViewById(R.id.CdEnddate)).setText("End date:"+util.DatetoNiceString(endDate));
        ((TextView) v.findViewById(R.id.CdTermID)).setText("Term Id: "+(termid));
    }




/*
    *  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.termmenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:

                Intent i =new Intent(this, AddOrModACourse.class);

                i.putExtra("CourseName", courseName);
                i.putExtra("CourseId",CourseId);
                i.putExtra( "CourseStatus",CourseStatus);
                i.putExtra("termid",termid);
                i.putExtra("CourseStartDate",startDate);
                i.putExtra("CourseEndDate",endDate);
//                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                return true;
            case R.id.delete:
                // Handle menu item 2
                AlertDialog.Builder b=new AlertDialog.Builder(this);

                    b.setTitle("Delete Course");
                    b.setMessage("are you sure you want to delete this Course");
                    b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {


                            Delete();
                        }
                    });
                    b.setNegativeButton("No",null);


                b.create().show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void Delete() {
        repo.DeleteCourseByID(CourseId);
        finish();
    }
*/
}
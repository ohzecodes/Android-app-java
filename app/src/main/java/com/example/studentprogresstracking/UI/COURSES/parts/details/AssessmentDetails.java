package com.example.studentprogresstracking.UI.COURSES.parts.details;

import static com.example.studentprogresstracking.UI.util.DatetoNiceString;
import static com.example.studentprogresstracking.UI.util.capitalize;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.studentprogresstracking.R;
import com.example.studentprogresstracking.UI.COURSES.parts.AddOrModAssesments;
import com.example.studentprogresstracking.database.Repository;
import com.example.studentprogresstracking.entity.CourseAssessment;

import java.util.Date;

public class AssessmentDetails extends AppCompatActivity {
    TextView idTV ;
    TextView titleTV ;
    TextView typeTV ;
    TextView startdateTV ;
    TextView enddateTV ;
    TextView courseIdTV ;


    int cid ;
    int id ;
    String title ;
    boolean type;
    Date startdate ;
    Date enddate ;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_assesment);
        cid = getIntent().getIntExtra("cid", 0);
        id = getIntent().getIntExtra("id", 0);
        title = getIntent().getStringExtra("title");
        type = getIntent().getBooleanExtra("type", false);
        startdate = (Date) getIntent().getSerializableExtra("startdate");
        enddate = (Date) getIntent().getSerializableExtra("enddate");

        idTV = findViewById(R.id.AssessmentDetails_id);
        titleTV = findViewById(R.id.AssessmentDetails_title);
        typeTV = findViewById(R.id.AssessmentDetails_type);
        startdateTV = findViewById(R.id.AssessmentDetails_startdate);
        enddateTV = findViewById(R.id.AssessmentDetails_enddate);
        courseIdTV = findViewById(R.id.AssessmentDetails_courseId);

        Button editButton = findViewById(R.id.AssessmentDetails_edit);
        Button deleteButton = findViewById(R.id.AssessmentDetails_delete);

        idTV.setText("Id: "+ id);
        titleTV.setText("Title: "+ capitalize(title));
        typeTV.setText("Type: "+capitalize( gettype()));
        startdateTV.setText("Start Date: "+ DatetoNiceString(startdate) );
        enddateTV.setText("End Date: "+  DatetoNiceString(enddate));
        courseIdTV.setText("Course Id: "+ cid);
        editButton.setOnClickListener(e->{
            Intent intent =new Intent(this, AddOrModAssesments.class);
            intent.putExtra("cid", cid);
            intent.putExtra("id", id);
            intent.putExtra("title", title);
            intent.putExtra("type", type);
            intent.putExtra("startdate", startdate);
            intent.putExtra("enddate", enddate);
            startActivity(intent);
        });
        deleteButton.setOnClickListener(e->Delete());
    }

    public void Delete() {
        AlertDialog.Builder bu = new AlertDialog.Builder(this);
        bu.setTitle("Confirm Delete");
        bu.setMessage("Are you sure you want to delete " + "?");
        bu.setPositiveButton("Yes", (dialogInterface, i1) -> {
            Repository repo = new Repository(this.getApplication());
            CourseAssessment ca = new CourseAssessment(title, type, enddate, cid, startdate);
            ca.setId(id);
            repo.deleteAssessment(ca);
            finish();
        });
        bu.setNegativeButton("No, Please", (dialogInterface, i1) -> {
            dialogInterface.dismiss();
        });
        bu.create();
        bu.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        inittext();
    }

    void inittext(){

        CourseAssessment ca=new Repository(this.getApplication()).getOneAssessment(id,cid);

        title=ca.getTitle();
        type=ca.getObjectiveAssessment();
        startdate=ca.getStartDate();
        enddate=ca.getEndDate();

        idTV.setText("Id: "+ id);
        titleTV.setText("Title: "+ capitalize(title));
        typeTV.setText("Type: "+capitalize( gettype()));
        startdateTV.setText("Start Date: "+ DatetoNiceString(startdate) );
        enddateTV.setText("End Date: "+  DatetoNiceString(enddate));
        courseIdTV.setText("Course Id: "+ cid);
    }


    String gettype (){
        return type ?"Objective Assessment":"Performance Assessment";
    }
}
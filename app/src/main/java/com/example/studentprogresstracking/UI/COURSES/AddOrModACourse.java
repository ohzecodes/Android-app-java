package com.example.studentprogresstracking.UI.COURSES;

import static com.example.studentprogresstracking.UI.util.makeDateString;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.studentprogresstracking.R;
import com.example.studentprogresstracking.UI.util;
import com.example.studentprogresstracking.database.Repository;
import com.example.studentprogresstracking.entity.Courses;
import com.example.studentprogresstracking.entity.OneTerm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

public class AddOrModACourse extends AppCompatActivity {



    boolean validate(){
//        Toast.makeText(this, termid+"", Toast.LENGTH_SHORT).show();
        if(Status.getSelectedItemPosition() <= 0
                || termid==-1
                ||title.getText().toString().length()==0
                ||StartDate==null
        ||EndDate==null){return false;}
        else{
            ZonedDateTime startZonedDateTime= ZonedDateTime.ofInstant(StartDate.toInstant(), ZoneId.systemDefault());
            ZonedDateTime endZonedDateTime=  ZonedDateTime.ofInstant(EndDate.toInstant(), ZoneId.systemDefault());
            if(startZonedDateTime.isBefore(endZonedDateTime) && endZonedDateTime.isAfter(startZonedDateTime)
                    ||startZonedDateTime.isEqual(endZonedDateTime)) {
                return true;
            }else {
                Toast.makeText(this, "Start time is NOT before the end time AND end time is NOT after the start ", Toast.LENGTH_SHORT).show();
                return false;
            }
        }


    }
    void save(View view){
        if(!validate()){
            Toast.makeText(this, "Please enter all values", Toast.LENGTH_SHORT).show();
            return;
        }
        Repository repo=new Repository(getApplication());
       String titletext=title.getText().toString();
        String Statustext=Status.getPrompt().toString();
//        Toast.makeText(this, "OKAY i am ready", Toast.LENGTH_SHORT).show();
        if (CourseId!=-1){
            Courses c=new Courses(0, titletext, StartDate, EndDate, Statustext, termid);
            c.setId(CourseId);
//            Toast.makeText(this, c.getId()+"update", Toast.LENGTH_SHORT).show();
            repo.UpdateCourse(c);

        }else {
            repo.insertCourse(new Courses(0, titletext, StartDate, EndDate, Statustext, termid));

        }

        finish();
        util.createNotification(this,StartDate.getTime(),"start_alert", "Start Date Alert", "Start Date Alert", "This is a notification to trigger at start date for "+courseName+"");
        util.createNotification(this,EndDate.getTime(),"end_alert", "End Date Alert", "End Date Alert", "This is a notification to trigger at The end date for "+courseName+" is approaching");
    }
    
    Spinner Status;
    int termid;
    EditText title;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    int CourseId;
    String courseName,CourseStatus;

    DatePickerDialog StartDatePickerDialog, EndDatePickerDialog;
    Date StartDate, EndDate;
    Button StartDatePickerButton,EndDatePickerButton;
    Spinner termidSpiner;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        title=findViewById(R.id.course_name);
        Status=findViewById(R.id.Status);


        termid= getIntent().getIntExtra("termId",-1);

        CourseId = getIntent().getIntExtra("CourseId", -1);


        ArrayList<String> statusArray=new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.StatusoftheCourse)));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,statusArray);
        adapter. setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Status.setAdapter(adapter);
        Status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
              Status.setPrompt(parent.getItemAtPosition(position).toString());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        termidSpiner=findViewById(R.id.termid_spinner);
    initTermid();
        findViewById(R.id.save_course).setOnClickListener(this::save);
        initDatePicker();
        if (CourseId!=-1){
//            update course
            courseName = getIntent().getStringExtra("CourseName");
            CourseStatus=getIntent().getStringExtra("CourseStatus");
            StartDate = (Date) getIntent().getSerializableExtra("CourseStartDate");
            EndDate = (Date) getIntent().getSerializableExtra("CourseEndDate");
            title.setText(courseName);
            Status.setSelection( statusArray.indexOf(CourseStatus));
            if (StartDate!=null)
                StartDatePickerButton.setText(util.DatetoNiceString(StartDate));


            if (EndDate!=null)
                EndDatePickerButton.setText(util.DatetoNiceString(EndDate));


        }

        
        
    }

    private void initTermid() {

        if(termid!=-1){

            termidSpiner.setVisibility(View.GONE);
            findViewById(R.id.termidtv).setVisibility(View.GONE);
        return;
        }
        ArrayList<Integer> t=new Repository(getApplication()).getTermList().stream().map(OneTerm::getTermid).collect(Collectors.toCollection(ArrayList::new));

        ArrayAdapter<Integer> adapter =new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, t);
            adapter. setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            termidSpiner.setAdapter(adapter);
            termidSpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    termid= (int) parent.getItemAtPosition(position);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

    }


    private void initDatePicker() {
        StartDatePickerButton =(Button) findViewById(R.id.AddAssesmentStartDatePicker);
        StartDatePickerButton.setOnClickListener(e-> openStartDatePicker());
        StartDatePickerButton.setText("Select start date");
        EndDatePickerButton=(Button) findViewById(R.id.AddAssesmentEndDatePickerButton);
        EndDatePickerButton.setText("Select End date");
        EndDatePickerButton.setOnClickListener(e->openEndDatePicker());
    }

    private void openStartDatePicker() {

        initStartDatePicker();
        StartDatePickerDialog.show();
    }
    private void initStartDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date ) {
                month+=1;

                String mkdate =  makeDateString( year,  month, date );
                String dd= date+"-"+month+"-"+year;
//                Toast.makeText(getApplicationContext(), year+"", Toast.LENGTH_SHORT).show();
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    StartDate = format.parse(dd);
                    StartDatePickerButton.setText("Start Date "+mkdate);
                } catch (ParseException e) {
                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                    StartDate =null;
                }
            }
        };
        int y= ZonedDateTime.now().getYear();
        int m=ZonedDateTime.now().getMonthValue();
        int d=ZonedDateTime.now().getDayOfMonth();
        StartDatePickerDialog =new DatePickerDialog(this,dateSetListener,y,m-1,d);
    }

    private void openEndDatePicker() {
        initEndDatePicker();
        EndDatePickerDialog.show();
    }
    private void initEndDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date ) {
                month+=1;
                String mkdate = makeDateString( year,  month, date );
                String dd= date+"-"+month+"-"+year;

                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    EndDate = format.parse(dd);
                    EndDatePickerButton.setText("End Date "+mkdate);
                } catch (ParseException e) {
                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                    EndDate =null;
                }
            }
        };
        int y=ZonedDateTime.now().getYear();
        int m=ZonedDateTime.now().getMonthValue();
        int d=ZonedDateTime.now().getDayOfMonth();
        EndDatePickerDialog =new DatePickerDialog(this,dateSetListener,y,m-1,d);
    }


}
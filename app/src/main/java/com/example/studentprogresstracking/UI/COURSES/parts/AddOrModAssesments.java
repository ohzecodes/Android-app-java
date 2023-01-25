package com.example.studentprogresstracking.UI.COURSES.parts;

import static com.example.studentprogresstracking.UI.util.DatetoNiceString;
import static com.example.studentprogresstracking.UI.util.makeDateString;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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
import com.example.studentprogresstracking.entity.CourseAssessment;
import com.example.studentprogresstracking.entity.OneTerm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;

public class AddOrModAssesments extends AppCompatActivity {




    Date StartDate, EndDate;
    EditText AddAssessmentTitle;
    Spinner AddAssessmentType;
    Button AddAssesmentStartDatePicker;
    Button AddAssesmentEndDatePicker;
    Button addAssessmentSave;
    int cid,id;

    Repository repo;
    DatePickerDialog StartDatePickerDialog, EndDatePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assesments);
         AddAssessmentTitle = findViewById(R.id.AddAssessmentTitle);
        AddAssessmentType=findViewById(R.id.AddAssessmentType);
        AddAssesmentStartDatePicker=findViewById(R.id.AddAssesmentStartDatePicker);
        AddAssesmentEndDatePicker=findViewById(R.id.AddAssesmentEndDatePicker);
        addAssessmentSave=findViewById(R.id.addAssessmentSave);
        cid= getIntent().getIntExtra("cid",-1);

        ArrayList<String>items=new ArrayList<String>();
        items.add( "Performance Assessment");
        items.add("Objective Assessment");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter. setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        AddAssessmentType.setAdapter(adapter);
        AddAssessmentType.setSelection(0);




         id = getIntent().getIntExtra("id", -1);
        if (id!=-1) {
            String title = getIntent().getStringExtra("title");
            boolean type = getIntent().getBooleanExtra("type", false);
            StartDate = (Date) getIntent().getSerializableExtra("startdate");
            EndDate= (Date) getIntent().getSerializableExtra("enddate");
            AddAssessmentTitle.setText(title);
            AddAssessmentType.setSelection(type?1:0);
            addAssessmentSave.setText("Modify Assessment:"+ id);
        }
      String  startdatetext=StartDate!=null?"Start date: "+DatetoNiceString(StartDate): "Select Start date",
              endDatetext= EndDate!=null?"End date: "+DatetoNiceString(EndDate): "Select end date";
        AddAssesmentStartDatePicker.setText(startdatetext);
        AddAssesmentEndDatePicker.setText(endDatetext);
        AddAssesmentStartDatePicker.setOnClickListener(e->openStartDatePicker());

        AddAssesmentEndDatePicker.setOnClickListener(e->openEndDatePicker());
        addAssessmentSave.setOnClickListener(e->save());


    }

    void save(){

        if (!validate()){
          return;
        }

            String tt=AddAssessmentTitle.getText().toString();
            Repository repo=new Repository(this.getApplication());

            CourseAssessment a= new CourseAssessment(tt,(AddAssessmentType.getSelectedItemPosition()==1) ,EndDate,cid,StartDate);

            if (id!=-1) {
            Toast.makeText(this, ""+id, Toast.LENGTH_SHORT).show();
            a.setId(id);
            repo.update(a);
        }else {
            repo.insert(a);
        }
        util.createNotification(this,StartDate.getTime(),"start_assessment_alert", "Assessment Start Date Alert", " Assessment Start Date Alert", "The start date for Assessment "+ AddAssessmentTitle.getText().toString()+" is approaching");
        util.createNotification(this,EndDate.getTime(),"end_assessment_alert", "Assessment End Date Alert", "Assessment End Date Alert", "The end date for Assessment "+AddAssessmentTitle.getText().toString()+" is approaching");

        finish();


    }
    boolean validate(){
        if (AddAssessmentTitle.getText().toString().length()==0 || StartDate==null || EndDate==null) {
            Toast.makeText(this,"Values can't be empty" , Toast.LENGTH_SHORT).show();
            return false;
        }else{
            ZonedDateTime startZonedDateTime= ZonedDateTime.ofInstant(StartDate.toInstant(), ZoneId.systemDefault());
            ZonedDateTime endZonedDateTime=  ZonedDateTime.ofInstant(EndDate.toInstant(), ZoneId.systemDefault());
            if(startZonedDateTime.isBefore(endZonedDateTime) && endZonedDateTime.isAfter(startZonedDateTime)
                    ||startZonedDateTime.isEqual(endZonedDateTime)){
                return true;
            }else {
                Toast.makeText(this, "Start time is NOT before the end time AND end time is NOT after the start ", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

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
                    AddAssesmentStartDatePicker.setText("Start Date: "+mkdate);
                } catch (ParseException e) {
                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                    StartDate =null;
                }
            }
        };
        int y=ZonedDateTime.now().getYear();
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
                    AddAssesmentEndDatePicker.setText("End Date "+mkdate);
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
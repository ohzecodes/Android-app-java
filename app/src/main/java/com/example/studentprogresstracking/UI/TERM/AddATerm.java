package com.example.studentprogresstracking.UI.TERM;

import static com.example.studentprogresstracking.UI.util.makeDateString;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentprogresstracking.R;
import com.example.studentprogresstracking.database.Repository;
import com.example.studentprogresstracking.entity.OneTerm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class AddATerm extends AppCompatActivity  {
    EditText title;
    DatePickerDialog StartDatePickerDialog, EndDatePickerDialog;
    Date StartDate, EndDate;
    Button StartDatePickerButton,EndDatePickerButton;
    void save(){
        if (validate()){
           String tt= String.valueOf(title.getText());
            (new Repository(getApplication())).insertTerm(new OneTerm(0,tt,StartDate,EndDate));
            finish();

        }
    }
    boolean validate(){
        if (title.getText().toString().length()==0 || StartDate==null || EndDate==null) {
            Toast.makeText(this,"Values can't be empty" , Toast.LENGTH_SHORT).show();

            return false;
        }else{
           ZonedDateTime startZonedDateTime= ZonedDateTime.ofInstant(StartDate.toInstant(), ZoneId.systemDefault());
            ZonedDateTime endZonedDateTime=  ZonedDateTime.ofInstant(EndDate.toInstant(), ZoneId.systemDefault());
            if(startZonedDateTime.isBefore(endZonedDateTime) && endZonedDateTime.isAfter(startZonedDateTime)) {
               return true;
                }else {
                Toast.makeText(this, "Start time is NOT before the end time AND end time is NOT after the start ", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_term);
        ((Button) findViewById(R.id.saveterm)).setOnClickListener(e->save());
        title= (EditText) findViewById(R.id.AddtermTitle);

        StartDatePickerButton =(Button) findViewById(R.id.AddtermStartDatePicker);
        StartDatePickerButton.setOnClickListener(e-> openStartDatePicker());
        StartDatePickerButton.setText("Select start date");
        EndDatePickerButton=(Button) findViewById(R.id.AddTermEndDatePicker);
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
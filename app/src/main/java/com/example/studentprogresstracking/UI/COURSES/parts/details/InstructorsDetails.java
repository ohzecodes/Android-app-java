package com.example.studentprogresstracking.UI.COURSES.parts.details;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.studentprogresstracking.R;
import com.example.studentprogresstracking.UI.COURSES.parts.AddOrModifyInstructor;
import com.example.studentprogresstracking.UI.util;
import com.example.studentprogresstracking.database.Repository;
import com.example.studentprogresstracking.entity.Instructor;

public class InstructorsDetails extends AppCompatActivity {
    TextView instructor_details_id,instructor_details_name,instructor_details_email,
            instructor_details_phone,instructor_details_courseid;
    int id, courseid;
    String name, email,phone;
    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_instructors);


            id= getIntent().getIntExtra("id",-1);
            name =  getIntent().getStringExtra("name");
            email=  getIntent().getStringExtra("email");
            phone= getIntent().getStringExtra("phone");
            courseid= getIntent().getIntExtra("cid",-1);

            instructor_details_id=findViewById(R.id.instructor_details_id);
            instructor_details_name=findViewById(R.id.instructor_details_name);
            instructor_details_email=findViewById(R.id.instructor_details_email);
            instructor_details_phone=findViewById(R.id.instructor_details_phone);
            instructor_details_courseid=findViewById(R.id.instructor_details_courseid);

        instructor_details_id.setText("Instructor ID: "+id);
        instructor_details_name.setText("Instructor Name: "+ util.capitalize(name));
        instructor_details_email.setText("Instructor Email: "+email);
        instructor_details_phone.setText("Instructor Phone Number: "+phone);
        instructor_details_courseid.setText("Course ID: "+courseid);


        ((Button) findViewById(R.id.delete_instructor_details)).setOnClickListener(e->Delete());
            ((Button) findViewById(R.id.edit_instructor_details)).setOnClickListener(e->{
                Intent intent = new Intent(this, AddOrModifyInstructor.class);
                intent.putExtra("id", id);
                intent.putExtra("Cid", courseid);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("phone", phone);
                startActivity(intent);


            });
    }


    @Override
    protected void onResume() {
        super.onResume();
        initTextField();
    }

    @SuppressLint("SetTextI18n")
    private void initTextField() {
   Instructor i =( new Repository(this.getApplication())).getInstructor(id,courseid);
        name=i.getName();
        email=i.getEmail();
        phone=i.getPhoneNumber();
        instructor_details_id.setText("Instructor ID: "+id);
        instructor_details_name.setText("Instructor Name: "+ util.capitalize(name));
        instructor_details_email.setText("Instructor Email: "+email);
        instructor_details_phone.setText("Instructor Phone Number: "+phone);
        instructor_details_courseid.setText("Course ID: "+courseid);

    }


    public void Delete() {


        AlertDialog.Builder bu = new AlertDialog.Builder(this);
        bu.setTitle("Confirm Delete");
        bu.setMessage("Are you sure you want to delete " + "?");
        bu.setPositiveButton("Yes", (dialogInterface, i1) -> {
            Repository repo=new Repository(this.getApplication());
            Instructor ins=new Instructor(0,email,name,phone,courseid);
            ins.setInstructorID(id);
            repo.deleteInstructor(ins);
            finish();
        });
        bu.setNegativeButton("No, Please", (dialogInterface, i1) -> {
            dialogInterface.dismiss();
        });
        bu.create();
        bu.show();

    }


}
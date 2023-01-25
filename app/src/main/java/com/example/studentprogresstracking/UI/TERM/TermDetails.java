package com.example.studentprogresstracking.UI.TERM;

import static com.example.studentprogresstracking.UI.util.DatetoNiceString;
import static com.example.studentprogresstracking.UI.util.capitalize;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentprogresstracking.R;
import com.example.studentprogresstracking.UI.COURSES.AddOrModACourse;
import com.example.studentprogresstracking.UI.adpaters.CourseRVAdpater;
import com.example.studentprogresstracking.database.Repository;
import com.example.studentprogresstracking.entity.Courses;

import java.util.ArrayList;
import java.util.Date;

public class TermDetails extends AppCompatActivity {
    int id;
    Repository repo;
    ArrayList<Courses> CoursesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_term);
        int termid = getIntent().getIntExtra("termid", -1);
        id=termid;
        Date startDate = (Date) getIntent().getSerializableExtra("termStartDate");
        Date endDate = (Date) getIntent().getSerializableExtra("termEndDate");
        String termtitle = getIntent().getStringExtra("term_title");
        Toast.makeText(this, termid+"", Toast.LENGTH_SHORT).show();
        ((TextView)findViewById(R.id.DetailedtermName)).setText(capitalize(termtitle));
        ((TextView)findViewById(R.id.Detailedtermid)).setText(capitalize("term Id: "+id));
        ((TextView)findViewById(R.id.ETstartdate)).setText(DatetoNiceString(startDate));
        ((TextView)findViewById(R.id.ETEnddate)).setText(DatetoNiceString(endDate));

         repo =   new Repository(getApplication());


        initrv();

        ((Button ) findViewById(R.id.AddCoursesHere)).setOnClickListener(e->{
            Intent intent = new Intent(this, AddOrModACourse.class);
            intent.putExtra("termId",id);
            startActivity(intent);
        });

    }

    private void initrv() {
        CoursesList= repo.getCourseListByTerm(id);
        RecyclerView RvCoursesthisTerm=findViewById(R.id.RvCoursesthisTerm);
        RvCoursesthisTerm.setLayoutManager(new LinearLayoutManager(this));
        RvCoursesthisTerm.setAdapter(new CourseRVAdpater(this, CoursesList));
    }

    @Override
    protected void onResume() {
        super.onResume();
        initrv();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.termmenu,menu);
        menu.findItem(R.id.edit).setVisible(false);
        if(!CoursesList.isEmpty()){
            MenuItem item = menu.findItem(R.id.delete);
            SpannableString s = new SpannableString(item.getTitle());
            s.setSpan(new StrikethroughSpan(), 0, s.length(), 0);
            item.setTitle(s);
        }
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit:
                Toast.makeText(this, "edit", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                AlertDialog.Builder b=new AlertDialog.Builder(this);
                if(CoursesList.isEmpty()){
                b.setTitle("Delete term");
                b.setMessage("are you sure you want to delete this term");
                b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Delete();
                    }
                });
                b.setNegativeButton("No",null);
               }
                else{
                    b.setTitle("Can't Delete term");
                    b.setMessage("You can't Delete the term with course associated.");
                    b.setNegativeButton("I Understood",null);
                }
                b.create().show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void Delete() {
        (new Repository(getApplication())).deleteTermById(id);
        finish();
    }
}
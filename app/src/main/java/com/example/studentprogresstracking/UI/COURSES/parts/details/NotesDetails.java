package com.example.studentprogresstracking.UI.COURSES.parts.details;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.studentprogresstracking.R;
import com.example.studentprogresstracking.UI.COURSES.parts.AddOrModNotes;
import com.example.studentprogresstracking.database.Repository;
import com.example.studentprogresstracking.entity.CourseNotes;
import com.example.studentprogresstracking.entity.Instructor;

public class NotesDetails extends AppCompatActivity {
    CourseNotes cn;
    int noteid,cid;

    TextView title,content;
    Button edit,delete,share;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_notes);
        title= findViewById(R.id.detailnotetitle);
        content=findViewById(R.id.detailnotecontent);
        edit=findViewById(R.id.detailnoteedit);
        delete=findViewById(R.id.detailnotedelete);
        share=findViewById(R.id.detailnoteshare);

        noteid= getIntent().getIntExtra("noteid",-1);
        cid= getIntent().getIntExtra("cid",-1);
        if (noteid!=-1) {
            inittextfields();
        }


    }

    public void Delete() {



        AlertDialog.Builder bu = new AlertDialog.Builder(this);
        bu.setTitle("Confirm Delete");
        bu.setMessage("Are you sure you want to delete " + "?");
        bu.setPositiveButton("Yes", (dialogInterface, i1) -> {
            Repository repo = new Repository(this.getApplication());

            repo.DeleteNote(cn);
            finish();
        });
        bu.setNegativeButton("No, Please", (dialogInterface, i1) -> {
            dialogInterface.dismiss();
        });
        bu.create();
        bu.show();
    }

    public void Share(){
        Intent intent = new Intent (Intent.ACTION_SEND) ;
        intent.putExtra (Intent. EXTRA_SUBJECT, title.getText());
        intent.putExtra(Intent. EXTRA_TEXT, title.getText()+"\n"+content.getText());
        intent.setType("message/rfc822");
        startActivity (Intent.createChooser (intent, "Choose an email client"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        inittextfields();
    }

    private void inittextfields() {
        Repository repo=new Repository(getApplication());
        cn= repo.getOneNote(noteid);
        title.setText(cn.getTitle());
        content.setText(cn.getText());
        delete.setOnClickListener(e->Delete());

        edit.setOnClickListener(e->{
            Intent i=new Intent(this, AddOrModNotes.class);
            i.putExtra("cid",cid);
            i.putExtra("noteid",noteid);
            i.putExtra("title",cn.getTitle());
            i.putExtra("content",cn.getText());
            startActivity(i);
        });
        share.setOnClickListener(e->{
            Share();


        });


    }
}
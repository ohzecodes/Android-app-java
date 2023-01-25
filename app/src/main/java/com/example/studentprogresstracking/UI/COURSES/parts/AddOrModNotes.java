package com.example.studentprogresstracking.UI.COURSES.parts;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentprogresstracking.R;
import com.example.studentprogresstracking.database.Repository;
import com.example.studentprogresstracking.entity.CourseNotes;

public class AddOrModNotes extends AppCompatActivity {
    int noteid,cid;
   TextView title,content;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
       title= findViewById(R.id.AddNoteTitle);
       content= findViewById(R.id.AddNoteContent);
      cid= getIntent().getIntExtra("cid",-1);
        noteid= getIntent().getIntExtra("noteid",-1);
        if(noteid!=-1){
//            modify: so set fields
           title.setText( getIntent().getStringExtra("title"));
             content.setText(getIntent().getStringExtra("content"));

        }
        ((Button)findViewById(R.id.AddNoteSave)).setOnClickListener(e->save());

    }

    private boolean validate(){

        return (title.getText().length()!=0 && content.getText().length()!=0&&cid!=-1);
    }
    private void save(){
        if(!validate()) {
            Toast.makeText(this, "INVALID FIELDS", Toast.LENGTH_SHORT).show();
            return;
        }
        Repository repo=new Repository(this.getApplication());
        CourseNotes cn=new CourseNotes(
                title.getText().toString(),
                content.getText().toString(),cid);
//        Toast.makeText(this, ""+noteid, Toast.LENGTH_SHORT).show();
        if (noteid!=-1){

            cn.setId(noteid);
            repo.update(cn);

        }else {
            repo.insert(cn);
        }
        finish();
    }
}
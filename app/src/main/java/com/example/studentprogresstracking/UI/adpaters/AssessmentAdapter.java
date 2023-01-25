package com.example.studentprogresstracking.UI.adpaters;

import static com.example.studentprogresstracking.UI.util.DatetoNiceString;
import static com.example.studentprogresstracking.UI.util.capitalize;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentprogresstracking.R;

import com.example.studentprogresstracking.UI.COURSES.parts.details.AssessmentDetails;
import com.example.studentprogresstracking.entity.CourseAssessment;
import com.example.studentprogresstracking.entity.Courses;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssementHolder> {

    ArrayList<CourseAssessment> AssesmentArrayList;

    Context c;
    LayoutInflater inflater;

    class AssementHolder extends RecyclerView.ViewHolder {
        TextView Assesmenttitle,Assesmenttype,Startdate,EndDate;
        public AssementHolder(@NonNull View itemView) {
            super(itemView);
            Assesmenttype=itemView.findViewById(R.id.Assesmenttype);
            Assesmenttitle =itemView.findViewById(R.id.Assesmenttitle);
           Startdate=itemView.findViewById(R.id.Assesmentstartdate);
            EndDate= itemView.findViewById(R.id.Assesmentenddate);
    itemView.setOnClickListener(e->{
       CourseAssessment a= AssesmentArrayList.get(getAdapterPosition()) ;
        Intent i =new Intent(c, AssessmentDetails.class);
        i.putExtra("cid",a.getCourseId());
        i.putExtra("id",a.getId());
        i.putExtra("title",a.getTitle());
        i.putExtra("type",a.getObjectiveAssessment());
        i.putExtra("startdate",a.getStartDate());
        i.putExtra("enddate",a.getEndDate());
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        c.startActivity(i);
    });
        }
    }

    public AssessmentAdapter(Context c, ArrayList<CourseAssessment> ArrayList) {
        this.AssesmentArrayList = ArrayList;
        this.c=c;
        inflater=LayoutInflater.from(c);

    }

    @NonNull
    @Override
    public AssementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AssementHolder(inflater.inflate(R.layout.one_assesment,parent,false ));

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AssementHolder holder, int position) {
        if (AssesmentArrayList != null) {
            CourseAssessment c = AssesmentArrayList.get(position);
          String type=  c.getObjectiveAssessment()?"Objective Assessment":"Performance Assessment";
          holder.Assesmenttype.setText(type);
          holder.Assesmenttitle.setText(capitalize(c.getTitle()));
          holder.EndDate.setText(DatetoNiceString(c.getEndDate()));
          holder.Startdate.setText(DatetoNiceString(c.getStartDate()));

        }else{

        }
    }

    @Override
    public int getItemCount() {
        if(AssesmentArrayList==null){
            return 0;
        }
        return AssesmentArrayList.size();

    }

    @SuppressLint("NotifyDataSetChanged")
    public void setCoursesArrayList(ArrayList<CourseAssessment> AssesmentArrayList) {
        this.AssesmentArrayList = AssesmentArrayList;
        notifyDataSetChanged();
    }



}


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

import com.example.studentprogresstracking.UI.COURSES.CourseDetails;
import com.example.studentprogresstracking.entity.Courses;

import java.util.ArrayList;
import java.util.Locale;


public class CourseRVAdpater extends RecyclerView.Adapter<CourseRVAdpater.CourseHolder> {

    ArrayList<Courses> coursesArrayList;

    Context c;
    LayoutInflater inflater;

    class CourseHolder extends RecyclerView.ViewHolder {
        TextView coursetitle, coursestartdate, courseenddate, coursestatus, termid;
        public CourseHolder(@NonNull View itemView) {
            super(itemView);

            coursetitle=itemView.findViewById(R.id.coursetitle);
            coursestartdate=itemView.findViewById(R.id.coursestartdate);
            courseenddate= itemView.findViewById(R.id.courseenddate);
            coursestatus= itemView.findViewById(R.id.courseStatus);
            termid= itemView.findViewById(R.id.termID);
            itemView.setOnClickListener(e-> {
                int position = getAdapterPosition();
                Courses ClickedCourse =coursesArrayList.get(position);
//                                Toast.makeText(c, ClickedTerm.getTitle(), Toast.LENGTH_SHORT).show();
                Intent i=new Intent(c, CourseDetails.class);
                i.putExtra("CourseName", ClickedCourse.getName());
                i.putExtra("CourseId",ClickedCourse.getId());
                i.putExtra( "CourseStatus",ClickedCourse.getStatus());
                i.putExtra("termid",ClickedCourse.getTermId());
                i.putExtra("CourseStartDate",ClickedCourse.getStart_date());
                i.putExtra("CourseEndDate",ClickedCourse.getEnd_date());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                c.startActivity(i);
            });

        }
    }

    public CourseRVAdpater(Context c, ArrayList<Courses> coursesArrayList) {
        this.coursesArrayList = coursesArrayList;
        this.c=c;
        inflater=LayoutInflater.from(c);

    }

    @NonNull
    @Override
    public CourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CourseHolder(inflater.inflate(R.layout.one_course,parent,false ));

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CourseHolder holder, int position) {
        if (coursesArrayList != null) {

            if (c.getClass().getSimpleName().toUpperCase(Locale.ROOT).contains("TERM")) {

                holder.termid.setVisibility(View.GONE);
            } else {
                holder.termid.setVisibility(View.VISIBLE);
            }
            holder.termid.setText("Term id: "+coursesArrayList.get(position).getTermId());
            holder.coursetitle.setText(capitalize(coursesArrayList.get(position).getName()));
            holder.coursestartdate.setText("Start Date: " +DatetoNiceString(coursesArrayList.get(position).getStart_date()));
            holder.courseenddate.setText("End Date: " +DatetoNiceString(coursesArrayList.get(position).getEnd_date()));
            holder.coursestatus.setText("Status: " +coursesArrayList.get(position).getStatus());
        }else{
            holder.coursetitle.setText(capitalize("No related Course"));
            Toast.makeText(c, "No related Course", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public int getItemCount() {
        if(coursesArrayList==null){
            return 0;
        }
        return coursesArrayList.size();

    }

    @SuppressLint("NotifyDataSetChanged")
    public void setCoursesArrayList(ArrayList<Courses> coursesArrayList) {
        this.coursesArrayList = coursesArrayList;
        notifyDataSetChanged();
    }



}


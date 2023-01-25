package com.example.studentprogresstracking.UI.adpaters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentprogresstracking.R;

import com.example.studentprogresstracking.UI.COURSES.parts.details.InstructorsDetails;
import com.example.studentprogresstracking.UI.util;
import com.example.studentprogresstracking.entity.Courses;
import com.example.studentprogresstracking.entity.Instructor;

import java.util.ArrayList;


public class InstructorAdapter extends RecyclerView.Adapter<InstructorAdapter.ViewHolder> {
    private ArrayList<Instructor> instructors;
    private Application app; // add this variable

   private Context context;
    public InstructorAdapter(ArrayList<Instructor> instructors,Application app,Context c  ) {
        this.instructors = instructors;
        this.app=app;
        this.context=c;

    }

    @Override
    public InstructorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_instructor, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InstructorAdapter.ViewHolder holder, int position) {
        Instructor instructor = instructors.get(position);
        holder.email.setText(instructor.getEmail());
        holder.name.setText(util.capitalize(instructor.getName()));
        holder.phoneNumber.setText(instructor.getPhoneNumber());
        holder.tvid.setText(instructor.getInstructorID()+"");

    }


    @Override
    public int getItemCount() {  return instructors.size();}

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView email, name, phoneNumber,tvid;



        public ViewHolder(View itemView) {
            super(itemView);
            tvid=itemView.findViewById(R.id.tvid);
            email = itemView.findViewById(R.id.instructor_email);
            name = itemView.findViewById(R.id.instructor_name);
            phoneNumber = itemView.findViewById(R.id.instructor_phone_number);
            itemView.setOnClickListener(e->{
                int position = getAdapterPosition();
                Instructor instructor =instructors.get(position);
                Intent i=new Intent(context, InstructorsDetails.class);
                i.putExtra("id",instructor.getInstructorID());
                i.putExtra("name",instructor.getName());
                i.putExtra("email",instructor.getEmail());
                i.putExtra("phone",instructor.getPhoneNumber());
                i.putExtra("cid",instructor.getCourseID());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            });

        }
    }
}


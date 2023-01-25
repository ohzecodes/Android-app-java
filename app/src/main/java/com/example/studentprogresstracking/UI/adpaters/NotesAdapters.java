package com.example.studentprogresstracking.UI.adpaters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentprogresstracking.R;
import com.example.studentprogresstracking.UI.COURSES.parts.details.NotesDetails;
import com.example.studentprogresstracking.entity.CourseNotes;

import java.util.ArrayList;

public class NotesAdapters  extends RecyclerView.Adapter<NotesAdapters.ViewHolder>{
    private Context c;
    private ArrayList<CourseNotes> cns;

    public NotesAdapters(Context c, ArrayList<CourseNotes> cns) {
        this.c = c;
        this.cns = cns;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
    TextView RVNoteid,RvNotecotent,RVNotetitle;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        RVNoteid =itemView.findViewById(R.id.RVNoteid );
        RvNotecotent=itemView.findViewById(R.id.RvNotecotent);
        RVNotetitle=itemView.findViewById(R.id.RVNotetitle);
        itemView.setOnClickListener(e->{
            Intent i=new Intent(c, NotesDetails.class);
            int position = getAdapterPosition();
            CourseNotes cn = cns.get(position);
            i.putExtra("noteid",cn.getId());
            i.putExtra("cid",cn.getCourseId());
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            c.startActivity(i);
        });
    }
}

    @NonNull
    @Override
    public NotesAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_note, parent, false);
        return new NotesAdapters.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull NotesAdapters.ViewHolder holder, int position) {
        if (cns!=null) {
            CourseNotes cn = cns.get(position);
            holder.RvNotecotent.setText(cn.getText());
            holder.RVNoteid.setText(cn.getId()+"");
            holder.RVNotetitle.setText(cn.getTitle());
        }else{}

    }

    @Override
    public int getItemCount() {
        return cns.size();
    }
}

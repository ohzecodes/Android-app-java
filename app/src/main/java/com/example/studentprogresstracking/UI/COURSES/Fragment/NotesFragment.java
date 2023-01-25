package com.example.studentprogresstracking.UI.COURSES.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.studentprogresstracking.R;
import com.example.studentprogresstracking.UI.adpaters.AssessmentAdapter;
import com.example.studentprogresstracking.UI.adpaters.NotesAdapters;
import com.example.studentprogresstracking.database.Repository;
import com.example.studentprogresstracking.entity.CourseAssessment;
import com.example.studentprogresstracking.entity.CourseNotes;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class NotesFragment extends Fragment {

    private int cid;
    private ArrayList<CourseNotes> Cal;
    private RecyclerView rv;
    @Override
    public void onStart() {
        super.onStart();

        initRv();
    }

    @Override
    public void onResume() {
        super.onResume();
        initRv();
    }

    private void initRv() {
        View v =getView();
        if (v!=null) {
            this.Cal=new Repository(this.getActivity().getApplication()).getNotesByCourse(cid);

            this.rv = v.findViewById(R.id.notesRV);
            rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
            rv.setAdapter(new NotesAdapters(this.getContext(),Cal));
        }

    }

    public NotesFragment(int cid ) {
        this.cid = cid;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }
}
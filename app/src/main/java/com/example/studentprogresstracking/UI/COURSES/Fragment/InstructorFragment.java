package com.example.studentprogresstracking.UI.COURSES.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.studentprogresstracking.R;
import com.example.studentprogresstracking.UI.adpaters.InstructorAdapter;
import com.example.studentprogresstracking.database.Repository;
import com.example.studentprogresstracking.entity.Instructor;

import java.util.ArrayList;

public class InstructorFragment extends Fragment {
    private RecyclerView instructorRecyclerView;
    private ArrayList<Instructor> ListoFInstructors;

    int cid;


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
            this.ListoFInstructors=new Repository(this.getActivity().getApplication()).getInstructorByCourse(cid);
            this.instructorRecyclerView = v.findViewById(R.id.instructorRV);
            instructorRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

            instructorRecyclerView.setAdapter( new InstructorAdapter(ListoFInstructors,this.getActivity().getApplication() ,this.getContext()));
          }

    }

    public InstructorFragment(int cid ) {
        this.cid = cid;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_instructor, container, false);
    }


}
package com.example.studentprogresstracking.UI.adpaters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import com.example.studentprogresstracking.UI.COURSES.Fragment.*;
import com.example.studentprogresstracking.database.Repository;
import com.example.studentprogresstracking.entity.Courses;

public class TabCourseAdapter extends FragmentStateAdapter {

    int CourseId;
    Courses Course;


    public TabCourseAdapter(@NonNull FragmentActivity fragmentActivity,int courseId,Repository repo
    ) {
        super(fragmentActivity);
        CourseId = courseId;
        this.Course=repo.getCoursebyId(CourseId);

    }



    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new InstructorFragment(CourseId);
            case 2:
                return new AssesmentsFragment(CourseId);
            case 3:
                return new NotesFragment(CourseId);
            default:
                return new CourseDetails(Course);
        }
    }


    @Override
    public int getItemCount() {
        return 4;
    }
}

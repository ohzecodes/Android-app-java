package com.example.studentprogresstracking.database;

import android.app.Application;


import com.example.studentprogresstracking.dao.*;
import com.example.studentprogresstracking.entity.*;

import java.util.ArrayList;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Repository {


    private InstructorDAO mInstructorDao;
    private CoursesDAO mCoursesDAO;
    private termDAO mTermDAO;
    private  CourseAssessmentDAO mAssesmentDao;
    private  CourseNotesDAO mCourseNotesDAO;
    ArrayList<Courses> CourseList;
    ArrayList<OneTerm> TermList;
    ArrayList<CourseNotes> n;
    ArrayList<CourseAssessment> allAssessments;
    ArrayList<Instructor> i;
    static int THREAD=4;

    static final ExecutorService databaseExecutor=Executors.newFixedThreadPool(THREAD);

    public Repository(Application application) {
        DatabaseBuilder db=DatabaseBuilder.getDatabase(application);
        mCoursesDAO= db.CoursesDAO();
        mTermDAO= db.termDAO();
        mInstructorDao=db.InstructorDAO();
        mAssesmentDao=  db.CourseAssessmentDao();
        mCourseNotesDAO=db.CourseNotesDAO();


    }

//    courses
    public ArrayList<Courses> getCourseListByTerm(int tid) {

        return   getAllCourse().stream().filter(e->e.getTermId()==tid).collect(Collectors.toCollection(ArrayList::new));

    }
    public ArrayList<Courses>  getAllCourse() {
            databaseExecutor.execute(() -> {
                CourseList = (ArrayList<Courses>) mCoursesDAO.GetAllCourses();
            });
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        return  CourseList;
    }
    public void insertCourse(Courses course) {
        databaseExecutor.execute(()-> mCoursesDAO.insertCourse(course));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<OneTerm> getTermList() {
        databaseExecutor.execute(()->{
            TermList= (ArrayList<OneTerm>) mTermDAO.GetAllterms();
//                    .stream().map().collect(Collectors.toList());
        });
        try {
            Thread.sleep(90);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  TermList;
    }
    public Courses getCoursebyId(int courseId) {

        Courses c =getAllCourse().stream().filter(e->e.getId()==courseId).collect(Collectors.toList()).get(0);
        return c;
    }
    public void UpdateCourse(Courses c) {
        databaseExecutor.execute(()->mCoursesDAO.UpdateCourse(c));
    }
    public void DeleteCourseByID(int id){
        databaseExecutor.execute(()->mAssesmentDao.DeleteAssessmentsByCourseId(id));
        databaseExecutor.execute(()->mInstructorDao.DeleteInstructorByCourse(id));
        databaseExecutor.execute(()->mCourseNotesDAO.deleteNotesByCourseId(id));
        databaseExecutor.execute(()->mCoursesDAO.DeleteCoursesById(id));
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ;
    }

//Term
    public void insertTerm(OneTerm t) {
        databaseExecutor.execute(()->{
            mTermDAO.insertTerm(t);
        });
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void UpdateTerm(OneTerm t) {
        databaseExecutor.execute(()->{
            mTermDAO.UpdateTerm(t);
        });
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void DeleteTerm(OneTerm t){
        databaseExecutor.execute(()->{
            mTermDAO.DeleteTerm(t);
        });
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void deleteTermById(int id){
        databaseExecutor.execute(()-> mTermDAO.deleteTermById(id));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


//Instructor
    public void insert(Instructor t) {
        databaseExecutor.execute(()->{
            mInstructorDao.insert(t);
        });
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Instructor> getInstructorByCourse(int cid){

        databaseExecutor.execute(()->{
           i= (ArrayList<Instructor>) mInstructorDao.GetInstructorByCourse(cid);
        });
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i;
    }
    public void update(Instructor n) {
        databaseExecutor.execute(()->{
            mInstructorDao.Update(n);
        });
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void deleteInstructor(Instructor instructor) {
        databaseExecutor.execute(()->{
        mInstructorDao.Delete(instructor);
        });
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ;
    }

    public Instructor getInstructor(int id,int cid) {
       return  getInstructorByCourse(cid).stream().filter(e->e.getInstructorID()==id).findFirst().get();


    }



//Assesment
    public void insert(CourseAssessment A ) {
        databaseExecutor.execute(()->{
            mAssesmentDao.insert(A);
        });
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<CourseAssessment> getAllAssessmentByCourseId(int cid){
        databaseExecutor.execute(()->{
            allAssessments= (ArrayList<CourseAssessment>) mAssesmentDao.getAssessmentsByCourseId(cid);
        });
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return allAssessments;
    }
    public void deleteAssessment(CourseAssessment ca) {
        databaseExecutor.execute(()->{
            mAssesmentDao.delete(ca);
        });
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ;

    }
    public void update(CourseAssessment ca) {
        databaseExecutor.execute(()->{
            mAssesmentDao.update(ca);
        });
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public CourseAssessment getOneAssessment(int id, int cid) {
  return getAllAssessmentByCourseId(cid).stream().filter(e->e.getId()==id).findFirst().get();

    }


//notes
    public ArrayList<CourseNotes> getAllNote(){
        databaseExecutor.execute(()->{
             n = (ArrayList<CourseNotes>) mCourseNotesDAO.getAllNotes();
        });
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return n;
    }
    public void insert(CourseNotes cn) {
        databaseExecutor.execute(()->{
            mCourseNotesDAO.insert(cn);
        });
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void update(CourseNotes cn) {
        databaseExecutor.execute(()->{
            mCourseNotesDAO.update(cn);
        });
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public CourseNotes getOneNote(int nid){
        return getAllNote().stream().filter(e->e.getId()==nid).findFirst().get();
    }
    public ArrayList<CourseNotes> getNotesByCourse(int cid){
      return (ArrayList<CourseNotes>) getAllNote().stream()
              .filter(e->e.getCourseId()==cid)
              .collect(Collectors.toList());
    }

    public void DeleteNote(CourseNotes cn) {
        databaseExecutor.execute(()->{
            mCourseNotesDAO.delete(cn);
        });
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



}

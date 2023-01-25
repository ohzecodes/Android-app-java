package com.example.studentprogresstracking.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.example.studentprogresstracking.dao.CourseAssessmentDAO;
import com.example.studentprogresstracking.dao.CourseAssessmentDAO_Impl;
import com.example.studentprogresstracking.dao.CourseNotesDAO;
import com.example.studentprogresstracking.dao.CourseNotesDAO_Impl;
import com.example.studentprogresstracking.dao.CoursesDAO;
import com.example.studentprogresstracking.dao.CoursesDAO_Impl;
import com.example.studentprogresstracking.dao.InstructorDAO;
import com.example.studentprogresstracking.dao.InstructorDAO_Impl;
import com.example.studentprogresstracking.dao.termDAO;
import com.example.studentprogresstracking.dao.termDAO_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class DatabaseBuilder_Impl extends DatabaseBuilder {
  private volatile CoursesDAO _coursesDAO;

  private volatile termDAO _termDAO;

  private volatile InstructorDAO _instructorDAO;

  private volatile CourseAssessmentDAO _courseAssessmentDAO;

  private volatile CourseNotesDAO _courseNotesDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(20) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `courses` (`Id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `Name` TEXT, `start_date` INTEGER, `end_date` INTEGER, `status` TEXT, `TermId` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Term` (`termid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `Title` TEXT, `StartDate` INTEGER, `EndDate` INTEGER)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Instructor` (`InstructorID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `email` TEXT, `name` TEXT, `phoneNumber` TEXT, `CourseID` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `notes` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT, `text` TEXT, `courseId` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `CourseAssessment` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `courseId` INTEGER NOT NULL, `title` TEXT, `ObjectiveAssessment` INTEGER NOT NULL, `StartDate` INTEGER, `endDate` INTEGER)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'd2d3e8fc93386efa3eae044ddb01db44')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `courses`");
        _db.execSQL("DROP TABLE IF EXISTS `Term`");
        _db.execSQL("DROP TABLE IF EXISTS `Instructor`");
        _db.execSQL("DROP TABLE IF EXISTS `notes`");
        _db.execSQL("DROP TABLE IF EXISTS `CourseAssessment`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsCourses = new HashMap<String, TableInfo.Column>(6);
        _columnsCourses.put("Id", new TableInfo.Column("Id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourses.put("Name", new TableInfo.Column("Name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourses.put("start_date", new TableInfo.Column("start_date", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourses.put("end_date", new TableInfo.Column("end_date", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourses.put("status", new TableInfo.Column("status", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourses.put("TermId", new TableInfo.Column("TermId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCourses = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCourses = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCourses = new TableInfo("courses", _columnsCourses, _foreignKeysCourses, _indicesCourses);
        final TableInfo _existingCourses = TableInfo.read(_db, "courses");
        if (! _infoCourses.equals(_existingCourses)) {
          return new RoomOpenHelper.ValidationResult(false, "courses(com.example.studentprogresstracking.entity.Courses).\n"
                  + " Expected:\n" + _infoCourses + "\n"
                  + " Found:\n" + _existingCourses);
        }
        final HashMap<String, TableInfo.Column> _columnsTerm = new HashMap<String, TableInfo.Column>(4);
        _columnsTerm.put("termid", new TableInfo.Column("termid", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTerm.put("Title", new TableInfo.Column("Title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTerm.put("StartDate", new TableInfo.Column("StartDate", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTerm.put("EndDate", new TableInfo.Column("EndDate", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTerm = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTerm = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTerm = new TableInfo("Term", _columnsTerm, _foreignKeysTerm, _indicesTerm);
        final TableInfo _existingTerm = TableInfo.read(_db, "Term");
        if (! _infoTerm.equals(_existingTerm)) {
          return new RoomOpenHelper.ValidationResult(false, "Term(com.example.studentprogresstracking.entity.OneTerm).\n"
                  + " Expected:\n" + _infoTerm + "\n"
                  + " Found:\n" + _existingTerm);
        }
        final HashMap<String, TableInfo.Column> _columnsInstructor = new HashMap<String, TableInfo.Column>(5);
        _columnsInstructor.put("InstructorID", new TableInfo.Column("InstructorID", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInstructor.put("email", new TableInfo.Column("email", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInstructor.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInstructor.put("phoneNumber", new TableInfo.Column("phoneNumber", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInstructor.put("CourseID", new TableInfo.Column("CourseID", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysInstructor = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesInstructor = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoInstructor = new TableInfo("Instructor", _columnsInstructor, _foreignKeysInstructor, _indicesInstructor);
        final TableInfo _existingInstructor = TableInfo.read(_db, "Instructor");
        if (! _infoInstructor.equals(_existingInstructor)) {
          return new RoomOpenHelper.ValidationResult(false, "Instructor(com.example.studentprogresstracking.entity.Instructor).\n"
                  + " Expected:\n" + _infoInstructor + "\n"
                  + " Found:\n" + _existingInstructor);
        }
        final HashMap<String, TableInfo.Column> _columnsNotes = new HashMap<String, TableInfo.Column>(4);
        _columnsNotes.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotes.put("title", new TableInfo.Column("title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotes.put("text", new TableInfo.Column("text", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotes.put("courseId", new TableInfo.Column("courseId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysNotes = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesNotes = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoNotes = new TableInfo("notes", _columnsNotes, _foreignKeysNotes, _indicesNotes);
        final TableInfo _existingNotes = TableInfo.read(_db, "notes");
        if (! _infoNotes.equals(_existingNotes)) {
          return new RoomOpenHelper.ValidationResult(false, "notes(com.example.studentprogresstracking.entity.CourseNotes).\n"
                  + " Expected:\n" + _infoNotes + "\n"
                  + " Found:\n" + _existingNotes);
        }
        final HashMap<String, TableInfo.Column> _columnsCourseAssessment = new HashMap<String, TableInfo.Column>(6);
        _columnsCourseAssessment.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourseAssessment.put("courseId", new TableInfo.Column("courseId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourseAssessment.put("title", new TableInfo.Column("title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourseAssessment.put("ObjectiveAssessment", new TableInfo.Column("ObjectiveAssessment", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourseAssessment.put("StartDate", new TableInfo.Column("StartDate", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourseAssessment.put("endDate", new TableInfo.Column("endDate", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCourseAssessment = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCourseAssessment = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCourseAssessment = new TableInfo("CourseAssessment", _columnsCourseAssessment, _foreignKeysCourseAssessment, _indicesCourseAssessment);
        final TableInfo _existingCourseAssessment = TableInfo.read(_db, "CourseAssessment");
        if (! _infoCourseAssessment.equals(_existingCourseAssessment)) {
          return new RoomOpenHelper.ValidationResult(false, "CourseAssessment(com.example.studentprogresstracking.entity.CourseAssessment).\n"
                  + " Expected:\n" + _infoCourseAssessment + "\n"
                  + " Found:\n" + _existingCourseAssessment);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "d2d3e8fc93386efa3eae044ddb01db44", "f84295f89ad2fbd576dc97dd780de6f7");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "courses","Term","Instructor","notes","CourseAssessment");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `courses`");
      _db.execSQL("DELETE FROM `Term`");
      _db.execSQL("DELETE FROM `Instructor`");
      _db.execSQL("DELETE FROM `notes`");
      _db.execSQL("DELETE FROM `CourseAssessment`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(CoursesDAO.class, CoursesDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(termDAO.class, termDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(InstructorDAO.class, InstructorDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(CourseAssessmentDAO.class, CourseAssessmentDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(CourseNotesDAO.class, CourseNotesDAO_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public CoursesDAO CoursesDAO() {
    if (_coursesDAO != null) {
      return _coursesDAO;
    } else {
      synchronized(this) {
        if(_coursesDAO == null) {
          _coursesDAO = new CoursesDAO_Impl(this);
        }
        return _coursesDAO;
      }
    }
  }

  @Override
  public termDAO termDAO() {
    if (_termDAO != null) {
      return _termDAO;
    } else {
      synchronized(this) {
        if(_termDAO == null) {
          _termDAO = new termDAO_Impl(this);
        }
        return _termDAO;
      }
    }
  }

  @Override
  public InstructorDAO InstructorDAO() {
    if (_instructorDAO != null) {
      return _instructorDAO;
    } else {
      synchronized(this) {
        if(_instructorDAO == null) {
          _instructorDAO = new InstructorDAO_Impl(this);
        }
        return _instructorDAO;
      }
    }
  }

  @Override
  public CourseAssessmentDAO CourseAssessmentDao() {
    if (_courseAssessmentDAO != null) {
      return _courseAssessmentDAO;
    } else {
      synchronized(this) {
        if(_courseAssessmentDAO == null) {
          _courseAssessmentDAO = new CourseAssessmentDAO_Impl(this);
        }
        return _courseAssessmentDAO;
      }
    }
  }

  @Override
  public CourseNotesDAO CourseNotesDAO() {
    if (_courseNotesDAO != null) {
      return _courseNotesDAO;
    } else {
      synchronized(this) {
        if(_courseNotesDAO == null) {
          _courseNotesDAO = new CourseNotesDAO_Impl(this);
        }
        return _courseNotesDAO;
      }
    }
  }
}

package com.example.studentprogresstracking.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.studentprogresstracking.database.DateConverter;
import com.example.studentprogresstracking.entity.CourseAssessment;
import java.lang.Class;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class CourseAssessmentDAO_Impl implements CourseAssessmentDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CourseAssessment> __insertionAdapterOfCourseAssessment;

  private final EntityDeletionOrUpdateAdapter<CourseAssessment> __deletionAdapterOfCourseAssessment;

  private final EntityDeletionOrUpdateAdapter<CourseAssessment> __updateAdapterOfCourseAssessment;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAssessmentsByCourseId;

  public CourseAssessmentDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCourseAssessment = new EntityInsertionAdapter<CourseAssessment>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `CourseAssessment` (`id`,`courseId`,`title`,`ObjectiveAssessment`,`StartDate`,`endDate`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CourseAssessment value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getCourseId());
        if (value.getTitle() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTitle());
        }
        final int _tmp = value.getObjectiveAssessment() ? 1 : 0;
        stmt.bindLong(4, _tmp);
        final Long _tmp_1 = DateConverter.toTimestamp(value.getStartDate());
        if (_tmp_1 == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindLong(5, _tmp_1);
        }
        final Long _tmp_2 = DateConverter.toTimestamp(value.getEndDate());
        if (_tmp_2 == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindLong(6, _tmp_2);
        }
      }
    };
    this.__deletionAdapterOfCourseAssessment = new EntityDeletionOrUpdateAdapter<CourseAssessment>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `CourseAssessment` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CourseAssessment value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfCourseAssessment = new EntityDeletionOrUpdateAdapter<CourseAssessment>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `CourseAssessment` SET `id` = ?,`courseId` = ?,`title` = ?,`ObjectiveAssessment` = ?,`StartDate` = ?,`endDate` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CourseAssessment value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getCourseId());
        if (value.getTitle() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTitle());
        }
        final int _tmp = value.getObjectiveAssessment() ? 1 : 0;
        stmt.bindLong(4, _tmp);
        final Long _tmp_1 = DateConverter.toTimestamp(value.getStartDate());
        if (_tmp_1 == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindLong(5, _tmp_1);
        }
        final Long _tmp_2 = DateConverter.toTimestamp(value.getEndDate());
        if (_tmp_2 == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindLong(6, _tmp_2);
        }
        stmt.bindLong(7, value.getId());
      }
    };
    this.__preparedStmtOfDeleteAssessmentsByCourseId = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "Delete FROM CourseAssessment Where courseId=?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final CourseAssessment courseAssessment) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCourseAssessment.insert(courseAssessment);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final CourseAssessment courseAssessment) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfCourseAssessment.handle(courseAssessment);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final CourseAssessment courseAssessment) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfCourseAssessment.handle(courseAssessment);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void DeleteAssessmentsByCourseId(final int cid) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAssessmentsByCourseId.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, cid);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAssessmentsByCourseId.release(_stmt);
    }
  }

  @Override
  public List<CourseAssessment> getAllAssessments() {
    final String _sql = "SELECT * FROM CourseAssessment";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfCourseId = CursorUtil.getColumnIndexOrThrow(_cursor, "courseId");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfObjectiveAssessment = CursorUtil.getColumnIndexOrThrow(_cursor, "ObjectiveAssessment");
      final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "StartDate");
      final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "endDate");
      final List<CourseAssessment> _result = new ArrayList<CourseAssessment>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final CourseAssessment _item;
        _item = new CourseAssessment();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final int _tmpCourseId;
        _tmpCourseId = _cursor.getInt(_cursorIndexOfCourseId);
        _item.setCourseId(_tmpCourseId);
        final String _tmpTitle;
        if (_cursor.isNull(_cursorIndexOfTitle)) {
          _tmpTitle = null;
        } else {
          _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        }
        _item.setTitle(_tmpTitle);
        final boolean _tmpObjectiveAssessment;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfObjectiveAssessment);
        _tmpObjectiveAssessment = _tmp != 0;
        _item.setObjectiveAssessment(_tmpObjectiveAssessment);
        final Date _tmpStartDate;
        final Long _tmp_1;
        if (_cursor.isNull(_cursorIndexOfStartDate)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getLong(_cursorIndexOfStartDate);
        }
        _tmpStartDate = DateConverter.toDate(_tmp_1);
        _item.setStartDate(_tmpStartDate);
        final Date _tmpEndDate;
        final Long _tmp_2;
        if (_cursor.isNull(_cursorIndexOfEndDate)) {
          _tmp_2 = null;
        } else {
          _tmp_2 = _cursor.getLong(_cursorIndexOfEndDate);
        }
        _tmpEndDate = DateConverter.toDate(_tmp_2);
        _item.setEndDate(_tmpEndDate);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<CourseAssessment> getAssessmentsByCourseId(final int courseId) {
    final String _sql = "SELECT * FROM CourseAssessment WHERE courseId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, courseId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfCourseId = CursorUtil.getColumnIndexOrThrow(_cursor, "courseId");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfObjectiveAssessment = CursorUtil.getColumnIndexOrThrow(_cursor, "ObjectiveAssessment");
      final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "StartDate");
      final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "endDate");
      final List<CourseAssessment> _result = new ArrayList<CourseAssessment>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final CourseAssessment _item;
        _item = new CourseAssessment();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final int _tmpCourseId;
        _tmpCourseId = _cursor.getInt(_cursorIndexOfCourseId);
        _item.setCourseId(_tmpCourseId);
        final String _tmpTitle;
        if (_cursor.isNull(_cursorIndexOfTitle)) {
          _tmpTitle = null;
        } else {
          _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        }
        _item.setTitle(_tmpTitle);
        final boolean _tmpObjectiveAssessment;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfObjectiveAssessment);
        _tmpObjectiveAssessment = _tmp != 0;
        _item.setObjectiveAssessment(_tmpObjectiveAssessment);
        final Date _tmpStartDate;
        final Long _tmp_1;
        if (_cursor.isNull(_cursorIndexOfStartDate)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getLong(_cursorIndexOfStartDate);
        }
        _tmpStartDate = DateConverter.toDate(_tmp_1);
        _item.setStartDate(_tmpStartDate);
        final Date _tmpEndDate;
        final Long _tmp_2;
        if (_cursor.isNull(_cursorIndexOfEndDate)) {
          _tmp_2 = null;
        } else {
          _tmp_2 = _cursor.getLong(_cursorIndexOfEndDate);
        }
        _tmpEndDate = DateConverter.toDate(_tmp_2);
        _item.setEndDate(_tmpEndDate);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public CourseAssessment getAssessmentById(final int id) {
    final String _sql = "SELECT * FROM CourseAssessment WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfCourseId = CursorUtil.getColumnIndexOrThrow(_cursor, "courseId");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfObjectiveAssessment = CursorUtil.getColumnIndexOrThrow(_cursor, "ObjectiveAssessment");
      final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "StartDate");
      final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "endDate");
      final CourseAssessment _result;
      if(_cursor.moveToFirst()) {
        _result = new CourseAssessment();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
        final int _tmpCourseId;
        _tmpCourseId = _cursor.getInt(_cursorIndexOfCourseId);
        _result.setCourseId(_tmpCourseId);
        final String _tmpTitle;
        if (_cursor.isNull(_cursorIndexOfTitle)) {
          _tmpTitle = null;
        } else {
          _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        }
        _result.setTitle(_tmpTitle);
        final boolean _tmpObjectiveAssessment;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfObjectiveAssessment);
        _tmpObjectiveAssessment = _tmp != 0;
        _result.setObjectiveAssessment(_tmpObjectiveAssessment);
        final Date _tmpStartDate;
        final Long _tmp_1;
        if (_cursor.isNull(_cursorIndexOfStartDate)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getLong(_cursorIndexOfStartDate);
        }
        _tmpStartDate = DateConverter.toDate(_tmp_1);
        _result.setStartDate(_tmpStartDate);
        final Date _tmpEndDate;
        final Long _tmp_2;
        if (_cursor.isNull(_cursorIndexOfEndDate)) {
          _tmp_2 = null;
        } else {
          _tmp_2 = _cursor.getLong(_cursorIndexOfEndDate);
        }
        _tmpEndDate = DateConverter.toDate(_tmp_2);
        _result.setEndDate(_tmpEndDate);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}

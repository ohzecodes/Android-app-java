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
import com.example.studentprogresstracking.entity.Courses;
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
public final class CoursesDAO_Impl implements CoursesDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Courses> __insertionAdapterOfCourses;

  private final EntityDeletionOrUpdateAdapter<Courses> __deletionAdapterOfCourses;

  private final EntityDeletionOrUpdateAdapter<Courses> __updateAdapterOfCourses;

  private final SharedSQLiteStatement __preparedStmtOfDeleteCoursesById;

  public CoursesDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCourses = new EntityInsertionAdapter<Courses>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `courses` (`Id`,`Name`,`start_date`,`end_date`,`status`,`TermId`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Courses value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        final Long _tmp = DateConverter.toTimestamp(value.getStart_date());
        if (_tmp == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, _tmp);
        }
        final Long _tmp_1 = DateConverter.toTimestamp(value.getEnd_date());
        if (_tmp_1 == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, _tmp_1);
        }
        if (value.getStatus() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getStatus());
        }
        stmt.bindLong(6, value.getTermId());
      }
    };
    this.__deletionAdapterOfCourses = new EntityDeletionOrUpdateAdapter<Courses>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `courses` WHERE `Id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Courses value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfCourses = new EntityDeletionOrUpdateAdapter<Courses>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `courses` SET `Id` = ?,`Name` = ?,`start_date` = ?,`end_date` = ?,`status` = ?,`TermId` = ? WHERE `Id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Courses value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        final Long _tmp = DateConverter.toTimestamp(value.getStart_date());
        if (_tmp == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, _tmp);
        }
        final Long _tmp_1 = DateConverter.toTimestamp(value.getEnd_date());
        if (_tmp_1 == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, _tmp_1);
        }
        if (value.getStatus() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getStatus());
        }
        stmt.bindLong(6, value.getTermId());
        stmt.bindLong(7, value.getId());
      }
    };
    this.__preparedStmtOfDeleteCoursesById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "Delete FROM Courses where id=?";
        return _query;
      }
    };
  }

  @Override
  public void insertCourse(final Courses T) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCourses.insert(T);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void DeleteCourse(final Courses T) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfCourses.handle(T);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void UpdateCourse(final Courses T) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfCourses.handle(T);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void DeleteCoursesById(final int id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteCoursesById.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteCoursesById.release(_stmt);
    }
  }

  @Override
  public List<Courses> GetAllCoursesByTerm(final int tid) {
    final String _sql = "select * FROM Courses where Termid=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, tid);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "Id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "Name");
      final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "start_date");
      final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "end_date");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfTermId = CursorUtil.getColumnIndexOrThrow(_cursor, "TermId");
      final List<Courses> _result = new ArrayList<Courses>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Courses _item;
        _item = new Courses();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        _item.setName(_tmpName);
        final Date _tmpStart_date;
        final Long _tmp;
        if (_cursor.isNull(_cursorIndexOfStartDate)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getLong(_cursorIndexOfStartDate);
        }
        _tmpStart_date = DateConverter.toDate(_tmp);
        _item.setStart_date(_tmpStart_date);
        final Date _tmpEnd_date;
        final Long _tmp_1;
        if (_cursor.isNull(_cursorIndexOfEndDate)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getLong(_cursorIndexOfEndDate);
        }
        _tmpEnd_date = DateConverter.toDate(_tmp_1);
        _item.setEnd_date(_tmpEnd_date);
        final String _tmpStatus;
        if (_cursor.isNull(_cursorIndexOfStatus)) {
          _tmpStatus = null;
        } else {
          _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
        }
        _item.setStatus(_tmpStatus);
        final int _tmpTermId;
        _tmpTermId = _cursor.getInt(_cursorIndexOfTermId);
        _item.setTermId(_tmpTermId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Courses> GetAllCourses() {
    final String _sql = "select * FROM Courses";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "Id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "Name");
      final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "start_date");
      final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "end_date");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfTermId = CursorUtil.getColumnIndexOrThrow(_cursor, "TermId");
      final List<Courses> _result = new ArrayList<Courses>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Courses _item;
        _item = new Courses();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        _item.setName(_tmpName);
        final Date _tmpStart_date;
        final Long _tmp;
        if (_cursor.isNull(_cursorIndexOfStartDate)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getLong(_cursorIndexOfStartDate);
        }
        _tmpStart_date = DateConverter.toDate(_tmp);
        _item.setStart_date(_tmpStart_date);
        final Date _tmpEnd_date;
        final Long _tmp_1;
        if (_cursor.isNull(_cursorIndexOfEndDate)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getLong(_cursorIndexOfEndDate);
        }
        _tmpEnd_date = DateConverter.toDate(_tmp_1);
        _item.setEnd_date(_tmpEnd_date);
        final String _tmpStatus;
        if (_cursor.isNull(_cursorIndexOfStatus)) {
          _tmpStatus = null;
        } else {
          _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
        }
        _item.setStatus(_tmpStatus);
        final int _tmpTermId;
        _tmpTermId = _cursor.getInt(_cursorIndexOfTermId);
        _item.setTermId(_tmpTermId);
        _result.add(_item);
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

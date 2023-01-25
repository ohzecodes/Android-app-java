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
import com.example.studentprogresstracking.entity.Instructor;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class InstructorDAO_Impl implements InstructorDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Instructor> __insertionAdapterOfInstructor;

  private final EntityDeletionOrUpdateAdapter<Instructor> __deletionAdapterOfInstructor;

  private final EntityDeletionOrUpdateAdapter<Instructor> __updateAdapterOfInstructor;

  private final SharedSQLiteStatement __preparedStmtOfDeleteInstructorByCourse;

  public InstructorDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfInstructor = new EntityInsertionAdapter<Instructor>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `Instructor` (`InstructorID`,`email`,`name`,`phoneNumber`,`CourseID`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Instructor value) {
        stmt.bindLong(1, value.getInstructorID());
        if (value.getEmail() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getEmail());
        }
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
        if (value.getPhoneNumber() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getPhoneNumber());
        }
        stmt.bindLong(5, value.getCourseID());
      }
    };
    this.__deletionAdapterOfInstructor = new EntityDeletionOrUpdateAdapter<Instructor>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Instructor` WHERE `InstructorID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Instructor value) {
        stmt.bindLong(1, value.getInstructorID());
      }
    };
    this.__updateAdapterOfInstructor = new EntityDeletionOrUpdateAdapter<Instructor>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Instructor` SET `InstructorID` = ?,`email` = ?,`name` = ?,`phoneNumber` = ?,`CourseID` = ? WHERE `InstructorID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Instructor value) {
        stmt.bindLong(1, value.getInstructorID());
        if (value.getEmail() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getEmail());
        }
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
        if (value.getPhoneNumber() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getPhoneNumber());
        }
        stmt.bindLong(5, value.getCourseID());
        stmt.bindLong(6, value.getInstructorID());
      }
    };
    this.__preparedStmtOfDeleteInstructorByCourse = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "Delete FROM Instructor where CourseID=?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Instructor T) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfInstructor.insert(T);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void Delete(final Instructor T) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfInstructor.handle(T);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void Update(final Instructor T) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfInstructor.handle(T);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void DeleteInstructorByCourse(final int Cid) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteInstructorByCourse.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, Cid);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteInstructorByCourse.release(_stmt);
    }
  }

  @Override
  public List<Instructor> GetInstructorByCourse(final int Cid) {
    final String _sql = "select * FROM Instructor where CourseID=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, Cid);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfInstructorID = CursorUtil.getColumnIndexOrThrow(_cursor, "InstructorID");
      final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfPhoneNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "phoneNumber");
      final int _cursorIndexOfCourseID = CursorUtil.getColumnIndexOrThrow(_cursor, "CourseID");
      final List<Instructor> _result = new ArrayList<Instructor>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Instructor _item;
        _item = new Instructor();
        final int _tmpInstructorID;
        _tmpInstructorID = _cursor.getInt(_cursorIndexOfInstructorID);
        _item.setInstructorID(_tmpInstructorID);
        final String _tmpEmail;
        if (_cursor.isNull(_cursorIndexOfEmail)) {
          _tmpEmail = null;
        } else {
          _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        }
        _item.setEmail(_tmpEmail);
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        _item.setName(_tmpName);
        final String _tmpPhoneNumber;
        if (_cursor.isNull(_cursorIndexOfPhoneNumber)) {
          _tmpPhoneNumber = null;
        } else {
          _tmpPhoneNumber = _cursor.getString(_cursorIndexOfPhoneNumber);
        }
        _item.setPhoneNumber(_tmpPhoneNumber);
        final int _tmpCourseID;
        _tmpCourseID = _cursor.getInt(_cursorIndexOfCourseID);
        _item.setCourseID(_tmpCourseID);
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

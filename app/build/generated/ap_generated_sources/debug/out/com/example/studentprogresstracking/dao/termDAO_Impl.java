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
import com.example.studentprogresstracking.entity.OneTerm;
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
public final class termDAO_Impl implements termDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<OneTerm> __insertionAdapterOfOneTerm;

  private final EntityDeletionOrUpdateAdapter<OneTerm> __deletionAdapterOfOneTerm;

  private final EntityDeletionOrUpdateAdapter<OneTerm> __updateAdapterOfOneTerm;

  private final SharedSQLiteStatement __preparedStmtOfDeleteTermById;

  public termDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfOneTerm = new EntityInsertionAdapter<OneTerm>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `Term` (`termid`,`Title`,`StartDate`,`EndDate`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, OneTerm value) {
        stmt.bindLong(1, value.getTermid());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        final Long _tmp = DateConverter.toTimestamp(value.getStartDate());
        if (_tmp == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, _tmp);
        }
        final Long _tmp_1 = DateConverter.toTimestamp(value.getEndDate());
        if (_tmp_1 == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, _tmp_1);
        }
      }
    };
    this.__deletionAdapterOfOneTerm = new EntityDeletionOrUpdateAdapter<OneTerm>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Term` WHERE `termid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, OneTerm value) {
        stmt.bindLong(1, value.getTermid());
      }
    };
    this.__updateAdapterOfOneTerm = new EntityDeletionOrUpdateAdapter<OneTerm>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Term` SET `termid` = ?,`Title` = ?,`StartDate` = ?,`EndDate` = ? WHERE `termid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, OneTerm value) {
        stmt.bindLong(1, value.getTermid());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        final Long _tmp = DateConverter.toTimestamp(value.getStartDate());
        if (_tmp == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, _tmp);
        }
        final Long _tmp_1 = DateConverter.toTimestamp(value.getEndDate());
        if (_tmp_1 == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, _tmp_1);
        }
        stmt.bindLong(5, value.getTermid());
      }
    };
    this.__preparedStmtOfDeleteTermById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Term WHERE termid = ?";
        return _query;
      }
    };
  }

  @Override
  public void insertTerm(final OneTerm T) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfOneTerm.insert(T);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void DeleteTerm(final OneTerm T) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfOneTerm.handle(T);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void UpdateTerm(final OneTerm T) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfOneTerm.handle(T);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteTermById(final int termId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteTermById.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, termId);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteTermById.release(_stmt);
    }
  }

  @Override
  public List<OneTerm> GetAllterms() {
    final String _sql = "select * FROM Term";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfTermid = CursorUtil.getColumnIndexOrThrow(_cursor, "termid");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "Title");
      final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "StartDate");
      final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "EndDate");
      final List<OneTerm> _result = new ArrayList<OneTerm>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final OneTerm _item;
        _item = new OneTerm();
        final int _tmpTermid;
        _tmpTermid = _cursor.getInt(_cursorIndexOfTermid);
        _item.setTermid(_tmpTermid);
        final String _tmpTitle;
        if (_cursor.isNull(_cursorIndexOfTitle)) {
          _tmpTitle = null;
        } else {
          _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        }
        _item.setTitle(_tmpTitle);
        final Date _tmpStartDate;
        final Long _tmp;
        if (_cursor.isNull(_cursorIndexOfStartDate)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getLong(_cursorIndexOfStartDate);
        }
        _tmpStartDate = DateConverter.toDate(_tmp);
        _item.setStartDate(_tmpStartDate);
        final Date _tmpEndDate;
        final Long _tmp_1;
        if (_cursor.isNull(_cursorIndexOfEndDate)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getLong(_cursorIndexOfEndDate);
        }
        _tmpEndDate = DateConverter.toDate(_tmp_1);
        _item.setEndDate(_tmpEndDate);
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

package com.fkulic.guessthenumber;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Filip on 1.4.2017..
 */

public class ScoreDBHelper extends SQLiteOpenHelper {
    static final String CREATE_TABLE_SCORES = "CREATE TABLE " + Schema.TABLE_SCORES + " (" + Schema.USERNAME + " TEXT," + Schema.SCORE + " INTEGER);";
    static final String DROP_TABLE_SCORES = "DROP TABLE IF EXISTS " + Schema.TABLE_SCORES + ";";
    static final String SELECT_SCORES = "SELECT * FROM " + Schema.TABLE_SCORES + ";";

    private static ScoreDBHelper mScoreDBHelper = null;

    private ScoreDBHelper(Context context) {
        super(context, Schema.DATABASE_NAME, null, Schema.SCHEMA_VERSION);
    }

    public static synchronized ScoreDBHelper getInstance(Context context) {
        if (mScoreDBHelper == null) {
            mScoreDBHelper = new ScoreDBHelper(context);
        }
        return mScoreDBHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SCORES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_SCORES);
        this.onCreate(db);
    }

    public void insertScore(User user) {
        ContentValues cv = new ContentValues();
        cv.put(Schema.USERNAME, user.getUsername());
        cv.put(Schema.SCORE, user.getScore());
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(Schema.TABLE_SCORES, Schema.USERNAME, cv);
        sqLiteDatabase.close();
    }

    public ArrayList<User> getScores() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor userCursor = sqLiteDatabase.rawQuery(SELECT_SCORES, null);
        ArrayList<User> users = new ArrayList<>();
        if (userCursor.moveToFirst()) {
            do {
                String username = userCursor.getString(0);
                int score = userCursor.getInt(1);
                users.add(new User(username, score));
            } while (userCursor.moveToNext());
        }
        userCursor.close();
        sqLiteDatabase.close();
        return users;
    }

    public static class Schema {
        private static final int SCHEMA_VERSION = 1;
        private static final String DATABASE_NAME = "score.db";

        static final String TABLE_SCORES = "scores";
        static final String USERNAME = "username";
        static final String SCORE = "score";

    }
}

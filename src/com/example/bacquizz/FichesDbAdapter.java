/*
 * Copyright (C) 2008 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.example.bacquizz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Simple notes database access helper class. Defines the basic CRUD operations
 * for the notepad example, and gives the ability to list all notes as well as
 * retrieve or modify a specific note.
 * 
 * This has been improved from the first version of this tutorial through the
 * addition of better error handling and also using returning a Cursor instead
 * of using a KEYlection of inner classes (which is less scalable and not
 * recommended).
 */
public class FichesDbAdapter {
	
	// table fiche
	public static final String KEY_TITLE = "title";
	public static final String KEY_ROWID = "_id";
	
	//table mon_quizz
	private static final String KEY_MIDQ = "id";
	private static final String KEY_MQUESTION = "question";
	private static final String KEY_MREP1 = "rep1";
	private static final String KEY_MREP2 = "rep2";
	private static final String KEY_MREP3 = "rep3";
	private static final String KEY_MREP4 = "rep4";
//	private static final String KEY_ORDRE = "ordre";
	private static final String KEY_ID_FICHE = "idFiche";
	
	private static final String TAG = "NotesDbAdapter";
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;

	/**
	 * Database creation sql statement
	 */
	private static final String DATABASE_CREATE = "create table fiches (_id integer primary key autoincrement, "
			+ "title text not null); " +
			"create table mon_quizz(id integer primary key autoincrement," +
			"question text not null, rep1 text not null, rep2 text not null, rep3 text not null, rep4 text not null," +
			"idFiche integer not null";

	private static final String DATABASE_NAME = "data";
	private static final String TABLE_FICHE = "fiches";
	private static final String TABLE_MON_QUIZZ = "mon_quizz";
	private static final int DATABASE_VERSION = 10;

	private final Context mCtx;

	private static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {

			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS fiches");
			db.execSQL("DROP TABLE IF EXISTS mon_quizz");
			onCreate(db);
		}
	}

	/**
	 * Constructor - takes the context to allow the database to be
	 * opened/created
	 * 
	 * @param ctx
	 *            the Context within which to work
	 */
	public FichesDbAdapter(Context ctx) {
		this.mCtx = ctx;
	}

	/**
	 * Open the notes database. If it cannot be opened, try to create a new
	 * instance of the database. If it cannot be created, throw an exception to
	 * signal the failure
	 * 
	 * @return this (self reference, allowing this to be chained in an
	 *         initialization call)
	 * @throws SQLException
	 *             if the database could be neither opened or created
	 */
	public FichesDbAdapter open() throws SQLException {
		mDbHelper = new DatabaseHelper(mCtx);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		mDbHelper.close();
	}
	
	//------------------------------CREATE-------------------------------------

	/**
	 * Create a new fiche using the title provided. If the note is
	 * successfully created return the new rowId for that note, otherwise return
	 * a -1 to indicate failure.
	 * 
	 * @param title
	 *            the title of the note
	 * @return rowId or -1 if failed
	 */
	public long createFiche(String title) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_TITLE, title);

		return mDb.insert(TABLE_FICHE, null, initialValues);
	}
	
	/**
	 * Create a new question using the rep1,...3 provided. If the note is
	 * successfully created return the new rowId for that note, otherwise return
	 * a -1 to indicate failure.
	 * 
	 * @param question
	 * @param rep1...4
	 * @param idFiche
	 *            the title of the note
	 * @return rowId or -1 if failed
	 */
	public long createQuestion(String question, String rep1, String rep2, String rep3, String rep4,int idFiche) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_MQUESTION, question);
		initialValues.put(KEY_MREP1, rep1);
		initialValues.put(KEY_MREP2, rep2);
		initialValues.put(KEY_MREP3, rep3);
		initialValues.put(KEY_MREP4, rep4);
		initialValues.put(KEY_ID_FICHE, idFiche);

		return mDb.insert(TABLE_MON_QUIZZ, null, initialValues);
	}
	
	//------------------------------DELETE-------------------------------------
	/**
	 * Delete the fiche with the given rowId
	 * 
	 * @param rowId
	 *            id of fiche to delete
	 * @return true if deleted, false otherwise
	 */
	public boolean deleteFiche(long rowId) {

		return mDb.delete(TABLE_FICHE, KEY_ROWID + "=" + rowId, null) > 0;
	}
	
	/**
	 * Delete the question with the given rowId
	 * 
	 * @param rowId
	 *            id of question to delete
	 * @return true if deleted, false otherwise
	 */
	public boolean deleteQuestion(long rowId) {

		return mDb.delete(TABLE_MON_QUIZZ, KEY_MIDQ + "=" + rowId, null) > 0;
	}
	
	//------------------------------SELECT-------------------------------------
	/**
	 * Return a Cursor over the list of all fiches in the database
	 * 
	 * @return Cursor over all notes
	 */
	public Cursor fetchAllFiches() {

		return mDb.query(TABLE_FICHE, new String[] { KEY_ROWID, KEY_TITLE }, null, null, null, null, null);
	}

	/**
	 * Return a Cursor positioned at the note that matches the given rowId
	 * 
	 * @param rowId
	 *            id of note to retrieve
	 * @return Cursor positioned to matching note, if found
	 * @throws SQLException
	 *             if note could not be found/retrieved
	 */
	public Cursor fetchFiche(long rowId) throws SQLException {

		Cursor mCursor =

		mDb.query(true, TABLE_FICHE, new String[] { KEY_ROWID, KEY_TITLE}, KEY_ROWID + "=" + rowId, null, null, null, null,
				null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;

	}
	
	public Cursor fetchQuestion(long rowId) throws SQLException {

		Cursor mCursor =

		mDb.query(true, TABLE_MON_QUIZZ, new String[] { KEY_MIDQ, KEY_MQUESTION, KEY_MREP1,KEY_MREP2,KEY_MREP3,KEY_MREP4,KEY_ID_FICHE}, KEY_MIDQ + "=" + rowId, null, null, null, null,
				null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;

	}
	
	public Cursor fetchAllQuestions() {

		return mDb.query(TABLE_MON_QUIZZ, new String[] { KEY_MIDQ, KEY_MQUESTION, KEY_MREP1,KEY_MREP2,KEY_MREP3,KEY_MREP4,KEY_ID_FICHE }, null, null, null, null, null);
	}
	//------------------------------UPDATE-------------------------------------
	/**
	 * Update the note using the details provided. The note to be updated is
	 * specified using the rowId, and it is altered to use the title
	 * values passed in
	 * 
	 * @param rowId
	 *            id of note to update
	 * @param title
	 *            value to set note title to
	 * @return true if the note was successfully updated, false otherwise
	 */
	public boolean updateFiche(long rowId, String title) {
		ContentValues args = new ContentValues();
		args.put(KEY_TITLE, title);

		return mDb.update(TABLE_FICHE, args, KEY_ROWID + "=" + rowId, null) > 0;
	}
	
	public boolean updateQuestion(long rowId, String question, String rep1, String rep2, String rep3, String rep4) {
		ContentValues args = new ContentValues();
		args.put(KEY_MQUESTION, question);
		args.put(KEY_MREP1, rep1);
		args.put(KEY_MREP2, rep2);
		args.put(KEY_MREP3, rep3);
		args.put(KEY_MREP4, rep4);
		return mDb.update(TABLE_MON_QUIZZ, args, KEY_MIDQ + "=" + rowId, null) > 0;
	}
}

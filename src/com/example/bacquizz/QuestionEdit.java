/*
 * Copyright (C) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.bacquizz;

import android.app.ActionBar;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class QuestionEdit extends Activity {

	private EditText edtQuestion;
	private EditText edtRep1;
	private EditText edtRep2;
	private EditText edtRep3;
	private EditText edtRep4;
	private Long mRowId;
	private QuestionsDbAdapter mDbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mDbHelper = new QuestionsDbAdapter(this);
		mDbHelper.open();
		
		ActionBar bar = getActionBar();
	    bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#345953")));
		setContentView(R.layout.question_edit);
		setTitle(R.string.edit_note);

		edtQuestion = (EditText) findViewById(R.id.edtQuestion);
		edtRep1 = (EditText) findViewById(R.id.edtRep1);
		edtRep2 = (EditText) findViewById(R.id.edtRep2);
		edtRep3 = (EditText) findViewById(R.id.edtRep3);
		edtRep4 = (EditText) findViewById(R.id.edtRep4);
		Button confirmButton = (Button) findViewById(R.id.confirm);

		mRowId = (savedInstanceState == null) ? null
				: (Long) savedInstanceState
						.getSerializable(QuestionsDbAdapter.KEY_MROWID);
		if (mRowId == null) {
			Bundle extras = getIntent().getExtras();
			mRowId = extras != null ? extras.getLong(QuestionsDbAdapter.KEY_MROWID)
					: null;
		}

		populateFields();

		confirmButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				setResult(RESULT_OK);
				finish();
			}

		});
	}

	private void populateFields() {
		if (mRowId != null) {
			Cursor note = mDbHelper.fetchQuestion(mRowId);
			startManagingCursor(note);
			edtQuestion.setText(note.getString(note
					.getColumnIndexOrThrow(QuestionsDbAdapter.KEY_MQUESTION)));
			edtRep1.setText(note.getString(note
					.getColumnIndexOrThrow(QuestionsDbAdapter.KEY_MREP1)));
			edtRep2.setText(note.getString(note
					.getColumnIndexOrThrow(QuestionsDbAdapter.KEY_MREP2)));
			edtRep3.setText(note.getString(note
					.getColumnIndexOrThrow(QuestionsDbAdapter.KEY_MREP3)));
			edtRep4.setText(note.getString(note
					.getColumnIndexOrThrow(QuestionsDbAdapter.KEY_MREP4)));
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		saveState();
		outState.putSerializable(QuestionsDbAdapter.KEY_MROWID, mRowId);
	}

	@Override
	protected void onPause() {
		super.onPause();
		saveState();
	}

	@Override
	protected void onResume() {
		super.onResume();
		populateFields();
	}

	private void saveState() {
		String question = edtQuestion.getText().toString();
		String rep1 = edtRep1.getText().toString();
		String rep2 = edtRep2.getText().toString();
		String rep3 = edtRep3.getText().toString();
		String rep4 = edtRep4.getText().toString();
		if (mRowId == null) {
			long id = mDbHelper.createQuestion(question, rep1,rep2,rep3,rep4);
			if (id > 0) {
				mRowId = id;
			}
		} else {
			mDbHelper.updateQuestion(mRowId, question, rep1,rep2,rep3,rep4);
		}
	}

}

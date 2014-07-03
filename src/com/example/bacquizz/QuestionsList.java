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
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class QuestionsList extends ListActivity {
	private static final int ACTIVITY_CREATE = 0;
	private static final int ACTIVITY_EDIT = 1;

	private static final int INSERT_ID = Menu.FIRST;
	private static final int DELETE_ID = Menu.FIRST + 1;
	private static final int QUIZZ_ID = Menu.FIRST + 2;
	private static final int ENVOI_QUIZZ_ID = Menu.FIRST + 3;

	private QuestionsDbAdapter mDbHelper;
	private long idFiche;
	private Bundle nomBundle;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar bar = getActionBar();
	    bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#345953")));
		setContentView(R.layout.questions_list);
		setTitle("Questions");
		mDbHelper = new QuestionsDbAdapter(this);
		mDbHelper.open();
		nomBundle = this.getIntent().getExtras();
		idFiche = nomBundle.getLong("idFiche");
		fillData(idFiche);
		registerForContextMenu(getListView());
		
		
	}

	private void fillData(long idFiches) {
		//on récupère l'id de la fiche à éditer
		
		
		int idF= (int)idFiches;
		
		Cursor notesCursor = mDbHelper.fetchQuestionsByIdFiche(idF);
		startManagingCursor(notesCursor);

		// Create an array to specify the fields we want to display in the list
		// (only TITLE)
		String[] from = new String[] { QuestionsDbAdapter.KEY_MQUESTION };

		// and an array of the fields we want to bind those fields to (in this
		// case just text1)
		int[] to = new int[] { R.id.text1 };

		// Now create a simple cursor adapter and set it to display
		SimpleCursorAdapter notes = new SimpleCursorAdapter(this,
				R.layout.fiches_row, notesCursor, from, to);
		setListAdapter(notes);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, INSERT_ID, 0, R.string.menu_insert_question);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case INSERT_ID:
			createNote();
			return true;
		}

		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, DELETE_ID, 0, R.string.menu_delete_question);
		menu.add(0, QUIZZ_ID, 0, R.string.menu_quizz);
		menu.add(0, ENVOI_QUIZZ_ID, 0, R.string.menu_envoi_quizz);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case DELETE_ID:
			AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
					.getMenuInfo();
			mDbHelper.deleteQuestion(info.id);
			fillData(idFiche);
			return true;
		}
		return super.onContextItemSelected(item);
	}
	
	private void createNote() {
		Intent i = new Intent(this, QuestionEdit.class);
//		i.putExtra("idFiche", idFiche);
//		Toast.makeText(getApplicationContext(),"idFiche::"+ idFiche, Toast.LENGTH_SHORT).show();
		startActivityForResult(i, ACTIVITY_CREATE);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent i = new Intent(this, QuestionEdit.class);
		i.putExtra(QuestionsDbAdapter.KEY_MROWID, id);
		startActivityForResult(i, ACTIVITY_EDIT);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
	
		super.onActivityResult(requestCode, resultCode, intent);
		
		fillData(idFiche);
		
		
	}
}

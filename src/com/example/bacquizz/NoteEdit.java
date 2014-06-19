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

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NoteEdit extends Activity {

	private EditText mNomFicheText;
	private Long mRowId;
	private FichesDbAdapter mDbHelper;
	private Bundle extras;
	private Button btnAddQuestion;
	// voir si le boutton confirm est cliqu�
	private boolean ok=false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mDbHelper = new FichesDbAdapter(this);
		mDbHelper.open();

		setContentView(R.layout.note_edit);
		setTitle("Edit Fiche");
		btnAddQuestion= (Button) findViewById(R.id.btnAddQuestion);
		mNomFicheText = (EditText) findViewById(R.id.title);
		
			Button confirmButton = (Button) findViewById(R.id.confirm);
	
			mRowId = (savedInstanceState == null) ? null
					: (Long) savedInstanceState
							.getSerializable(FichesDbAdapter.KEY_ROWID);
			if (mRowId == null) {
				extras = getIntent().getExtras();
				
//				mRowId = extras != null ? extras.getLong(FichesDbAdapter.KEY_ROWID)
//						: null;
				
				if(extras != null)
				{
					mRowId= extras.getLong(FichesDbAdapter.KEY_ROWID);
					btnAddQuestion.setVisibility(View.VISIBLE);
				}
				else
				{
					mRowId=null;
					btnAddQuestion.setVisibility(View.INVISIBLE);
				}
					
			}
	
			populateFields();
			
				
			confirmButton.setOnClickListener(new View.OnClickListener() {
	
				public void onClick(View view) {
						ok=true;
						setResult(RESULT_OK);
						finish();
				}
	
			});
			
		}

	private void populateFields() {
		if (mRowId != null) {
			Cursor note = mDbHelper.fetchFiche(mRowId);
			startManagingCursor(note);
			mNomFicheText.setText(note.getString(note
					.getColumnIndexOrThrow(FichesDbAdapter.KEY_TITLE)));
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		saveState();
		outState.putSerializable(FichesDbAdapter.KEY_ROWID, mRowId);
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
		long id=0;
		String title = mNomFicheText.getText().toString();
		if(mNomFicheText.getText().toString().isEmpty() && ok==false)
			Toast.makeText(getApplicationContext(), "Aucune fiche enregistr�e", Toast.LENGTH_SHORT).show();
		else if(!mNomFicheText.getText().toString().isEmpty() && ok==true)
		{
			
			if (mRowId == null) //ajout
			{
				
				id = mDbHelper.createFiche(title);
				Toast.makeText(getApplicationContext(), "La fiche "+ mNomFicheText.getText().toString()+ " a �t� enregistr�e", Toast.LENGTH_SHORT).show();
				mRowId =id;

				if (id > 0) {
					mRowId = id;
				}
				
			}
			 else  //edition
			 {
				
				String ancienTexte;
				Cursor note = mDbHelper.fetchFiche(mRowId);
				ancienTexte=(note.getString(note
						.getColumnIndexOrThrow(FichesDbAdapter.KEY_TITLE)).toString());
				if(!title.contentEquals(ancienTexte))
				{
					mDbHelper.updateFiche(mRowId, title);
					Toast.makeText(getApplicationContext(), "La fiche "+ ancienTexte+ " a �t� modifi�e en "+ mNomFicheText.getText().toString(), Toast.LENGTH_SHORT).show();
				}	
					
			}
		}
		
			
		
			
	}

}

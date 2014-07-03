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
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FicheEdit extends Activity {

	private EditText mNomFicheText;
	private Long mRowId;
	private FichesDbAdapter mDbHelper;
	private QuizzSQLite mDbHelperQuizz;
	private Bundle extras;
	private Button btnAddQuestion;
	// voir si le boutton confirm est cliqué
	private boolean ok=false;
	final Context context = this;
	
//	private Questions questionFromBDD;
//	private QuestionsBdd mesQuestionsBdd;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ouverture bdd Fiche
		mDbHelper = new FichesDbAdapter(this);
		mDbHelper.open();
		// ouverture monQuizz
//		mesQuestionsBdd = new QuestionsBdd(this);
		ActionBar bar = getActionBar();
	    bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#345953")));
		setContentView(R.layout.fiche_edit);
		setTitle("Edit Fiche");
		// écouteur sur les différents objets de l'activité
		btnAddQuestion= (Button) findViewById(R.id.btnAddQuestion2);
		mNomFicheText = (EditText) findViewById(R.id.title);
		Button confirmButton = (Button) findViewById(R.id.btnConfirm);
		
		// on récupère l'id du onListItemClick s'il y'en a 1
		mRowId = (savedInstanceState == null) ? null
				: (Long) savedInstanceState
							.getSerializable(FichesDbAdapter.KEY_ROWID);
			if (mRowId == null) {
				// vérifie si ya un clic sur un chapitre
				extras = getIntent().getExtras();
				
				if(extras != null)
				{
					mRowId= extras.getLong(FichesDbAdapter.KEY_ROWID);
					btnAddQuestion.setVisibility(View.VISIBLE);
					// Quand on clique on affiche un formulaire dans une dialog
					
//					Cursor maQuestion = mDbHelper.fetchQuestion(mRowId);
//					String uneQuestion = (maQuestion.getString(maQuestion
//							.getColumnIndexOrThrow(FichesDbAdapter.KEY_MQUESTION)).toString());
//					
//					Toast.makeText(getApplicationContext(), "question:  "+uneQuestion, Toast.LENGTH_SHORT).show();
				}
				else
				{
					mRowId=null;
					btnAddQuestion.setVisibility(View.INVISIBLE);
				}
					
			}
			
			// remplis la listView
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
	public void onClickAddQuestion(View v)
	{
		Bundle bundle = new Bundle();
		Intent intent = new Intent(this, QuestionsList.class);
		bundle.putLong("idFiche", mRowId);
		intent.putExtras(bundle);
		startActivity(intent);
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
			Toast.makeText(getApplicationContext(), "Aucune fiche enregistrée", Toast.LENGTH_SHORT).show();
		else if(!mNomFicheText.getText().toString().isEmpty() && ok==true)
		{
			
			if (mRowId == null) //ajout
			{
				
				id = mDbHelper.createFiche(title);
				Toast.makeText(getApplicationContext(), "La fiche "+ mNomFicheText.getText().toString()+ " a été enregistrée", Toast.LENGTH_SHORT).show();
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
					Toast.makeText(getApplicationContext(), "La fiche "+ ancienTexte+ " a été modifiée en "+ mNomFicheText.getText().toString(), Toast.LENGTH_SHORT).show();
				}	
					
			}
		}
		
			
		
			
	}

}

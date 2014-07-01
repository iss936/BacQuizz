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
	
	private Questions questionFromBDD;
	private QuestionsBdd mesQuestionsBdd;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ouverture bdd Fiche
		mDbHelper = new FichesDbAdapter(this);
		mDbHelper.open();
		// ouverture monQuizz
		mesQuestionsBdd = new QuestionsBdd(this);
		ActionBar bar = getActionBar();
	    bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#345953")));
		setContentView(R.layout.fiche_edit);
		setTitle("Edit Fiche");
		// écouteur sur les différents objets de l'activité
		btnAddQuestion= (Button) findViewById(R.id.btnAddQuestion);
		mNomFicheText = (EditText) findViewById(R.id.title);
		Button confirmButton = (Button) findViewById(R.id.confirm);
		
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
					btnAddQuestion.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							  final Dialog dialog = new Dialog(FicheEdit.this);
				              dialog.getWindow();
				              dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				              dialog.setContentView(R.layout.add_fiche);
				              //affiche la dialog
				              dialog.show();
				            //listener sur les objets de la dialog
				            final EditText edtQuestion = (EditText) dialog.findViewById(R.id.edtQuestion);
				            //la bonne reponse
				            final EditText edtRep1 = (EditText) dialog.findViewById(R.id.edtRep1);
				            final EditText edtRep2 = (EditText) dialog.findViewById(R.id.edtRep2);
				            final EditText edtRep3 = (EditText) dialog.findViewById(R.id.edtRep3);
				            final EditText edtRep4 = (EditText) dialog.findViewById(R.id.edtRep4);
				            Button btnSaveQuestion = (Button) dialog.findViewById(R.id.btnSaveQuestion); 
				            btnSaveQuestion.setOnClickListener(new View.OnClickListener() {
								
								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									
									if(edtQuestion.getText().toString().isEmpty())
									{
										Toast.makeText(getApplicationContext(), " Saisir une question!", Toast.LENGTH_SHORT).show();
									}
									else if(edtRep1.getText().toString().isEmpty())
									{
										Toast.makeText(getApplicationContext(), " Saisir la bonne réponse!", Toast.LENGTH_SHORT).show();
									}	
									else if(edtRep2.getText().toString().isEmpty())
									{
										Toast.makeText(getApplicationContext(), " Saisir la mauvaise réponse 1!", Toast.LENGTH_SHORT).show();
									}
									else if(edtRep3.getText().toString().isEmpty())
									{
										Toast.makeText(getApplicationContext(), " Saisir la mauvaise réponse 2!", Toast.LENGTH_SHORT).show();
									}
									else if(edtRep4.getText().toString().isEmpty())
									{
										Toast.makeText(getApplicationContext(), " Saisir la mauvaise réponse 3!", Toast.LENGTH_SHORT).show();
									}
									else// on ajoute la question
									{
										// récupère les informations concernant la fiche
										Cursor note = mDbHelper.fetchFiche(mRowId);
										int idFiche = (note.getInt(note
												.getColumnIndexOrThrow(FichesDbAdapter.KEY_ROWID)));
										//insert de la question
										 Questions maQuestion= new Questions(edtQuestion.getText().toString(),edtRep1.getText().toString(),
												 edtRep2.getText().toString(),edtRep3.getText().toString(),edtRep4.getText().toString(),
												 idFiche);
										 mesQuestionsBdd.open();
										 mesQuestionsBdd.insertMaQuestion(maQuestion);
//										mDbHelperQuizz.createQuestion(edtQuestion.getText().toString(), edtRep1.getText().toString(), edtRep2.getText().toString(),
//												edtRep3.getText().toString(),edtRep4.getText().toString());
										
										String titreChapitre = (note.getString(note
												.getColumnIndexOrThrow(FichesDbAdapter.KEY_TITLE)).toString());
										Toast.makeText(getApplicationContext(), "Question enregistré dans la fiche! "+titreChapitre, Toast.LENGTH_SHORT).show();
										Toast.makeText(getApplicationContext(), "Question:: "+mesQuestionsBdd.getQuestion2(2).getQuestions(), Toast.LENGTH_SHORT).show();
									}
										
								}
						        
								
							});
						}
				   
					});
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

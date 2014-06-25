package com.example.bacquizz;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class Chapitre1 extends Activity {
	
//	private Button mBtnQuizz;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#345953")));
		setContentView(R.layout.activity_chapitre1);
		setTitle("Une aire en expension l'Asie orientiale");
//		mBtnQuizz = (Button) findViewById(R.id.btnQuizz);
//		mBtnQuizz.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	public void onClickBtnQuizz2(View v) {
		// TODO Auto-generated method stub
		
		
		
//			Intent intent = new Intent(this, Quizz.class);
//			this.startActivity(intent);
		new AlertDialog.Builder(this)
	    .setTitle("Commencer le quizz ?")
	    .setMessage("Voulez-vous commencer le quizz ?")
	    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	        	Bundle objetbunble = new Bundle();
	    		Intent intent = new Intent(Chapitre1.this, Quizz.class);
	    		objetbunble.putInt("idChapitre", 1);
	    		objetbunble.putString("ex", "ficheRevision");
	    		intent.putExtras(objetbunble);
	    		startActivity(intent);
	        }
	     })
	    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            // do nothing
	        }
	     })
	    .setIcon(android.R.drawable.ic_dialog_alert)
	     .show();
	}


}
	
//	private void showQuizzDialog()
//	{
//
//		final View addView = getLayoutInflater().inflate(R.layout.quizz, null);
//		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//			// set title
//			alertDialogBuilder.setTitle("prêt");
//
//			// set dialog message
//			alertDialogBuilder
//				.setMessage("Commencer le quizz?")
//				.setPositiveButton("Oui",new DialogInterface.OnClickListener() {
//					
//				
//			
//					public void onClick(DialogInterface dialog,int id) {
//					
//						setContentView(addView);
//					
//					}
//				  })
//				.setNegativeButton("Non",new DialogInterface.OnClickListener() {
//					public void onClick(DialogInterface dialog,int id) {
//						// if this button is clicked, just close
//						// the dialog box and do nothing
//						
//						dialog.cancel();
//					}
//				});
// 
//				// create alert dialog
//				AlertDialog alertDialog = alertDialogBuilder.create();
//
//				 
//
//				// show alert
//				alertDialog.show();
//
//			}
//		;
//	}
//	


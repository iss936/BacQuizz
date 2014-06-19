package com.example.bacquizz;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class Chapitre4 extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chapitre4);
		setTitle("La Seconde Guerre Mondiale");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	public void onClickBtnQuizz3(View v) {
		new AlertDialog.Builder(this)
	    .setTitle("Commencer le quizz ?")
	    .setMessage("Voulez-vous commencer le quizz ?")
		.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int which) { 
			Bundle objetbunble = new Bundle();
			Intent intent = new Intent(Chapitre4.this, Quizz.class);
			objetbunble.putInt("idChapitre", 4);
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
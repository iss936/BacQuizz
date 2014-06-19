package com.example.bacquizz;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FicheRevision extends ListActivity {

	private String exercice;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
<<<<<<< HEAD
		String[] myList = new String[] {"Une aire en expansion l'Asie orientale","La mondialisation","Les mutations en France depuis 1945","La Seconde Guerre Mondiale"}; 
=======
		String[] myList = new String[] {"Une aire en expansion l'Asie orientale","La mondialisation","Les mutations en France depuis 1945",}; 
>>>>>>> 6ac44a2ee7d0f6d6f3039505167754541d75f7f2
		setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myList));
		Bundle nomBundle = this.getIntent().getExtras();
		exercice = nomBundle.getString("faire");
		if(exercice.contains("quizz"))
			setTitle("Quizz");
		if(exercice.contains("ficheRevision"))
			setTitle("Fiche révision");
<<<<<<< HEAD
=======
//		if(exercice.contains("ficheRevision") == true)
//			Toast.makeText(getApplicationContext(), exercice.toString(), Toast.LENGTH_SHORT).show();
>>>>>>> 6ac44a2ee7d0f6d6f3039505167754541d75f7f2
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, final long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
//		final Intent i ;		
		
//		int indice =(int) id;
<<<<<<< HEAD
		final Class[] mesClasses = new Class[]{Chapitre1.class, Chapitre2.class,Chapitre3.class, Chapitre4.class};
=======
		final Class[] mesClasses = new Class[]{Chapitre1.class, Chapitre2.class,Chapitre3.class};
>>>>>>> 6ac44a2ee7d0f6d6f3039505167754541d75f7f2
		Object o = this.getListAdapter().getItem(position);
		final Bundle objetbunble = new Bundle();
		
		if(exercice.contains("quizz")) {
		
		new AlertDialog.Builder(this)
	    .setTitle("Commencer le quizz ?")
	    .setMessage("Voulez-vous commencer le quizz ?")
	    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            // continue with delete
	        	for(int j=0;j<=mesClasses.length;j++)
	    		{
	    			if(id == j)
	    			{	
	    				if(exercice.contains("quizz"))
	    				{
	    					Intent i = new Intent(FicheRevision.this, Quizz.class);
	    					objetbunble.putInt("idChapitre", j+1);
	    					objetbunble.putString("ex", exercice);
	    					i.putExtras(objetbunble);
	    					startActivity(i);
	    					
	    				}
	    				if(exercice.contains("ficheRevision"))
	    				{
	    					Intent i = new Intent(FicheRevision.this, mesClasses[j]);
	    					objetbunble.putInt("idChapitre", j+1);
	    					i.putExtras(objetbunble);
	    					startActivity(i);
	    				}				
	    				
	    				
	    			}
	    		}
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
		
		if(exercice.contains("ficheRevision")) {
			for(int j=0;j<=mesClasses.length;j++)
    		{
    			if(id == j)
    			{
					Intent i = new Intent(FicheRevision.this, mesClasses[j]);
					objetbunble.putInt("idChapitre", j+1);
					i.putExtras(objetbunble);
					startActivity(i);
    			}
    		}
		}
<<<<<<< HEAD
=======
		
		
		
       
        
			
//			startActivity(FicheRevision.this, Chapitre1.class);
//		Intent intent = new Intent(this, Chapitre1.class);
//		this.startActivity(intent);
>>>>>>> 6ac44a2ee7d0f6d6f3039505167754541d75f7f2
	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	

}

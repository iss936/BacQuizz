package com.example.bacquizz;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Score extends Activity {

	/** Called when the activity is first created. */
	private Bundle nomBundle;
	private String faire;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#345953")));
	    setContentView(R.layout.activity_score);
	    // TODO Auto-generated method stub
	    
	    // on récupère la variable passer en paramètre(extras)
	    nomBundle = this.getIntent().getExtras();
	    faire = nomBundle.getString("exercice");
	    int score = nomBundle.getInt("score");
	    
	    
	    TextView txtView = (TextView) findViewById(R.id.txtScore);
	    txtView.setText("Votre score est de : " + score + "/10");
	    
	    
	    
	}
	
	public void onClickRetourMenu(View v)
	{
		Intent intent = new Intent(this, MenuES.class);
		this.startActivity(intent);

	}
	public void onClickRetourChapitre(View v)
	{
		
		Bundle objetbunble = new Bundle();
		Intent intent = new Intent(this, FicheRevision.class);
		if(faire.contains("quizz"))
		{
			objetbunble.putString("faire", faire);
			intent.putExtras(objetbunble);
		}
		
		if(faire.contains("ficheRevision"))
		{
			objetbunble.putString("faire", faire);
			intent.putExtras(objetbunble);
		}
		this.startActivity(intent);
	}
	

}

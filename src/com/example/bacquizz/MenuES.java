package com.example.bacquizz;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MenuES extends Activity{
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#345953")));
        setContentView(R.layout.menu_es);
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	
	public void onClickMaFiche(View v)
	{
		Intent intent = new Intent(this, Notepadv3.class);
		this.startActivity(intent);
	}
	
	public void onClickFicheRevision(View v)
	{
		
		Bundle objetbunble = new Bundle();
		Intent intent = new Intent(this, FicheRevision.class);
		objetbunble.putString("faire", "ficheRevision");
		intent.putExtras(objetbunble);
		this.startActivity(intent);
	}
	
	public void onClickQuizz(View v)
	{
		Bundle objetbunble = new Bundle();
		Intent intent = new Intent(this, FicheRevision.class);
		objetbunble.putString("faire", "quizz");
		intent.putExtras(objetbunble);
		this.startActivity(intent);
		
	}
}

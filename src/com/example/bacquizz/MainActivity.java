package com.example.bacquizz;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void onClickS(View v)
    {
    	Intent intent = new Intent(this, MenuS.class);
    	this.startActivity(intent);
    }
    
    public void onClickL(View v)
    {
    	Intent intent = new Intent(this, MenuL.class);
    	this.startActivity(intent);
    }
    
    public void onClickES(View v)
    {
    	Intent intent = new Intent(this, MenuES.class);
    	this.startActivity(intent);
    }
    
    public void onClickInfo(View v)
    {
    	String message = "Issassassin";
    	message += "\nMad1";
    	new AlertDialog.Builder(this).setTitle("Crédits").setMessage(message).setIcon(R.drawable.information).setNeutralButton("Close", null).show();
    }
}

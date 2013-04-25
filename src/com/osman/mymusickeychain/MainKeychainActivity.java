package com.osman.mymusickeychain;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


public class MainKeychainActivity extends Activity implements OnItemSelectedListener{
	public final static String KEY = "com.osman.mymusickeychain.KEY";
	public final static String MODE = "com.osman.mymusickeychain.MODE";
	private Button submit;
	private Spinner keySpinner;
	private Spinner modeSpinner;
	private String key;
	private String mode;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_keychain);	
		
		keySpinner = (Spinner) findViewById(R.id.key_spinner);
		keySpinner.setOnItemSelectedListener(this);
		modeSpinner = (Spinner) findViewById(R.id.mode_spinner);
		modeSpinner.setOnItemSelectedListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_keychain, menu);
		return true;
	}

	/*Called when user clicks Submit button*/
	public void displayKey (View view){
		Intent intent = new Intent(this, DisplayKey.class);
		intent.putExtra(KEY, key);
		intent.putExtra(MODE, mode);
		startActivity(intent);
	}
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
		if(parent == keySpinner){
			key = parent.getSelectedItem().toString();
		} else {
			mode = parent.getSelectedItem().toString();
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

	
}

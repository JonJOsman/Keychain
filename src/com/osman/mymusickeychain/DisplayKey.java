/**
 * @author Jon Osman 
 * 
 * Activity launched after users hits "submit" in the main activity. 
 * Displays information about the key selected.
 * 
 */

package com.osman.mymusickeychain;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class DisplayKey extends Activity {
	
	/**
	 * Takes the information contained within the passed Intent object
	 * and uses it to initialize a MusicKey object. Calls the methods to
	 * set the activity title and print the first column.
	 * */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_key);
		// Show the Up button in the action bar.
		setupActionBar();
		Intent intent = getIntent();
		String key = intent.getStringExtra(MainKeychainActivity.KEY);
		String intentMode = intent.getStringExtra(MainKeychainActivity.MODE);
		Mode myMode = null;
		for (Mode mode : Mode.values()){
			if(intentMode.equalsIgnoreCase(mode.name())){
				myMode = mode;				
			}
		}		
		MusicKey myKey = new MusicKey(key, myMode);
		Button displayChords = (Button) findViewById(R.id.display_chords);
		displayChords.setTag(R.id.display_chords, myKey);
		setTitle(myKey);
		printColumn("Col1Text", myKey.getScale());		
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_key, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent intent = new Intent (this, MainKeychainActivity.class);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}		
	}	
	
	/** Sets the activity title using the MusicKeys Mode and Tonic fields. */
	public void setTitle(MusicKey myKey){
		String modeText = (String) myKey.getMode();
		modeText = modeText.toLowerCase();
		modeText = modeText.substring(0,1).toUpperCase() + modeText.substring(1);
		setTitle(myKey.getTonic() + " " + modeText);
	}
	
	
	/** Takes as parameters a String to specify which column to use,
	 * and a String array to specify which array to use. */
	public void printColumn(String column, String[] textArray){
		TextView[] rowArray = new TextView[7];
		for (int i = 0; i < rowArray.length; i++) {
			rowArray[i] = new TextView(this);
			String rowID = column + (i + 1);
			int resID = getResources().getIdentifier(rowID, "id", "com.osman.mymusickeychain");
			rowArray[i] = (TextView)findViewById(resID);
			rowArray[i].setText(textArray[i]);
		}
	}
	
	/** Called when the user clicks the "Show chords" button. Calls the methods for printing the
	 * chord qualities and the triads of the chords. Then removes the "Show Chords" button. */
	public void displayChords(View view){
		MusicKey myKey = new MusicKey();
		myKey = ((MusicKey) view.getTag(R.id.display_chords));
		printColumn("Col2Text",myKey.getQualities());
		printTriads(myKey.getScale());
		ViewGroup layout = (ViewGroup) findViewById(R.id.display_chords).getParent();
		layout.removeView(findViewById(R.id.display_chords));
	}

	/** Prints the triads column after "Show chords" has been clicked.  */
	public void printTriads(String[] notes){
		TextView[] rowArray = new TextView[7];
		for (int i = 0; i < rowArray.length; i++){
			rowArray[i] = new TextView(this);
			String rowID = "Col3Text" + (i + 1);
			int resID = getResources().getIdentifier(rowID, "id", "com.osman.mymusickeychain");
			rowArray[i] = (TextView)findViewById(resID);
			String triad = notes[i] + " " + notes[(i+2)%7] + " " + notes[(i+4)%7];
			rowArray[i].setText(triad);
			
		}
		
	}
	
}

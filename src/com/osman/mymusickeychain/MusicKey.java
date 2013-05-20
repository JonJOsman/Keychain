/** 
 * @author Jon Osman
 * 
 * Class used in the MyMusicKeychain app to represent a musical key.
 * Provides the notes in the key, the mode, and the qualities
 * of the triads in the key.
 * 
 */

package com.osman.mymusickeychain;

public class MusicKey {

	private String[] notes = { "A", "A#", "B", "C", "C#",
			"D", "D#", "E", "F", "F#", "G", "G#"
	};
	private int[] stepPattern = {2,2,1,2,2,2,1};
	private String[] qualityPattern = {"Maj", "Min", "Min", "Maj", "Maj", "Min", "Dim"};
	
	private String tonic;
	private String[] scale = new String[7];
	private String[] qualities = new String[7];
	private Mode mode;
	
	
	public MusicKey() {
		super();
	}

	public MusicKey(String tonic, Mode mode) {
		this.tonic = tonic;
		this.scale[0] = tonic;
		this.mode = mode;
		this.populateScale(tonic, mode);
	}
	
	/** Used to populate the scale array based on the tonic and mode chosen.
	 * The switch statement is used to decide which permutation of stepPattern to use. */
	private void populateScale(String tonic, Mode mode) {
		int noteIndex = 0;
		int stepIndex = 0;
		for(int i=0;i<notes.length;i++){
			if(tonic.equals(notes[i]))
				noteIndex = i;
		}
		switch (mode) {
			case MAJOR:
				stepIndex = 0;
				break;
			case MINOR:
				stepIndex = 5;
				break;
			case DORIAN:
				stepIndex = 1;
				break;
			case PHRYGIAN:
				stepIndex = 2;
				break;
			case LYDIAN:
				stepIndex = 3;
				break;
			case MIXOLYDIAN:
				stepIndex = 4;
				break;
			case LOCRIAN:
				stepIndex = 6;
				break;
			default:
				stepIndex = 0;
				break;
		}
		for(int i = 0; i < scale.length; i++){
			this.scale[i] = notes[noteIndex%12];
			this.qualities[i] = qualityPattern[stepIndex%7];
			noteIndex += stepPattern[stepIndex%7];
			stepIndex++;
		}
	}
	
	public void printScale() {
		for(int i = 0; i < 7; i++){
			System.out.println(this.scale[i]);			
		}
	}

	public String[] getScale() {
		return scale;
	}
	
	public String getTonic() {
		return tonic;
	}
	
	public String[] getQualities() {
		return qualities;
	}
	
	public String getMode() {
		return this.mode.name();
	}	
	
}

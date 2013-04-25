package com.osman.mymusickeychain;

public class MusicKey {

	public String[] notes = { "A", "A#", "B", "C", "C#",
			"D", "D#", "E", "F", "F#", "G", "G#"
	};
	/*fields*/
	String tonic;
	String[] scale = new String[7];
	Mode mode;
	
	
	public MusicKey(String tonic, Mode mode){
		this.tonic = tonic;
		this.scale[0] = tonic;
		this.mode = mode;
		this.populateScale(tonic, mode);
	}
	
	private void populateScale(String tonic, Mode mode){
		int index = 0;
		for(int i=0;i<notes.length;i++){
			if(tonic.equals(notes[i]))
				index = i;
		}
		switch (mode) {
			case MAJOR:
				index += 2;
				this.scale[1] = notes[index%12];
				index += 2;
				this.scale[2] = notes[index%12];
				index += 1;
				this.scale[3] = notes[index%12];
				index += 2;
				this.scale[4] = notes[index%12];
				index += 2;
				this.scale[5] = notes[index%12];
				index += 2;
				this.scale[6] = notes[index%12];						
				break;
		case MINOR:
			index += 2;
			this.scale[1] = notes[index%12];
			index += 1;
			this.scale[2] = notes[index%12];
			index += 2;
			this.scale[3] = notes[index%12];
			index += 2;
			this.scale[4] = notes[index%12];
			index += 1;
			this.scale[5] = notes[index%12];
			index += 2;
			this.scale[6] = notes[index%12];
			break;
		default:
			break;
		}
	}
	
	public void printScale(){
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
	
	public String getMode(){
		return this.mode.name();
	}

	
	/*
	public static void main(String[] args){
		MusicKey C = new MusicKey("F", Mode.MAJOR);
		C.printScale();
		
	}*/
	
}

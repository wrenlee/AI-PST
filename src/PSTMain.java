
//Wren Lee
//prediction suffix tree
//main class

import processing.core.*;

import java.util.*;

//importing the JMusic stuff
import jm.music.data.*;
import jm.JMC;
import jm.util.*;
import jm.midi.*;

import java.io.UnsupportedEncodingException;
import java.net.*;

//make sure this class name matches your file name, if not fix.
public class PSTMain extends PApplet {

	// init string with empty space
	ArrayList<String> initStr = new ArrayList<String>();
	ArrayList<Integer> pitchArr = new ArrayList<Integer>();
	// ArrayList<Double> rhythmArr = new ArrayList<Double>();

	float pMin = (float) .1;
	float pMin2 = (float) .15;

	Tree abra = new Tree(initStr, pMin);
	Tree abc = new Tree(initStr, pMin);
	Tree abcComplex = new Tree(initStr, pMin);
	Tree maryP = new Tree(pitchArr, pMin);

	Tree abra2 = new Tree(initStr, pMin2);
	Tree abc2 = new Tree(initStr, pMin2);
	Tree abcComplex2 = new Tree(initStr, pMin2);
	Tree maryP2 = new Tree(pitchArr, pMin2);

	MelodyPlayer player; // play a midi sequence
	MidiFileToNotes midiNotes; // read a midi file

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("PSTMain");
	}

	// setting the window size to 300x300
	public void settings() {
		size(500, 500);
	}

	// doing all the setup stuff
	public void setup() {
		// midi player, music code
		String filePath = getPath("midi/MaryHadALittleLamb.mid");
		midiNotes = new MidiFileToNotes(filePath);
		midiNotes.setWhichLine(0);
		player = new MelodyPlayer(this, 100.0f);

		fill(120, 50, 240);
		background(100);

		// set order length
		int order = 4;

		// make string into an arraylist
		Character[] abraStr = { 'a', 'b', 'r', 'a', 'c', 'a', 'd', 'a', 'b', 'r', 'a' };
		ArrayList<Character> abraArr = new ArrayList<Character>(Arrays.asList(abraStr));

		Character[] abcStr = { 'a', 'c', 'a', 'd', 'a', 'a', 'c', 'b', 'd', 'a' };
		ArrayList<Character> abcArr = new ArrayList<Character>(Arrays.asList(abcStr));

		Character[] abcComplexStr = { 'a', 'b', 'c', 'c', 'c', 'd', 'a', 'a', 'd', 'c', 'd', 'a', 'a', 'b', 'c', 'a', 'd', 'a', 'd' };
		ArrayList<Character> abcComplexArr = new ArrayList<Character>(Arrays.asList(abcComplexStr));

		// trains
		abra.train(abraArr, order);
		abc.train(abcArr, order);
		abcComplex.train(abcComplexArr, order);
		maryP.train(midiNotes.getPitchArray(), order);
		
		abra2.train(abraArr, order);
		abc2.train(abcArr, order);
		abcComplex2.train(abcComplexArr, order);
		maryP2.train(midiNotes.getPitchArray(), order);

		fill(255);
		text("1 for abracadabra, 2 for abc", 50, 100);
		text("3 for abc complex, 4 for Mary Had a Little Lamb song", 50, 120);
		text("a for abra, b for abc, c for abc complex", 50, 140);
		text("and d for Mary Had for pmin of .5", 50, 160);
	}

	public void draw() {

	}// draw

	public void keyPressed() {
		if(key == '1') {
			abra.print();
		}
		if(key == '2') {
			abc.print();
		}
		if(key == '3') {
			abcComplex.print();
		}
		if(key == '4') {
			System.out.println("Pitches");
			maryP.print();
		}
		
		//keys for .5 pmin
		if(key == 'a') {
			abra2.print();
		}
		if(key == 'b') {
			abc2.print();
		}
		if(key == 'c') {
			abcComplex2.print();
		}
		if(key == 'd') {
			maryP2.print();
		}
		
	}// key press

	String getPath(String path) {

		String filePath = "";
		try {
			filePath = URLDecoder.decode(getClass().getResource(path).getPath(), "UTF-8");

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filePath;
	}

}// ends main

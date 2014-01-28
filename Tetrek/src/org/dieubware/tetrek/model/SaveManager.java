package org.dieubware.tetrek.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class SaveManager {

	public static final String SCORE_FILE = "save/highscore.tk";
	
	public static void saveScore(int score) {
		save(SCORE_FILE,String.valueOf(score));
	}
	
	public static int loadScore() {
		String score = load(SCORE_FILE);
		if(score == null) {
			return 0;
		}
		else {
			return Integer.parseInt(score);
		}
	}
	
	public static void saveSettings() {
		
	}
	
	private static void save(String file, String content) {
		if(Gdx.files.isLocalStorageAvailable()) {
		  FileHandle fh = Gdx.files.local(file);	
		  fh.writeString(content, false);
		}
	}
	
	private static String load(String file) {
		if(Gdx.files.isLocalStorageAvailable()) {
		  FileHandle fh = Gdx.files.local(file);	
		  if(fh.exists())
			  return fh.readString();
		  else
			  return null;
		}
		else
			return null;
	}
}

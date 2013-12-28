package org.gwenn.blokenn;

import org.dieubware.tetrek.BlokennGame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Blokenn";
		cfg.useGL20 = false;
		cfg.width = 500;
		cfg.height = 550;
		
		new LwjglApplication(new BlokennGame(), cfg);
	}
}

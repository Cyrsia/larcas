package com.grush.larcas;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.grush.larcas.server.ServerWorldChain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("larcas");

		ServerWorldChain serverWorldChain = new ServerWorldChain(World.INSTANCE_, 3491);
		serverWorldChain.start();

		new Lwjgl3Application(new Larcas(), config);
	}
}

package my.trpg.main;

import java.awt.Cursor;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;

import my.gop.main.GameWindow;
import my.trpg.entity.Human;
import my.trpg.gameloop.GameLoop;
import my.trpg.manangers.MouseManager;




public class Main {
	
	public static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	public static int width = gd.getDisplayMode().getWidth();
	public static int height = gd.getDisplayMode().getHeight();
	
	public static void main(String[] args) {
		
		GameWindow frame = new GameWindow("CSC-335 TRPG Final Project", width, height);
		frame.setFullscreen(0);
		frame.addKeyListener(new Human());
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Cursor cursor = toolkit.createCustomCursor(toolkit.getImage(""), new Point(0,0), "Cursor");
		frame.setCursor(cursor);
		frame.addMouseListener(new MouseManager());
		frame.addMouseMotionListener(new MouseManager());
		frame.addMouseWheelListener(new MouseManager());
		frame.add(new GameLoop(width, height));
		frame.setVisible(true);
	}

}

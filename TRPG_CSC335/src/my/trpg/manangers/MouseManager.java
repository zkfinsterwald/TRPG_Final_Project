package my.trpg.manangers;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import my.trpg.res.Assets;

public class MouseManager implements MouseListener, MouseMotionListener, MouseWheelListener {

	private static int mouseMovedX, mouseMovedY;
	public static Point mouse;
	
	public static boolean pressed;
	
	public void tick() {
		mouse = new Point(mouseMovedX, mouseMovedY);
	}
	
	public void render(Graphics2D g) {
		if(pressed){
			g.drawImage(Assets.getMouse_pressed(), mouseMovedX, mouseMovedY, 32, 32, null);
		} else {
			g.drawImage(Assets.getMouse_unpressed(), mouseMovedX, mouseMovedY, 32, 32, null);
		}
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseMovedX = e.getX();
		mouseMovedY = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {	
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			pressed = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			pressed = false;
		}
	}

}

package JOGL;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;


/**
 * 
 * @author dvbar
 * v1.2.3
 */
public class InputHandler implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
	
	
	private boolean reset;
	private float xTrans = 0.0f;
	private float scale = 1.0f;
	private int channel;
	
	//returns xTrans 
	public float getxTrans() {
		System.out.println("xTrans: " + xTrans);
		return xTrans;
	}
	
	//returns scale
	public float getScale() {
		System.out.println("scale: " + scale);
		return scale;
	}
	
	//returns channel
	public int getChannel() {
		System.out.println("channel: " + channel);
		return channel;
	}
	
	//returns true if reset pressed
	public boolean resetPressed() {
		return reset;
	}
	
	/** Keyboard Input **/
	
	//handles key press events
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_UP) {
			System.out.println("up pressed");
			scale += 0.5;
		}
		else if (keyCode == KeyEvent.VK_DOWN) {
			System.out.println("down pressed");
			if (scale <= 0.5) { scale = 1.0f; }
			else scale -= 0.5;
		}
		else if (keyCode == KeyEvent.VK_LEFT) {
			System.out.println("left pressed");
			if (scale > 1.0) 	{ xTrans += 0.1; }
			if (scale > 5.0) 	{ xTrans += 0.05; }
			if (scale > 10.0) 	{ xTrans += 0.02; }
			else xTrans += 0.2;
		}
		else if (keyCode == KeyEvent.VK_RIGHT) {
			System.out.println("right pressed");
			if (scale > 1.0) 	{ xTrans -= 0.1; }
			if (scale > 5.0) 	{ xTrans -= 0.05; }
			if (scale > 10.0) 	{ xTrans -= 0.02; }
			else xTrans -= 0.2;
		}
		
		else if (keyCode == KeyEvent.VK_1) {
			System.out.println("1 pressed");
			channel = 1;
		}
		else if (keyCode == KeyEvent.VK_2) {
			System.out.println("2 pressed");
			channel = 2;
		}
		else if (keyCode == KeyEvent.VK_3) {
			System.out.println("3 pressed");
			channel = 3;
		}
		else if (keyCode == KeyEvent.VK_4) {
			System.out.println("4 pressed");
			channel = 4;
		}
		else if (keyCode == KeyEvent.VK_5) {
			System.out.println("5 pressed");
			channel = 5;
		}
		else if (keyCode == KeyEvent.VK_6) {
			System.out.println("6 pressed");
			channel = 6;
		}
		else if (keyCode == KeyEvent.VK_7) {
			System.out.println("7 pressed");
			channel = 7;
		}
		else if (keyCode == KeyEvent.VK_8) {
			System.out.println("8 pressed");
			channel = 8;
		}
		else if (keyCode == KeyEvent.VK_9) {
			System.out.println("9 pressed");
			channel = 9;
		}
		else if (keyCode == KeyEvent.VK_0) {
			System.out.println("0 pressed");
			channel = 0;
		}
		else if (keyCode == KeyEvent.VK_A) {
			System.out.println("A pressed");
			channel = 10;
		}
		else if (keyCode == KeyEvent.VK_S) {
			System.out.println("S pressed");
			channel = 11;
		}
		else if (keyCode == KeyEvent.VK_D) {
			System.out.println("D pressed");
			channel = 12;
		}
		else if (keyCode == KeyEvent.VK_F) {
			System.out.println("F pressed");
			channel = 13;
		}
		else if (keyCode == KeyEvent.VK_O) {
			System.out.println("O(odba) pressed");
			channel = 14;
		}
		else if (keyCode == KeyEvent.VK_V) {
			System.out.println("V(vedba) pressed");
			channel = 15;
		}
		
		else if (keyCode == KeyEvent.VK_ESCAPE) {
			System.out.println("escape pressed");
			System.exit(0);
		}
		else if (keyCode == KeyEvent.VK_R) {
			System.out.println("reset pressed");
			reset = true;
			//xTrans = 0.0f;
			//scale = 1.0f;
		}
		else System.out.println("pressed: " + keyCode);
	}
	
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
	/** Mouse Input **/
	private boolean dragging;
	private float startX, prevX;
	private float dx = 0.0f;
	private int button, scroll; 
	private float wheel = 0.75f; 

	//returns true if dragging
	public boolean isDragging() {
		return dragging;
	}
	
	//returns DX
	public float getDX() {
		System.out.println("	dx: " + dx);
		return dx;
	}
	
	//mouse input for drag-translate
	public void mouseDragged(MouseEvent e) {
		if (!dragging) {
			return;
		}
		if (dragging) {
			System.out.println("dragging");
			if (button == MouseEvent.BUTTON3) {
				float x = e.getX();
				prevX = x;
				dx = (prevX - startX) * 0.001f;
			}
		}
		xTrans += dx;
	}
	
	//mouse input for button press
	public void mousePressed(MouseEvent e) {
		button = e.getButton();
		if (dragging) {
			return;
		}
		if (button == MouseEvent.BUTTON3) {
		float x = e.getX();
		prevX = startX = x;
		dragging = true;
		}
	}

	
	public void mouseReleased(MouseEvent e) {
		System.out.println(" mouse released");
		if (!dragging) {
			return;
		}
		dragging = false;
	}

	//mouse wheel input for zoom function
	public void mouseWheelMoved(MouseWheelEvent e) {
		scroll = e.getUnitsToScroll();
		if (scroll < 0) {
			scale += wheel;
		}
		else if (scroll > 0) {
			if (scale <= 0.5) { scale = 1.0f; }
			else scale  -= wheel;
		}
		System.out.println("  scroll: " + scroll);
		scroll = 0;
	}
	
	//test for mouse buttons
	public void mouseClicked(MouseEvent e) {
		button = e.getButton();
		if (button == MouseEvent.BUTTON1) System.out.println("button1 clicked (left click)");
		if (button == MouseEvent.BUTTON2) System.out.println("button2 clicked (right click)");
		if (button == MouseEvent.BUTTON3) System.out.println("button3 clicked");
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}

}

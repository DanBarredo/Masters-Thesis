package JOGL;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

/**
 * 
 * @author dvbar
 * v1.6.0
 */

@SuppressWarnings("serial")
public class Chart extends GLCanvas implements GLEventListener, KeyListener, MouseListener, MouseMotionListener, MouseWheelListener, ActionListener {
	
	private float xTrans;
	private float scale;
	private int channel = 0;
	private JButton ch1, ch2, ch3, ch4, ch5, ch6, ch7, ch8, ch9, ch10, ch11, ch12, ch13;
	private JButton odba, vedba;
	private JButton clearButton, resetButton;
	
	//creates chart program
	public Chart() {
		xTrans = 0.0f;
		scale = 1.0f;
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		addGLEventListener(this);

		FPSAnimator animator = new FPSAnimator(this, 30);		
		JFrame frame = new JFrame("JOGL Visualisation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(this, BorderLayout.CENTER);
		frame.setSize(2000,800);
		frame.setLayout(new BorderLayout());
		frame.add(this);
		
		JPanel panel = new JPanel (new GridLayout(1,0));
		JPanel chPanel = new JPanel(new GridLayout(17, 0)); 
			ch1 = new JButton("Channel 1");
			ch1.setFont(new Font("Arial", Font.BOLD, 20));
			ch1.addActionListener(this);
			chPanel.add(ch1);
			ch2 = new JButton("Channel 2");
			ch2.setFont(new Font("Arial", Font.BOLD, 20));
			ch2.addActionListener(this);
			chPanel.add(ch2);
			ch3 = new JButton("Channel 3");
			ch3.setFont(new Font("Arial", Font.BOLD, 20));
			ch3.addActionListener(this);
			chPanel.add(ch3);	
			ch4 = new JButton("Channel 4");
			ch4.setFont(new Font("Arial", Font.BOLD, 20));
			ch4.addActionListener(this);
			chPanel.add(ch4);
			ch5 = new JButton("Channel 5");
			ch5.setFont(new Font("Arial", Font.BOLD, 20));
			ch5.addActionListener(this);
			chPanel.add(ch5);
			ch6 = new JButton("Channel 6");
			ch6.setFont(new Font("Arial", Font.BOLD, 20));
			ch6.addActionListener(this);
			chPanel.add(ch6);
			ch7 = new JButton("Channel 7");
			ch7.setFont(new Font("Arial", Font.BOLD, 20));
			ch7.addActionListener(this);
			chPanel.add(ch7);
			ch8 = new JButton("Channel 8");
			ch8.setFont(new Font("Arial", Font.BOLD, 20));
			ch8.addActionListener(this);
			chPanel.add(ch8);
			ch9 = new JButton("Channel 9");
			ch9.setFont(new Font("Arial", Font.BOLD, 20));
			ch9.addActionListener(this);
			chPanel.add(ch9);
			ch10 = new JButton("Channel 10");
			ch10.setFont(new Font("Arial", Font.BOLD, 20));
			ch10.addActionListener(this);
			chPanel.add(ch10);	
			ch11 = new JButton("Channel 11");
			ch11.setFont(new Font("Arial", Font.BOLD, 20));
			ch11.addActionListener(this);
			chPanel.add(ch11);
			ch12 = new JButton("Channel 12");
			ch12.setFont(new Font("Arial", Font.BOLD, 20));
			ch12.addActionListener(this);
			chPanel.add(ch12);
			ch13 = new JButton("Channel 13");
			ch13.setFont(new Font("Arial", Font.BOLD, 20));
			ch13.addActionListener(this);
			chPanel.add(ch13);
			odba = new JButton("ODBA");
			odba.setFont(new Font("Arial", Font.BOLD, 20));
			odba.addActionListener(this);
			chPanel.add(odba);
			vedba = new JButton("VeDBA");
			vedba.setFont(new Font("Arial", Font.BOLD, 20));
			vedba.addActionListener(this);
			chPanel.add(vedba);
			resetButton = new JButton("Reset Graph");
			resetButton.setFont(new Font("Arial", Font.BOLD, 20));
			resetButton.addActionListener(this);
			chPanel.add(resetButton); 
			clearButton = new JButton("Clear Graph");
			clearButton.setFont(new Font("Arial", Font.BOLD, 20));
			clearButton.addActionListener(this);
			chPanel.add(clearButton);
		panel.add(chPanel);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu statsMenu = new JMenu("Statistics");
		statsMenu.setFont(new Font("Arial", Font.PLAIN, 26));
		menuBar.add(statsMenu);
		
		JMenuItem corrStats = new JMenuItem("Correlation");
		corrStats.setFont(new Font("Arial", Font.PLAIN, 24));
		statsMenu.add(corrStats);
		JMenuItem linReg = new JMenuItem("Linear Regression");
		linReg.setFont(new Font("Arial", Font.PLAIN, 24));
		statsMenu.add(linReg);
		JMenuItem owAnova = new JMenuItem("One-way Anova");
		owAnova.setFont(new Font("Arial", Font.PLAIN, 24));
		statsMenu.add(owAnova);
		JMenu menuDBA = new JMenu("DBA");
		menuDBA.setFont(new Font("Arial", Font.PLAIN, 24));
			JMenuItem odbaStats = new JMenuItem("ODBA");
			odbaStats.setFont(new Font("Arial", Font.PLAIN, 24));
			menuDBA.add(odbaStats);
			JMenuItem vedbaStats = new JMenuItem("VeDBA");
			vedbaStats.setFont(new Font("Arial", Font.PLAIN, 24));
			menuDBA.add(vedbaStats);
		statsMenu.add(menuDBA);
		
		frame.add(menuBar, BorderLayout.NORTH);
		frame.add(panel, BorderLayout.EAST);
		frame.setVisible(true);
		animator.start();
	}
		
	//initiates chart program
	public static void main(String[] args) {
		new Chart();
	}

	
	Data data = new Data("Lena1-corm2.txt");
	Graph graph = new Graph(data);
	
	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glClearColor(0, 0, 0, 0);
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		graph.zoom(gl, scale);
		graph.translate(gl, xTrans);
		graph.drawGraph(gl, channel);
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		System.out.println("dispose");
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		System.out.println("init");
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		System.out.println("reshape");
	}
	
	
	InputHandler input = new InputHandler();
	/**
	 * key input
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		input.keyPressed(e);
		xTrans = input.getxTrans();
		if (input.resetPressed() == true) {
			scale = 1.0f;
			xTrans = 0.0f;
			display();
		}
		scale = input.getScale();
		channel = input.getChannel();
		display();
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}

	
	/**
	 * mouse input
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		input.mouseClicked(e);
	}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		input.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		input.mouseReleased(e);
	}
	
	
	//motion listener - work on this
	@Override
	public void mouseDragged(MouseEvent e) {
		input.mouseDragged(e);
		xTrans = input.getxTrans();	
		System.out.println("xTrans: " + xTrans);
		display();
	}

	@Override
	public void mouseMoved(MouseEvent e) {	
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		System.out.println("wheel moved");
		input.mouseWheelMoved(e);
		float wheelScale = input.getScale();
		scale = wheelScale; 
		System.out.println("scale: " + scale);
		display();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ch1) { channel = 1; }
		if (e.getSource() == ch2) { channel = 2; }
		if (e.getSource() == ch3) { channel = 3; }
		if (e.getSource() == ch4) { channel = 4; }
		if (e.getSource() == ch5) { channel = 5; }
		if (e.getSource() == ch6) { channel = 6; }
		if (e.getSource() == ch7) { channel = 7; }
		if (e.getSource() == ch8) { channel = 8; }
		if (e.getSource() == ch9) { channel = 9; }
		if (e.getSource() == ch10) { channel = 10; }
		if (e.getSource() == ch11) { channel = 11; }
		if (e.getSource() == ch12) { channel = 12; }
		if (e.getSource() == ch13) { channel = 13; }
		if (e.getSource() == odba) { channel = 14; }
		if (e.getSource() == vedba) { channel = 15; }
		if (e.getSource() == clearButton) { channel = 0; }
		if (e.getSource() == resetButton) { 
			xTrans = 0.0f;
			scale = 1.0f;
		}
		display();
	}
}

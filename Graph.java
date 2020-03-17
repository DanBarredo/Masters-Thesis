package JOGL;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;

/**
 * 
 * @author dvbar
 * v1.3.3
 */

public class Graph { 
	private Data data;

	//empty constructor
	public Graph() {
	}
	
	//creates graph object with data 
	public Graph(Data data) {
		this.data = data;
	}
	
	//draws interactive graph to display channels 
	public void drawGraph(GL2 gl2, int channel) {
		if (channel == 0) {
			gl2.glBegin(GL.GL_LINES);
			gl2.glVertex2d(-1.0, 0.0);
			gl2.glVertex2d(1.0, 0.0);
			gl2.glEnd();
		}
		else {
			double[] array;
			if (channel == 14) { array = data.ODBA(); }
			else if (channel == 15) { array = data.VeDBA(); }
			else { array = data.getChannel(channel);}
			int size = array.length;
			array = Data.scaleData(array);

			gl2.glTranslated(0.0,  -0.8,  0.0);
			double xIndex = -1.0;
			gl2.glBegin(GL.GL_LINE_STRIP);
			for (int i = 0; i < size; i++) {
				gl2.glVertex2d(xIndex, array[i]);
				xIndex += 2.0/size;
			}
			gl2.glEnd();
		}
	}
	
	//draws line graph of array
	public void drawGraph(GL2 gl2, double[] array) {
		int size = array.length;
		array = Data.scaleData(array);
		double min = Data.minValue(array);
		if (min >= 0.0) {
			gl2.glTranslated(0.0, -0.8, 0.0);
		}
		
		double xIndex = -1.0;
		gl2.glBegin(GL.GL_LINE_STRIP);
		for (int i = 0; i < size; i++) {
			gl2.glVertex2d(xIndex, array[i]);
			xIndex += 2.0/size;
		}
		gl2.glEnd();
	}
	
	//interactive translate function (for mouse drag)
	public void translate(GL2 gl2, float xTrans) {
			gl2.glTranslatef(xTrans, 0.0f, 0.0f);
	}
	
	//interactive zoom function (for mouse wheel)
	public void zoom(GL2 gl2, float scale) {
		if (scale <= 0.5) {
			scale = 1.0f;
			gl2.glScalef(scale, 1.0f, 1.0f);
		} 
		else {
			gl2.glScalef(scale, 1.0f, 1.0f);
		}
	}
}

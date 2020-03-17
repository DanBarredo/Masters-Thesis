package JOGL;

/**
 * 
 * @author dvbar
 * v1.2
 */

public class ODBA {
	
	private double[] x;
	private double[] y;
	private double[] z;
	
	//empty constrcutor
	public ODBA() {	
	}
	
	//creates ODBA object with acceleration arrrays
	public ODBA(double[] x, double[] y, double[] z) {
		if (x == null) { throw new NullPointerException("x array empty"); }
		if (y == null) { throw new NullPointerException("y array empty"); }
		if (z == null) { throw new NullPointerException("z array empty"); }
		if (x.length != y.length || x.length != z.length || y.length != z.length) {
			throw new IllegalArgumentException("arrays are not equal length");
		}
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	//returns double array of odba values
	public double[] getODBA(int period) {
		double[] odba = new double[x.length];
		double[] xmean = Statistics.runningMean(x,period);
		double[] ymean = Statistics.runningMean(y,period);
		double[] zmean = Statistics.runningMean(z,period);
		for (int i = 0; i < odba.length; i++) {
			odba[i] = Math.abs(x[i] - xmean[i]) + Math.abs(y[i] - ymean[i]) + Math.abs(z[i] - zmean[i]);
		}
		return odba;
	}
	
	//sets acceleration data
	public void setData(double[] xAxis, double[] yAxis, double[] zAxis) {
		if (xAxis == null) { throw new NullPointerException("x array null"); }
		if (yAxis == null) { throw new NullPointerException("y array null"); }
		if (zAxis == null) { throw new NullPointerException("z array null"); }
		if (xAxis.length != yAxis.length || xAxis.length != zAxis.length || yAxis.length != zAxis.length) {
			throw new IllegalArgumentException("arrays are not equal length");
		}
		x = xAxis;
		y = yAxis;
		z = zAxis;
	}
	
	//test
	public static void main(String[] args) {
		Data data = new Data("test-data.txt");
		double[] x = data.getChannel(1);
		double[] y = data.getChannel(2);
		double[] z = data.getChannel(3);
		//ODBA odba = new ODBA(x, y, z);
		ODBA odba = new ODBA();
		odba.setData(x, y, z);
		double[] acc = odba.getODBA(9);
		for (int i = 0; i < x.length; i++) {
			System.out.println( i + " ODBA: " + acc[i]);
		}
	}
}

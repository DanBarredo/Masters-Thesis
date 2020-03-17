package JOGL;

/**
 * 
 * @author dvbar
 * v1.2
 */

public class VeDBA {
	
	private double[] x;
	private double[] y;
	private double[] z;
	
	//empty constructor
	public VeDBA() {
	}
	
	//creates VeDBA object with acceleration arrays
	public VeDBA(double[] x, double[] y, double[] z) {
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
	
	//returns array of VeDBA values
	public double[] getVeDBA(int period) {
		double[] vedba = new double[x.length];
		double[] xmean = Statistics.runningMean(x,period);
		double[] ymean = Statistics.runningMean(y,period);
		double[] zmean = Statistics.runningMean(z,period);
		for (int i = 0; i < vedba.length; i++) {
			vedba[i] = Math.sqrt( (x[i]-xmean[i])*(x[i]-xmean[i]) + (y[i]-ymean[i])*(y[i]-ymean[i]) + (z[i]-zmean[i])*(z[i]-zmean[i]) );

		}
		return vedba;
	}
	
	//sets acceleration data
	public boolean setData(double[] xAxis, double[] yAxis, double[] zAxis) {
		if (xAxis == null) { throw new NullPointerException("x array null"); }
		if (yAxis == null) { throw new NullPointerException("y array null"); }
		if (zAxis == null) { throw new NullPointerException("z array null"); }
		if (xAxis.length != yAxis.length || xAxis.length != zAxis.length || yAxis.length != zAxis.length) {
			throw new IllegalArgumentException("arrays are not equal length");
		}
		x = xAxis;
		y = yAxis;
		z = zAxis;
		return true;
	}
	
	//test
	public static void main(String[] args) {
		Data data = new Data("test-data.txt");
		double[] x = data.getChannel(1);
		double[] y = data.getChannel(2);
		double[] z = data.getChannel(3);
		VeDBA vedba = new VeDBA();
		vedba.setData(x, y, z);
		double[] acc = vedba.getVeDBA(9);
		for (int i = 0; i < x.length; i++) {
			System.out.println( i + " VeDBA: " + acc[i]);
		}
	}
}

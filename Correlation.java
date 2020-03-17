package JOGL;

import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.correlation.SpearmansCorrelation;

/**
 * 
 * @author dvbar
 * v1.2
 */

public class Correlation {
	
	private double[] xArray, yArray;
	private double rho;
	private double stderrX, stderrY;
	private int n;
	private double pValue;
	
	//empty constructor 
	public Correlation() {
	}
	
	//consructor with double arrays
	public Correlation(double[] x, double[] y) {
		this.xArray = x;
		this.yArray = y;
		this.n = x.length;
	}
	
	//carries out pearsons correlation test if data already available 
	public double PearsonsCorrelation() {
		if (xArray == null) { throw new NullPointerException("x array null"); }
		if (yArray == null) { throw new NullPointerException("y array null"); }
		if (xArray.length != yArray.length) { 
			throw new IllegalArgumentException("arrays not equal length"); 
		}
		double sx = 0.0;
		double sy = 0.0;
		double squareX = 0.0;
		double squareY = 0.0;
		double squareXY = 0.0;
		
		n = xArray.length;
		for (int i = 0; i < n; i++) {
			double a = xArray[i];
			double b = yArray[i];
			sx += a;
			sy += b;
			squareX += a * a;
			squareY += b * b;
			squareXY += a * b;
		}
		double cov = squareXY / n - sx * sy / n / n;
		double stderrX = Math.sqrt(squareX / n - sx * sx / n / n);
		double stderrY = Math.sqrt(squareY / n - sy * sy / n / n);
		rho = cov / stderrX / stderrY;
		return rho;
	}
	
	//carries out pearsons test for new double arrrays
	public double PearsonCorrelation(double[] x, double[] y) {
		if (x == null) { throw new NullPointerException("x array null"); }
		if (y == null) { throw new NullPointerException("y array null"); }
		if (x.length != y.length) { 
			throw new IllegalArgumentException("arrays not equal length"); 
		}
		double sx = 0.0;
		double sy = 0.0;
		double squareX = 0.0;
		double squareY = 0.0;
		double squareXY = 0.0;
		
		n = x.length;
		for (int i = 0; i < n; i++) {
			double a = x[i];
			double b = y[i];
			sx += a;
			sy += b;
			squareX += a * a;
			squareY += b * b;
			squareXY += a * b;
		}
		double cov = squareXY / n - sx * sy / n / n;
		stderrX = Math.sqrt(squareX / n - sx * sx / n / n);
		stderrY = Math.sqrt(squareY / n - sy * sy / n / n);
		rho = cov / stderrX / stderrY;
		return rho;
	}	
	
	//carries out non-parametric correlation
	public double SpearmansCorrelation() {
		if (xArray == null) { throw new NullPointerException("x array null"); }
		if (yArray == null) { throw new NullPointerException("y array null"); }
		if (xArray.length != yArray.length) { 
			throw new IllegalArgumentException("arrays not equal length"); 
		}
		rho = new SpearmansCorrelation().correlation(xArray, yArray);
		return rho;
	}
	
	//carries out non-parametric correlation with new double arrays
	public double SpearmansCorrelation(double[] x, double[] y) {
		if (x == null) { throw new NullPointerException("x array null"); }
		if (y == null) { throw new NullPointerException("y array null"); }
		if (x.length != y.length) { 
			throw new IllegalArgumentException("arrays not equal length"); 
		}
		rho = new SpearmansCorrelation().correlation(x,y);
		return rho;
	}
	
	//returns standard error for x
	public double getStdErrX() {
		return stderrX;
	}
	
	//returns standard error for y
	public double getStdErrY() {
		return stderrY;
	}
	
	//returns p-value
	public double getPValue() {
		double t = rho * Math.sqrt((n-2)/(1-(rho*rho)));
		TDistribution tdis = new TDistribution(n-2);
		pValue = 2.0 * (1.0 - tdis.cumulativeProbability(t));
		return pValue;
	}
	
	//sets data
	public void setData(double[] x, double[] y) {
		if (x == null) { throw new NullPointerException("x array null"); }
		if (y == null) { throw new NullPointerException("y array null"); }
		if (x.length != y.length) { 
			throw new IllegalArgumentException("arrays not equal length"); 
		}
		xArray = x;
		yArray = y;
	}
	
	//test
	public static void main(String[] args) {
		Data data = new Data("test-data.txt");
		double[] a = data.ODBA();
		//double[] b = data.VeDBA();
		double[] ch = data.getChannel(3);
		
		Correlation c = new Correlation(a,ch);
		double corr = c.PearsonsCorrelation();
		//double corr =corr c.SpearmansCorrelation();
		double stderrX = c.getStdErrX();
		double stderrY = c.getStdErrY();
		System.out.println("correlation: " + corr + ", stderrX: " + stderrX + " stderrY: " + stderrY);
		System.out.println("pValue: " + c.getPValue());
		System.out.println();
		
	}

}

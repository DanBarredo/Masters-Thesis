package JOGL;

/**
 * 
 * @author dvbar
 * v1.2
 */

public class LinearRegression {
	private double[] xArray, yArray;
	private double intercept;
	private double slope;
	private double r2;
	private double sVar0, sVar1;
	
	//empty constructor
	public LinearRegression() {
	}
	
	//constructor with double arrays
	public LinearRegression(double[] x, double[] y) {
		this.xArray = x;
		this.yArray = y;
	}
	
	//carries out regression test 
	public void regression() {
		if (xArray == null) { throw new NullPointerException("x array null"); }
		if (yArray == null) { throw new NullPointerException("y array null"); }
		if (xArray.length != yArray.length) { throw new IllegalArgumentException("arrays not equal length"); }
		int n = xArray.length;
		
		double sumX = 0.0;
		double sumY = 0.0;
		for (int i = 0; i < n; i++) {
			sumX += xArray[i];
			sumY += yArray[i];
		}
		double xmean = sumX/n;
		double ymean = sumY/n;
		
		double xxmean = 0.0;
		double yymean = 0.0;
		double xymean = 0.0;
		for (int i = 0; i < n; i++) {
			xxmean += (xArray[i] - xmean) * (xArray[i] - xmean);
			yymean += (yArray[i] - ymean) * (yArray[i] - ymean);
			xymean += (xArray[i] - xmean) * (yArray[i] - ymean); 
		}
		slope = xymean / xxmean;
		intercept = ymean - slope * xmean;
		
		double resSumSquares = 0.0;
		double regSumSquares = 0.0;
		for (int i = 0; i< n; i++) {
			double fit = slope*xArray[i] + intercept;
			resSumSquares += (fit - yArray[i]) + (fit - yArray[i]);
			regSumSquares += (fit - ymean) * (fit - ymean);
		}
		
		int DoF = n-2;
		r2 = regSumSquares / yymean;
		double sVar = resSumSquares / DoF;
		sVar1 = sVar / xxmean;
		sVar0 = sVar/n + xmean*xmean*sVar1;
		
	}
	
	//returns intercept
	public double getIntercept() {
		return intercept;
	}
	
	//returns slope/gradient
	public double getSlope() {
		return slope;
	}
	
	//returns r squared value
	public double getR2() {
		return r2;
	}
	
	//returns standard error for intercept
	public double getStdErrIntercept() {
		return Math.sqrt(sVar0);
	}
	
	//return standard error for slope
	public double getStdErrSlope() {
		return Math.sqrt(sVar1);
	}
	
	//sets data for object
	public void setData(double[] x, double[] y) {
		if (x == null) { throw new NullPointerException("x array null"); }
		if (y == null) { throw new NullPointerException("y array null"); }
		xArray = x;
		yArray = y;
	}
	
	public static void main(String[] args) {
		Data data = new Data("test-data.txt");
		double[] x = data.getChannel(1);
		double[] y = data.getChannel(2);
		
		LinearRegression lr = new LinearRegression();
		lr.setData(x, y);
		//lr.setData(x, x);
		lr.regression();
		System.out.println("r2: " + lr.getR2());
		System.out.println("intercept: " + lr.getIntercept());
		System.out.println("slope: " + lr.getSlope());
		System.out.println("StdErrIntercept: " + lr.getStdErrIntercept());
		System.out.println("StdErrSlope: " + lr.getStdErrSlope());
	}
}

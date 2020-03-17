package JOGL;

/**
 * 
 * @author dvbar
 * v1.2
 */

//class for basic stats
public class Statistics {
	
	//returns array sum
	public static double sum(double[] array) {
		double sum = 0.0;
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
		}
		return sum;
	}
	
	//returns array mean
	public static double mean(double[] array) {
		if (array != null) {
			double sum = 0.0;
			for ( int i = 0; i < array.length; i++) {
				sum += array[i];
			}
			double mean = sum/array.length;
			return mean;
		}else {
			System.out.println("  array empty");
			return 0.0;
		}
	}
	
	//returns variance
	public static double variance(double[] array) { 
		if (array != null) {
			double mean = Statistics.mean(array);
			double temp = 0;
			for (int i = 0; i < array.length; i++) {
				temp += (array[i] - mean)*(array[i] - mean);
			}
			return temp/array.length;
		}else {
			System.out.println("  array empty");
			return 0.0;
		}
	}
	
	//returns standard error
	public static double STDev(double[] array) {
		double stdev = Statistics.variance(array);
		return Math.sqrt(stdev);
	}
	
	//running mean for specified array and period
	public static double[] runningMean(double[] array, int period) {
		double[] averages = new double[array.length];
		int n = period;
		double[] a = new double[n];
		double sum = 0.0;
		for (int i = 0; i < array.length; i++) {
			sum -= a[i%n];
			a[i%n] = array[i];
			sum += a[i%n];
			if (i >= n) averages[i] = sum/n;
		}
		return  averages;
	}
	
	//test
	public static void main(String[] args) {
		Data data = new Data("test-data.txt");
		double[] channel = data.getChannel(3);

		System.out.println("mean		: 	" + Statistics.mean(channel));
		System.out.println("variance	: 	" + Statistics.variance(channel));
		System.out.println("StDev		: 	" + Statistics.STDev(channel));	
		
		double[] rm = Statistics.runningMean(channel, 2);
		for (int i = 0; i < rm.length; i++) {
			System.out.println(i + ": " + rm[i]);
		}
	}
}

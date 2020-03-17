package JOGL;

import java.util.LinkedList;
import java.util.Queue;

public class RunningMean {
	private Queue<Double> window = new LinkedList<Double>();
	private int period;
	private double sum;
		
	public RunningMean(int period) {
		assert period > 0 : "period must be positive";
		this.period = period;
	}
		
	public void addNum(double num) {
		sum += num;
		window.add(num);
		if (window.size() > period) {
			sum -= window.remove();
		}
	}
		
	public double getMean() {
		if (window.isEmpty()) return 0;
		return sum / window.size();
	}
}
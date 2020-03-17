package JOGL;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.math3.stat.inference.*;

/**
 * 
 * @author dvbar
 * v1.2
 */

public class OneWayAnova {
	
	private Collection<double[]> classes = new ArrayList<double[]>();
	
	//empty constructor
	public OneWayAnova() {
	}
	
	//constructor with collection object
	public OneWayAnova(Collection<double[]> classes) {
		this.classes = classes;
	}
	
	//adds array to collection object
	public boolean addClass(double[] array) {
		if (array == null) {
			throw new NullPointerException("added array empty");
		}
		else {
			int size = classes.size();
			classes.add(array);
			int size2 = classes.size();
			if (size2 <= size) { return false; }
			return true;
		}
	}
	
	//returns f-value
	public double FValue() {
		double fValue = TestUtils.oneWayAnovaFValue(classes);
		return fValue;
	}
	
	//returns p-value
	public double PValue() {
		double pValue = TestUtils.oneWayAnovaPValue(classes);
		return pValue;
	}
	
	//returns false if null hypothesis rejected
	public boolean anova(double alpha) {
		boolean test = TestUtils.oneWayAnovaTest(classes, alpha);
		return test;
	}
	
	//checks if classes collection is empty
	public boolean isEmpty() {
		return classes.isEmpty();
	}
	
	//returns size of classes
	public int size() {
		return classes.size();
	}
	
	//test
	public static void main(String[] args) {
		OneWayAnova owa = new OneWayAnova();
		Data data = new Data("test-data.txt");
		owa.addClass(data.ODBA());
		owa.addClass(data.VeDBA());
		//owa.addClass(data.getChannel(3));
		System.out.println("F value: 	" + owa.FValue());
		System.out.println("P value:	" + owa.PValue());
		System.out.println("test:		" + owa.anova(0.01));	
	}
}

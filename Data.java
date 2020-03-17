package JOGL;
import java.io.*;
import java.util.*;

/**
 * 
 * @author dvbar
 * v1.7.2
 */

//data object containing 14 channels of data
public class Data {
	private String[] arr0; private double[] arr1;
    private double[] arr2; private double[] arr3;
    private double[] arr4; private double[] arr5;
    private double[] arr6; private double[] arr7;
    private double[] arr8; private double[] arr9;
    private double[] arr10; private double[] arr11;
    private double[] arr12; private double[] arr13;
    private int numberofChannels = 14;
    
    //creates object for data file
    public Data(String file) {
    	ArrayList<String> dataset = new ArrayList<String>();
    	Scanner scanner = null;
    	try {
    		try {
				scanner = new Scanner(new File(file));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
    		while (scanner.hasNext()) {
    			String[] columns = scanner.nextLine().split("/t");
    			String data = columns[columns.length-1]; 
    			dataset.add(data);
    		}
        
    		int size = dataset.size();
    		arr0 = new String[size]; arr1 = new double[size]; 
    		arr2 = new double[size]; arr3 = new double[size];
    		arr4 = new double[size]; arr5 = new double[size];
    		arr6 = new double[size]; arr7 = new double[size];
    		arr8 = new double[size]; arr9 = new double[size];
    		arr10 = new double[size]; arr11 = new double[size];
    		arr12 = new double[size]; arr13 = new double[size];
        
    		for (int i = 0; i < size; i++) {
	            String[] temp = dataset.get(i).split("\t");
	            arr0[i] = temp[0];
	            arr1[i] = Double.parseDouble(temp[1]);
	            arr2[i] = Double.parseDouble(temp[2]);
	            arr3[i] = Double.parseDouble(temp[3]);
	            arr4[i] = Double.parseDouble(temp[4]);
	            arr5[i] = Double.parseDouble(temp[5]);
	            arr6[i] = Double.parseDouble(temp[6]);
	            arr7[i] = Double.parseDouble(temp[7]);
	            arr8[i] = Double.parseDouble(temp[8]);
	            arr9[i] = Double.parseDouble(temp[9]);
	            arr10[i] = Double.parseDouble(temp[10]);
	            arr11[i] = Double.parseDouble(temp[11]);
	            arr12[i] = Double.parseDouble(temp[12]);
	            arr13[i] = Double.parseDouble(temp[13]);
    		}
    	} finally {
    		if (scanner != null) {
        		scanner.close();
        	}
    	}
    }
    
    
    //returns channel size
	public int channelSize() {
    	return arr0.length;
    }
    
	//returns time channel
    public String[] timeChannel() {
        return arr0;
    }
    
    //returns specified channel
    public double[] getChannel(int channel) {
    	if (channel == 0) 
    		System.out.println("Cannot return this channel with this method. Please use timeChannel().");
    	if (channel == 1) return arr1; if (channel == 2) return arr2;
    	if (channel == 3) return arr3; if (channel == 4) return arr4;
    	if (channel == 5) return arr5; if (channel == 6) return arr6;
    	if (channel == 7) return arr7; if (channel == 8) return arr8;
    	if (channel == 9) return arr9; if (channel == 10) return arr10;
    	if (channel == 11) return arr11; if (channel == 12) return arr12;
    	if (channel == 13) return arr13;
    	else return null;
    }
    
    //returns value (for time channel)
    public String getValue(String[] array, int i) {
    	return array[i];
    }
    
    //returns value from channel
    public double getValue(double[] array, int i) {
    	return array[i];
    }
    
    //returns number of channels
    public int numberOfChannels() throws IOException {
    	return numberofChannels;
    }
    
    //returns array as string
    public static String toString(double[] array) {
    	return Arrays.toString(array);
    }
    
    //returns min value of array
    public static double minValue(double[] array) {
    	double value = Integer.MAX_VALUE;
    	for (int i = 0; i < array.length; i++) {
    		if (array[i] < value) value = array[i];
    	}
    	return value;
    }
    
    //returns max value of array
    public static double maxValue(double[] array) {
    	double value = Integer.MIN_VALUE;
    	for (int i = 0; i < array.length; i++) {
    		if (array[i] > value) value = array[i];
    	}
    	return value;
    }
    
    //returns midpoint between two points
    public static double midPoint(double point1, double point2) {
    	return (point1 + point2)/2;
    }
    
    //scales channel array to fit within openGL coordinates
    public static double[] scaleData(double[] array) {
		double min = Data.minValue(array);
		double max = Data.maxValue(array);
		double[] scaledArr = new double[array.length];
		
		for (int i = 0; i < array.length; i++) {
			double x = (array[i]- min)/(max-min);
			scaledArr[i] = x;
		}
    	
		return scaledArr;
    }
    
    //returns ODBA data at 1s running mean
    public double[] ODBA() {
    	ODBA odba = new ODBA(arr1, arr2, arr3);
		return odba.getODBA(9);
	}
    
    //returns VeDBA data at 1s running mean
    public double[] VeDBA() {
    	VeDBA vedba = new VeDBA(arr1, arr2, arr3);
		return vedba.getVeDBA(9);
    }
    
    //test
    public static void main(String[] args) throws IOException {
        Data data = new Data("Lena1-corm2.txt");
        System.out.println(data.getValue(data.getChannel(5), 76));
        System.out.println("min value: " + minValue(data.getChannel(5)));
        System.out.println("max value: " + maxValue(data.getChannel(5)));
        double[] array1 = data.getChannel(1);
        double[] array2 = data.getChannel(2);
        double[] array3 = data.getChannel(3);
        System.out.println("array1 -> min: " + minValue(array1) + ", " +  "max: " + maxValue(array1));
        System.out.println("array2 -> min: " + minValue(array2) + ", " +  "max: " + maxValue(array3));
        System.out.println("array3 -> min: " + minValue(array3) + ", " +  "max: " + maxValue(array3));
        System.out.println("channel size: " + data.channelSize());
 
    }
}
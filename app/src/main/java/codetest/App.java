package codetest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class App {

public static void main(String args[]) throws IOException {
	
	String[] prospects = ReadFile("prospects.txt"); 
	String[][] cleaned_prospects = CleanFile(prospects);
	
	for (int i = 0; i < cleaned_prospects.length; i++) {
		System.out.printf("Prospect %d: %s wants to borrow %s€ for a period of %s years and pay %.2f€ each month%n", 
				i, cleaned_prospects[i][0], cleaned_prospects[i][1], cleaned_prospects[i][3], monthlyPayment(cleaned_prospects[i]));

	}
	
	
}
	
public static String[] ReadFile(String name) throws IOException {
	
	BufferedReader in = new BufferedReader(new FileReader(name, StandardCharsets.UTF_8));
	String str;

	List<String> list = new ArrayList<String>();
	while((str = in.readLine()) != null){
	    list.add(str);
	}
	in.close();
	
	String[] stringArr = list.toArray(new String[0]);
	
	return stringArr;
}

public static String[][] CleanFile(String[] file) {
	
	List<String> temp = new ArrayList<String>();
	for (int i = 1; i < file.length; i++) {
		if(file[i].startsWith("\"")) {
			file[i] = file[i].replaceFirst(",", " ");
			file[i] = file[i].replace("\"", "");
		}
		if(file[i].length() > 10) {
			temp.add(file[i]);
		}
	}
	
	String [][] clean = new String[temp.size()][];
	
	for (int i=0;i<temp.size();i++)
		clean[i]=temp.get(i).split(",");
	
	return clean;
}

public static double monthlyPayment(String[] file) {
	
	double b = (Double.parseDouble(file[2])/100)/12;
	double U = Double.parseDouble(file[1]);
	double p = 12*Double.parseDouble(file[3]);
	
	double E = ( U*(b*power((1 + b), p)) ) / ( power((1 + b), p) - 1 );
	
	return E;
	
}

public static double power(double base, double p) {
	
	// Credits to https://stackoverflow.com/questions/23172704/how-to-get-exponents-without-using-the-math-pow-for-java
	// using exponentiation by sqauaring https://en.wikipedia.org/wiki/Exponentiation_by_squaring
	
    double ans = 1;
    if (p != 0) {
        int absExponent = (int) (p > 0 ? p : (-1) * p);
        for (int i = 1; i <= absExponent; i++) {
            ans *= base;
        }

        if (p < 0) {
            ans = 1.0 / ans;
        }
    } else {
        ans = 1;
    }

    return ans;
}

}
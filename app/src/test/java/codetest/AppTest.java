package codetest;

import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;  


public class AppTest {

	// Tests if the monthlyPayment gives a positive number, as a negative monthly payment would not really be very realistic
	
	@Test  
    public void positive_montlyPayment() throws IOException{  
		String[] prospects = App.ReadFile("prospects.txt"); 
		String[][] cleaned_prospects = App.CleanFile(prospects);
		
		// Can be tested for negative numbers if changing the assertion to negative
		for (int i = 0; i < cleaned_prospects.length; i++) {
			assertEquals("positive", checkPositive(App.monthlyPayment(cleaned_prospects[i])));
		}
    }
	

public String checkPositive(Double number) {
	if(number >= 0 ) {
		return "positive";
	} else {
		return "negative";
	}
}

}
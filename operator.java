//@author: Fartash Nejad

import java.io.*;
import java.util.Hashtable;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class operator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int cheapestOperatorCode = 0;
		FileHandler hand = null;
		
		try {
			hand = new FileHandler("vk.log");
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Logger log = Logger.getLogger("log_file");
		log.addHandler(hand);
		  
		String phoneNo = null;
		System.out.println("Please enter phone number");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			phoneNo = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//removing dashes from the input
		char [] polishedNo = new char[25];
		
		polishedNo = polishInput(phoneNo.toCharArray(), log);
		
		//Cell operator information
		
		Hashtable<String, Double> operatorA = new Hashtable<String, Double>();
		
		operatorA.put("1", 0.9);
		operatorA.put("268", 5.1);
		operatorA.put("46", 0.17);
		operatorA.put("4620", 0.0);
		operatorA.put("468", 0.15);
		operatorA.put("4631", 0.15);
		operatorA.put("4673", 0.9);
		operatorA.put("46732", 1.1);
		
		//
		
		Hashtable<String, Double> operatorB = new Hashtable<String, Double>();
		
		operatorB.put("1", 0.92);
		operatorB.put("44", 0.5);
		operatorB.put("46", 0.2);
		operatorB.put("467", 1.0);
		operatorB.put("48", 1.2);
		
		//
		
		Hashtable<Integer, String> operatorsNames = new Hashtable<Integer, String>();
		
		operatorsNames.put(operatorA.hashCode(), "Operator A");
		operatorsNames.put(operatorB.hashCode(), "Operator B");
		
		
		
		Object operators[] = {operatorA, operatorB};   
			
		cheapestOperatorCode = findCheapOperator(operators, polishedNo, log);
		
		if (cheapestOperatorCode == 0) {
			System.out.println("None of the operators support your call!");
		}
		else {
			System.out.println("The operator" + operatorsNames.get(cheapestOperatorCode)
					+ "provides the cheapest service for this call");
		}
		
	}
	
	/**
	Finds the operator with the lowest price
	@param operators Array of operators
	@param no The phone number
	@return 
	*/
	
	private static int findCheapOperator(Object[] operators, char[] no, Logger log) {
		int i,j;
		int codeLength = 0;
		int cheapestOperatorHashcode = 0;
		double price = 0;
		for (Object operator : operators) {
			for(i=0; i<no.length; ++i) {
				if (((Hashtable<String, Double>)operator).get
						(new String(no).substring(0, i)) != null && i>codeLength) {
					price = ((Hashtable<String, Double>)operator).get
											(new String(no).substring(0, i));
					codeLength = i;
					cheapestOperatorHashcode = ((Hashtable<String, Double>)operator).hashCode();
				}
				log.info("price: " + price);
				log.info("code length: " + codeLength);
			}
		}
		
		return cheapestOperatorHashcode;
	}
	
	
	
	/**
	removes possibles dashes from the input
	@param no the number for removing the possible dashes
	@param log a Logger object for troubleshooting
	@return 
	*/
	private static char[] polishInput (char[] no, Logger log) {
		char[] polishedNo = new char[25];
		
		for (int i=0; i<no.length; ++i) {
			if (no[i] == '-') {
				continue;
			}
			else {
				polishedNo[i] = no[i];
			}
		}
		return polishedNo;
		
	}

}

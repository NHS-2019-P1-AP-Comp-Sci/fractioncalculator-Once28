/**
 * @author Mr. Rasmussen
 */

package fracCalc;
import java.util.Scanner;
import java.util.ArrayList;

public class FracCalc {

    public static void main(String[] args)
    {
        // TODO: Read the input from the user and call produceAnswer with an equation
    	boolean quit = false; //If the user does not type quit, quit is set to false on default. 
    	Scanner userInput = new Scanner(System.in);
    	
    	while (quit != true) {
    		
    		System.out.println("Enter equation here: ");
    		String equation = userInput.nextLine();
    		
    		if (equation.equalsIgnoreCase("quit")) {
    			
    			quit = true;
    			
    		} else {
    			
    		String results = produceAnswer(equation);
    		System.out.println(results + "\n");
    		
    		}
    		
    	}
    	userInput.close();
    	
    }

    // ** IMPORTANT ** DO NOT DELETE THIS FUNCTION.  This function will be used to test your code
    // This function takes a String 'input' and produces the result
    //
    // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.
    //      e.g. input ==> "1/2 + 3/4"
    //
    // The function should return the result of the fraction after it has been calculated
    //      e.g. return ==> "1_1/4"
    public static String produceAnswer(String input)
    {
        // TODO: Implement this function to produce the solution to the input
//----------------------------------Checkpoint1------------------------------------------------------------------------
    	int firstSpace  = input.indexOf(" ");
    	String substringOne = input.substring(firstSpace + 1);//numbers before operation symbol
    	int secondSpace = substringOne.indexOf(" ");
    	String substringTwo = input.substring(firstSpace + 1, firstSpace + 2); //the operation symbol
    	String substringThree = substringOne.substring(secondSpace + 1);//numbers after operation
    	//return substringThree;
    	
//--------------------------------Checkpoint1 test----------------------------------------------------------------------
    	//System.out.println(substringOne + " " + firstSpace + " " + substringTwo + " " + secondSpace + " " + substringThree); // checks what values are what
    	//System.out.println(substringOne + firstSpace + substringTwo + secondSpace + substringThree);
    	
//--------------------------------Checkpoint2----------------------------------------------------------------------------
    	/* The code finds the position of the special characters to figure out the relative position of the whole number, 
    	 * numerator and denominator. The if else statements says that if there is in fact a whole number or a fraction,
    	 * then it will either set the strings to 0 or set them to the number they represent.*/   
    	
    	String wholeNumber; //These are only second operand whole number, numerator and denominator
    	String numerator;
    	String denominator;
 
    	int wholeNumberIndex = substringThree.indexOf("_");
    	int fractionIndex = substringThree.indexOf("/");
    	
    	if (wholeNumberIndex != -1) {
    		
    		wholeNumber = substringThree.substring(0, wholeNumberIndex);
    		
    	} else if (fractionIndex == -1) {
    		
    		wholeNumber = substringThree;
    		
    	} else {
    		
    		wholeNumber = "0";
    		
    	}
    	if (fractionIndex != -1) {
    		
    		numerator = substringThree.substring(wholeNumberIndex + 1, fractionIndex);
    		denominator = substringThree.substring(fractionIndex + 1);
    		 //is not bounded because there are no spaces after the second operand
    		
    	} else {
    		
    		numerator = "0";
    		denominator = "1";
    		
    	}

    	String substringFour = "whole:" + wholeNumber + " numerator:" + numerator + " denominator:" + denominator;
    	return substringFour; //commented out for the purpose of checkpoint3.
    	
//---------------------------------------Checkpoint3----------------------------------------------------------------------
/*This part will be dealing with the first operand. It will also include calling the math part of the project. This part
 * of the code will do the the same thing as the last part just with the first operand. Then it will plug in the numbers
 * into the math part and then return a String. The tests in this checkpoint may be useless depending on what is 
 * commented out.*/
    	/*String operandOne = input.substring(0, firstSpace + 1);
    	String operandOneWholeNumber;
    	String operandOneNumerator;
    	String operandOneDenominator;
 
    	int operandWholeNumberIndex = operandOne.indexOf("_");
    	int operandFractionIndex = operandOne.indexOf("/");
    	
    	if (operandWholeNumberIndex != -1) {
    		
    		operandOneWholeNumber = operandOne.substring(0, operandWholeNumberIndex);
    		
    	} else if (operandFractionIndex == -1) {
    		
    		operandOneWholeNumber = input.substring(0, firstSpace);
    		
    	} else {
    		
    		operandOneWholeNumber = "0";
    		
    	}
    	if (operandFractionIndex != -1) {
    		
    		operandOneNumerator = operandOne.substring(operandWholeNumberIndex + 1, operandFractionIndex);
    		operandOneDenominator = operandOne.substring(operandFractionIndex + 1, operandOne.length()-1); 
    		// bounded by the total length minus the space counted before the operational sign.
    		
    	} else {
    		
    		operandOneNumerator = "0";
    		operandOneDenominator = "1";
    		
    	}

//--------------------------------Checkpoint3 test----------------------------------------------------------------------
    	//String operand = operandOneWholeNumber + operandOneNumerator + substringTwo + operandOneDenominator; //Tests out what the first operand numbers were
//-------------------------------------------------------------------------------------------------------------------------	
    	String operand = mathMethod(operandOneWholeNumber, operandOneNumerator, operandOneDenominator, substringTwo, wholeNumber, numerator, denominator);
    	return operand; //*/
    }

    // TODO: Fill in the space below with any helper methods that you think you will need
    public static String mathMethod(String wholeNumber1, String numerator1, String denominator1, String operation, String wholeNumber2, String numerator2, String denominator2) {
    	String results = null;
    	int parsedWholeNum1 = Integer.parseInt(wholeNumber1);
   	 	int parsedWholeNum2 = Integer.parseInt(wholeNumber2);
    	int parsedNumerator1 = Integer.parseInt(numerator1);
   	 	int parsedNumerator2 = Integer.parseInt(numerator2);
   	 	int parsedDenominator1 = Integer.parseInt(denominator1);
	 	int parsedDenominator2 = Integer.parseInt(denominator2);
	 	
//-------------------Addition---------------------------------------------------------------------------
    	if (operation.contentEquals("+")) {
    		
    		 int sum; //sum with no fractions
    		 int fractionSum = 0; //sum with some sort of fraction in either operand, default no fraction
    		 int denominator = 1; //the common denominator of the factions, default no fraction
//--------------------------------------Checkpoint3 Sum FractionChecker------------------------------------------------------------------------------------------    		 
    	/*	 
    	     if (parsedNumerator1 == 0 && parsedNumerator2 == 0) { //No fractions
    			 
    			 sum = parsedWholeNum1 + parsedWholeNum2;
    			 
    		 } else if ((parsedNumerator1 != 0 && parsedNumerator2 == 0) || (parsedNumerator1 == 0 && parsedNumerator2 != 0)) { //if one is a fraction
    			 
    			 sum = parsedWholeNum1 + parsedWholeNum2;
    			 fractionSum = parsedNumerator1 + parsedNumerator2;
    			 denominator = parsedDenominator1 + parsedDenominator2;
 
    		 } else { //both are fractions
    			 
    			 	sum = parsedWholeNum1 + parsedWholeNum2;

    			 if (parsedDenominator1 == parsedDenominator2) { //common denominators
    			 
    			 	fractionSum = parsedNumerator1 + parsedNumerator2;
    			 	denominator = parsedDenominator1;
    			 
    		 	 } else {
    			 
    			 	fractionSum = (parsedNumerator1 * parsedDenominator2) + (parsedNumerator2 * parsedDenominator1);
    			 	denominator = parsedDenominator1 * parsedDenominator2;
    			 	
    		 	 }
    		 }
    		 
    		 if (fractionSum /  denominator >= 1) { //tests to see whether if the fraction from the sum is a improper fraction
    			 
    			 int improper = fractionSum / denominator;
    			 sum += improper;
    			 fractionSum -= improper;
    			//System.out.println(improper);
    		 } 
    		 
    	*/
//-------------------------------------------------------------------------------------------------------------------------------------------------------------
    		 
    		 if ((parsedWholeNum1 < 0 || parsedNumerator1 < 0) && (parsedWholeNum2 < 0 || parsedNumerator2 < 0)) { //when both of the numbers are negative
    			 
    			 if (parsedDenominator1 == parsedDenominator2) { //common denominators
        			 
     		 		denominator = parsedDenominator1;
     		 		int positiveFirstOp = Math.abs(parsedWholeNum1) * denominator + Math.abs(parsedNumerator1); // set first operand to positive when adding fractions
     		 		int positiveSecondOp = Math.abs(parsedWholeNum2) * denominator + Math.abs(parsedNumerator2); // set second operand to positive when adding fractions
     		 		fractionSum = (-positiveFirstOp + -positiveSecondOp); // the sum of the numerator
   			 	
   			 	
     		 	} else { //no common denominators
   			 
     		 		denominator = parsedDenominator1 * parsedDenominator2;
   			 		parsedNumerator1 *= parsedDenominator2;
   			 		parsedNumerator2 *= parsedDenominator1;
   			 	int positiveFirstOp = Math.abs(parsedWholeNum1) * denominator + Math.abs(parsedNumerator1); // set first operand to positive when adding fractions
 		 		int positiveSecondOp = Math.abs(parsedWholeNum2) * denominator + Math.abs(parsedNumerator2); // set second operand to positive when adding fractions
   			 		fractionSum = (-positiveFirstOp + -positiveSecondOp);
   			 	
     		 	}
    		 	 
    		 } else if (parsedWholeNum1 < 0 || parsedNumerator1 < 0){ //when first operand is negative
    			 
    		 	if (parsedDenominator1 == parsedDenominator2) { //common denominators
    			 
    		 		denominator = parsedDenominator1;
    		 		int positiveFirstOp = Math.abs(parsedWholeNum1) * denominator + Math.abs(parsedNumerator1); // set first operand to positive when adding fractions
    		 		fractionSum = (-positiveFirstOp + (parsedWholeNum2 * denominator + parsedNumerator2)); // the sum of the numerator
  			 	
  			 	
    		 	} else { //no common denominators
  			 
    		 		denominator = parsedDenominator1 * parsedDenominator2;
  			 		parsedNumerator1 *= parsedDenominator2;
  			 		parsedNumerator2 *= parsedDenominator1;
  			 		int positiveFirstOp = Math.abs(parsedWholeNum1) * denominator + Math.abs(parsedNumerator1); // set first operand to positive when adding fractions
  			 		fractionSum = (-positiveFirstOp + (parsedWholeNum2 * denominator + parsedNumerator2));
  			 	
    		 	}
    		 	
    		 } else if (parsedWholeNum2 < 0 || parsedNumerator2 < 0){ //when second operand is negative
    			 
    		 	if (parsedDenominator1 == parsedDenominator2) { //common denominators
    			 
    		 		denominator = parsedDenominator1;
    		 		int positiveSecondOp = Math.abs(parsedWholeNum2) * denominator + Math.abs(parsedNumerator2); // set second operand to positive when adding fractions
    		 		fractionSum = ((parsedWholeNum1 * denominator + parsedNumerator1) + -positiveSecondOp); // the sum of the numerator
  			 	
  			 	
    		 	} else { //no common denominators
  			 
    		 		denominator = parsedDenominator1 * parsedDenominator2;
  			 		parsedNumerator1 *= parsedDenominator2;
  			 		parsedNumerator2 *= parsedDenominator1;
  			 		int positiveSecondOp = Math.abs(parsedWholeNum2) * denominator + Math.abs(parsedNumerator2); // set second operand to positive when adding fractions
  			 		fractionSum = ((parsedWholeNum1 * denominator + parsedNumerator1) + -positiveSecondOp);
  			 	
    		 	}
    		 	
    		 } else { //when both numbers are positive
    			 
    			 if (parsedDenominator1 == parsedDenominator2) { //common denominators
    			 
    			 	denominator = parsedDenominator1;
    			 	fractionSum = ((parsedWholeNum1 * denominator + parsedNumerator1) + (parsedWholeNum2 * denominator + parsedNumerator2)); // the sum of the numerator
 			 	
 			 	
 		 	 	} else { //no common denominators
 		 		 
 		 		 	denominator = parsedDenominator1 * parsedDenominator2;
 			 		parsedNumerator1 *= parsedDenominator2;
 			 		parsedNumerator2 *= parsedDenominator1;
 			 		fractionSum = ((parsedWholeNum1 * denominator + parsedNumerator1) + (parsedWholeNum2 * denominator + parsedNumerator2));
 			 		//System.out.println(denominator); // for checking what the denominator is by this point
 			 	
 		 	 	}
    		 }
    		 
    		 if (fractionSum < 0 && denominator < 0) {  //negatives cancel, if negative is on the denominator, it will be moved onto the numerator
      			
      			fractionSum = Math.abs(fractionSum);
      			denominator = Math.abs(denominator);
      			
      		 } else if (denominator < 0 ) {
      			 
      			 fractionSum = -fractionSum;
      			 denominator = Math.abs(denominator);
      			 
      		 }
    		 
    		 if (Math.abs(fractionSum) /  denominator >= 1) { //tests to see whether if the fraction from the sum is a improper fraction
    			 
    			 sum = fractionSum / denominator;
    			 fractionSum -= sum * denominator;
    			 fractionSum = Math.abs(fractionSum);
    			 
    			 if (fractionSum == 0) {
     				 
      				results = Integer.toString(sum);
      				
    			 } else if (denominator == 1) { //when it is a whole number, there is no fractions
        			 
     				results = Integer.toString(sum);
     				 
     			 } else {
      				 
      			 results = Integer.toString(sum) + "_" + Integer.toString(fractionSum) + "/" + Integer.toString(denominator);
      			
      			 }
    			 
    		 } else if (fractionSum == 0) { //sum is 0
     			 
     			 results = Integer.toString(fractionSum);
     			      			 
     		 } else if ((fractionSum == 1 || fractionSum == -1 ) && denominator == 1) { //sum is 1
     			 
     			 results = Integer.toString(fractionSum);
     	
     		 } else { //sum is not an improper fraction
     			 
     			 results = Integer.toString(fractionSum) + "/" + Integer.toString(denominator);
     			 
     		 }
    		 
        	 
        	 
         }
//-------------------------------------Subtraction---------------------------------------------------------
         if  (operation.contentEquals("-")) {
        	 
        	 int difference; //difference with no fractions
    		 int fractionSum = 0; //sum with some sort of fraction in either operand, default no fraction
    		 int denominator = 1; //the common denominator of the factions, default no fraction
    		 
    		 if ((parsedWholeNum1 < 0 || parsedNumerator1 < 0) && (parsedWholeNum2 < 0 || parsedNumerator2 < 0)) { //when both of the numbers are negative
    			 
    			 if (parsedDenominator1 == parsedDenominator2) { //common denominators
        			 
     		 		denominator = parsedDenominator1;
     		 		int positiveFirstOp = Math.abs(parsedWholeNum1) * denominator + Math.abs(parsedNumerator1); // set first operand to positive when adding fractions
     		 		int positiveSecondOp = Math.abs(parsedWholeNum2) * denominator + Math.abs(parsedNumerator2); // set second operand to positive when adding fractions
     		 		fractionSum = (-positiveFirstOp - -positiveSecondOp); // the sum of the numerator
   			 	
   			 	
     		 	} else { //no common denominators
   			 
     		 		denominator = parsedDenominator1 * parsedDenominator2;
   			 		parsedNumerator1 *= parsedDenominator2;
   			 		parsedNumerator2 *= parsedDenominator1;
   			 	int positiveFirstOp = Math.abs(parsedWholeNum1) * denominator + Math.abs(parsedNumerator1); // set first operand to positive when adding fractions
 		 		int positiveSecondOp = Math.abs(parsedWholeNum2) * denominator + Math.abs(parsedNumerator2); // set second operand to positive when adding fractions
   			 		fractionSum = (-positiveFirstOp - -positiveSecondOp);
   			 	
     		 	}
    		 	 
    		 } else if (parsedWholeNum1 < 0 || parsedNumerator1 < 0){ //when first operand is negative
    			 
    		 	if (parsedDenominator1 == parsedDenominator2) { //common denominators
    			 
    		 		denominator = parsedDenominator1;
    		 		int positiveFirstOp = Math.abs(parsedWholeNum1) * denominator + Math.abs(parsedNumerator1); // set first operand to positive when adding fractions
    		 		fractionSum = (-positiveFirstOp - (parsedWholeNum2 * denominator + parsedNumerator2)); // the sum of the numerator
  			 	
  			 	
    		 	} else { //no common denominators
  			 
    		 		denominator = parsedDenominator1 * parsedDenominator2;
  			 		parsedNumerator1 *= parsedDenominator2;
  			 		parsedNumerator2 *= parsedDenominator1;
  			 		int positiveFirstOp = Math.abs(parsedWholeNum1) * denominator + Math.abs(parsedNumerator1); // set first operand to positive when adding fractions
  			 		fractionSum = (-positiveFirstOp - (parsedWholeNum2 * denominator + parsedNumerator2));
  			 	
    		 	}
    		 	
    		 } else if (parsedWholeNum2 < 0 || parsedNumerator2 < 0){ //when second operand is negative
    			 
    		 	if (parsedDenominator1 == parsedDenominator2) { //common denominators
    			 
    		 		denominator = parsedDenominator1;
    		 		int positiveSecondOp = Math.abs(parsedWholeNum2) * denominator + Math.abs(parsedNumerator2); // set second operand to positive when adding fractions
    		 		fractionSum = ((parsedWholeNum1 * denominator + parsedNumerator1) - -positiveSecondOp); // the sum of the numerator
  			 	
  			 	
    		 	} else { //no common denominators
  			 
    		 		denominator = parsedDenominator1 * parsedDenominator2;
  			 		parsedNumerator1 *= parsedDenominator2;
  			 		parsedNumerator2 *= parsedDenominator1;
  			 		int positiveSecondOp = Math.abs(parsedWholeNum2) * denominator + Math.abs(parsedNumerator2); // set second operand to positive when adding fractions
  			 		fractionSum = ((parsedWholeNum1 * denominator + parsedNumerator1) - -positiveSecondOp);
  			 	
    		 	}
    		 	
    		 } else { //when both numbers are positive
    			 
    		 	if (parsedDenominator1 == parsedDenominator2) { //common denominators
    			 
    		 		denominator = parsedDenominator1;
    		 		fractionSum = ((parsedWholeNum1 * denominator + parsedNumerator1) - (parsedWholeNum2 * denominator + parsedNumerator2)); // the sum of the numerator
  			 	
  			 	
    		 	} else { //no common denominators
  			 
    		 		denominator = parsedDenominator1 * parsedDenominator2;
  			 		parsedNumerator1 *= parsedDenominator2;
  			 		parsedNumerator2 *= parsedDenominator1;
  			 		fractionSum = ((parsedWholeNum1 * denominator + parsedNumerator1) - (parsedWholeNum2 * denominator + parsedNumerator2));
  			 	
    		 	}
    		 }
    		 
    		 if (fractionSum < 0 && denominator < 0) {  //negatives cancel, if negative is on the denominator, it will be moved onto the numerator
     			
     			fractionSum = Math.abs(fractionSum);
     			denominator = Math.abs(denominator);
     			
     		 } else if (denominator < 0 ) {
      			 
      			 fractionSum = -fractionSum;
      			 denominator = Math.abs(denominator);
      			 
      		 }
     		     		 
    		//System.out.println(denominator); 
        	//System.out.println(fractionSum); 
        	//System.out.println(parsedWholeNum1 + " " + parsedWholeNum2); //for checking what the denominator and fraction sum is.
        	  		
    		 if (Math.abs(fractionSum) /  denominator >= 1) { //tests to see whether if the fraction from the difference is a improper fraction
     			 
     			 difference = fractionSum / denominator;
     			 fractionSum -= difference * denominator;
     			 fractionSum = Math.abs(fractionSum); // makes sure the negative is on the numerator
     			 
     			 if (fractionSum == 0) {
     				 
     				results = Integer.toString(difference);
     				
     			 } else if (denominator == 1) { //when it is a whole number, there is no fractions
       			 
    				results = Integer.toString(difference);
    				 
    			 } else { 
     				 
     			 results = Integer.toString(difference) + "_" + Integer.toString(fractionSum) + "/" + Integer.toString(denominator);
     			
     			 }
     			 
     		 } else if (fractionSum == 0) { //difference is 0
     			 
     			 results = Integer.toString(fractionSum);
     	
     		 } else if ((fractionSum == 1 || fractionSum == -1) && denominator == 1) { //difference is 1
     			 
     			 results = Integer.toString(fractionSum);
     	
     		 } else { //difference is not an improper fraction
     			 
     			 results = Integer.toString(fractionSum) + "/" + Integer.toString(denominator);
     			 
     		 }
        	 
         }
//------------------------------------Multiplication-------------------------------------------------------------------------------------------------------------------
         if (operation.contentEquals("*")) {
        	
        	int product; //product with no fractions
          	int numerator = 0; //the numerator after the multiplication
        	int denominator = 1; //the common denominator of the factions, default no fraction
        	
        	denominator = parsedDenominator1 * parsedDenominator2;	
        	
        	if ((parsedWholeNum1 < 0 || parsedNumerator1 < 0) && (parsedWholeNum2 < 0 || parsedNumerator2 < 0)) {
        	
                 	int positiveFirstOp = Math.abs(parsedWholeNum1) * parsedDenominator1 + Math.abs(parsedNumerator1); // set first operand to positive when adding fractions
     		 		int positiveSecondOp = Math.abs(parsedWholeNum2) * parsedDenominator2 + Math.abs(parsedNumerator2); // set second operand to positive when adding fractions
        			numerator = (-positiveFirstOp * -positiveSecondOp); // the sum of the numerator
        			//System.out.println(positiveFirstOp + " " + positiveSecondOp); //checks what the numerators are after changing to positive
 			 	
        	} else if (parsedWholeNum1 < 0 || parsedNumerator1 < 0) { //first operand is negative
        	
        	  		int positiveFirstOp = Math.abs(parsedWholeNum1) * parsedDenominator1 + Math.abs(parsedNumerator1); // set first operand to positive when adding fractions
           			numerator = (-positiveFirstOp * (parsedWholeNum2 * parsedDenominator2 + parsedNumerator2)); // the sum of the numerator
 			 	
        	} else if (parsedWholeNum2 < 0 || parsedNumerator2 < 0) { //second operand is negative
        	
       		 		int positiveSecondOp = Math.abs(parsedWholeNum2) * parsedDenominator2 + Math.abs(parsedNumerator2); // set second operand to positive when adding fractions          			 
        			numerator = ((parsedWholeNum1 * parsedDenominator1 + parsedNumerator1) * -positiveSecondOp); // the sum of the numerator
 			 	    //System.out.println(positiveSecondOp); //checks what the numerator is after changing to positive
 			 	    
        	} else { // not negative
        		
        		if (parsedDenominator1 == parsedDenominator2) { //common denominators
   			 
        			numerator = ((parsedWholeNum1 * denominator + parsedNumerator1) * (parsedWholeNum2 * denominator + parsedNumerator2)); // the sum of the numerator
 			 	
        		} else { //no common denominators
 			 
        			numerator = ((parsedWholeNum1 * parsedDenominator1 + parsedNumerator1) * (parsedWholeNum2 * parsedDenominator2 + parsedNumerator2));
 			 	
        		}
        	    
         	}
         
    		/* System.out.println(denominator); 
        	 System.out.println(numerator); 
        	 System.out.println(parsedWholeNum1 + " " + parsedWholeNum2);*/
        	
    		if ( numerator < 0 && denominator < 0) { //negatives cancel, if negative is on the denominator, it will be moved onto the numerator
    			
    			numerator = Math.abs(numerator);
    			denominator = Math.abs(denominator);
    			
    		} else if (denominator < 0 ) {
    			 
    			 numerator = -numerator;
    			 denominator = Math.abs(denominator);
    			 
    		 } 
    		
        	if (Math.abs(numerator) /  denominator >= 1) { //tests to see whether if the fraction from the difference is a improper fraction
    			 
    			 product = numerator / denominator;
    			 numerator -= product * denominator;
    			 
    			 if (product < 0 && numerator < 0) {
    				 
    				 numerator = Math.abs(numerator);
    				 
    			 }
    			 
    			 if (numerator == 0) {
    				 
    				results = Integer.toString(product);
    				
    			 } else if (denominator == 1) { //when it is a whole number, there is no fractions
    			 
    				results = Integer.toString(product);
    				 
    			 } else if (denominator == -1) {
    				 
    				 results = Integer.toString(-product);
    				 
    			 } else {
    				 
    			 results = Integer.toString(product) + "_" + Integer.toString(numerator) + "/" + Integer.toString(denominator);
    			
    			 }
    			 
        	} else if (numerator == 0) { //product is 0
    			 
    			 results = Integer.toString(numerator);
    	
    		 } else if ((numerator == 1 || numerator == -1) && denominator == 1) { //product is 1
    			 
    			 results = Integer.toString(numerator);
    	
    		 } else { //product is not an improper fraction
    			 
    			 results = Integer.toString(numerator) + "/" + Integer.toString(denominator);
    			 
    		 }
        	
         }
//-----------------------------------------Division--------------------------------------------------------------------------------------------------------------------------   
         if (operation.contentEquals("/")){
        	 
        	int quotient; //quotient with no fractions
        	int numerator = 0; //the numerator after the multiplication
        	int denominator = 1; //the common denominator of the factions, default no fraction
                	
        	if ((parsedWholeNum1 < 0 || parsedNumerator1 < 0) && (parsedWholeNum2 < 0 || parsedNumerator2 < 0)) {
        	
                int positiveFirstOp = Math.abs(parsedWholeNum1) * parsedDenominator1 + Math.abs(parsedNumerator1); // set first operand to positive when adding fractions
     		 	int positiveSecondOp = Math.abs(parsedWholeNum2) * parsedDenominator2 + Math.abs(parsedNumerator2); // set second operand to positive when adding fractions
        		
     		 	if (parsedDenominator1 == parsedDenominator2) { //common denominators so denominators cancel out
     	   			 
         			denominator = -positiveSecondOp;
         			numerator = -positiveFirstOp;  
 			 	
 			 	
         		} else { //no common denominators
 			 
         			denominator = parsedDenominator1 * -positiveSecondOp;
         			numerator = -positiveFirstOp * parsedDenominator2;
 			 	
         		}
     		 	
        		//System.out.println(positiveFirstOp + " " + positiveSecondOp); //checks what the numerators are after changing to positive
 			 	
        	} else if (parsedWholeNum1 < 0 || parsedNumerator1 < 0) { //first operand is negative
        	
        	  	int positiveFirstOp = Math.abs(parsedWholeNum1) * parsedDenominator1 + Math.abs(parsedNumerator1); // set first operand to positive when adding fractions
           			
        	  	if (parsedDenominator1 == parsedDenominator2) { //common denominators so denominators cancel out
        	   			 
             		denominator = parsedWholeNum2 * denominator + parsedNumerator2;
             		numerator = -positiveFirstOp;  
     			 	
     			 	
             	} else { //no common denominators
     			 
             		denominator = parsedDenominator1 * (parsedWholeNum2 * denominator + parsedNumerator2);
             		numerator = -positiveFirstOp * parsedDenominator2;
     			 	
             	}
 			 	
        	} else if (parsedWholeNum2 < 0 || parsedNumerator2 < 0) { //second operand is negative
        	
       		 	int positiveSecondOp = Math.abs(parsedWholeNum2) * parsedDenominator2 + Math.abs(parsedNumerator2); // set second operand to positive when adding fractions          			 
       		 	
       		 	if (parsedDenominator1 == parsedDenominator2) { //common denominators so denominators cancel out
          			 
         			denominator = -positiveSecondOp;
         			numerator = parsedWholeNum1 * denominator + parsedNumerator1;  // 
 			 	
 			 	
         		} else { //no common denominators
 			 
         			denominator = parsedDenominator1 * -positiveSecondOp;
         			numerator = (parsedWholeNum1 * denominator + parsedNumerator1) * parsedDenominator2;
 			 	
         		}
       		 		//System.out.println(positiveSecondOp); //checks what the numerator is after changing to positive
 			 	    
        	} else { // not negative
        		
         		if (parsedDenominator1 == parsedDenominator2) { //common denominators so denominators cancel out
   			 
         			denominator = parsedWholeNum2 * denominator + parsedNumerator2;
         			numerator = parsedWholeNum1 * denominator + parsedNumerator1;  // 
 			 	
 			 	
         		} else { //no common denominators
 			 
         			denominator = parsedDenominator1 * (parsedWholeNum2 * denominator + parsedNumerator2);
         			numerator = (parsedWholeNum1 * denominator + parsedNumerator1) * parsedDenominator2;
 			 	
         		}
        	
         }
         
    		 //System.out.println(numerator); //checks what the numerator is
        	
        	if ( numerator < 0 && denominator < 0) {  //negatives cancel, if negative is on the denominator, it will be moved onto the numerator
    			
    			numerator = Math.abs(numerator);
    			denominator = Math.abs(denominator);
    			
    		} else if (denominator < 0 ) {
     			 
     			 numerator = -numerator;
     			 denominator = Math.abs(denominator);
     			 
     		 }
    		 
        	if (Math.abs(numerator) /  denominator >= 1) { //tests to see whether if the fraction from the difference is a improper fraction
    			 
    			 quotient = numerator / denominator;
    			 numerator -= quotient * denominator;
    			 
    			 if (quotient < 0 && numerator < 0) {
    				 
    				 numerator = Math.abs(numerator);
    				 
    			 }

    			 if (numerator == 0) {
    				 
    				results = Integer.toString(quotient);
    				
    			 } else if (denominator == 1) { //when it is a whole number, there is no fractions
    			 
    				results = Integer.toString(quotient);
    				 
    			 } else if (denominator == -1) {
    				 
    				 results = Integer.toString(-quotient);
    				 
    			 } else {
    			    				 
    			 results = Integer.toString(quotient) + "_" + Integer.toString(numerator) + "/" + Integer.toString(denominator);
    			
    			 }
    			 
        	} else if (numerator == 0) { //product is 0
    			 
    			 results = Integer.toString(numerator);
    	
    		 } else if ((numerator == 1 || numerator == -1) && denominator == 1) { //product is 1
    			 
    			 results = Integer.toString(numerator);
    	
    		 } else { //product is not an improper fraction
    			 
    			 results = Integer.toString(numerator) + "/" + Integer.toString(denominator);
    			 
    		 }
        	
         }
         
		//System.out.println(results + " " + i + " " + wholeNumber1 + " " + Integer.parseInt(wholeNumber1) + " " + wholeNumber2 + " " + Integer.parseInt(wholeNumber2));
         return results;
    }
    
}

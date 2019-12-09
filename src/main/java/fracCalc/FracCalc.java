/**
 * @author Mr. Rasmussen
 */

package fracCalc;

import java.util.Scanner;
import java.util.ArrayList;

public class FracCalc {

	public static void main(String[] args) {
		// TODO: Read the input from the user and call produceAnswer with an equation
		boolean quit = false; // If the user does not type quit, quit is set to false on default.
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

	// ** IMPORTANT ** DO NOT DELETE THIS FUNCTION. This function will be used to
	// test your code
	// This function takes a String 'input' and produces the result
	//
	// input is a fraction string that needs to be evaluated. For your program, this
	// will be the user input.
	// e.g. input ==> "1/2 + 3/4"
	//
	// The function should return the result of the fraction after it has been
	// calculated
	// e.g. return ==> "1_1/4"
	public static String produceAnswer(String input) {
		// Finds the space and uses where it is to get the operands and the operator
		int space = input.indexOf(" ");
		String operand1 = input.substring(0, space);
		String operator = input.substring(space + 1, space + 2);
		String operand2 = input.substring(space + 3);

		// Set arbitrary values first
		String whole1 = operand1;
		String num1 = "0";
		String den1 = "1";

		// Check to see if it is a fraction
		if (operand1.indexOf("/") != -1) {
			// Determine of the whole number exists
			if (operand1.indexOf("_") != -1) {
				whole1 = operand1.substring(0, operand1.indexOf("_"));
			} else {
				whole1 = "0";
			}
			// Gets the numerator and denominator
			num1 = operand1.substring(operand1.indexOf("_") + 1, operand1.indexOf("/"));
			den1 = operand1.substring(operand1.indexOf("/") + 1);
		}

		// Repeat the same for the second operand
		String whole2 = operand2;
		String num2 = "0";
		String den2 = "1";

		if (operand2.indexOf("/") != -1) {
			// Determine of the whole number exists
			if (operand2.indexOf("_") != -1) {
				whole2 = operand2.substring(0, operand2.indexOf("_"));
			} else {
				whole2 = "0";
			}
			// Gets the numerator and denominator
			num2 = operand2.substring(operand2.indexOf("_") + 1, operand2.indexOf("/"));
			den2 = operand2.substring(operand2.indexOf("/") + 1);
		}

		// Parse everything to integers
		int whole_1 = Integer.parseInt(whole1);
		int num_1 = Integer.parseInt(num1);
		int den_1 = Integer.parseInt(den1);

		int whole_2 = Integer.parseInt(whole2);
		int num_2 = Integer.parseInt(num2);
		int den_2 = Integer.parseInt(den2);

		int den = 1;
		String res = "0";

		// For addition
		if (operator.equals("+")) {
			// Make improper fraction
			num_1 += Math.abs(den_1 * whole_1);
			if (whole_1 < 0) {
				num_1 *= -1;
			}
			num_2 += Math.abs(den_2 * whole_2);
			if (whole_2 < 0) {
				num_2 *= -1;
			}
			if (den_1 != den_2) {
				// Cross multiply
				den = den_1 * den_2;
				num_1 *= den_2;
				num_2 *= den_1;
			} else {
				den = den_1;
			}
			int reduceNum = (num_1 + num_2);
			int reduceDen = (den);
			int finalFrac = (reduceNum % reduceDen);

			res = reduce(finalFrac, Math.abs(reduceNum), reduceDen);
			if (reduceNum < 0) {
				res = "-" + res;
			}
		}

		// For subtraction
		if (operator.equals("-")) {

			// Make improper fraction
			num_1 += Math.abs(den_1 * whole_1);
			if (whole_1 < 0) {
				num_1 *= -1;
			}
			num_2 += Math.abs(den_2 * whole_2);
			if (whole_2 < 0) {
				num_2 *= -1;
			}
			if (den_1 != den_2) {
				// Cross multiply
				den = den_1 * den_2;
				num_1 *= den_2;
				num_2 *= den_1;
			} else {
				den = den_1;
			}
			int reduceNum = (num_1 - num_2);
			int reduceDen = (den);
			int finalFrac = (reduceNum % reduceDen);

			res = reduce(finalFrac, Math.abs(reduceNum), reduceDen);
			if (reduceNum < 0) {
				res = "-" + res;
			}
		}

		// For multiplication
		if (operator.equals("*")) {
			// Get the numerator
			num_1 += Math.abs(den_1 * whole_1);
			num_2 += Math.abs(den_2 * whole_2);

			// Negatives
			int negc = 0;
			if (whole_1 < 0 || num_1 < 0) {
				negc++;
			}
			if (whole_2 < 0 || num_2 < 0) {
				negc++;
			}
			if (num_1 == 0 || num_2 == 0) {
				return res;
			}
			int reduceNum = (num_1 * num_2);
			int reduceDen = (den_1 * den_2);
			int finalFrac = (reduceNum % reduceDen);

			res = reduce(finalFrac, reduceNum, reduceDen);
			if (zeroes(num_1, num_2) == false) {
				if (negc % 2 == 1) {
					res = "-" + res;
				}
			}
		}

		// For division
		if (operator.equals("/")) {
			// Get the numerator
			num_1 += Math.abs(den_1 * whole_1);
			num_2 += Math.abs(den_2 * whole_2);

			// Negatives
			int negc = 0;
			if (whole_1 < 0 || num_1 < 0) {
				negc++;
			}
			if (whole_2 < 0 || num_2 < 0) {
				negc++;
			}
			int reduceNum = (num_1 * den_2);
			int reduceDen = (den_1 * num_2);
			int finalFrac = (reduceNum % reduceDen);

			res = reduce(finalFrac, reduceNum, reduceDen);
			if (zeroes(num_1, num_2) == false) {
				if (negc % 2 == 1) {
					res = "-" + res;
				}
			}
		}
		if (res.equals("0/1")) {
			res = "0";
		}
		return res;
	}
	//simplifies the results and outputs the greatest common denominator
	public static int simplify(int reduceNum, int reduceDen) {
		int res = 0;
		if (reduceDen == 0) {
			res = reduceNum;
			return res;
		}

		return simplify(reduceDen, reduceNum % reduceDen);
	}

	// reduces fraction and differentiates the result based on whether output is a mix fraction or not
	public static String reduce(int finalFrac, int reduceNum, int reduceDen) {
		String res = "0";
		if (finalFrac == 0) {
			if (reduceDen != 0) {
				res = "" + Math.abs(reduceNum / (simplify(reduceNum, reduceDen))) + "/"
						+ Math.abs(reduceDen / (simplify(reduceNum, reduceDen)));
			} else {
				res = "" + Math.abs(reduceNum);
			}
		} else {
			res = Math.abs(reduceNum / (simplify(reduceNum, reduceDen))) + "/"
					+ Math.abs(reduceDen / (simplify(reduceNum, reduceDen)));
		}
		if (Math.abs(reduceNum / reduceDen) > 0 && ones(reduceDen) != true) {
			int whole = reduceNum / reduceDen;
			reduceNum -= whole * reduceDen;
			res = Math.abs(whole) + "_" + Math.abs(reduceNum / (simplify(reduceNum, reduceDen))) + "/"
					+ Math.abs(reduceDen / (simplify(reduceNum, reduceDen)));
			if (reduceNum == 0) {
				res = "" + Math.abs(whole);
			}
		}
		if (ones(reduceDen) == true) {
			res = "" + reduceNum;
		}
		return res;
	}

	// if number is zero, then there will be no negatives
	public static boolean zeroes(int num_1, int num_2) {
		boolean isZero = false;
		if (num_1 == 0 || num_2 == 0) {
			isZero = true;
		}
		return isZero;
	}

	// if denominator is one, then denominator cancels out
	public static boolean ones(int den) {
		boolean isOne = false;
		if (den == 1) {
			isOne = true;
		}
		return isOne;
	}
}

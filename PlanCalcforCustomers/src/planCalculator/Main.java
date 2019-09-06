package planCalculator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Main class has main() where execution of code begins
 *
 * @author Namrata Mehare
 * @version "1.8.0_221"
 * @since 09/06/2019
 * @see java.lang.System
 */
public class Main {

	private static Date purchaseDate;
	private static double purchasePrice;

	public static void main(String[] args) {
		readUserInput(); // read inputs purchase date and price from user
		GeneralPlanRule generalPlanRuleObj = new GeneralPlanRule(purchaseDate, purchasePrice);
		generalPlanRuleObj.purchasePlanCalc(); // calling this method to get purchase plan details
	}

	/**
	 * Method is to read user input purchase date and purchase price
	 * 
	 */
	private static void readUserInput() {
		Scanner reader = new Scanner(System.in); // Reading from System.in

		readPurchaseDate(reader);
		// System.out.println("Purchase Date is : " + purchaseDate);

		readPurchasePrice(reader);
		// System.out.println("Purchase Price is : " + purchasePrice);

		// once finished close the reader
		reader.close();
	}

	/**
	 * Method is to read Purchase Date value from user.
	 * 
	 */
	private static void readPurchaseDate(Scanner reader) {
		String date = null;
		do {
			try {
				System.out.println("Enter Purchase Date in MM-dd-yyyy format: ");
				date = reader.next(); // Scans the next token of the input as an date.
			} catch (Exception e) {
				System.out.println("You entered date in wrong format: ");
				e.getMessage();
			}
		} while (!isValidDate(date));
	}

	/**
	 * Method is to read Purchase Price value from user.
	 * 
	 */
	public static void readPurchasePrice(Scanner input) {
		System.out.println("Enter Purchase Price: ");
		try {
			purchasePrice = input.nextDouble();
			if (purchasePrice < 0) {
				System.out.println("You must enter a number greater than or equal to 0.");
				readPurchasePrice(input);
			}
		} catch (Exception e) {
			System.out.println("Please enter valid integer: ");
			e.getMessage();
		}
	}

	/**
	 * Method is validate the date .
	 * 
	 * @param date in string data type
	 * 
	 * @return A boolean data type
	 */
	public static boolean isValidDate(String date) {
		boolean isValid = false;
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date todayDate = new Date();

		String expression = "^([0][1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])-((19|20)\\d{2})";
		/*
		 * ^[0-1][1-2] : The month starts with a 0 and a digit between 1 and 2 [-]?:
		 * Followed by an optional "-". (0[1-9]|[12][0-9]|3[01]) : The day part must be
		 * either between 01-09, or 10-29 or 30-31. [-]?: Day part will be followed by
		 * an optional "-". (19|20)\\d{2}$ : Year begins with either 19, 20 and ends
		 * with two digits.
		 */
		CharSequence inputStr = date;
		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(inputStr);

		try {
			purchaseDate = dateFormat.parse(date);
			if (purchaseDate.after(todayDate) || purchaseDate.equals(todayDate)) {
				System.out.println("Your Purchase date must be less than current date");
				isValid = false;
			} else if (matcher.matches()) {
				isValid = true;
			} else {
				System.out.println("You have entered date in wrong format");
				isValid = false;
			}
		} catch (ParseException e) {
			System.out.println("You entered date in WRONG format");
			e.getMessage();
		}
		// System.out.println("Purchase Date is : " + purchaseDate);
		// System.out.println("today Date is : " + todayDate);

		return isValid;
	}
}

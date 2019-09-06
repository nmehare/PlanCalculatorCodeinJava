package planCalculator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * The GeneralPlanRule class has logic for Plan Calculator
 *
 * @author Namrata Mehare
 * @version "1.8.0_221"
 * @since 09/06/2019
 * @see java.lang.System
 */
public class GeneralPlanRule {

	//declare class variables
	private Date purchaseDate;
	private double purchasePrice;
	private int lowThreshold = 100;
	private int firstTier = 1000;
	private int secondTier = 10000;
	private double depositAmount;

	/**
	 * Retrieve the value of Deposit amount.
	 * 
	 * @return A double data type.
	 */
	public double getDepositAmount() {
		return depositAmount;
	}

	/**
	 * Constructor to initialize the values.
	 * 
	 * @param purchaseDate
	 * @param purchasePrice
	 * 
	 */
	public GeneralPlanRule(Date purchaseDate, double purchasePrice) {
		this.purchaseDate = purchaseDate;
		this.purchasePrice = purchasePrice;
	}

	/**
	 * Method is to handle login of plan calculator.
	 * 
	 */
	public void purchasePlanCalc() {
		double depositRateStage1 = 0.20;
		double depositRateStage2 = 0.25;
		double depositRateStage3 = 0.30;// have not added the logic as condition here is repeated
		int installmentNoStage1 = 5;
		int installmentNoStage2 = 4;
		int intervalStage1 = 15;
		int intervalStage2 = 30;

		if (purchasePrice < lowThreshold || purchasePrice >= secondTier) {
			System.out.println("NO PLANS OFFERED. Please Pay full amount now");
		} else if (purchasePrice == lowThreshold || purchasePrice < firstTier) {
			depositCalc(depositRateStage1);
			installmentCalc(installmentNoStage1, intervalStage1);
		} else if (purchasePrice <= firstTier || purchasePrice < secondTier) {
			depositCalc(depositRateStage2);
			installmentCalc(installmentNoStage2, intervalStage2);
		}
	}

	/**
	 * Method is to handle installment calculation logic.
	 * 
	 * @param installmentNo - number of installments
	 * @param interval      - installment interval
	 * 
	 */
	private void installmentCalc(int installmentNo, int interval) {
		List<Date> installmentDates = new ArrayList<Date>(installmentNo); // initial size
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		Date date = purchaseDate;

		double amountRemaining = purchasePrice - getDepositAmount();
		double installmentAmt = amountRemaining / installmentNo;

		for (int i = 0; i < installmentNo; i++) {
			date = addDays(date, interval);
			installmentDates.add(date);
		}

		System.out.println("----------------------------------------------------------");
		System.out.println("Remaining Amount after paid deposit is " + amountRemaining);
		System.out.println("Number of Installments are " + installmentNo);
		System.out.println("----------------------------------------------------------");
		System.out.println("Installment amount is " + installmentAmt);
		System.out.println("----------------------------------------------------------");
		System.out.println("Below are the dates on which installments must be paid ");

		for (Date installmentDate : installmentDates) {
			String dateFormatted = formatter.format(installmentDate);
			System.out.println(dateFormatted);
		}
	}

	/**
	 * Method is to handle installment dates logic.
	 * 
	 * @param date
	 * @param days
	 * 
	 * @return A new Date in Date data type with days added
	 */
	public static Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days); // minus number would decrement the days
		return cal.getTime();
	}

	/**
	 * Method is to handle deposit amount logic.
	 * 
	 */
	private void depositCalc(double depositRate) {
		depositAmount = purchasePrice * depositRate;
		System.out.println("Dear Customer, you have to pay " + depositAmount + " Deposit Amount");
	}
}

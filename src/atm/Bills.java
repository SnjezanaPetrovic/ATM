package atm;

import java.io.IOException;

public class Bills {

	private int tens;
	private int twentys;
	private int fiftys;
	private int hundreds;
	private double atmBalance;

	public Bills() {
		this.tens = 60;
		this.twentys = 30;
		this.fiftys = 20;
		this.hundreds = 10;
		this.atmBalance = (tens * 10) + (twentys * 20) + (fiftys * 50) + (hundreds * 100);

	}

	public Bills(int tens, int twentys, int fiftys, int hundreds, double atmBalance) {
		super();
		this.tens = tens;
		this.twentys = twentys;
		this.fiftys = fiftys;
		this.hundreds = hundreds;
		this.atmBalance = atmBalance;
	}

	public int getTens() {
		return tens;
	}

	public void setTens(int numberOfTens) throws IOException {
		int start = this.tens;
		if ((numberOfTens + tens) < 0) {
			System.out.println("ATM has only " + start + " of 10$ bills");
		} else if (tens + numberOfTens <= 100) {
			tens += numberOfTens;
			System.out.println("Now ATM has " + tens + " 10$ bills");

			atmBalance = (App.readBalance()) + (10 * numberOfTens);
			System.out.println("ATM balance is now: " + atmBalance);
			ATM.writeNewAtmBalance(atmBalance);

		} else {

			System.out.println(
					"Maximum number of bills must be 100.You can add only " + (100 - start) + " number of 10$ bills");
		}

	}

	public int getTwentys() {
		return twentys;
	}

	public void setTwentys(int numberOfTwentys) throws IOException {
		int start = this.twentys;
		if ((numberOfTwentys + twentys) < 0) {
			System.out.println("ATM has only " + start + " of 20$ bills");
		} else if (twentys + numberOfTwentys <= 100) {
			twentys += numberOfTwentys;
			System.out.println("Now ATM has " + twentys + " 20$ bills");
			atmBalance = (App.readBalance()) + (20 * numberOfTwentys);
			System.out.println("ATM balance is now: " + atmBalance);
			ATM.writeNewAtmBalance(atmBalance);

		} else {

			System.out.println(
					"Maximum number of bills must be 100.You can add only " + (100 - start) + " number of 20$ bills");
		}

	}

	public int getFiftys() {
		return fiftys;
	}

	public void setFiftys(int numberOfFiftys) throws IOException {
		int start = this.fiftys;
		if ((numberOfFiftys + fiftys) < 0) {
			System.out.println("ATM has only " + start + " of 50$ bills");

		} else if (fiftys + numberOfFiftys <= 100) {
			fiftys += numberOfFiftys;
			System.out.println("Now ATM has " + fiftys + " 50$ bills");
			atmBalance = (App.readBalance()) + (50 * numberOfFiftys);
			System.out.println("ATM balance is now: " + atmBalance);
			ATM.writeNewAtmBalance(atmBalance);
		} else {

			System.out.println(
					"Maximum number of bills must be 100.You can add only " + (100 - start) + " number of 50$ bills");
		}

	}

	public int getHundreds() {
		return hundreds;
	}

	public void setHundreds(int numberOfHundreds) throws IOException {
		int start = this.hundreds;
		if ((numberOfHundreds + hundreds) < 0) {
			System.out.println("ATM has only " + start + " of 100$ bills");
		} else if (hundreds + numberOfHundreds <= 100) {
			hundreds += numberOfHundreds;
			System.out.println("Now ATM has " + hundreds + " 100$ bills");

			atmBalance = (App.readBalance()) + (100 * numberOfHundreds);
			System.out.println("ATM balance is now: " + atmBalance);
			ATM.writeNewAtmBalance(atmBalance);

		} else {

			System.out.println("Maximum number of bills must be 100.\nYou can add only " + (100 - start)
					+ " number of 100$ bills");
		}

	}

	public double getAtmBalance() {
		return atmBalance;
	}

	public void setAtmBalance(double atmBalance) {
		this.atmBalance = (getTens() * 10) + (getTwentys() * 20) + (getFiftys() * 50) + (getHundreds() * 100);

	}

	@Override
	public String toString() {
		return "Bills [tens=" + getTens() + ", twentys=" + getTwentys() + ", fiftys=" + getFiftys() + ", hundreds="
				+ getHundreds() + ", atmBalance=" + getAtmBalance() + "]";
	}

}

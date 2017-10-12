package atm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class App {

	static Scanner input = new Scanner(System.in);
	private static ATM atm = new ATM();
	private static Bills bills = new Bills();

	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);

		System.out.println("-------Welcome-------");
		System.out.println("Choose an option:\n--------------------\n1.Log in as user\n"
				+ "2.Log in as admin\n3.Leave application ");

		int choice = input.nextInt();

		boolean valid = true;
		while (valid) {
			try {
				switch (choice) {
				case 1:
					logIn();
					break;
				case 2:
					logIn();
					break;
				case 3:
					System.out.println("You successfully loged out.Good buy.");
					break;
				default:
					System.out.println("Invalid input.Choose 1,2 or 3 please.");

					break;
				}

			} catch (Exception ex) {
				ex.getMessage();
			}

			valid = false;
		}
		input.close();
	}

	public static void logIn() throws IOException {

		System.out.println("Enter your user name: ");
		String userName = input.nextLine();

		System.out.println("Enter your password: ");
		String password = input.nextLine();

		try {
			if (Files.exists(Paths.get(userName + ".txt"))) {
				validateLogIn(userName, password);
			} else {
				System.out.println("User not found.");
			}

		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("gr");
		}

	}

	public static void validateLogIn(String userName, String password) throws IOException {
		Admin admin = new Admin();

		if (userName.equals(admin.getUserName()) && password.equals(admin.getPassword())) {
			adminMenu();
		} else {

			Path path = Paths.get(userName + ".txt");

			try (BufferedReader reader = Files.newBufferedReader(path)) {
				String line;
				while ((line = reader.readLine()) != null) {
					String[] elements = line.split("-");
					if (userName.equals(elements[0]) && password.equals(elements[1])) {

						userMenu(userName, password);
					} else {
						System.out.println("Incorrect user name or password.Try again.");
						logIn();
						break;
					}
				}
			} catch (Exception ex) {
				ex.getMessage();
			}

		}
	}

	public static void adminMenu() throws IOException {

		System.out.println("********************");
		System.out.println("Choose an option:\n--------------------\n1.Add user\n2.Remove user\n3.Change "
				+ "number of bills\n4.Display ATM balance\n5.Log out ");
		int choice = input.nextInt();
		boolean validan = true;
		while (validan) {
			try {
				switch (choice) {
				case 1:
					atm.createUser();
					break;
				case 2:
					atm.removeUser();
					break;
				case 3:
					atm.changingNumberOfBills();
					break;
				case 4:
					atm.displayAtmBalance();
					break;
				case 5:
					System.out.println("You successfully loged out.Good buy.");
					break;
				default:
					System.out.println("Invalid input.Choose 1,2,3,4,5 or 6 please.");
					adminMenu();
					break;
				}

			} catch (Exception ex) {
				ex.getMessage();
			}

			validan = false;

		}
	}

	public static void userMenu(String userName, String password) throws IOException {
		System.out.println("********************");
		System.out.println("Choose an option:\n--------------------\n1.Withdraw money\n2.Display balance\n3.Log out ");
		int choice = input.nextInt();
		boolean validan = true;
		while (validan) {
			switch (choice) {
			case 1:

				System.out.println("How much money do you want to withdraw? ");
				int amount = input.nextInt();

				double balance = 0;
				try (BufferedReader reader = Files.newBufferedReader(Paths.get(userName + ".txt"))) {
					String line;
					while ((line = reader.readLine()) != null) {
						String[] accountData = line.split("-");
						balance = Double.parseDouble(accountData[2]);
					}
				} catch (Exception ex) {
					ex.getMessage();

				}

				if (balance < amount) {
					System.out.println("There is no enough money on your account.");
				} else if (amount > bills.getAtmBalance()) {
					System.out.println("There is no enough money in this ATM.\n"
							+ "Try different ATM or go directly to the bank.");
				} else if (amount % 10 != 0) {
					System.out.println(
							"Amount is not valid. Please choose amount that can be issued with 10, 20, 50 and 100€ bills. ");

				} else {
					try (BufferedWriter write = Files.newBufferedWriter(Paths.get(userName + ".txt"))) {
						write.write(userName + "-");
						write.write(password + "-");
						write.write((balance - amount) + "");
						System.out.println(
								"You successfully withdrow the money.\nYour account balance is: " + (balance - amount));
					} catch (Exception ex) {
						ex.getMessage();

					} finally {
						Path path = Paths.get("Balance.txt");
						double newBalance = 0;
						try (BufferedReader reader = Files.newBufferedReader(path)) {
							String line;
							while ((line = reader.readLine()) != null) {
								String[] balanceData = line.split(" ");
								newBalance = Double.parseDouble(balanceData[0]);
								double newAtmBalance = newBalance - amount;
								ATM.writeNewAtmBalance(newAtmBalance);
							}
						} catch (Exception ex) {
							ex.getMessage();

						}
						System.out.println("Another transaction?\n1.Yes       2.No");
						int yn = input.nextInt();
						if (yn == 1) {
							userMenu(userName, password);
						} else {
							System.out.println("You successfully loged out.Good buy.");
						}

					}

					break;
				}
			case 2:

				try (BufferedReader reader = Files.newBufferedReader(Paths.get(userName + ".txt"))) {
					String line;
					while ((line = reader.readLine()) != null) {
						String[] accountData = line.split("-");
						balance = Double.parseDouble(accountData[2]);
						System.out.println("Your balance is: " + balance);
					}
				} catch (Exception ex) {
					ex.getMessage();
				}
				System.out.println("Another transaction?\n1.Yes       2.No");
				int yn = input.nextInt();
				if (yn == 1) {
					userMenu(userName, password);
				} else {
					System.out.println("You successfully loged out.Good buy.");
				}
				break;

			case 3:
				System.out.println("You successfully loged out.Good buy.");
				break;
			default:
				System.out.println("Invalid input.Choose 1,2 or 3 please.");
				userMenu(userName, password);
				break;
			}
			validan = false;

		}

	}

	public static double readBalance() {

		Path path = Paths.get("Balance.txt");
		double newBalance = 0;
		try (BufferedReader reader = Files.newBufferedReader(path)) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] balanceData = line.split(" ");
				newBalance = Double.parseDouble(balanceData[0]);

			}
		} catch (Exception ex) {
			ex.getMessage();
		}
		return newBalance;

	}

}

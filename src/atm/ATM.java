package atm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ATM {

	private ArrayList<User> users;

	static Scanner input = new Scanner(System.in);

	public ATM() {

	}

	public ATM(ArrayList<User> users, Bills bills) {
		super();
		this.users = new ArrayList<>();

	}

	public void createUser() throws IOException {

		System.out.println("Name: ");
		String userName = input.nextLine();
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(userName + ".txt"))) {
			writer.write(userName + "-");
			System.out.println("Password: ");
			String password = input.nextLine();
			writer.write(password + "-");

			System.out.println("Balance: ");
			double balance = input.nextDouble();
			writer.write(balance + " ");

			users.add(new User(userName, password, balance));

		} catch (Exception ex) {
			ex.getMessage();

		} finally {
			System.out.println("User has been created.");
			App.adminMenu();
		}

	}

	//

	public void removeUser() throws IOException {

		try {
			System.out.println("Type username of the user you want to remove: ");
			String userName = input.nextLine();

			if (Files.exists(Paths.get(userName + ".txt"))) {
				Files.delete(Paths.get(userName + ".txt"));
				System.out.println("User has been removed.");
			} else {
				System.out.println("There is no user under this username");
				System.exit(1);
			}

		} catch (Exception ex) {
			ex.getMessage();
		} finally {
			App.adminMenu();
		}

	}

	public void changingNumberOfBills() throws IOException {
		Bills bills = new Bills();
		System.out.println("********************");
		System.out.println(
				"Choose an option:\n--------------------\n1.Set tens\n2.Set twentys\n3.Set fiftys\n4.Set hundreds\n5.Log out");
		int choice = input.nextInt();
		System.out.println("How many bills do you want to add or remove(use '-' for remove)");
		int numberOfBills = input.nextInt();
		boolean valid = true;
		while (valid) {
			try {
				switch (choice) {
				case 1:
					bills.setTens(numberOfBills);
					break;
				case 2:
					bills.setTwentys(numberOfBills);
					break;
				case 3:
					bills.setFiftys(numberOfBills);
					break;
				case 4:
					bills.setHundreds(numberOfBills);
					break;
				case 5:
					System.out.println("You successfully loged out.Good buy.");
					break;
				default:
					System.out.println("Invalid input.Choose 1,2,3 or 4 please.");
					break;
				}
				App.adminMenu();

			} catch (Exception e) {

				System.err.println(e);

			}
			valid = false;
		}
		App.adminMenu();

	}

	public void displayAtmBalance() throws IOException {

		Path path = Paths.get("Balance.txt");

		try (BufferedReader reader = Files.newBufferedReader(path)) {
			String line;
			while ((line = reader.readLine()) != null) {

				System.out.println("ATM balance is: " + line);
			}

		} catch (Exception ex) {
			ex.getMessage();
		}

		App.adminMenu();
	}

	public static void writeNewAtmBalance(double newAtmBalance) throws IOException {
		Path path = Paths.get("Balance.txt");
		if (!Files.exists(path)) {
			Files.createFile(path);
		}
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			writer.write(newAtmBalance + "");
			writer.close();

		} catch (Exception ex) {
			ex.getMessage();
		}

	}

}

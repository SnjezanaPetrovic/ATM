package atm;

public class User {

	private String userName;
	private String password;
	private double balance;

	public User() {

	}

	public User(String userName, String password, double balance) {
		super();
		this.userName = userName;
		this.password = password;
		this.balance = balance;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double amount) {
		this.balance = amount;

	}

	public double withdraw(int amount) {
		return balance -= amount;
	}

}
